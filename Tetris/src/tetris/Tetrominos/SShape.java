package tetris.Tetrominos;

import java.awt.Color;
import tetris.Block;
import tetris.Gameboard;
import tetris.Tetromino;

public class SShape extends Tetromino {
    public SShape(Gameboard gb) {
        color = Color.red;

        bodyPieces.add(new Block(gb, this, 5, 1));
        bodyPieces.add(new Block(gb, this, 6, 1));
        bodyPieces.add(new Block(gb, this, 4, 0));
        bodyPieces.add(new Block(gb, this, 5, 0));
    }
}
