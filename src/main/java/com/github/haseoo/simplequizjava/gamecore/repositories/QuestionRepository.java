package com.github.haseoo.simplequizjava.gamecore.repositories;

import com.github.haseoo.simplequizjava.exceptions.gamecore.repositories.RepositoryInitalizationException;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.Question;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.QuestionCoords;
import com.github.haseoo.simplequizjava.gamecore.utility.GlobalQuestionRepository;
import lombok.NoArgsConstructor;

import java.util.stream.IntStream;

import static com.github.haseoo.simplequizjava.gamecore.utility.Constants.BEGIN_INDEX;
import static com.github.haseoo.simplequizjava.gamecore.utility.Constants.CATEGORIES_LIST_INITIALIZATION_DEFAULT_POLICY;

@NoArgsConstructor
public class QuestionRepository implements IQuestionRepository{
    public QuestionRepository(String categoryListFilePath) throws RepositoryInitalizationException {
        GlobalQuestionRepository.initRepository(categoryListFilePath,
                CATEGORIES_LIST_INITIALIZATION_DEFAULT_POLICY);
    }

    @Override
    public Question getQuestionByCoords(QuestionCoords questionCoords) {
        return GlobalQuestionRepository.getQuestionByCoords(questionCoords);
    }

    @Override
    public Integer getNumberOfCategories() {
        return GlobalQuestionRepository.getNumberOfCategories();
    }

    @Override
    public Integer getNumberOfQuestionInCategory(Integer categoryIndex) {
        return GlobalQuestionRepository.getNumberOfQuestionInCategory(categoryIndex);
    }

    @Override
    public Integer getTotalNumberOfQuestions() {
        IntStream numberOfCategories = IntStream.range(BEGIN_INDEX, getNumberOfCategories());
        return numberOfCategories.map(this::getNumberOfQuestionInCategory).sum();
    }
}
