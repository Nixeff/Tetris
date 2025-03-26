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
    	player = new Player();
    	setupUI();
        setPreferredSize(new Dimension(600, 700));
        
        setFocusable(true);
        addKeyListener(this);
        this.setLayout(new BorderLayout());
        
        add(gameboard, BorderLayout.CENTER);  // Game in center
        add(rightSideFrame, BorderLayout.EAST);       // Score on right
        add(leftSideFrame, BorderLayout.WEST);

        new Thread(this).start();
    }
    
    public void setupUI() {
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
			
			if(activeTetromino != null) {
				while(activeTetromino.isMoving && running) {
					// Checks all rows if any are blocked and adds them to the markedRowsForDeletion
					for(int i = 0; i<gameboard.getGrid().size(); i++) {
						gameboard.isRowBlocked(i);
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
					javax.swing.SwingUtilities.windowForComponent(this).repaint();
					
					
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
        	gameboard.holdTetromino();
            break;
        case KeyEvent.VK_R:
        	restart();
            break;
    }
    }

    private void restart() {
        death();  // Stop the game loop
        removeAll(); // Remove any game elements from this JPanel
        
        // Reset key state
        activeTetromino = null;
        player.resetScore();
        gameSpeed = 1000;
        running = true;
        
        // Reinitialize the game components
        setupUI();
       
        // Re-attach new components to layout if needed
        
        add(gameboard, BorderLayout.CENTER);  // Game in center
        add(rightSideFrame, BorderLayout.EAST);       // Score on right
        add(leftSideFrame, BorderLayout.WEST);
        
        new Thread(this).start();  // Start the game loop again
    }
	
	public void death() {
		
		removeAll(); // Remove any game elements from this JPanel
        add(new RestartFrame(player), BorderLayout.CENTER);
        revalidate();
        javax.swing.SwingUtilities.windowForComponent(this).repaint();
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
