package gamecore.players;

public class PlayerInternal {
    private String playerName;
    private int score = 0,
                id;
    private boolean isPlaying = true;

    public PlayerInternal(String playerName, int id) {
        this.playerName = playerName;
        this.id = id;
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


    public void addScore() {
        score++;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getId() {
        return id;
    }

    public class Player {
        public String getPlayerName() {
            return playerName;
        }

        public int getPlayerScore() {
            return score;
        }

        public int getId() {
            return id;
        }

        public boolean isPlayerPlaying() {
            return isPlaying;
        }
    }
}