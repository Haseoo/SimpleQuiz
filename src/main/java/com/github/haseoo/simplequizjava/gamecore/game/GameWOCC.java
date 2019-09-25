package com.github.haseoo.simplequizjava.gamecore.game;

import com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy;
import com.github.haseoo.simplequizjava.gamecore.services.IPlayerService;
import com.github.haseoo.simplequizjava.gamecore.services.IQuestionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameWOCC extends AbstractGame{
    public GameWOCC(IQuestionService questionService,
                    IPlayerService playerService,
                    FallingOutPolicy fallingOutPolicy,
                    Integer numberOfRounds) {
        super(questionService,
                playerService,
                numberOfRounds,
                fallingOutPolicy);
    }
}
