package com.github.haseoo.simplequizjava.gui.controllers;

import com.github.haseoo.simplequizjava.exceptions.gamecore.repositories.RepositoryInitalizationException;
import com.github.haseoo.simplequizjava.gamecore.utility.GlobalQuestionRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.*;
import static com.github.haseoo.simplequizjava.gui.utilities.Utilities.getErrorMessage;

public class LoadRepositoryController {

    @FXML
    TextField filePathField;

    @FXML
    private void onBrowse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(FILE_FILTER_DESCRIPTION, FILE_FILTER_EXTENSION));
        File categoryListFile = fileChooser.showOpenDialog(filePathField.getScene().getWindow());
        if (categoryListFile != null) {
            filePathField.setText(categoryListFile.getAbsolutePath());
        }

    }

    @FXML
    private void onApply() {
        String filePathValue = filePathField.getText();
        if (filePathValue != null && !filePathValue.isEmpty()) {
            try {
                GlobalQuestionRepository.initRepository(filePathValue, REINITIALISATION_POLICY);
                closeWindow();
            } catch (RepositoryInitalizationException e) {
                showAlertWindow(e);
            }
        }
    }

    private void showAlertWindow(RepositoryInitalizationException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, ERROR_DIALOG_CONTEXT_TEXT);
        alert.setHeaderText(getErrorMessage(e));
        alert.showAndWait();
    }

    @FXML
    private void onCancel() {
        closeWindow();
    }

    private void closeWindow() {
        ((Stage) filePathField.getScene().getWindow()).close();
    }

}
