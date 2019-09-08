package com.github.haseoo.simplequizjava.gamecore.repositories;

import com.github.haseoo.simplequizjava.gamecore.projections.questions.Question;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.QuestionCoords;

public interface IQuestionRepository {
    Question getQuestionByCoords(QuestionCoords questionCoords);
    Integer getNumberOfCategories();
    Integer getNumberOfQuestionInCategory(Integer categoryIndex);
}
