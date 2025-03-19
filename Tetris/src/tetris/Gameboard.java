package tetris;

import java.util.ArrayList;

import javax.swing.JPanel;

public class Gameboard extends JPanel{
	
	ArrayList<Tetromino> Tetrominos = new ArrayList<Tetromino>();
	ArrayList<ArrayList<GridBlock>> grid = new ArrayList<ArrayList<GridBlock>>();
	ArrayList<Integer> markedRowsForDeletion = new ArrayList<Integer>(); // int cant be used and Integer is the same apperently? idk
	
	int height = 22;
	int width = 10;
	
	private Player player;

	public Gameboard(Player player) {
		
		this.player = player;
		
		for (int i = 0; i < height; i++ ) {
			grid.add(new ArrayList<GridBlock>());
			for (int o = 0; o < width; o++ ) {
				grid.get(i).add(new GridBlock(o,i,10));
			}
		}
	}
	
	public void isRowBlocked(int row) {
		boolean isBlocked = false;
		for(GridBlock gridSquare : grid.get(row)) {
			if(gridSquare.isBlocked()) {
				isBlocked = false;
				break;
			} else {
				isBlocked = true;
			}
		}
		if(isBlocked) {
			markedRowsForDeletion.add(row);
		}
	}
	
	public void renderGameboard() {
		for(Tetromino shape : Tetrominos) {
			for(Block body : shape.getBodyPieces()) {
				body.draw();
			}
		}
	}
	
	public void deleteRow(int row) {
		for(Tetromino shape : Tetrominos) {
			for(Block bodyPart : shape.getBodyPieces()) {
				if(bodyPart.getY() == row) {
					bodyPart.removeSelf();
				}
			}
		}
		for(Tetromino shape : Tetrominos) {
			boolean moveIt = true;
			for(Block bodyPart : shape.getBodyPieces()) {
				if(!bodyPart.checkIfAbove(row)) {
					moveIt = false;
				}
			}
			if(moveIt) {
				shape.moveDown();
			}
		}
	}
	
	public ArrayList<ArrayList<GridBlock>> getGrid() {
		return grid;
	}

	public ArrayList<Tetromino> getTetrominos() {
		return Tetrominos;
	}

	public ArrayList<Integer> getMarkedRowsForDeletion() {
		return markedRowsForDeletion;
	}
	
	
	
	
}
