package gamecore.services;

import gamecore.projections.questions.Question;

public interface IQuestionService {
    Integer getNumberOfAvailableQuestions();
    Question getRandomQuestion();
    Question getRandomQuestion(Integer categoryIndex);
    Integer[] getAvailableCategoryIndexes();
}
