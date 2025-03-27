package tetris.Tetrominos;

import java.awt.Color;

import tetris.Block;
import tetris.Gameboard;
import tetris.Tetromino;

public class IShape extends Tetromino {
    public IShape(Gameboard gb) {
    	this.gameboard = gb;
    	color = Color.cyan;
    	generateBlocks();
    }
    
    @Override
    protected void generateBlocks() {
        bodyPieces.add(new Block(gameboard, this, 6, 0));
        bodyPieces.add(new Block(gameboard, this, 6, 1));
        bodyPieces.add(new Block(gameboard, this, 6, 2));
        bodyPieces.add(new Block(gameboard, this, 6, 3));
	}
}
