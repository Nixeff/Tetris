package tetris;

import java.util.ArrayList;

public class Tetromino implements Runnable {

	ArrayList<Block> bodyPieces;
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

	@Override
	public void run() {
		while(isMoving) {
			checkBelow();
			moveDown();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
