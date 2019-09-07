package gamecore.game;

import gamecore.projections.players.Player.PlayerInfo;
import gamecore.views.QuestionView;

import java.util.Map;

public interface IGame {
    PlayerInfo[] getPlayers();
    boolean isGameEnded();
    QuestionView getNextQuestion();
    boolean answerCurrentQuestion(Integer answerIndex, PlayerInfo answeringPlayer);
    Map<PlayerInfo, Integer> getPlayersScores();
}
