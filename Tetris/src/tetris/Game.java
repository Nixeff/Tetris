package tetris;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game extends JPanel implements Runnable {
    private static Gameboard gameboard;
    private static Player player;
    private Tetromino activeTetromino;
    private Random random = new Random();
    private boolean running = true;

    public Game() {
        player = new Player();
        gameboard = new Gameboard(player);
        setPreferredSize(new Dimension(300, 600));

        spawnRandomTetromino();

        new Thread(this).start();
    }

    public static void start() {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game game = new Game();
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void spawnRandomTetromino() {
        String[] types = {"L"};
        String randomType = types[random.nextInt(types.length)];
        activeTetromino = Tetromino.createTetromino(gameboard, randomType);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);

        if (activeTetromino != null) {
            for (Block block : activeTetromino.getBodyPieces()) {
                g.fillRect(block.getGridX() * 30, block.getGridY() * 30, 30, 30);
            }
        }
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
    spawnRandomTetromino();
    repaint();
	}
}
