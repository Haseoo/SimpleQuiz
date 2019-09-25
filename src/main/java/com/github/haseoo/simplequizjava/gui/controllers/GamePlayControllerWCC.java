package com.github.haseoo.simplequizjava.gui.controllers;

import com.github.haseoo.simplequizjava.gamecore.game.IGameWCC;
import com.github.haseoo.simplequizjava.gui.utilities.GameInitializer;
import com.github.haseoo.simplequizjava.gui.utilities.PlayerList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.util.function.IntConsumer;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.CATEGORY_WINDOW_FXML_PATH;
import static com.github.haseoo.simplequizjava.gui.utilities.Utilities.getResourceURL;

public class GamePlayControllerWCC extends AbstractGamePlayController {

    private final IGameWCC game;
    private final PlayerList categoryPlayerList;
    private Node choosingCategoryPane;
    private boolean chooseCategory = true;

    public GamePlayControllerWCC(GameInitializer gameInitializer,
                                 MenuItem playerResignMenuItem,
                                 ScrollPane scrollPane) {
        super(playerResignMenuItem,
                scrollPane,
                gameInitializer.buildGameWCC());
        game = (IGameWCC) getGame();
        categoryPlayerList = new PlayerList(game.getPlayers());
    }

    @FXML
    @Override
    protected void onNext() throws IOException {
        removeCategoryPane();
        super.onNext();
    }

    @Override
    protected void nextAction() throws IOException {
        if (getAnswerPlayerList().willIterationOccur() && chooseCategory) {
            if (game.isGameEnded()) {
                printResults();
            } else {
                categoryChoosing();
                chooseCategory = false;
            }
        } else {
            getAnswerPlayerList().setNext();
            displayQuestionForPlayer(game.getCurrentQuestion(), getAnswerPlayerList().getCurrentPlayer());
            chooseCategory = true;
        }
    }

    private void categoryChoosing() throws IOException {
        getPlayerResignMenuItem().setDisable(true);
        getNextButton().setDisable(true);
        categoryPlayerList.setNext();
        IntConsumer onCategoryApply = (categoryIndex) -> {
            game.getNextQuestion(categoryIndex);
            getNextButton().setDisable(false);
        };
        FXMLLoader categoryChoose = new FXMLLoader(getResourceURL(CATEGORY_WINDOW_FXML_PATH));
        categoryChoose.setController(new CategoryController(game,
                                                            onCategoryApply,
                                                            categoryPlayerList.getCurrentPlayer()));
        choosingCategoryPane = categoryChoose.load();
        getRoundNumber().setText(Integer.toString(calculateRoundNumber()));
        addCategoryPane();
    }

    private int calculateRoundNumber() {
        return game.getCurrentRound() + 1;
    }

    private void addCategoryPane() {
        getContentPane().getChildren().add(choosingCategoryPane);
    }

    private void removeCategoryPane() {
        getContentPane().getChildren().remove(choosingCategoryPane);
    }
}
