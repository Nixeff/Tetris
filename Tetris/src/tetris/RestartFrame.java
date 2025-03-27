package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RestartFrame extends JComponent{
	private JLabel hintText;
	private JLabel score;
	private JLabel highScore;
	private Player player;
	public RestartFrame(Player player) {
		this.player = player;
		setUpUi();
    }
	
	private void setUpUi() {
        setLayout(new GridBagLayout()); 

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

        hintText = new JLabel("Press 'R' to restart!", SwingConstants.CENTER);
        score = new JLabel("Score: " + player.getScore(), SwingConstants.CENTER);
        highScore = new JLabel("Highscore: " + player.getHighScore(), SwingConstants.CENTER);

        hintText.setFont(new Font("Arial", Font.BOLD, 24));
        score.setFont(new Font("Arial", Font.PLAIN, 20));
        highScore.setFont(new Font("Arial", Font.PLAIN, 20));

        hintText.setAlignmentX(Component.CENTER_ALIGNMENT);
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        highScore.setAlignmentX(Component.CENTER_ALIGNMENT);

        hintText.setForeground(Color.BLACK);
        score.setForeground(Color.BLACK);
        highScore.setForeground(Color.BLACK);

        labelPanel.add(hintText);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPanel.add(score);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        labelPanel.add(highScore);

        add(labelPanel);
	}
}
