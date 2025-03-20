package tetris;

import java.util.ArrayList;

import tetris.Tetrominos.LShape;

public abstract class Tetromino  {

	protected ArrayList<Block> bodyPieces = new ArrayList<Block>();
	boolean isMoving = true;
	
	public void moveDown() {
		for(Block bodyPiece: bodyPieces) {
			bodyPiece.moveDown();
		}
	}
	
	public void moveSideways(int direction) {
		if(direction == 0) {
			for(Block block: bodyPieces) {
				block.moveSideways(-30);
			}
		}else {
			for(Block block: bodyPieces) {
				block.moveSideways(30);
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
	}
	
	
}
