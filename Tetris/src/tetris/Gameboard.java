package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import tetris.Tetrominos.LShape;

public class Gameboard extends JPanel{
	
	ArrayList<Tetromino> Tetrominos = new ArrayList<Tetromino>();
	ArrayList<ArrayList<GridBlock>> grid = new ArrayList<ArrayList<GridBlock>>();
	ArrayList<Integer> markedRowsForDeletion = new ArrayList<Integer>(); // int cant be used and Integer is the same apperently? idk
	
	Game game;
	
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
		setLayout(null);
	}
	
    public void spawnRandomTetromino() {
        String[] types = {"L"};
        String randomType = types[random.nextInt(types.length)];
        Tetromino temp;
        switch(randomType){
        	case "L":
        		temp = new LShape(this);
        		Tetrominos.add(temp);
        		game.setActiveTetromino(temp);
        		for(Block block: temp.getBodyPieces()) {
        			this.add(block);
        		}
        	default:
        		System.out.println("Lul");
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
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (Tetromino t : Tetrominos) {
            for (Block block : t.getBodyPieces()) {
                block.repaint();
            }
        }

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
	
	public void deleteRow(int row) {
		for(Tetromino shape : Tetrominos) {
			for(Block bodyPart : shape.getBodyPieces()) {
				if(bodyPart.getY() == row) {
					bodyPart.removeSelf();
				}
			}
		}
		for(Tetromino shape : Tetrominos) {
			boolean moveIt = true;
			for(Block bodyPart : shape.getBodyPieces()) {
				if(!bodyPart.checkIfAbove(row)) {
					moveIt = false;
				}
			}
			if(moveIt) {
				shape.moveDown();
			}
		}
	}
	
	public ArrayList<ArrayList<GridBlock>> getGrid() {
		return grid;
	}

	public ArrayList<Tetromino> getTetrominos() {
		return Tetrominos;
	}

	public ArrayList<Integer> getMarkedRowsForDeletion() {
		return markedRowsForDeletion;
	}
	
	
	
	
}
