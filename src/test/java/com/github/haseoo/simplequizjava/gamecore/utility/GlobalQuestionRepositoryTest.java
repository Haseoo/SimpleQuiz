package com.github.haseoo.simplequizjava.gamecore.utility;

import com.github.haseoo.simplequizjava.exceptions.gamecore.repositories.RepositoryInitalizationException;
import com.github.haseoo.simplequizjava.exceptions.gamecore.repositories.UninitializedRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.haseoo.simplequizjava.testutils.Constants.*;
import static com.github.haseoo.simplequizjava.testutils.QuestionRepositoryTestDataGenerator.generateQuestionCoords;

class GlobalQuestionRepositoryTest {
    @BeforeEach
    void beforeEach() {
        GlobalQuestionRepository.clearRepository();
    }
    @Test
    void should_throw_exception_when_get_questions_with_uninitialized_repository() {
        //given & when & then
        Assertions
                .assertThatThrownBy(() -> GlobalQuestionRepository.getQuestionByCoords(generateQuestionCoords()))
                .isInstanceOf(UninitializedRepository.class);
    }

    @Test
    void should_throw_exception_when_get_number_of_category_uninitialized_repository() {
        //given & when & then
        Assertions
                .assertThatThrownBy(() -> GlobalQuestionRepository.getNumberOfCategories())
                .isInstanceOf(UninitializedRepository.class);
    }

    @Test
    void should_throw_exception_when_get_number_of_question_uninitialized_repository() {
        //given & when & then
        Assertions
                .assertThatThrownBy(() -> GlobalQuestionRepository.getNumberOfQuestionInCategory(TEST_CATEGORY_INDEX))
                .isInstanceOf(UninitializedRepository.class);
    }

    @Test
    void should_throw_exception_when_path_not_found() {
        //given & when & then
        Assertions
                .assertThatThrownBy(() -> GlobalQuestionRepository
                        .initRepository(NOT_EXISTENT_CATEGORY_LIST_PATH, DO_REINITIALIZATION))
                .isInstanceOf(RepositoryInitalizationException.class);
    }

}