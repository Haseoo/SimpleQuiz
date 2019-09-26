package com.github.haseoo.simplequizjava.gui.controllers;

import com.github.haseoo.simplequizjava.gamecore.game.IGame;
import com.github.haseoo.simplequizjava.gamecore.projections.players.Player;
import com.github.haseoo.simplequizjava.gamecore.views.QuestionView;
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
import lombok.AccessLevel;
import lombok.Getter;

import java.io.IOException;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.QUESTION_PANE_FXML_PATH;
import static com.github.haseoo.simplequizjava.gui.utilities.Constants.SCORE_TABLE_FXML_PATH;
import static com.github.haseoo.simplequizjava.gui.utilities.Utilities.getResourceURL;

@Getter(AccessLevel.PROTECTED)
public abstract class AbstractGamePlayController {
    private final MenuItem playerResignMenuItem;
    private final ScrollPane scrollPane;
    private final IGame game;
    private final PlayerList answerPlayerList;

    private Node currentQuestionPane;

    @FXML
    private Label roundNumber;
    @FXML
    private Button nextButton;
    @FXML
    private StackPane contentPane;

    public AbstractGamePlayController(MenuItem playerResignMenuItem,
                                      ScrollPane scrollPane,
                                      IGame game) {
        this.playerResignMenuItem = playerResignMenuItem;
        this.scrollPane = scrollPane;
        this.game = game;
        answerPlayerList = new PlayerList(game.getPlayers());
    }

    protected abstract void nextAction() throws IOException;

    @FXML
    public void initialize() throws IOException {
        playerResignMenuItem.setOnAction(this::onResign);
        nextAction();
    }

    @FXML
    protected void onNext() throws IOException {
        nextButton.setDisable(true);
        removeQuestionPane();
        nextAction();
    }

    protected void displayQuestionForPlayer(QuestionView question, Player.PlayerInfo player) throws IOException {
        playerResignMenuItem.setDisable(false);
        FXMLLoader questionFXML = new FXMLLoader(getResourceURL(QUESTION_PANE_FXML_PATH));
        questionFXML.setController(new QuestionController(player, question, this::onAnswer));
        currentQuestionPane = questionFXML.load();
        addQuestionPane();
    }

    protected void printResults() throws IOException {
        getPlayerResignMenuItem().setDisable(true);
        FXMLLoader scoreTableFXML = new FXMLLoader(getResourceURL(SCORE_TABLE_FXML_PATH));
        scoreTableFXML.setController(new ScoreTableController(game.getPlayersScores()));
        getScrollPane().setContent(scoreTableFXML.load());
    }

    private boolean onAnswer(Player.PlayerInfo player, Integer answerIndex) {
        nextButton.setDisable(false);
        return game.answerCurrentQuestion(answerIndex, player);
    }

    private void addQuestionPane() {
        contentPane.getChildren().add(currentQuestionPane);
    }

    private void removeQuestionPane() {
        contentPane.getChildren().remove(currentQuestionPane);
    }

    private void onResign(ActionEvent actionEvent) {
        game.markPlayerAsLost(answerPlayerList.getCurrentPlayer());
        removeQuestionPane();
        playerResignMenuItem.setDisable(true);
        nextButton.setDisable(false);
    }
}
