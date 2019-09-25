package com.github.haseoo.simplequizjava.gamecore.game;

import com.github.haseoo.simplequizjava.gamecore.projections.players.Player.PlayerInfo;
import com.github.haseoo.simplequizjava.gamecore.services.IPlayerService;
import com.github.haseoo.simplequizjava.gamecore.services.IQuestionService;
import com.github.haseoo.simplequizjava.gamecore.services.PlayerService;
import com.github.haseoo.simplequizjava.gamecore.services.QuestionService;
import com.github.haseoo.simplequizjava.gamecore.views.QuestionView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.stream.IntStream;

import static com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy.WITHOUT_PLAYERS_FALLING_OUT;
import static com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy.WITH_PLAYERS_FALLING_OUT;
import static com.github.haseoo.simplequizjava.testutils.Constants.*;
import static com.github.haseoo.simplequizjava.testutils.PlayerServiceTestDataGenerator.generatePlayerList;
import static com.github.haseoo.simplequizjava.testutils.QuestionRepositoryTestDataGenerator.generateFirstQuestion;
import static org.mockito.Mockito.*;

class GameWOCCTest {
    @InjectMocks
    private GameWOCC sut;

    @Mock
    private IPlayerService playerService = mock(PlayerService.class, RETURNS_MOCKS);
    @Mock
    private IQuestionService questionService = mock(QuestionService.class, RETURNS_MOCKS);

    @BeforeEach
    void setUp() {
        when(playerService.getPlayerList()).thenReturn(generatePlayerList());
        when(questionService.getRandomQuestion()).thenReturn(generateFirstQuestion());
        sut = new GameWOCC(questionService, playerService, WITHOUT_PLAYERS_FALLING_OUT, NUMBER_OF_ROUNDS);
    }

    @Test
    void should_return_current_round() {
        //given & when
        IntStream.range(0, NUMBER_OF_ROUNDS).forEach((i) -> sut.getNextQuestion());
        //then
        Assertions.assertThat(sut.getCurrentRound()).isEqualTo(NUMBER_OF_ROUNDS);
    }

    @Test
    void should_return_player_list() {
        //given & when & then
        Assertions.assertThat(sut.getPlayers()[0].getName()).isEqualTo(PLAYER_NICKNAME1);
    }

    @Test
    void game_should_end() {
        //given & when
        IntStream.range(0, NUMBER_OF_ROUNDS).forEach((i) -> sut.getNextQuestion());
        //then
        Assertions.assertThat(sut.isGameEnded()).isTrue();
    }

    @Test
    void should_random_question() {
        //given & when
        sut.getNextQuestion();
        //then
        verify(questionService).getRandomQuestion();
    }

    @Test
    void should_return_score_for_player() {
        //given
        PlayerInfo player = sut.getPlayers()[0];
        //when && then
        Assertions.assertThat(sut.getPlayersScores()).containsKeys(player);
    }

    @Test
    void should_return_correct_answer_result() {
        //given
        sut.getNextQuestion();
        PlayerInfo player = sut.getPlayers()[0];
        //when & then
        Assertions.assertThat(sut.answerCurrentQuestion(FIRST_TEST_QUESTION_CORRECT_ANSWER, player)).isTrue();
    }

    @Test
    void should_return_incorrect_answer_result() {
        //given
        sut.getNextQuestion();
        PlayerInfo player = sut.getPlayers()[0];
        //when & then
        Assertions.assertThat(sut.answerCurrentQuestion(FIRST_TEST_QUESTION_INCORRECT_ANSWER, player)).isFalse();
    }

    @Test
    void should_player_lost_after_incorrect_answer() {
        //given
        sut = new GameWOCC(questionService, playerService, WITH_PLAYERS_FALLING_OUT, NUMBER_OF_ROUNDS);
        sut.getNextQuestion();
        PlayerInfo player = sut.getPlayers()[0];
        //when
        sut.answerCurrentQuestion(FIRST_TEST_QUESTION_INCORRECT_ANSWER, player);
        //then
        verify(playerService).setPlayerLost(player);

    }

    @Test
    void should_mark_player_as_lost() {
        //given
        PlayerInfo player = sut.getPlayers()[0];
        //when
        sut.markPlayerAsLost(player);
        //then
        verify(playerService).setPlayerLost(player);
    }

    @Test
    void should_return_current_question() {
        //given & when
        QuestionView question = sut.getNextQuestion();
        //then
        Assertions.assertThat(sut.getCurrentQuestion()).isEqualTo(question);
    }
}