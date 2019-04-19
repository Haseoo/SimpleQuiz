package gamecore;

import gamecore.players.PlayerInternal;
import gamecore.players.PlayersList;
import gamecore.questions.CategoriesList;
import gamecore.questions.Question;
import gamecore.questions.QuestionsList;

public abstract class Game {
    protected PlayersList playersList;
    protected QuestionsList questionsList;
    protected boolean gameHasEndedVar;
    public enum GameModes {
        ALL_QUESTIONS_WFO;
    }



    protected Game(String[]players) {
        gameHasEndedVar = false;
        playersList = new PlayersList();
        for (String it : players) {
            playersList.addPlayer(it);
        }
        CategoriesList.initList();
        questionsList = new QuestionsList();
    }

    public static Game getInstance(GameModes gameMode, String[] players) {
        //TODO throws CategoryImproperFormatted
        Game retVar = null;
        switch (gameMode) {
            case ALL_QUESTIONS_WFO:
                retVar =  new GameAllQuestionsWFO(players);
                break;
        }

        return retVar;
    }

    PlayerInternal.Player[] getPlayerList() {
        return playersList.getPlayersList();
    }

    public abstract Question getNewQuestion();

    public abstract Question getNewQuestion(int categoryNo);

    public abstract boolean setAnswerFromPlayer(PlayerInternal.Player player, Question question, int playerAnswer);

    public boolean gameHasEnded() {
        return gameHasEndedVar;
    }
}
