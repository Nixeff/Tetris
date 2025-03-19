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
		for(int i = 0; i < width; i++) {
			if(!grid.get(row).get(i).isBlocked()) {
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
	
	// Needs fixing for multiple rows removed at once
	public void deleteRow(int row) {
		for(int i = 0; i < Tetrominos.size(); i++) {
			for(int o = 0; o < Tetrominos.get(i).getBodyPieces().size(); o++) {
				if(Tetrominos.get(i).getBodyPieces().get(o).getY() == row) {
					Tetrominos.get(i).getBodyPieces().get(o).removeSelf();
				}
			}
		}
		for(int i = 0; i < Tetrominos.size(); i++) {
			boolean moveIt = true;
			for(int o = 0; 0 < Tetrominos.get(i).getBodyPieces().size(); o++) {
				if(!Tetrominos.get(i).getBodyPieces().get(o).checkIfAbove(row)) {
					moveIt = false;
				}
			}
			if(moveIt) {
				Tetrominos.get(i).moveDown();
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
