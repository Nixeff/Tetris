package tetris.Tetrominos;

import tetris.Block;
import tetris.Gameboard;
import tetris.Tetromino;

public class LShape extends Tetromino  {

	public LShape(Gameboard gb) {
		bodyPieces.add(new Block(gb,this,6,1));
		bodyPieces.add(new Block(gb,this,6,0));
		bodyPieces.add(new Block(gb,this,5,1));
		bodyPieces.add(new Block(gb,this,4,1));
	}
}
