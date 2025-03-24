package tetris.Tetrominos;

import java.awt.Color;
import tetris.Block;
import tetris.Gameboard;
import tetris.Tetromino;

public class ReverseLShape extends Tetromino {
    public ReverseLShape(Gameboard gb) {
    	this.gameboard = gb;
        color = Color.orange;

        bodyPieces.add(new Block(gb, this, 7, 1));
        bodyPieces.add(new Block(gb, this, 6, 1));
        bodyPieces.add(new Block(gb, this, 8, 1));
        bodyPieces.add(new Block(gb, this, 6, 0));
    }
}
