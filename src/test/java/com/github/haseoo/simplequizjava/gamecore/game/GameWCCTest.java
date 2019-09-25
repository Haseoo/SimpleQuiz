package com.github.haseoo.simplequizjava.gamecore.game;

import com.github.haseoo.simplequizjava.gamecore.services.IPlayerService;
import com.github.haseoo.simplequizjava.gamecore.services.IQuestionService;
import com.github.haseoo.simplequizjava.gamecore.services.PlayerService;
import com.github.haseoo.simplequizjava.gamecore.services.QuestionService;
import com.github.haseoo.simplequizjava.gamecore.views.CategoryView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy.WITHOUT_PLAYERS_FALLING_OUT;
import static com.github.haseoo.simplequizjava.testutils.Constants.NUMBER_OF_ROUNDS;
import static com.github.haseoo.simplequizjava.testutils.Constants.TEST_CATEGORY_INDEX;
import static com.github.haseoo.simplequizjava.testutils.PlayerServiceTestDataGenerator.generatePlayerList;
import static com.github.haseoo.simplequizjava.testutils.QuestionRepositoryTestDataGenerator.generateFirstQuestion;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class GameWCCTest {
    @InjectMocks
    private GameWCC sut;

    @Mock
    private IPlayerService playerService = mock(PlayerService.class, RETURNS_MOCKS);
    @Mock
    private IQuestionService questionService = mock(QuestionService.class, RETURNS_MOCKS);

    @BeforeEach
    void setUp() {
        when(playerService.getPlayerList()).thenReturn(generatePlayerList());
        when(questionService.getRandomQuestion(any())).thenReturn(generateFirstQuestion());
        sut = new GameWCC(questionService, playerService, WITHOUT_PLAYERS_FALLING_OUT, NUMBER_OF_ROUNDS);
    }


    @Test
    void should_get_available_categories() {
        //given & when
        CategoryView[] categoryViews = sut.getAvailableCategoriesIndexes();
        //then
        verify(questionService).getAvailableCategoryIndexes();
    }

    @Test
    void should_get_question_with_category() {
        //given & when
        sut.getNextQuestion(TEST_CATEGORY_INDEX);
        //then
        verify(questionService).getRandomQuestion(TEST_CATEGORY_INDEX);
    }

    @Test
    void should_trow_exception_when_get_question_without_category() {
        //given & when & then
        Assertions.assertThatThrownBy(() ->sut.getNextQuestion()).isInstanceOf(UnsupportedOperationException.class);
    }
}