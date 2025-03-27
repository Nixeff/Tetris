package tetris.Tetrominos;

import java.awt.Color;

import tetris.Block;
import tetris.Gameboard;
import tetris.Tetromino;

public class OShape extends Tetromino {
    public OShape(Gameboard gb) {
    	this.gameboard = gb;
    	color = Color.yellow;
    	generateBlocks();
    }
    
    @Override
	protected void generateBlocks() {
        bodyPieces.add(new Block(gameboard, this, 5, 0));
        bodyPieces.add(new Block(gameboard, this, 6, 0));
        bodyPieces.add(new Block(gameboard, this, 5, 1));
        bodyPieces.add(new Block(gameboard, this, 6, 1));
	}
}