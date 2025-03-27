package tetris.Tetrominos;

import java.awt.Color;
import tetris.Block;
import tetris.Gameboard;
import tetris.Tetromino;

public class TShape extends Tetromino {
    public TShape(Gameboard gb) {
    	this.gameboard = gb;
        color = Color.magenta;
        generateBlocks();
    }
    
    @Override
	protected void generateBlocks() {
        bodyPieces.add(new Block(gameboard, this, 5, 1));
        bodyPieces.add(new Block(gameboard, this, 4, 1));
        bodyPieces.add(new Block(gameboard, this, 6, 1));
        bodyPieces.add(new Block(gameboard, this, 5, 0));
	}
}
