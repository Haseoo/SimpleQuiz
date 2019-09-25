package com.github.haseoo.simplequizjava.gui.controllers;

import com.github.haseoo.simplequizjava.gamecore.game.IGame;
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

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.QUESTION_PANE_FXML_PATH;
import static com.github.haseoo.simplequizjava.gui.utilities.Constants.SCORE_TABLE_FXML_PATH;
import static com.github.haseoo.simplequizjava.gui.utilities.Utilities.getResourceURL;


@RequiredArgsConstructor
public class GamePlayControllerWOCC {
    private final MenuItem playerResignMenuItem;
    private final GameInitializer gameInitializer;
    private final ScrollPane scrollPane;

    @FXML
    private Label roundNumber;
    @FXML
    private Button nextButton;
    @FXML
    private StackPane contentPane;

    private IGame game;
    private Node currentQuestionPane;
    private PlayerList playerList;
    private QuestionView currentQuestion;

    @FXML
    private void initialize() throws IOException {
        playerResignMenuItem.setOnAction(this::onResign);
        game = gameInitializer.buildGameWOCC();
        playerList = new PlayerList(game.getPlayers());
        nextAction();
    }

    @FXML
    private void onNext() throws IOException {
        nextButton.setDisable(true);
        removeQuestionPane();
        nextAction();
    }

    private void displayQuestionForPlayer(QuestionView question, PlayerInfo player) throws IOException {
        playerResignMenuItem.setDisable(false);
        FXMLLoader questionFXML = new FXMLLoader(getResourceURL(QUESTION_PANE_FXML_PATH));
        questionFXML.setController(new QuestionController(player, question, this::onAnswer));
        currentQuestionPane = questionFXML.load();
        contentPane.getChildren().add(currentQuestionPane);
    }

    private void onResign(ActionEvent actionEvent) {
        game.markPlayerAsLost(playerList.getCurrentPlayer());
        removeQuestionPane();
        playerResignMenuItem.setDisable(true);
        nextButton.setDisable(false);
    }

    private void removeQuestionPane() {
        contentPane.getChildren().remove(currentQuestionPane);
    }

    private boolean onAnswer (PlayerInfo player, Integer answerIndex) {
        nextButton.setDisable(false);
        return game.answerCurrentQuestion(answerIndex, player);
    }

    private void nextAction() throws IOException {
        if (playerList.willIterationOccur()){
            if (game.isGameEnded()) {
                printResults();
                return;
            }
            currentQuestion = game.getNextQuestion();
        }
        playerList.setNext();
        displayQuestionForPlayer(currentQuestion, playerList.getCurrentPlayer());
    }

    private void printResults() throws IOException {
        playerResignMenuItem.setDisable(true);
        FXMLLoader scoreTableFXML = new FXMLLoader(getResourceURL(SCORE_TABLE_FXML_PATH));
        scoreTableFXML.setController(new ScoreTableController(game.getPlayersScores()));
        scrollPane.setContent(scoreTableFXML.load());
    }
}
