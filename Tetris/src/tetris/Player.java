package tetris;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player extends JComponent{
    private int score;
    private int highScore;
    private JLabel lable;

    public Player() {
        this.score = 0;
        this.highScore = 0;
        this.setPreferredSize(new Dimension(100, 200));
        this.setLayout(new FlowLayout());
        lable = new JLabel();
        lable.setText("0000");
        
        this.add(lable);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
    	
    }

    public void addScore(int rowsCleared) {
        int points = calculatePoints(rowsCleared);
        score += points;

        if (score > highScore) {
            highScore = score;
        }
        lable.setText(Integer.toString(score));
    }

    private int calculatePoints(int rowsCleared) {
        switch (rowsCleared) {
            case 1: return 100;
            case 2: return 300;
            case 3: return 500;
            case 4: return 800;
            default: return 0;
        }
    }

    public void resetScore() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }
}


