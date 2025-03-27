package tetris.Tetrominos;

import java.awt.Color;
import tetris.Block;
import tetris.Gameboard;
import tetris.Tetromino;

public class LShape extends Tetromino {
    public LShape(Gameboard gb) {
    	this.gameboard = gb;
    	color = Color.blue;
    	generateBlocks();
    }
    
    @Override
	protected void generateBlocks() {
        bodyPieces.add(new Block(gameboard, this, 5, 2));
        bodyPieces.add(new Block(gameboard, this, 5, 1));
        bodyPieces.add(new Block(gameboard, this, 5, 0));
        bodyPieces.add(new Block(gameboard, this, 6, 2));
	}
}

