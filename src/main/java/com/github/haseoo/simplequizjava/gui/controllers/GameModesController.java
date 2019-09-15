package com.github.haseoo.simplequizjava.gui.controllers;

import com.github.haseoo.simplequizjava.gamecore.utility.GlobalQuestionRepository;
import com.github.haseoo.simplequizjava.gui.projections.NumberOfQuestionsComponent;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.FIXED_NUMBER_OF_QUESTION_GM_START_INDEX;

@Slf4j
public class GameModesController {
    @Setter
    private ScrollPane scrollPane;
    private HBox numberOfQuestionsComponent;
    @FXML
    private ChoiceBox<String> gameModeChoiceBox;
    @FXML
    private VBox GameModeChoiceVBox;

    @FXML
    private void initialize() throws IOException {
        gameModeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(this::gameModeChoiceBoxObserver);
        numberOfQuestionsComponent = new NumberOfQuestionsComponent(10).getComponent();
        gameModeChoiceBox.setDisable(!GlobalQuestionRepository.isInitialized());
    }
    @FXML void onButton(ActionEvent ev) {

    }

    private void gameModeChoiceBoxObserver(Observable observable, Number oldValue, Number newValue) {
        if (oldValue.intValue() < FIXED_NUMBER_OF_QUESTION_GM_START_INDEX &&
                newValue.intValue() >= FIXED_NUMBER_OF_QUESTION_GM_START_INDEX) {
            GameModeChoiceVBox.getChildren().add(numberOfQuestionsComponent);
        }
        if (oldValue.intValue() >= FIXED_NUMBER_OF_QUESTION_GM_START_INDEX &&
                newValue.intValue() < FIXED_NUMBER_OF_QUESTION_GM_START_INDEX) {
            GameModeChoiceVBox.getChildren().remove(numberOfQuestionsComponent);
        }
    }
}
