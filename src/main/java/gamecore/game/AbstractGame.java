package gamecore.game;

import gamecore.projections.players.Player.PlayerInfo;
import gamecore.projections.questions.Question;
import gamecore.services.IPlayerService;
import gamecore.services.IQuestionService;
import gamecore.views.QuestionView;
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
    public PlayerInfo[] getPlayers() {
        return playerService.getPlayerList().toArray(new PlayerInfo[0]);
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
    public Map<PlayerInfo, Integer> getPlayersScores() {
        return playerService
                .getPlayerList()
                .stream()
                .collect(Collectors.toMap(playerInfo -> playerInfo, PlayerInfo::getScore));
    }

    protected boolean checkAnswer(Integer answerIndex) {
        return currentQuestion.getCorrectAnswerIndex().equals(answerIndex);
    }

    private boolean playedAllRounds() {
        return currentRound >= numberOfRounds;
    }
}
