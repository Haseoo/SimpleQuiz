package com.github.haseoo.simplequizjava.gamecore.game;

import com.github.haseoo.simplequizjava.gamecore.projections.players.Player;
import com.github.haseoo.simplequizjava.gamecore.views.QuestionView;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.Question;
import com.github.haseoo.simplequizjava.gamecore.services.IPlayerService;
import com.github.haseoo.simplequizjava.gamecore.services.IQuestionService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractGame implements IGame{
    private final IQuestionService questionService;
    private final IPlayerService playerService;
    private final Integer numberOfRounds;
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

    protected boolean checkAnswer(Integer answerIndex) {
        return currentQuestion.getCorrectAnswerIndex().equals(answerIndex);
    }

    private boolean playedAllRounds() {
        return currentRound >= numberOfRounds;
    }
}
