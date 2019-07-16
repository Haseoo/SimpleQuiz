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
    protected CategoriesList categoriesList;
    public enum GameModes {
        ALL_QUESTIONS_WOFO,
        ALL_QUESTIONS_WFO,
        SPEC_QUESTIONS_WOFO,
        SPEC_QUESTIONS_WFO,
        W_CHOOSING_CATEGORY
    }



    protected Game(String[]players) throws CategoriesListImproperFormatted{
        gameHasEndedVar = false;
        playersList = new PlayersList();
        for (String it : players) {
            playersList.addPlayer(it);
        }
        categoriesList = new CategoriesList();
        questionsList = new QuestionsList();
        questionsCounter = 0;
    }

    public static Game getInstance(GameModes gameMode, String[] players) throws CategoriesListImproperFormatted{
        Game retVar = null;
        switch (gameMode) {
            case ALL_QUESTIONS_WOFO:
                retVar =  new GameAllQuestionsWOFO(players);
                break;
            case ALL_QUESTIONS_WFO:
                retVar = new GameAllQuestionsWFO(players);
        }

        return retVar;
    }

    public PlayerInternal.Player[] getPlayerList() {
        return playersList.getPlayersList();
    }

    public abstract Question getNewQuestion() throws QuestionGenerationNotPossible, QuestionReadingException, UnsupportedOperationException;

    public abstract Question getNewQuestion(int categoryNo) throws QuestionGenerationNotPossible, QuestionReadingException, UnsupportedOperationException;

    public abstract boolean setAnswerFromPlayer(PlayerInternal.Player player, Question question, int playerAnswer);

    public boolean gameHasEnded() {
        return gameHasEndedVar;
    }
}
