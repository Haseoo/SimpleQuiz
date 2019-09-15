package com.github.haseoo.simplequizjava.gui.controllers;

import com.github.haseoo.simplequizjava.gamecore.repositories.IQuestionRepository;
import com.github.haseoo.simplequizjava.gamecore.utility.GlobalQuestionRepository;
import com.github.haseoo.simplequizjava.gui.projections.NumberOfQuestionsComponent;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.FIXED_NUMBER_OF_QUESTION_GM_START_INDEX;
import static com.github.haseoo.simplequizjava.gui.utilities.Constants.PLAYER_INFO_FXML_PATH;
import static com.github.haseoo.simplequizjava.gui.utilities.Utilities.getResourceURL;

@RequiredArgsConstructor
public class GameModesController {

    @FXML
    private ChoiceBox<String> gameModeChoiceBox;
    @FXML
    private VBox gameModeChoiceVBox;
    @FXML
    private Button nextButton;
    @FXML
    private CheckBox fallingOutCheckBox;

    private final IQuestionRepository questionRepository;
    private final ScrollPane scrollPane;
    private final MenuItem initRepositoryMenuItem;

    private NumberOfQuestionsComponent numberOfQuestionsComponent;

    @FXML
    private void initialize() {
        gameModeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(this::gameModeChoiceBoxObserver);
        if (GlobalQuestionRepository.isInitialized()) {
            numberOfQuestionsComponent = new NumberOfQuestionsComponent(questionRepository.getTotalNumberOfQuestions());
        }
        gameModeChoiceBox.setDisable(!GlobalQuestionRepository.isInitialized());
    }
    @FXML
    private void onNext() throws IOException {
        initRepositoryMenuItem.setDisable(true);
        FXMLLoader playerInfo = new FXMLLoader(getResourceURL(getClass(), PLAYER_INFO_FXML_PATH));
        playerInfo.setController(new PlayerInfoController());
        scrollPane.setContent(playerInfo.load());
    }

    private void gameModeChoiceBoxObserver(Observable observable, Number oldValue, Number newValue) {
        if (oldValue.intValue() < FIXED_NUMBER_OF_QUESTION_GM_START_INDEX &&
                newValue.intValue() >= FIXED_NUMBER_OF_QUESTION_GM_START_INDEX) {
            gameModeChoiceVBox.getChildren().add(numberOfQuestionsComponent.getComponent());
        }
        if (oldValue.intValue() >= FIXED_NUMBER_OF_QUESTION_GM_START_INDEX &&
                newValue.intValue() < FIXED_NUMBER_OF_QUESTION_GM_START_INDEX) {
            gameModeChoiceVBox.getChildren().remove(numberOfQuestionsComponent.getComponent());
        }
        if (oldValue.intValue() == -1) {
            nextButton.setDisable(false);
        }
    }
}
