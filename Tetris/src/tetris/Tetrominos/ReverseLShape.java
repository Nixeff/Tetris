package tetris.Tetrominos;

import java.awt.Color;
import tetris.Block;
import tetris.Gameboard;
import tetris.Tetromino;

public class ReverseLShape extends Tetromino {
    public ReverseLShape(Gameboard gb) {
    	this.gameboard = gb;
        color = Color.orange;
        generateBlocks();
    }
    
    @Override
	protected void generateBlocks() {
        bodyPieces.add(new Block(gameboard, this, 5, 1));
        bodyPieces.add(new Block(gameboard, this, 4, 1));
        bodyPieces.add(new Block(gameboard, this, 6, 1));
        bodyPieces.add(new Block(gameboard, this, 4, 0));
	}
}
