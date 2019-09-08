package com.github.haseoo.simplequizjava.gamecore.repositories;

import com.github.haseoo.simplequizjava.exceptions.gamecore.repositories.RepositoryInitalizationException;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.Question;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.QuestionCoords;
import com.github.haseoo.simplequizjava.gamecore.utility.GlobalQuestionRepository;

import java.util.Objects;

import static com.github.haseoo.simplequizjava.gamecore.utility.Constants.CATEGORIES_LIST_FILE_DEFAULT_PATH;
import static com.github.haseoo.simplequizjava.gamecore.utility.Constants.CATEGORIES_LIST_INITIALIZATION_DEFAULT_POLICY;

public class QuestionRepository implements IQuestionRepository{
    public QuestionRepository() throws RepositoryInitalizationException {
        GlobalQuestionRepository.initRepository(getCategoryListPath(CATEGORIES_LIST_FILE_DEFAULT_PATH),
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

    private String getCategoryListPath(String relativePath) {
        return Objects.requireNonNull(getClass().getClassLoader().getResource(relativePath)).getFile();
    }
}
