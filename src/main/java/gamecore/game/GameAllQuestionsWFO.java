package gamecore.game;

import exceptions.repositories.RepositoryInitalizationException;
import gamecore.game.enums.FallingOutPolicy;
import gamecore.projections.players.Player.PlayerInfo;
import gamecore.repositories.QuestionRepository;
import gamecore.services.IPlayerService;
import gamecore.services.IQuestionService;
import gamecore.services.PlayerService;
import gamecore.services.QuestionService;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static gamecore.game.enums.FallingOutPolicy.WITHOUT_PLAYERS_FALLING_OUT;
import static gamecore.game.enums.FallingOutPolicy.WITH_PLAYERS_FALLING_OUT;
import static gamecore.utility.Constants.DEFAULT_SCORE_INCREMENT_VALUE;

@Slf4j
public class GameAllQuestionsWFO extends AbstractGame {

    private static final Map<FallingOutPolicy, BiConsumer<IPlayerService, PlayerInfo>> fallingOutAction;

    static {
        fallingOutAction = new HashMap<>();
        fallingOutAction.put(WITH_PLAYERS_FALLING_OUT, (IPlayerService::setPlayerLost));
        fallingOutAction.put(WITHOUT_PLAYERS_FALLING_OUT, (s, p) -> {});
    }

    private FallingOutPolicy fallingOutPolicy;

    public GameAllQuestionsWFO(IQuestionService questionService,
                               IPlayerService playerService,
                               FallingOutPolicy fallingOutPolicy) {
        super(questionService,
                playerService,
                questionService.getNumberOfAvailableQuestions());
        this.fallingOutPolicy = fallingOutPolicy;
    }

    @Override
    public boolean answerCurrentQuestion(Integer answerIndex, PlayerInfo answeringPlayer) {
        boolean isCorrectAnswer = checkAnswer(answerIndex);
        if (isCorrectAnswer) {
            getPlayerService().addPointsToPlayerScore(answeringPlayer, DEFAULT_SCORE_INCREMENT_VALUE);
        } else {
            fallingOutAction.get(fallingOutPolicy).accept(getPlayerService(), answeringPlayer);
        }
        return isCorrectAnswer;
    }

    public static void main(String...args) throws RepositoryInitalizationException {
        List<String> testPlayers = Arrays.asList("Anna", "Wanna");
        IGame game = new GameAllQuestionsWFO(new QuestionService(new QuestionRepository()),
                                             new PlayerService(testPlayers),
                                             WITH_PLAYERS_FALLING_OUT);
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
