package tetris;

import java.util.ArrayList;

public class Gameboard {

	public Gameboard() {
		int height = 22;
		int width = 10;
		ArrayList<ArrayList<GridBlock>> grid = new ArrayList<ArrayList<GridBlock>>();
		
		for (int i = 0; i < height; i++ ) {
			grid.add(new ArrayList<GridBlock>());
			for (int o = 0; o < width; o++ ) {
				grid.get(i).add(new GridBlock(o,i));
			}
		}
	}
}
