package tetris;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Game  implements Runnable{

	private Gameboard gameboard;
	private Player player;
	private Random random;
	private Tetromino activeTetromino;
	
	public Game() {
        this.gameboard = new Gameboard();
        this.player = new Player();
        this.random = new Random();
    }
	
    public void start() {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
	
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            game.start();
        });
    }

    
	@Override
	public void run() {
		while(activeTetromino.isMoving) {
			activeTetromino.checkBelow();
			activeTetromino.moveDown();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
