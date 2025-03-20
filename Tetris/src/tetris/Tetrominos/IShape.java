package tetris.Tetrominos;

import tetris.Block;
import tetris.Gameboard;
import tetris.Tetromino;

public class IShape extends Tetromino {
    public IShape(Gameboard gb) {
        bodyPieces.add(new Block(gb, this, 6, 0));
        bodyPieces.add(new Block(gb, this, 6, 1));
        bodyPieces.add(new Block(gb, this, 6, 2));
        bodyPieces.add(new Block(gb, this, 6, 3));
    }
}
