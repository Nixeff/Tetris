package tetris;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class RightSideFrame extends JComponent {
	
	Player player;
	TetrominoQueue tetrominoQueue;

	public RightSideFrame(Player player, TetrominoQueue tetrominoQueue) {
		this.player = player;
		this.tetrominoQueue = tetrominoQueue;
        this.setPreferredSize(new Dimension(150, 800));
        this.setLayout(new BorderLayout());
        
        this.add(player, BorderLayout.NORTH);
        this.add(tetrominoQueue, BorderLayout.CENTER);
	}
	
}
