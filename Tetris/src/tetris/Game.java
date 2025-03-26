package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements Runnable, KeyListener {
    private static Gameboard gameboard;
    private static Player player;
    private static TetrominoQueue tetrominoQueue;
    private static RightSideFrame rightSideFrame;
    private static HeldTetromino heldTetromino;
    private static LeftSideFrame leftSideFrame;
    private Tetromino activeTetromino;
    private Random random = new Random();
    private boolean running = true;
    private int gameSpeed = 1000;

    public Game() {
       
    	setupUI();
        setPreferredSize(new Dimension(600, 1200));
        
        setFocusable(true);
        addKeyListener(this);
        
        add(gameboard, BorderLayout.CENTER);  // Game in center
        add(rightSideFrame, BorderLayout.EAST);       // Score on right
        add(leftSideFrame, BorderLayout.WEST);

        new Thread(this).start();
    }
    
    public void setupUI() {
    	 player = new Player();
         gameboard = new Gameboard(player, this);
         tetrominoQueue = new TetrominoQueue(gameboard);
         rightSideFrame = new RightSideFrame(player,tetrominoQueue);
         heldTetromino = new HeldTetromino();
         leftSideFrame = new LeftSideFrame(heldTetromino);
    }

    public static void start() {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        Game game = new Game();
        frame.add(game);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
    }


    @Override
    protected void paintComponent(Graphics g) {
    	
    }
    


    
    public static HeldTetromino getHeldTetromino() {
		return heldTetromino;
	}

	public static void main(String[] args) {
        
        start();
    }
    
	@Override
	public void run() {
		while(running) {

			activeTetromino = gameboard.spawnRandomTetromino();
			tetrominoQueue.update();
			
			System.out.println(Thread.currentThread().getName());
			
			System.out.println("Spawned: " + activeTetromino);
			System.out.println("Still moving? " + activeTetromino.isMoving());
			
			if(activeTetromino != null) {
				while(activeTetromino.isMoving && running) {
					// Checks all rows if any are blocked and adds them to the markedRowsForDeletion
					for(int i = 0; i<gameboard.getGrid().size(); i++) {
						gameboard.isRowBlocked(i);
						repaint();
					}
					// Removes all the rows that are blocked highest up to lowest first
					if(gameboard.getMarkedRowsForDeletion().size() > 0) {
						player.addScore(gameboard.getMarkedRowsForDeletion().size());
						for (int row : gameboard.getMarkedRowsForDeletion()) {
							try {
								gameboard.deleteRow(row);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						gameboard.setMarkedRowsForDeletion(new ArrayList<Integer>());
					}
					
					gameboard.repaint();
					rightSideFrame.repaint();
					
				}
			}
			
		}
	}

	public Tetromino getActiveTetromino() {
		return activeTetromino;
	}

	public void setActiveTetromino(Tetromino activeTetromino) {
		this.activeTetromino = activeTetromino;
	}
	
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            activeTetromino.moveSideways(0);
            break;
        case KeyEvent.VK_RIGHT:
        	activeTetromino.moveSideways(1);
            break;
        case KeyEvent.VK_DOWN:
            gameSpeed = 50;
            break;
        case KeyEvent.VK_UP:
        	activeTetromino.rotate();
            break;
        case KeyEvent.VK_SPACE:
        	activeTetromino.instantPlace();
            break;
        case KeyEvent.VK_C:
        	repaint();
        	gameboard.holdTetromino();
        	repaint();
            break;
        case KeyEvent.VK_R:
        	restart();
            break;
    }
    }

    private void restart() {
        death();  // Stop the game loop
        removeAll(); // Remove any game elements from this JPanel
        this.remove(rightSideFrame);
        this.remove(gameboard);
        this.remove(leftSideFrame);
        
        
        // Reset key state
        activeTetromino = null;
        gameSpeed = 1000;
        running = true;
        
        // Reinitialize the game components
        setupUI();

        //activeTetromino = gameboard.spawnRandomTetromino();
       
        // Re-attach new components to layout if needed
        add(gameboard, BorderLayout.CENTER);  // Game in center
        add(rightSideFrame, BorderLayout.EAST);       // Score on right
        add(leftSideFrame, BorderLayout.WEST);

        revalidate(); // Force layout refresh
        repaint();    // Refresh graphics

        
        new Thread(this).start();  // Start the game loop again
    }
	
	public void death() {
		Thread.currentThread().interrupt();
		running = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            break;
        case KeyEvent.VK_RIGHT:
            break;
        case KeyEvent.VK_DOWN:
            gameSpeed = 1000;
            break;
        case KeyEvent.VK_UP:
            break;
    }
		
	}

	public boolean isRunning() {
		return running;
	}

	public int getGameSpeed() {
		return gameSpeed;
	}
	
	
	
	
}
