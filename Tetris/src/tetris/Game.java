package tetris;

import javax.swing.*;
import java.awt.*;

public class Game  implements Runnable{

	private static Gameboard gameboard;
	private static Player player;
	private Tetromino activeTetromino;
	
	public static void start() {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        player = new Player();
        gameboard = new Gameboard(player);

        frame.add(gameboard, BorderLayout.CENTER);

        frame.setBounds(100, 100, 300, 600);
        frame.setVisible(true);
    }
	
	
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            start();
        });
    }

    
	@Override
	public void run() {
		while(activeTetromino.isMoving) {
			activeTetromino.checkBelow();
			activeTetromino.moveDown();
			
			// Checks all rows if any are blocked and adds them to the markedRowsForDeletion
			for(int i = 0; i<24; i++) {
				gameboard.isRowBlocked(i);
			}
			// Removes all the rows that are blocked highest up to lowest first
			if(gameboard.getMarkedRowsForDeletion().size() > 0) {
				for (int row : gameboard.getMarkedRowsForDeletion()) {
					gameboard.deleteRow(row);
				}
			}
			
			gameboard.renderGameboard();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
