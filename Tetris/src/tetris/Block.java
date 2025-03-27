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
    
    int originalGridX;
    int originalGridY;

    Gameboard gameBoard;
    Tetromino tetromino;

    public Block(Gameboard gameBoard, Tetromino tetromino, int x, int y) {
        this.gameBoard = gameBoard;
        this.tetromino = tetromino;
        this.gridX = x;
        this.gridY = y;
        this.originalGridX = gridX;
        this.originalGridY = gridY;
        this.x = x * 30;
        this.y = y * 30;

        setBounds(this.x, this.y, 30, 30); // Set initial position
    }
    
    public void reset() {
    	setGridX(originalGridX);
    	setGridY(originalGridY);
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
		gameBoard.placedBlocks.remove(this);
		gameBoard.remove(this);
		unBlockGrid();
	}
	
	public void removeSelfHold() {
		gameBoard.remove(this);
	}
	
	public void removeSelf(Tetromino tetromino, TetrominoQueue que) {
		tetromino.bodyPieces.remove(this);
		que.remove(this);
	}
	
	public void moveDown() {
		setGridY(getGridY()+1);
		
	}
	
	/**
	 * @param direction		Increments of 1. Positive right, Negative left
	 */
	public void moveSideways(int direction) {
		setGridX(getGridX()+direction);   
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
	 * Checks if the grid square the block is on is blocked
	 * 
	 * @return
	 */
	public boolean isBlocked() {
		GridBlock gridSquare = gameBoard.getGrid().get(gridY).get(gridX);
		if(gridSquare.isBlocked()) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * Checks if the grid square below the block is blocked and if it is. It tells the tetromino its connected to
	 * to stop moving. 
	 */
	public void checkBelow() {
		if(gridY+1<22) {
			GridBlock gridSquareBelow = gameBoard.getGrid().get(gridY+1).get(gridX);
			if(gridSquareBelow.isBlocked()) {
				tetromino.stopMoving();
			}
		} else {
			tetromino.stopMoving();
		}
	}
	
	/**
	 * @param direction		Increments of 1. Positive right, Negative left
	 */
	public boolean checkSides(int direction) {
		switch(direction) {
			case 0:
				if(gridX-1>-1) {
					GridBlock gridSquareBelow = gameBoard.getGrid().get(gridY).get(gridX-1);
					if(gridSquareBelow.isBlocked()) {
						return false;
					} else {
						return true;
					}
				} else {
					return false;
				}
			case 1:
				if(gridX+1<10) {
					GridBlock gridSquareBelow = gameBoard.getGrid().get(gridY).get(gridX+1);
					if(gridSquareBelow.isBlocked()) {
						return false;
					} else {
						return true;
					}
				} else {
					return false;
				}

			default:
				return false;
		}
	}
	
	/**
	 * Checks if this is above (not on) the row
	 * 
	 * @param row	The row we check from
	 * @return
	 */
	public boolean checkIfAbove(int row) {
		if(gridY < row) {
			return true;
		} else {
			return false;
		}
	}


	
}
