package com.github.haseoo.simplequizjava.gamecore.game;

import com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy;
import com.github.haseoo.simplequizjava.gamecore.projections.players.Player;
import com.github.haseoo.simplequizjava.gamecore.services.IPlayerService;
import com.github.haseoo.simplequizjava.gamecore.services.IQuestionService;
import com.github.haseoo.simplequizjava.gamecore.views.QuestionView;

public class GameWCC extends  AbstractGame implements IGameWCC{
    public GameWCC(IQuestionService questionService,
                    IPlayerService playerService,
                    FallingOutPolicy fallingOutPolicy,
                    Integer numberOfRounds) {
        super(questionService,
                playerService,
                numberOfRounds,
                fallingOutPolicy);
    }
    @Override
    public boolean answerCurrentQuestion(Integer answerIndex, Player.PlayerInfo answeringPlayer) {
        return false;
    }

    @Override
    public Integer[] getAvailableCategoriesIndexes() {
        return getQuestionService().getAvailableCategoryIndexes();
    }

    @Override
    public QuestionView getNextQuestion(Integer categoryIndex) {
        return QuestionView.of(getQuestionService().getRandomQuestion(categoryIndex));
    }

    @Override
    public QuestionView getNextQuestion() {
        throw new UnsupportedOperationException();
    }
}
