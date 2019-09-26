package com.github.haseoo.simplequizjava.gamecore.game;

import com.github.haseoo.simplequizjava.gamecore.projections.players.Player.PlayerInfo;
import com.github.haseoo.simplequizjava.gamecore.views.QuestionView;

import java.util.Map;

public interface IGame {
    PlayerInfo[] getPlayers();

    boolean isGameEnded();

    QuestionView getNextQuestion();

    boolean answerCurrentQuestion(Integer answerIndex, PlayerInfo answeringPlayer);

    Map<PlayerInfo, Integer> getPlayersScores();

    Integer getCurrentRound();

    void markPlayerAsLost(PlayerInfo player);

    QuestionView getCurrentQuestion();
}
