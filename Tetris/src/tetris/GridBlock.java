package tetris;

public class GridBlock {
	private int gridX;
	private int gridY;
	private int x;
	private int y;
	private int width;
	private boolean blocked = false;
	
	/**
	 * @param x
	 * @param y
	 * @param width
	 */
	public GridBlock(int x, int y, int width) {
		this.gridX = x;
		this.gridY = y;
		this.width = width;
		this.x = gridX*width;
		this.y = gridY*width;
	}
	

	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getGridX() {
		return gridX;
	}
	public void setGridX(int x) {
		this.gridX = x;
	}
	public int getGridY() {
		return gridY;
	}
	public void setGridY(int y) {
		this.gridY = y;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	
}
