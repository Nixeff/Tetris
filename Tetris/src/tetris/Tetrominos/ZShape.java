package tetris.Tetrominos;

import java.awt.Color;
import tetris.Block;
import tetris.Gameboard;
import tetris.Tetromino;

public class ZShape extends Tetromino {
    public ZShape(Gameboard gb) {
    	this.gameboard = gb;
        color = Color.green;

        bodyPieces.add(new Block(gb, this, 5, 0));
        bodyPieces.add(new Block(gb, this, 6, 0));
        bodyPieces.add(new Block(gb, this, 4, 1));
        bodyPieces.add(new Block(gb, this, 5, 1));
    }
}
