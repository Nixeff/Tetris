package tetris;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Tetromino  {

	protected ArrayList<Block> bodyPieces = new ArrayList<Block>();
	protected Color color;
	protected Gameboard gameboard;
	boolean isMoving = true;
	
	
	public void moveDown() {
		for(Block bodyPiece: bodyPieces) {
			bodyPiece.moveDown();
		}
	}
	
	public void instantPlace() {
		while(isMoving) {
			checkBelow();
			if(isMoving) {
				moveDown();
			}
		}
	}
	
	public void moveSideways(int direction) {
		if(isMoving) {
			if(direction == 0) {
				boolean move = true;
				for(Block block: bodyPieces) {
					if(!block.checkSides(0)) {
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
					if(!block.checkSides(1)) {
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
	}
	
	public void rotate() {
	    if (bodyPieces.isEmpty()) return;

	    Block pivot = bodyPieces.get(0);
	    int pivotX = pivot.getGridX();
	    int pivotY = pivot.getGridY();

	    //System.out.println("Pivot: (" + pivotX + ", " + pivotY + ")");

	    ArrayList<int[]> newPositions = new ArrayList<>();

	    for (Block block : bodyPieces) {
	        int relX = block.getGridX() - pivotX;
	        int relY = block.getGridY() - pivotY;

	        int newX = pivotX - relY;
	        int newY = pivotY + relX;

	        //System.out.println("Block: (" + block.getGridX() + ", " + block.getGridY() + ") -> (" + newX + ", " + newY + ")");

	        newPositions.add(new int[]{newX, newY});
	    }

	    for (int i = 0; i < bodyPieces.size(); i++) {
	        bodyPieces.get(i).setGridX(newPositions.get(i)[0]);
	        bodyPieces.get(i).setGridY(newPositions.get(i)[1]);
	    }

	    
	    if (gameboard != null) {
	        gameboard.repaint();
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

	public void stopMoving() {
		this.isMoving = false;
		for(Block block: bodyPieces) {
			block.blockGrid();
			gameboard.addPlacedBlocks(block);
		}
	}
	
	
}
