package com.github.haseoo.simplequizjava.gui.controllers;

import com.github.haseoo.simplequizjava.exceptions.gui.FXMLLoadException;
import com.github.haseoo.simplequizjava.gamecore.projections.players.Player.PlayerInfo;
import com.github.haseoo.simplequizjava.gamecore.views.QuestionView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.*;
import static com.github.haseoo.simplequizjava.gui.utilities.Utilities.getResourceURL;

@RequiredArgsConstructor
public class QuestionController {
    private static Map<Boolean, Node> resultPaneContent;

    static {
        try {
            resultPaneContent = new HashMap<>();
            resultPaneContent.put(true, FXMLLoader.load(getResourceURL(GOOD_ANSWER_PANE_FXML_PATH)));
            resultPaneContent.put(false, FXMLLoader.load(getResourceURL(BAD_ANSWER_PANE_FXML_PATH)));
        } catch (IOException e) {
            throw new FXMLLoadException(e);
        }
    }

    private final PlayerInfo player;
    private final QuestionView question;
    private final BiFunction<PlayerInfo, Integer, Boolean> onAnswer;

    @FXML
    private TextArea questionText;
    @FXML
    private Button applyButton;
    @FXML
    private VBox answersVBox;
    @FXML
    private StackPane resultPane;
    @FXML
    private Text playerNickname;

    private ToggleGroup answerToggleGroup;

    @FXML
    private void initialize() {
        playerNickname.setText(player.getName());
        questionText.setText(question.getContent());

        String[] answers = question.getAnswers();
        answerToggleGroup = new ToggleGroup();
        IntStream.range(BEGIN_INDEX, answers.length)
                 .forEach(index -> setupRadioButton(answers[index], index));
    }

    @FXML
    private void onApplyAnswer() {
        applyButton.setDisable(true);
        Toggle toggle = answerToggleGroup.getSelectedToggle();
        Integer answerIndex = ((toggle != null) ? (Integer)toggle.getUserData() : null);
        resultPane.getChildren().add(resultPaneContent.get((onAnswer.apply(player, answerIndex))));
    }

    private void setupRadioButton(String answer, Integer index) {
        RadioButton radioButton = new RadioButton();
        radioButton.setText(answer);
        radioButton.setToggleGroup(answerToggleGroup);
        radioButton.setUserData(index);
        answersVBox.getChildren().add(radioButton);
    }
}
