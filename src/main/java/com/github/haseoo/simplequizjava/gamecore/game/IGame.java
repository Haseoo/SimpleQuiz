package com.github.haseoo.simplequizjava.gamecore.game;

import com.github.haseoo.simplequizjava.gamecore.projections.players.Player;
import com.github.haseoo.simplequizjava.gamecore.views.QuestionView;

import java.util.Map;

public interface IGame {
    Player.PlayerInfo[] getPlayers();
    boolean isGameEnded();
    QuestionView getNextQuestion();
    boolean answerCurrentQuestion(Integer answerIndex, Player.PlayerInfo answeringPlayer);
    Map<Player.PlayerInfo, Integer> getPlayersScores();
}
