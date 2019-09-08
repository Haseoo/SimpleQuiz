package com.github.haseoo.simplequizjava.gamecore.game;

import com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy;
import com.github.haseoo.simplequizjava.gamecore.projections.players.Player;
import com.github.haseoo.simplequizjava.gamecore.views.QuestionView;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.Question;
import com.github.haseoo.simplequizjava.gamecore.services.IPlayerService;
import com.github.haseoo.simplequizjava.gamecore.services.IQuestionService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy.WITHOUT_PLAYERS_FALLING_OUT;
import static com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy.WITH_PLAYERS_FALLING_OUT;
import static com.github.haseoo.simplequizjava.gamecore.utility.Constants.DEFAULT_SCORE_INCREMENT_VALUE;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractGame implements IGame{

    private static final Map<FallingOutPolicy, BiConsumer<IPlayerService, Player.PlayerInfo>> fallingOutAction;

    static {
        fallingOutAction = new EnumMap<>(FallingOutPolicy.class);
        fallingOutAction.put(WITH_PLAYERS_FALLING_OUT, (IPlayerService::setPlayerLost));
        fallingOutAction.put(WITHOUT_PLAYERS_FALLING_OUT, (s, p) -> {});
    }

    private final IQuestionService questionService;
    private final IPlayerService playerService;
    private final Integer numberOfRounds;
    private final FallingOutPolicy fallingOutPolicy;
    private int currentRound;
    private Question currentQuestion;

    @Override
    public Player.PlayerInfo[] getPlayers() {
        return playerService.getPlayerList().toArray(new Player.PlayerInfo[0]);
    }

    @Override
    public boolean isGameEnded() {
        return playerService.isAllPlayerLost() || playedAllRounds();
    }

    @Override
    public QuestionView getNextQuestion() {
        currentRound++;
        currentQuestion =  questionService.getRandomQuestion();
        return QuestionView.of(currentQuestion);
    }

    @Override
    public Map<Player.PlayerInfo, Integer> getPlayersScores() {
        return playerService
                .getPlayerList()
                .stream()
                .collect(Collectors.toMap(playerInfo -> playerInfo, Player.PlayerInfo::getScore));
    }

    @Override
    public boolean answerCurrentQuestion(Integer answerIndex, Player.PlayerInfo answeringPlayer) {
        boolean isCorrectAnswer = checkAnswer(answerIndex);
        if (isCorrectAnswer) {
            getPlayerService().addPointsToPlayerScore(answeringPlayer, DEFAULT_SCORE_INCREMENT_VALUE);
        } else {
            fallingOutAction.get(fallingOutPolicy).accept(getPlayerService(), answeringPlayer);
        }
        return isCorrectAnswer;
    }

    protected boolean checkAnswer(Integer answerIndex) {
        return currentQuestion.getCorrectAnswerIndex().equals(answerIndex);
    }

    private boolean playedAllRounds() {
        return currentRound >= numberOfRounds;
    }
}
