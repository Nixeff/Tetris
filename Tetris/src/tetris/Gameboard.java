package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JPanel;

import tetris.Tetrominos.IShape;
import tetris.Tetrominos.LShape;
import tetris.Tetrominos.OShape;
import tetris.Tetrominos.ReverseLShape;
import tetris.Tetrominos.SShape;
import tetris.Tetrominos.TShape;
import tetris.Tetrominos.ZShape;

public class Gameboard extends JPanel implements Runnable{
	private ArrayList<ArrayList<GridBlock>> grid = new ArrayList<ArrayList<GridBlock>>();
	private ArrayList<Integer> markedRowsForDeletion = new ArrayList<Integer>(); // INT can't be used and Integer is the same apparently? idk
	private ArrayList<String> bag = new ArrayList<>();
	private ArrayList<String> nextBag = new ArrayList<>();
	private ArrayList<Block> placedBlocks = new ArrayList<Block>();
	private Tetromino activeTetromino;
	
	private Game game;
	
	private Random random = new Random();
	
	int height = 22;
	int width = 10;
	
	private Player player;

	public Gameboard(Player player, Game game) {
		this.player = player;
		this.game = game;
		for (int i = 0; i < height; i++ ) {
			grid.add(new ArrayList<GridBlock>());
			for (int o = 0; o < width; o++ ) {
				grid.get(i).add(new GridBlock(o,i,30));
			}
		}
		this.setPreferredSize(new Dimension(300, 700));
		setLayout(null);
		new Thread(this).start();
		refillBag();
	}
	
	/**
	 * The order is predetermined by a bag so only after every tetromino is displayed once can each tetromino be displayed again.
	 * @return
	 */
	public Tetromino spawnRandomTetromino() {
        if (bag.isEmpty()) {
            refillBag();
        }
        
	    String randomType = bag.remove(0);

	    Tetromino temp;
	    
	    switch (randomType) {
	        case "L":
	            temp = new LShape(this);
	            break;
	        case "T":
	            temp = new TShape(this);
	            break;
	        case "RL":
	            temp = new ReverseLShape(this);
	            break;
	        case "I":
	            temp = new IShape(this);
	            break;
	        case "O":
	            temp = new OShape(this);
	            break;
	        case "S":
	            temp = new SShape(this);
	            break;
	        case "Z":
	            temp = new ZShape(this);
	            break;
	        default:
	            System.out.println("lul");
	            return null;
	    }

	    try {
	        activeTetromino = temp;
	        checkSpawn();
	        for (Block block : temp.getBodyPieces()) {
	            this.add(block);
	        }
	        
	    } catch (Exception e) {
	        System.out.println("lul: " + e.getMessage());
	    }
		return temp;
	}
	
	public void holdTetromino() {
		Tetromino tempTetromino = activeTetromino;
		Tetromino heldTetromino = game.getHeldTetromino().getHeldTetromino();
		if(heldTetromino == null) {
			game.getHeldTetromino().setHeldTetromino(activeTetromino);
			for(Block block: activeTetromino.getBodyPieces()) {
				block.removeSelfHold();
			}
			spawnRandomTetromino();
		} else {
			for(Block block: activeTetromino.getBodyPieces()) {
				block.removeSelfHold();
			}
			activeTetromino = heldTetromino;
			game.getHeldTetromino().setHeldTetromino(tempTetromino);
		}
        game.setActiveTetromino(activeTetromino);
        
        for (Block block : activeTetromino.getBodyPieces()) {
        	block.reset();
            this.add(block);
        }
	}
	
    private void refillBag() {
    	String[] allTypes = {"L", "T", "RL", "I", "O", "S", "Z"};
    	if(bag.isEmpty()||nextBag.isEmpty()) {
            Collections.addAll(bag, allTypes);
            Collections.shuffle(bag);
            Collections.addAll(nextBag, allTypes);
            Collections.shuffle(nextBag);
    	} else {
    		bag = nextBag;
    		Collections.addAll(nextBag, allTypes);
            Collections.shuffle(nextBag);
    	}
        
    }
	

	/**
	 * Checks if the area where the activeTetromino is all ready has blocked grid squares. If so it triggers the death method in game
	 */
	private void checkSpawn() {
		boolean shouldDie = false;
		for(Block block: activeTetromino.getBodyPieces()) {
			if(block.isBlocked()) {
				shouldDie = true;
			}
		}
		if(shouldDie) {
			game.death();
		}
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = 30;
        int columns = 9;
        int rows = 21;

        g.setColor(Color.GRAY);
        for (int x = 0; x <= columns * cellSize; x += cellSize) {
            for (int y = 0; y <= rows * cellSize; y += cellSize) {
                g.drawRect(x, y, cellSize, cellSize);
            }
        }
    }
    
	public void isRowBlocked(int row) {
		boolean isBlocked = true;
		for(GridBlock gridSquare : grid.get(row)) {
			if(!gridSquare.isBlocked()) {
				isBlocked = false;
			}
		}
		if(isBlocked) {
			markedRowsForDeletion.add(row);
		}
	}
	
	public void deleteRow(int row) {
		ArrayList<Block> markedForDeletion = new ArrayList<Block>();
		for(Block block : placedBlocks) {
			if(block.getGridY() == row) {
				markedForDeletion.add(block);
			}
		}
		for(Block bodyPart: markedForDeletion) {
			bodyPart.removeSelf();
		}
		for(int i = row; i>0; i--) {
			for(Block block : placedBlocks) {
				if(block.getGridY() == i) {
					if(block.checkIfAbove(row)) {
						block.unBlockGrid();
						block.moveDown();
						block.blockGrid();
					}
				}
			}
		}
	}
	
	public ArrayList<ArrayList<GridBlock>> getGrid() {
		return grid;
	}

	

	public ArrayList<String> getBag() {
		return bag;
	}

	public ArrayList<String> getNextBag() {
		return nextBag;
	}

	public ArrayList<Block> getPlacedBlocks() {
		return placedBlocks;
	}

	public ArrayList<Integer> getMarkedRowsForDeletion() {
		return markedRowsForDeletion;
	}

	public void setMarkedRowsForDeletion(ArrayList<Integer> markedRowsForDeletion) {
		this.markedRowsForDeletion = markedRowsForDeletion;
	}

	public void addPlacedBlocks(Block block) {
		placedBlocks.add(block);
	}
	
	
	@Override
	public void run() {
		while(game.isRunning()) {
			if(activeTetromino != null) {
				while(activeTetromino.isMoving()) {
					activeTetromino.checkBelow();
					if(activeTetromino.isMoving()) {
						activeTetromino.moveDown();
					}
					try {
						Thread.sleep(game.getGameSpeed());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			// This is ze coconut 
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
