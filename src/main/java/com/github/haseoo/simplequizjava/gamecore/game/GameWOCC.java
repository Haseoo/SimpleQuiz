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
import static com.github.haseoo.simplequizjava.gamecore.utility.Constants.CATEGORIES_LIST_FILE_DEFAULT_PATH;

@Slf4j
public class GameWOCC extends AbstractGame{
    public GameWOCC(IQuestionService questionService,
                    IPlayerService playerService,
                    FallingOutPolicy fallingOutPolicy,
                    Integer numberOfRounds) {
        super(questionService,
                playerService,
                numberOfRounds,
                fallingOutPolicy);
    }

    public static void main(String...args) throws RepositoryInitalizationException {
        List<String> testPlayers = Arrays.asList("Anna", "Wanna");
        QuestionService questionService = new QuestionService(new QuestionRepository(CATEGORIES_LIST_FILE_DEFAULT_PATH));
        IGame game = new GameWOCC(questionService,
                new PlayerService(testPlayers),
                WITH_PLAYERS_FALLING_OUT,
                questionService.getNumberOfAvailableQuestions());
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
