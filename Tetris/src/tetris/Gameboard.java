package tetris;

import java.util.ArrayList;

public class Gameboard {
	
	ArrayList<Tetromino> Tetrominos = new ArrayList<Tetromino>();
	ArrayList<ArrayList<GridBlock>> grid = new ArrayList<ArrayList<GridBlock>>();
	


	int height = 22;
	int width = 10;

	public Gameboard() {
		
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
			deleteRow(row);
		}
	}
	
	private void deleteRow(int row) {
		for(int i = 0; i < Tetrominos.size(); i++) {
			for(int o = 0; o < 4; o++) {
				if(Tetrominos.get(i).getBodyPieces().get(o).getY() == row) {
					Tetrominos.get(i).getBodyPieces().get(o).removeSelf();
				}
			}
			
		}
	}
	
	public ArrayList<ArrayList<GridBlock>> getGrid() {
		return grid;
	}

	public ArrayList<Tetromino> getTetrominos() {
		return Tetrominos;
	}
	
	
}
