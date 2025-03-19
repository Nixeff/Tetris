package tetris;

import java.util.ArrayList;

public class Block {

	int gridX;
	int gridY;
	
	int x;
	int y;
	Gameboard gameBoard;
	Tetromino tetromino;
	
	public Block(Gameboard gameBoard, Tetromino tetromino) {
		this.gameBoard = gameBoard;
		this.tetromino = tetromino;
	}
	
	
	
	public int getGridX() {
		return gridX;
	}



	/**
	 * @param gridX
	 */
	public void setGridX(int gridX) {
		this.gridX = gridX;
	}



	public int getGridY() {
		return gridY;
	}



	/**
	 * @param gridY		this is the y cordinate in the grid system
	 */
	public void setGridY(int gridY) {
		this.gridY = gridY;
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
	}
	
	public void moveDown() {
		y += 10;
		gridY += 1;
	}
	
	
	/**
	 * @param direction		Increments of 10. Positive right, Negative left
	 */
	public void moveSideways(int direction) {
		gridX += direction;
	}
	
	public void checkBelow() {
		ArrayList<GridBlock> row = gameBoard.getGrid().get(y+1);
		for(int i = 0; i < row.size(); i++) {
			if(row.get(i).isBlocked()) {
				tetromino.setMoving(false);
			}
		}
	}
	
	public boolean checkIfAbove(int row) {
		if(y < row) {
			return true;
		} else {
			return false;
		}
	}
	
}
