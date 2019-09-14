package com.github.haseoo.simplequizjava.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class GameModesController {
    @Setter
    private ScrollPane scrollPane;

    @FXML
    private VBox gameModesVBox;

    @FXML
    private void initialize() throws IOException {

    }
    @FXML void onButton(ActionEvent ev) {

    }
}
