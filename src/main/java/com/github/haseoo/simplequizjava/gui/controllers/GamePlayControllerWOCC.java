package com.github.haseoo.simplequizjava.gui.controllers;

import com.github.haseoo.simplequizjava.gui.utilities.GameInitializer;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class GamePlayControllerWOCC extends AbstractGamePlayController{

    public GamePlayControllerWOCC(GameInitializer gameInitializer,
                                  MenuItem playerResignMenuItem,
                                  ScrollPane scrollPane) {
        super(playerResignMenuItem, scrollPane, gameInitializer.buildGameWOCC());
    }
    @Override
    protected void nextAction() throws IOException {
        if (getAnswerPlayerList().willIterationOccur()){
            if (getGame().isGameEnded()) {
                printResults();
                return;
            }
            getGame().getNextQuestion();
            getRoundNumber().setText(getGame().getCurrentRound().toString());
        }
        getAnswerPlayerList().setNext();
        displayQuestionForPlayer(getGame().getCurrentQuestion(), getAnswerPlayerList().getCurrentPlayer());
    }
}
