package gamecore;

import gamecore.players.PlayerInternal;
import gamecore.players.PlayersList;
import gamecore.questions.*;

public abstract class Game {
    protected PlayersList playersList;
    protected QuestionsList questionsList;
    protected boolean gameHasEndedVar;
    protected int TOTAL_NUMBER_OF_QUESTIONS;
    protected int questionsCounter;
    public enum GameModes {
        ALL_QUESTIONS_WFO;
    }



    protected Game(String[]players) throws CategoriesListImproperFormatted{
        gameHasEndedVar = false;
        playersList = new PlayersList();
        for (String it : players) {
            playersList.addPlayer(it);
        }
        CategoriesList.initList();
        questionsList = new QuestionsList();
        questionsCounter = 0;
    }

    public static Game getInstance(GameModes gameMode, String[] players) throws CategoriesListImproperFormatted{
        Game retVar = null;
        switch (gameMode) {
            case ALL_QUESTIONS_WFO:
                retVar =  new GameAllQuestionsWFO(players);
                break;
        }

        return retVar;
    }

    public PlayerInternal.Player[] getPlayerList() {
        return playersList.getPlayersList();
    }

    public abstract Question getNewQuestion() throws QuestionGenerationNotPossible, QuestionReadingException;

    public abstract Question getNewQuestion(int categoryNo);

    public abstract boolean setAnswerFromPlayer(PlayerInternal.Player player, Question question, int playerAnswer);

    public boolean gameHasEnded() {
        return gameHasEndedVar;
    }
}
