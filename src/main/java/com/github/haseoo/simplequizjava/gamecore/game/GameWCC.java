package com.github.haseoo.simplequizjava.gamecore.game;

import com.github.haseoo.simplequizjava.gamecore.projections.players.Player;
import com.github.haseoo.simplequizjava.gamecore.services.IPlayerService;
import com.github.haseoo.simplequizjava.gamecore.services.IQuestionService;
import com.github.haseoo.simplequizjava.gamecore.views.QuestionView;

import static com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy.WITHOUT_PLAYERS_FALLING_OUT;

public class GameWCC extends  AbstractGame implements IGameWCC{
    public GameWCC(IQuestionService questionService,
                            IPlayerService playerService) {
        super(questionService,
                playerService,
                questionService.getNumberOfAvailableQuestions(),
                WITHOUT_PLAYERS_FALLING_OUT);
    }
    @Override
    public boolean answerCurrentQuestion(Integer answerIndex, Player.PlayerInfo answeringPlayer) {
        return false;
    }

    @Override
    public Integer[] getAvailableCategoriesIndexes() {
        return new Integer[0];
    }

    @Override
    public QuestionView getNextQuestion(Integer categoryIndex) {
        return null;
    }

    @Override
    public QuestionView getNextQuestion() {
        throw new UnsupportedOperationException();
    }
}
