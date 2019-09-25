package com.github.haseoo.simplequizjava.gui.controllers;

import com.github.haseoo.simplequizjava.gamecore.repositories.QuestionRepository;
import com.github.haseoo.simplequizjava.gamecore.utility.GlobalQuestionRepository;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.*;
import static com.github.haseoo.simplequizjava.gui.utilities.Utilities.getResourceURL;

@RequiredArgsConstructor
public class MainWindowController {

    private static Map<Boolean, URL> questionRepositoryStatusNodes;

    static {
        questionRepositoryStatusNodes = new HashMap<>();
        questionRepositoryStatusNodes.put(true, getResourceURL(QUESTION_LOADED_FXML_PATH));
        questionRepositoryStatusNodes.put(false, getResourceURL(QUESTION_NOT_LOADED_FXML_PATH));
    }

    private final Application application;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private MenuItem loadRepository;
    @FXML
    private MenuItem playerResignMenuItem;

    @FXML
    private void initialize() throws IOException {
        FXMLLoader newGame = new FXMLLoader(getResourceURL(GAME_MODES_FXML_PATH));
        newGame.setController(new GameModesController(new QuestionRepository(), scrollPane, loadRepository, playerResignMenuItem));
        scrollPane.setContent(newGame.load());
        loadRepository.setDisable(false);
        playerResignMenuItem.setDisable(true);
        setRepositoryStatus();
    }

    @FXML
    private void onAbout() throws IOException {
        FXMLLoader onAbout = new FXMLLoader(getResourceURL(ON_ABOUT_FXML_PATH));
        onAbout.setController(new OnAboutController(application));
        Parent root = onAbout.load();
        prepareDialog(root, ON_ABOUT_TITLE);
    }

    @FXML
    private void onExit() {
        Platform.exit();
    }

    @FXML
    private void onLoadRepository() throws IOException {
        Parent root = FXMLLoader.load(getResourceURL(REPOSITORY_INITIALIZATION_DIALOG_FXML_PATH));
        prepareDialog(root, LOAD_REPOSITORY_TITLE);
        initialize();
    }

    private void setRepositoryStatus() throws IOException {
        borderPane.setBottom(FXMLLoader.load(questionRepositoryStatusNodes.get(GlobalQuestionRepository.isInitialized())));
    }

    private void prepareDialog(Parent root, String dialogTitle) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        dialog.setTitle(dialogTitle);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
