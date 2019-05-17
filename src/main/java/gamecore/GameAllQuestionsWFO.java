package gamecore;

import gamecore.players.PlayerInternal;
import gamecore.questions.CategoriesListImproperFormatted;
import gamecore.questions.Question;

public class GameAllQuestionsWFO extends GameAllQuestionsWOFO {
    protected GameAllQuestionsWFO(String[] players) throws CategoriesListImproperFormatted {
        super(players);
    }

    @Override
    public boolean setAnswerFromPlayer(PlayerInternal.Player player, Question question, int playerAnswer) {
        if (!player.isPlayerPlaying()) {
            throw new IllegalPlayerException("Got answer from player who has lost");
        }
        boolean retVal = false;
        if (playerAnswer == question.correctAnswer) {
            retVal = true;
        } else {
            playersList.getPlayer(player).playerIsLost();
            if (playersList.allPlayerHasLost()) {
                gameHasEndedVar = true;
            }
        }
        return retVal;
    }
}
