package gamecore.game;

import exceptions.repositories.RepositoryInitalizationException;
import gamecore.projections.players.Player.PlayerInfo;
import gamecore.repositories.QuestionRepository;
import gamecore.services.IPlayerService;
import gamecore.services.IQuestionService;
import gamecore.services.PlayerService;
import gamecore.services.QuestionService;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

import static gamecore.utility.Constants.DEFAULT_SCORE_INCREMENT_VALUE;

@Slf4j
public class GameAllQuestionsWFO extends AbstractGame {

    public GameAllQuestionsWFO(IQuestionService questionService,
                               IPlayerService playerService) {
        super(questionService,
                playerService,
                questionService.getNumberOfAvailableQuestions());
    }

    @Override
    public boolean answerCurrentQuestion(Integer answerIndex, PlayerInfo answeringPlayer) {
        boolean isCorrectAnswer = checkAnswer(answerIndex);
        if (isCorrectAnswer) {
            getPlayerService().addPointsToPlayerScore(answeringPlayer, DEFAULT_SCORE_INCREMENT_VALUE);
        } else {
            getPlayerService().setPlayerLost(answeringPlayer);
        }
        return isCorrectAnswer;
    }

    public static void main(String...args) throws RepositoryInitalizationException {
        List<String> testPlayers = Arrays.asList("Anna", "Wanna");
        IGame game = new GameAllQuestionsWFO(new QuestionService(new QuestionRepository()),
                                         new PlayerService(testPlayers));
        PlayerInfo[] players = game.getPlayers();
        while (!game.isGameEnded()) {
            log.info("---------");
            log.info(game.getNextQuestion().toString());
            Arrays.stream(players)
                    .filter(PlayerInfo::isPlaying)
                    .forEach(playerInfo -> game.answerCurrentQuestion(playerInfo.getId(), playerInfo));
        }
        game.getPlayersScores()
                .forEach((playerInfo, score) -> log.info(String
                                                .format("%s:%d%n", playerInfo.getName(), score)));
    }
}
