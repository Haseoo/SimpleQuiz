package com.github.haseoo.simplequizjava.gui.controllers;

import com.github.haseoo.simplequizjava.gui.utilities.GameInitializer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class PlayerInfoController {
    @FXML
    private ListView<String> playerList;
    @FXML
    private Button startButton;
    @FXML
    private TextField playerNickname;

    private final GameInitializer gameInitializer;

    @FXML
    private void onStartGame() {
        if(!playerList.getItems().isEmpty()) {
            gameInitializer.setPlayerNicknames(playerList.getItems());
            log.info(gameInitializer.toString());
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
