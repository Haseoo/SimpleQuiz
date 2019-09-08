package com.github.haseoo.simplequizjava.gamecore.game;

import com.github.haseoo.simplequizjava.exceptions.gamecore.repositories.RepositoryInitalizationException;
import com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy;
import com.github.haseoo.simplequizjava.gamecore.projections.players.Player;
import com.github.haseoo.simplequizjava.gamecore.repositories.QuestionRepository;
import com.github.haseoo.simplequizjava.gamecore.services.IPlayerService;
import com.github.haseoo.simplequizjava.gamecore.services.IQuestionService;
import com.github.haseoo.simplequizjava.gamecore.services.PlayerService;
import com.github.haseoo.simplequizjava.gamecore.services.QuestionService;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

import static com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy.WITH_PLAYERS_FALLING_OUT;

@Slf4j
public class GameAllQuestions extends AbstractGame {

    public GameAllQuestions(IQuestionService questionService,
                            IPlayerService playerService,
                            FallingOutPolicy fallingOutPolicy) {
        super(questionService,
                playerService,
                questionService.getNumberOfAvailableQuestions(),
                fallingOutPolicy);
    }

    public static void main(String...args) throws RepositoryInitalizationException {
        List<String> testPlayers = Arrays.asList("Anna", "Wanna");
        IGame game = new GameAllQuestions(new QuestionService(new QuestionRepository()),
                                             new PlayerService(testPlayers),
                                             WITH_PLAYERS_FALLING_OUT);
        Player.PlayerInfo[] players = game.getPlayers();
        while (!game.isGameEnded()) {
            log.info("---------");
            log.info(game.getNextQuestion().toString());
            Arrays.stream(players)
                    .filter(Player.PlayerInfo::isPlaying)
                    .forEach(playerInfo -> game.answerCurrentQuestion(playerInfo.getId(), playerInfo));
        }
        game.getPlayersScores()
                .forEach((playerInfo, score) -> log.info(String
                                                .format("%s:%d%n", playerInfo.getName(), score)));
    }
}
