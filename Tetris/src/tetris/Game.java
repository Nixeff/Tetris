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

        gameboard = new Gameboard(player, this);
        setPreferredSize(new Dimension(600, 1200));
        gameboard.spawnRandomTetromino();

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
        frame.add(gameboard);
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

        g.setColor(Color.RED);
        if (activeTetromino != null) {
            for (Block block : activeTetromino.getBodyPieces()) {
                g.fillRect(block.getGridX() * cellSize, block.getGridY() * cellSize, cellSize, cellSize);
            }
        }
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            start();
        });
    }
    
	@Override
	public void run() {
		while(activeTetromino.isMoving) {
			System.out.println("Hi");
			activeTetromino.checkBelow();
			activeTetromino.moveDown();
			
			// Checks all rows if any are blocked and adds them to the markedRowsForDeletion
			for(int i = 0; i<gameboard.getGrid().size(); i++) {
				gameboard.isRowBlocked(i);
			}
			// Removes all the rows that are blocked highest up to lowest first
			if(gameboard.getMarkedRowsForDeletion().size() > 0) {
				for (int row : gameboard.getMarkedRowsForDeletion()) {
					System.out.println("hi");
					gameboard.deleteRow(row);
				}
			}
			
			gameboard.repaint();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    repaint();
	}

	public Tetromino getActiveTetromino() {
		return activeTetromino;
	}

	public void setActiveTetromino(Tetromino activeTetromino) {
		this.activeTetromino = activeTetromino;
	}
	
	
	
	
}
