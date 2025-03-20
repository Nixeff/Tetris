package tetris.Tetrominos;

import tetris.Block;
import tetris.Gameboard;
import tetris.Tetromino;

public class ReverseLShape extends Tetromino {
    public ReverseLShape(Gameboard gb) {
        bodyPieces.add(new Block(gb, this, 6, 1));
        bodyPieces.add(new Block(gb, this, 6, 0));
        bodyPieces.add(new Block(gb, this, 7, 1));
        bodyPieces.add(new Block(gb, this, 8, 1));
    }
}
