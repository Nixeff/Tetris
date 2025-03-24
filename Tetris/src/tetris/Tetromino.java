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
	    if (bodyPieces.isEmpty()) return; // FAILSAFE (in case you somehow dont have a block to rotate)

	    //use the first block as the pivot/rotate point for rotation (+ get X, Y cords)
	    Block pivot = bodyPieces.get(0);
	    int pivotX = pivot.getGridX();
	    int pivotY = pivot.getGridY();

	    //put new calculated position in arrayList
	    ArrayList<int[]> newPositions = new ArrayList<>();

	    //calc for each block of the tetromino piece
	    for (Block block : bodyPieces) {
	    	//gets relative positions to the rotation-point
	        int relX = block.getGridX() - pivotX;
	        int relY = block.getGridY() - pivotY;
	        
	        //applies a 90-degree rotation
	        int newX = pivotX - relY;
	        int newY = pivotY + relX;

	        newPositions.add(new int[]{newX, newY});
	    }

	    //actually applies the rotation (and only if canRotate is true)
	    if (canRotate(newPositions)) {
	        for (int i = 0; i < bodyPieces.size(); i++) {
	            bodyPieces.get(i).setGridX(newPositions.get(i)[0]);
	            bodyPieces.get(i).setGridY(newPositions.get(i)[1]);
	        }
	    }

	    if (gameboard != null) {
	        gameboard.repaint();
	    }
	}

	private boolean canRotate(ArrayList<int[]> newPositions) {
	    //look at all pieces and their positions
		for (int[] pos : newPositions) {
	        int x = pos[0];
	        int y = pos[1];
	        
	        //if rotation is out of bounds (of the grid) return false
	        if (x < 0 || x >= 10 || y >= 22) {
	            return false;
	        }
	        //if rotation would rotate inside other block return false
	        if (y >= 0 && gameboard.getGrid().get(y).get(x).isBlocked()) {
	            return false;
	        }
	    }
	    return true;
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
