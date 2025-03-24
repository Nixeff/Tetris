package tetris.Tetrominos;

import java.awt.Color;
import tetris.Block;
import tetris.Gameboard;
import tetris.Tetromino;

public class LShape extends Tetromino {
    public LShape(Gameboard gb) {
        color = Color.blue;
        this.gameboard = gb;
        bodyPieces.add(new Block(gb, this, 5, 2));
        bodyPieces.add(new Block(gb, this, 5, 1));
        bodyPieces.add(new Block(gb, this, 5, 0));
        bodyPieces.add(new Block(gb, this, 6, 2));
    }
}

