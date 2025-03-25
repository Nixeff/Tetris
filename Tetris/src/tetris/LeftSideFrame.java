package tetris;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;

public class LeftSideFrame extends JComponent {
	
	HeldTetromino heldTetromino;

	public LeftSideFrame(HeldTetromino heldTetromino) {
		this.heldTetromino = heldTetromino;
		
        this.setPreferredSize(new Dimension(150, 200));
        this.setLayout(new BorderLayout());
        
        this.add(heldTetromino, BorderLayout.NORTH);
	}
	
}

