package tetris;

public class Player {
    private int score;
    private int highScore;

    public Player() {
        this.score = 0;
        this.highScore = 0;
    }

    public void addScore(int rowsCleared) {
        int points = calculatePoints(rowsCleared);
        score += points;

        if (score > highScore) {
            highScore = score;
        }
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


