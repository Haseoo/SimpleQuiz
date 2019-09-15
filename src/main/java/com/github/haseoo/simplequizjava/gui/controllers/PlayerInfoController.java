package com.github.haseoo.simplequizjava.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlayerInfoController {
    @FXML
    private ListView<String> playerList;
    @FXML
    private Button startButton;
    @FXML
    private TextField playerNickname;

    @FXML
    private void onStartGame() {
        if(!playerList.getItems().isEmpty()) {
            log.info(playerList.getItems().toString());
        }
    }

    @FXML
    private void onAddPlayer() {
        String playerNicknameText = playerNickname.getText();
        if (isNicknameValid(playerNicknameText)) {
            playerList.getItems().add(playerNicknameText);
            playerNickname.setText(null);
        }
    }

    @FXML
    private void onRemovePlayer() {
        playerList.getItems().removeAll(playerList.getSelectionModel().getSelectedItems());
    }

    private boolean isNicknameValid(String playerNicknameText) {
        return playerNicknameText != null && !playerNicknameText.isEmpty() && !playerList.getItems().contains(playerNicknameText);
    }
}
