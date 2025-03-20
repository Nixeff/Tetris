package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Block extends JPanel{

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
		this.x = x*30;
		this.y = y*30;
	}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("hi");
        g.setColor(Color.RED);
        g.fillRect(x, y, 30, 30);
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
		y += 30;
		gridY += 1;
	}
	
	
	/**
	 * @param direction		Increments of 10. Positive right, Negative left
	 */
	public void moveSideways(int direction) {
		gridX += direction;
	}
	
	public void checkBelow() {
		GridBlock gridSquareBelow = gameBoard.getGrid().get(gridY+1).get(gridX);
		if(gridSquareBelow.isBlocked()) {
			tetromino.setMoving(false);
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
