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
    private Tetromino activeTetromino;
    private Random random = new Random();
    private boolean running = true;
    private int gameSpeed = 1000;

    public Game() {
        player = new Player();

        gameboard = new Gameboard(player, this);
        setPreferredSize(new Dimension(600, 1200));
        
        setFocusable(true);
        addKeyListener(this);

        new Thread(this).start();
    }

    public static void start() {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        Game game = new Game();
        frame.add(game);
        frame.add(gameboard, BorderLayout.CENTER);  // Game in center
        frame.add(player, BorderLayout.EAST);       // Score on right
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        
    }


    @Override
    protected void paintComponent(Graphics g) {
    	
    }
    


    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            start();
        });
    }
    
	@Override
	public void run() {
		while(running) {
			gameboard.spawnRandomTetromino();
			
			while(activeTetromino.isMoving) {
				// Checks all rows if any are blocked and adds them to the markedRowsForDeletion
				for(int i = 0; i<gameboard.getGrid().size(); i++) {
					gameboard.isRowBlocked(i);
				}
				// Removes all the rows that are blocked highest up to lowest first
				if(gameboard.getMarkedRowsForDeletion().size() > 0) {
					player.addScore(gameboard.getMarkedRowsForDeletion().size());
					for (int row : gameboard.getMarkedRowsForDeletion()) {
						gameboard.deleteRow(row);
					}
					gameboard.setMarkedRowsForDeletion(new ArrayList<Integer>());
				}
				
				gameboard.repaint();
				
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
        	repaint();
        	activeTetromino.rotate();
        	repaint();
            break;
    }
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
