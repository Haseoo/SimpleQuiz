package com.github.haseoo.simplequizjava.gamecore.services;

import com.github.haseoo.simplequizjava.gamecore.projections.questions.Question;
import com.github.haseoo.simplequizjava.gamecore.views.CategoryView;

public interface IQuestionService {
    Integer getNumberOfAvailableQuestions();
    Question getRandomQuestion();
    Question getRandomQuestion(Integer categoryIndex);
    CategoryView[] getAvailableCategoryIndexes();
}
