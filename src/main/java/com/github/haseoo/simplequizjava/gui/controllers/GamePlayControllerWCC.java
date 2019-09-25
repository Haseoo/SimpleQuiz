package com.github.haseoo.simplequizjava.gui.controllers;

import com.github.haseoo.simplequizjava.gamecore.game.IGameWCC;
import com.github.haseoo.simplequizjava.gamecore.projections.players.Player.PlayerInfo;
import com.github.haseoo.simplequizjava.gamecore.views.QuestionView;
import com.github.haseoo.simplequizjava.gui.utilities.GameInitializer;
import com.github.haseoo.simplequizjava.gui.utilities.PlayerList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.function.IntConsumer;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.*;
import static com.github.haseoo.simplequizjava.gui.utilities.Utilities.getResourceURL;


@RequiredArgsConstructor
public class GamePlayControllerWCC {
    private final MenuItem playerResignMenuItem;
    private final GameInitializer gameInitializer;
    private final ScrollPane scrollPane;

    @FXML
    private Label roundNumber;
    @FXML
    private Button nextButton;
    @FXML
    private StackPane contentPane;

    private IGameWCC game;
    private Node currentQuestionPane;
    private Node choosingCategoryPane;
    private PlayerList answerPlayerList;
    private PlayerList categoryPlayerList;
    private QuestionView currentQuestion;
    private boolean chooseCategory = true;

    @FXML
    private void initialize() throws IOException {
        playerResignMenuItem.setOnAction(this::onResign);
        game = gameInitializer.buildGameWCC();
        answerPlayerList = new PlayerList(game.getPlayers());
        categoryPlayerList = new PlayerList(game.getPlayers());
        nextAction();
    }

    @FXML
    private void onNext() throws IOException {
        removeCategoryPane();
        removeQuestionPane();
        nextAction();
    }

    private void displayQuestionForPlayer(QuestionView question, PlayerInfo player) throws IOException {
        playerResignMenuItem.setDisable(false);
        FXMLLoader questionFXML = new FXMLLoader(getResourceURL(QUESTION_PANE_FXML_PATH));
        questionFXML.setController(new QuestionController(player, question, this::onAnswer));
        currentQuestionPane = questionFXML.load();
        addQuestionPane();
    }


    private void onResign(ActionEvent actionEvent) {
        game.markPlayerAsLost(answerPlayerList.getCurrentPlayer());
        removeQuestionPane();
        playerResignMenuItem.setDisable(true);
        nextButton.setDisable(false);
    }

    private void addQuestionPane() {
        contentPane.getChildren().add(currentQuestionPane);
    }

    private void removeQuestionPane() {
        contentPane.getChildren().remove(currentQuestionPane);
    }

    private boolean onAnswer (PlayerInfo player, Integer answerIndex) {
        nextButton.setDisable(false);
        return game.answerCurrentQuestion(answerIndex, player);
    }

    private void nextAction() throws IOException {
        if (answerPlayerList.willIterationOccur() && chooseCategory) {
            if (game.isGameEnded()) {
                printResults();
            } else {
                categoryChoosing();
                chooseCategory = false;
            }
        } else {
            answerPlayerList.setNext();
            displayQuestionForPlayer(currentQuestion, answerPlayerList.getCurrentPlayer());
            chooseCategory = true;
        }
    }

    private void categoryChoosing() throws IOException {
        playerResignMenuItem.setDisable(true);
        nextButton.setDisable(true);
        categoryPlayerList.setNext();
        IntConsumer onCategoryApply = (categoryIndex) -> {
            currentQuestion = game.getNextQuestion(categoryIndex);
            nextButton.setDisable(false);
        };
        FXMLLoader categoryChoose = new FXMLLoader(getResourceURL(CATEGORY_WINDOW_FXML_PATH));
        categoryChoose.setController(new CategoryController(game,
                                                            onCategoryApply,
                                                            categoryPlayerList.getCurrentPlayer()));
        choosingCategoryPane = categoryChoose.load();
        roundNumber.setText(Integer.toString(calculateRoundNumber()));
        addCategoryPane();
    }

    private int calculateRoundNumber() {
        return game.getCurrentRound() + 1;
    }

    private void printResults() throws IOException {
        playerResignMenuItem.setDisable(true);
        FXMLLoader scoreTableFXML = new FXMLLoader(getResourceURL(SCORE_TABLE_FXML_PATH));
        scoreTableFXML.setController(new ScoreTableController(game.getPlayersScores()));
        scrollPane.setContent(scoreTableFXML.load());
    }

    private void addCategoryPane() {
        contentPane.getChildren().add(choosingCategoryPane);
    }

    private void removeCategoryPane() {
        contentPane.getChildren().remove(choosingCategoryPane);
    }
}
