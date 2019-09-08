package com.github.haseoo.simplequizjava.gamecore.services;

import com.github.haseoo.simplequizjava.gamecore.projections.questions.Question;

public interface IQuestionService {
    Integer getNumberOfAvailableQuestions();
    Question getRandomQuestion();
    Question getRandomQuestion(Integer categoryIndex);
    Integer[] getAvailableCategoryIndexes();
}
