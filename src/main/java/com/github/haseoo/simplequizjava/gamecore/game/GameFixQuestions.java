package com.github.haseoo.simplequizjava.gamecore.game;

import com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy;
import com.github.haseoo.simplequizjava.gamecore.services.IPlayerService;
import com.github.haseoo.simplequizjava.gamecore.services.IQuestionService;

public class GameFixQuestions extends AbstractGame{
    public GameFixQuestions(IQuestionService questionService,
                            IPlayerService playerService,
                            FallingOutPolicy fallingOutPolicy,
                            Integer numberOfRounds) {
        super(questionService,
                playerService,
                numberOfRounds,
                fallingOutPolicy);
    }
}
