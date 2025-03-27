package tetris;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class HeldTetromino extends JComponent{

	private Tetromino heldTetromino;
	private JLabel label;
	
	public HeldTetromino() {
		super();
		this.setPreferredSize(new Dimension(200, 700));
        this.setLayout(null);
        this.label = new JLabel();
        this.label.setPreferredSize(new Dimension(100,50));
        this.label.setText("Hold");
        this.label.setBounds(50, 0, 100,50);
        this.add(label);
	}
	
	private void displayTetromino() {
        for (Block block : heldTetromino.getBodyPieces()) {
        	block.reset();
            this.add(block);
        }
    	for(int y = 0; y<4;y++) {
    		heldTetromino.moveSideways(0);
    	}
    	heldTetromino.moveDown();
    	heldTetromino.moveDown();
	}

	public Tetromino getHeldTetromino() {
		return heldTetromino;
	}

	/**
	 * Calls displayTetromino automatically 
	 * @param heldTetromino
	 */
	public void setHeldTetromino(Tetromino heldTetromino) {
		this.heldTetromino = heldTetromino;
		displayTetromino();
	}
}
