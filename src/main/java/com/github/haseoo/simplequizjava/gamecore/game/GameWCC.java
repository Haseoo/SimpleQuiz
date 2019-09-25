package com.github.haseoo.simplequizjava.gamecore.game;

import com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.Question;
import com.github.haseoo.simplequizjava.gamecore.services.IPlayerService;
import com.github.haseoo.simplequizjava.gamecore.services.IQuestionService;
import com.github.haseoo.simplequizjava.gamecore.views.CategoryView;
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
    public CategoryView[] getAvailableCategoriesIndexes() {
        return getQuestionService().getAvailableCategoryIndexes();
    }

    @Override
    public QuestionView getNextQuestion(Integer categoryIndex) {
        incrementCurrentRound();
        Question question = getQuestionService().getRandomQuestion(categoryIndex);
        setCurrentQuestion(question);
        return QuestionView.of(question);
    }

    @Override
    public QuestionView getNextQuestion() {
        throw new UnsupportedOperationException();
    }
}
