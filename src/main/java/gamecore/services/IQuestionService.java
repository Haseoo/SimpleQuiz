package gamecore.services;

import gamecore.projections.questions.QuestionCoords;

public interface IQuestionService {
    Integer getNumberOfAvailableQuestions();
    public QuestionCoords getRandomQuestion();
    QuestionCoords getRandomQuestion(Integer categoryIndex);
    public Integer[] getAvailableCategoryIndexes();
}
