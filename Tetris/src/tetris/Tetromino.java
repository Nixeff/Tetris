package tetris;

import java.awt.Color;
import java.util.ArrayList;

import tetris.Tetrominos.LShape;

public abstract class Tetromino  {

	protected ArrayList<Block> bodyPieces = new ArrayList<Block>();
	protected Color color;
	boolean isMoving = true;
	
	
	public void moveDown() {
		for(Block bodyPiece: bodyPieces) {
			bodyPiece.moveDown();
		}
	}
	
	public void moveSideways(int direction) {
		if(direction == 0) {
			boolean move = true;
			for(Block block: bodyPieces) {
				if(block.getGridX()-1<0) {
					move = false;
				}
			}
			if(move) {
				for(Block block: bodyPieces) {
					block.moveSideways(-1);
				}
			}

		}else {
			boolean move = true;
			for(Block block: bodyPieces) {
				if(block.getGridX()+1>9) {
					move = false;
				}
			}
			if(move) {
				for(Block block: bodyPieces) {
					block.moveSideways(1);
				}
			}
		}
	}
	
	public void checkBelow() {
		for(Block bodyPiece: bodyPieces) {
			bodyPiece.checkBelow();
		}
	}

	public ArrayList<Block> getBodyPieces() {
		return bodyPieces;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
		if(isMoving) {
			for(Block block: bodyPieces) {
				block.unBlockGrid();
			}
		} else {
			for(Block block: bodyPieces) {
				block.blockGrid();
			}
		}
	}
	
	
}
