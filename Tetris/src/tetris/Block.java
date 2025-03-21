package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Block extends JPanel {

    int gridX;
    int gridY;

    int x;
    int y;
    Gameboard gameBoard;
    Tetromino tetromino;

    public Block(Gameboard gameBoard, Tetromino tetromino, int x, int y) {
        this.gameBoard = gameBoard;
        this.tetromino = tetromino;
        this.gridX = x;
        this.gridY = y;
        this.x = x * 30;
        this.y = y * 30;

        setBounds(this.x, this.y, 30, 30); // Set initial position
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
      
        g.setColor(tetromino.color);
        g.fillRect(0, 0, 30, 30);
        g.setColor(Color.BLACK); // Border for visibility
        g.drawRect(0, 0, 30, 30);
    }

    public int getGridX() {
        return gridX;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
        this.x = gridX * 30; // Update pixel coordinate
        setBounds(this.x, this.y, 30, 30); // Reposition the component
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
        this.y = gridY * 30; // Update pixel coordinate
        setBounds(this.x, this.y, 30, 30); // Reposition the component
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


	
	public void removeSelf() {
		tetromino.getBodyPieces().remove(this);
		if(tetromino.getBodyPieces().size() == 0) {
			gameBoard.getTetrominos().remove(tetromino);
		}
		gameBoard.remove(this);
		unBlockGrid();
	}
	
	public void moveDown() {
		y += 30;
		gridY += 1;
		
	}
	
	public void blockGrid() {
		ArrayList<ArrayList<GridBlock>> grid = gameBoard.getGrid();
		grid.get(gridY).get(gridX).setBlocked(true);
	}
	public void unBlockGrid() {
		ArrayList<ArrayList<GridBlock>> grid = gameBoard.getGrid();
		grid.get(gridY).get(gridX).setBlocked(false);
	}
	
	
	/**
	 * @param direction		Increments of 10. Positive right, Negative left
	 */
	public void moveSideways(int direction) {
		gridX += direction;
		x = gridX*30;
	}
	
	public void checkBelow() {
		if(gridY+1<22) {
			GridBlock gridSquareBelow = gameBoard.getGrid().get(gridY+1).get(gridX);
			if(gridSquareBelow.isBlocked()) {
				tetromino.setMoving(false);
			}
		} else {
			tetromino.setMoving(false);
		}

	}
	
	public boolean checkIfAbove(int row) {
		if(gridY < row) {
			return true;
		} else {
			return false;
		}
	}
	
}
