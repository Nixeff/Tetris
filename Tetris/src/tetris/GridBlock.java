package tetris;

public class GridBlock {
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
		this.x = x;
		this.y = y;
		this.width = width;
	}
	

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	
}
