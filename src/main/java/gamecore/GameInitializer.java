package gamecore;

public class GameInitializer {
    private Game.GameModes gameMode;
    private String[] playersNicknames;
    private int numberOfQuestions;

    public GameInitializer(Game.GameModes gameMode, String[] playersNicknames, int numberOfQuestions) {
        this.gameMode = gameMode;
        this.playersNicknames = playersNicknames;
        this.numberOfQuestions = numberOfQuestions;
    }

    public Game.GameModes getGameMode() {
        return gameMode;
    }

    public String[] getPlayersNicknames() {
        return playersNicknames;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }
}
