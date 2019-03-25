public class Player{
    private String playerName;
    private int score = 0;
    private boolean isPlaying = true;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerScore() {
        return score;
    }

    public boolean isPlayerPlaying() {
        return isPlaying;
    }

    public void playerIsLost() {
        isPlaying = false;
    }

    public String toString() {
        return String.format("Name: %s, Score: %d", playerName, score); 
    }

    public void addScore() {
        score++;
    }

    public void addScore(int score) {
        this.score += score;
    }
}