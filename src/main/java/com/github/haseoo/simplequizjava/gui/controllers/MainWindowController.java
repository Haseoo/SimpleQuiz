package com.github.haseoo.simplequizjava.gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class MainWindowController {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void initialize() throws IOException {
        FXMLLoader newGame = new FXMLLoader(getClass().getResource("/gameModes.fxml"));
        Node newNode = newGame.load();
        newGame.<GameModesController>getController().setScrollPane(scrollPane);
        scrollPane.setContent(newNode);
    }

    @FXML
    private void onAbout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/OnAbout.fxml"));
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);

        dialog.setTitle("On About");
        dialog.setScene(scene);
        dialog.show();

    }

    @FXML
    private void onExit() {
        Platform.exit();
    }
}
