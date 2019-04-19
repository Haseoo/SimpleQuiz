package gamecore;

import gamecore.players.PlayerInternal;
import gamecore.questions.*;

public class GameAllQuestionsWFO extends Game{
    protected GameAllQuestionsWFO(String[] players) throws CategoriesListImproperFormatted {
        super(players);
        TOTAL_NUMBER_OF_QUESTIONS = CategoriesList.getTotalNumberOfQuestions() - 1;
    }

    @Override
    public Question getNewQuestion(int categoryNo) {
        //TODO throw an exception
        return null;
    }

    @Override
    public Question getNewQuestion() throws QuestionGenerationNotPossible, QuestionReadingException {
        questionsList.newQuestion();
        questionsCounter++;
        if (questionsCounter == TOTAL_NUMBER_OF_QUESTIONS) {
            gameHasEndedVar = true;
        }
        return new Question(questionsList.getLastQuestion());
    }

    @Override
    public boolean setAnswerFromPlayer(PlayerInternal.Player player, Question question, int playerAnswer) {
        boolean retVal = false;
        if (playerAnswer == question.correctAnswer) {
            playersList.getPlayer(player).addScore();
            retVal = true;
        }
        return retVal;
    }

    public static void main(String...args) {
        try {
            Game game = Game.getInstance(GameModes.ALL_QUESTIONS_WFO, new String[]{"Anna"});
            PlayerInternal.Player[] players = game.getPlayerList();
            Question q = game.getNewQuestion();
            System.out.println(q.questionContent);
            System.out.println(game.setAnswerFromPlayer(players[0], q, 1));
            System.out.println("P:" +  players[0].getPlayerName() + "-" + players[0].getPlayerScore());
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

}
