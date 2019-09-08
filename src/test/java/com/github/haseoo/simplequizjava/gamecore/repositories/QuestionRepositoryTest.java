package com.github.haseoo.simplequizjava.gamecore.repositories;

import com.github.haseoo.simplequizjava.exceptions.gamecore.repositories.RepositoryInitalizationException;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.Question;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.QuestionCoords;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.haseoo.simplequizjava.testutils.Constants.*;
import static com.github.haseoo.simplequizjava.testutils.QuestionRepositoryTestDataGenerator.generateFirstQuestion;
import static com.github.haseoo.simplequizjava.testutils.QuestionRepositoryTestDataGenerator.generateQuestionCoords;

class QuestionRepositoryTest {

    private QuestionRepository sut;

    @BeforeEach
    void beforeEach() throws RepositoryInitalizationException {
        sut = new QuestionRepository();
    }

    @Test
    void should_return_question() {
        //given
        QuestionCoords testQuestionCoords = generateQuestionCoords();
        Question testQuestion = generateFirstQuestion();
        Assertions.assertThat(sut.getQuestionByCoords(testQuestionCoords)).isEqualTo(testQuestion);
    }

    @Test
    void should_return_number_of_test_categories() {
        //given & when & then
        Assertions.assertThat(sut.getNumberOfCategories()).isEqualTo(NUMBER_OF_TEST_QUESTION);
    }

    @Test
    void should_return_number_of_question_in_test_category() {
        //given & when & then
        Assertions.assertThat(sut.getNumberOfQuestionInCategory(TEST_CATEGORY_INDEX)).isEqualTo(NUMBER_OF_TEST_CATEGORIES);
    }
}