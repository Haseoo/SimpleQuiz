package gamecore.repositories;

import gamecore.projections.questions.Question;
import gamecore.projections.questions.QuestionCoords;

public interface IQuestionRepository {
    Question getQuestionByCoords(QuestionCoords questionCoords);
    Integer getNumberOfCategories();
    Integer getNumberOfQuestionInCategory(Integer categoryIndex);
}
