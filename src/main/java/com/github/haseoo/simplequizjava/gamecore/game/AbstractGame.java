package com.github.haseoo.simplequizjava.gamecore.game;

import com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy;
import com.github.haseoo.simplequizjava.gamecore.projections.players.Player.PlayerInfo;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.Question;
import com.github.haseoo.simplequizjava.gamecore.services.IPlayerService;
import com.github.haseoo.simplequizjava.gamecore.services.IQuestionService;
import com.github.haseoo.simplequizjava.gamecore.views.QuestionView;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy.WITHOUT_PLAYERS_FALLING_OUT;
import static com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy.WITH_PLAYERS_FALLING_OUT;
import static com.github.haseoo.simplequizjava.gamecore.utility.Constants.DEFAULT_SCORE_INCREMENT_VALUE;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
abstract class AbstractGame implements IGame {

    private static final Map<FallingOutPolicy, BiConsumer<IPlayerService, PlayerInfo>> fallingOutAction;

    static {
        fallingOutAction = new EnumMap<>(FallingOutPolicy.class);
        fallingOutAction.put(WITH_PLAYERS_FALLING_OUT, (IPlayerService::setPlayerLost));
        fallingOutAction.put(WITHOUT_PLAYERS_FALLING_OUT, (s, p) -> {
        });
    }

    @Getter(AccessLevel.PROTECTED)
    private final IQuestionService questionService;
    private final IPlayerService playerService;
    private final Integer numberOfRounds;
    private final FallingOutPolicy fallingOutPolicy;

    private int currentRound;
    @Setter(AccessLevel.PROTECTED)
    private Question currentQuestion;

    @Override
    public Integer getCurrentRound() {
        return currentRound;
    }

    @Override
    public PlayerInfo[] getPlayers() {
        return playerService.getPlayerList().toArray(new PlayerInfo[0]);
    }

    @Override
    public boolean isGameEnded() {
        return playerService.isAllPlayerLost() || playedAllRounds();
    }

    @Override
    public QuestionView getNextQuestion() {
        incrementCurrentRound();
        currentQuestion = questionService.getRandomQuestion();
        return QuestionView.of(currentQuestion);
    }

    @Override
    public Map<PlayerInfo, Integer> getPlayersScores() {
        return playerService
                .getPlayerList()
                .stream()
                .collect(Collectors.toMap(playerInfo -> playerInfo, PlayerInfo::getScore));
    }

    @Override
    public boolean answerCurrentQuestion(Integer answerIndex, PlayerInfo answeringPlayer) {
        boolean isCorrectAnswer = checkAnswer(answerIndex);
        if (isCorrectAnswer) {
            playerService.addPointsToPlayerScore(answeringPlayer, DEFAULT_SCORE_INCREMENT_VALUE);
        } else {
            fallingOutAction.get(fallingOutPolicy).accept(playerService, answeringPlayer);
        }
        return isCorrectAnswer;
    }

    @Override
    public void markPlayerAsLost(PlayerInfo player) {
        playerService.setPlayerLost(player);
    }

    @Override
    public QuestionView getCurrentQuestion() {
        return QuestionView.of(currentQuestion);
    }

    protected int incrementCurrentRound() {
        return currentRound++;
    }

    private boolean checkAnswer(Integer answerIndex) {
        return currentQuestion.getCorrectAnswerIndex().equals(answerIndex);
    }

    private boolean playedAllRounds() {
        return currentRound >= numberOfRounds;
    }
}
