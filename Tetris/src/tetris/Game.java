package tetris;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Game{

	private Gameboard gameboard;
	private Player player;
	private Random random;
	
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

}
