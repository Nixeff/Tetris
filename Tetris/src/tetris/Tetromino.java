package tetris;

import java.util.ArrayList;

public abstract class Tetromino  {

	protected ArrayList<Block> bodyPieces = new ArrayList<Block>();
	boolean isMoving = true;
	
	public void moveDown() {
		for(int i = 0; i < bodyPieces.size(); i++) {
			bodyPieces.get(i).moveDown();
		}
	}
	
	public void checkBelow() {
		for(int i = 0; i < bodyPieces.size(); i++) {
			bodyPieces.get(i).checkBelow();
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
