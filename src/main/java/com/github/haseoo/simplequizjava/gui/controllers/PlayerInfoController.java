package com.github.haseoo.simplequizjava.gui.controllers;

import com.github.haseoo.simplequizjava.exceptions.gui.UnsupportedGameMode;
import com.github.haseoo.simplequizjava.gui.utilities.GameInitializer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.GAME_PLAY_WINDOW_FXML_PATH;
import static com.github.haseoo.simplequizjava.gui.utilities.Utilities.getResourceURL;

@RequiredArgsConstructor
@Slf4j
public class PlayerInfoController {
    @FXML
    private ListView<String> playerList;
    @FXML
    private Button startButton;
    @FXML
    private TextField playerNickname;

    private final ScrollPane scrollPane;
    private final MenuItem playerResignMenuItem;
    private final GameInitializer gameInitializer;

    @FXML
    private void onStartGame() throws IOException {
        if(!playerList.getItems().isEmpty()) {
            gameInitializer.setPlayerNicknames(playerList.getItems());
            log.debug(gameInitializer.toString());
            FXMLLoader gamePlayWindow = new FXMLLoader(getResourceURL(GAME_PLAY_WINDOW_FXML_PATH));
            initController(gamePlayWindow);
            scrollPane.setContent(gamePlayWindow.load());
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

    private void initController(FXMLLoader gamePlayWindow) {
        switch (gameInitializer.getGameMode()) {
            case WITHOUT_CHOOSING_CATEGORY:
                gamePlayWindow.setController(new GamePlayControllerWOCC(gameInitializer, playerResignMenuItem, scrollPane));
                break;
            case WITH_CHOOSING_CATEGORY:
                gamePlayWindow.setController(new GamePlayControllerWCC(gameInitializer, playerResignMenuItem, scrollPane));
                break;
            default:
                throw new UnsupportedGameMode();
        }
    }
}
