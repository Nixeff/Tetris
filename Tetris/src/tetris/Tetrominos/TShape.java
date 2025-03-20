package tetris.Tetrominos;

import java.awt.Color;

import tetris.Block;
import tetris.Gameboard;
import tetris.Tetromino;

public class TShape extends Tetromino {
    public TShape(Gameboard gb) {
    	color = Color.magenta;
        bodyPieces.add(new Block(gb, this, 6, 1));
        bodyPieces.add(new Block(gb, this, 5, 1));
        bodyPieces.add(new Block(gb, this, 4, 1));
        bodyPieces.add(new Block(gb, this, 5, 0));
    }
}

