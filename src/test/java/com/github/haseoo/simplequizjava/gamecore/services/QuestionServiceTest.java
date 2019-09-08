package com.github.haseoo.simplequizjava.gamecore.services;

import com.github.haseoo.simplequizjava.exceptions.gamecore.questions.UnableToDrawQuestionException;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.Question;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.QuestionCoords;
import com.github.haseoo.simplequizjava.gamecore.repositories.QuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static com.github.haseoo.simplequizjava.testutils.Constants.*;
import static com.github.haseoo.simplequizjava.testutils.QuestionRepositoryTestDataGenerator.generateFirstQuestion;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class QuestionServiceTest {
    @InjectMocks
    private QuestionService sut;

    @Mock
    private QuestionRepository questionRepository = mock(QuestionRepository.class, RETURNS_DEEP_STUBS);

    @BeforeEach
    void beforeEach() {
        when(questionRepository.getNumberOfCategories()).thenReturn(NUMBER_OF_TEST_CATEGORIES);
        when(questionRepository.getNumberOfQuestionInCategory(any())).thenReturn(NUMBER_OF_TEST_QUESTION);
        sut = new QuestionService(questionRepository);
    }

    @Test
    void should_return_three_available_questions() {
        //given & when & then
        Assertions.assertThat(sut.getNumberOfAvailableQuestions()).isEqualTo(NUMBER_OF_TEST_CATEGORIES * NUMBER_OF_TEST_QUESTION);
    }

    @Test
    void should_return_random_question() {
        //given
        Question question = generateFirstQuestion();
        when(questionRepository.getQuestionByCoords(any())).thenReturn(question);
        //when & then
        Assertions.assertThat(sut.getRandomQuestion()).isEqualTo(question);
    }

    @Test
    void should_return_random_question_with_category() {
        //given
        ArgumentCaptor<QuestionCoords> captor = ArgumentCaptor.forClass(QuestionCoords.class);
        //when
        sut.getRandomQuestion(TEST_CATEGORY_INDEX);
        verify(questionRepository).getQuestionByCoords(captor.capture());
        //then
        Assertions.assertThat(captor.getValue().getCategoryIndex()).isEqualTo(TEST_CATEGORY_INDEX);
    }

    @Test
    void should_result_two_available_categories() {
        //given
        sut.getRandomQuestion();
        //when & then
        Assertions.assertThat(sut.getAvailableCategoryIndexes().length).isEqualTo(NUMBER_OF_TEST_CATEGORIES - 1);
    }

    @Test
    void should_throw_exception_when_drawing_fourth_question() {
        //give
        for(int i = 0; i < 3; i++) {
            sut.getRandomQuestion();
        }
        //when
        Assertions.assertThatThrownBy(() -> sut.getRandomQuestion()).isInstanceOf(UnableToDrawQuestionException.class);
        Assertions.assertThatThrownBy(() -> sut.getRandomQuestion(TEST_CATEGORY_INDEX)).isInstanceOf(UnableToDrawQuestionException.class);

    }

}