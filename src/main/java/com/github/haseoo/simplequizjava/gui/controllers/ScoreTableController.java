package com.github.haseoo.simplequizjava.gui.controllers;

import com.github.haseoo.simplequizjava.gamecore.projections.players.Player.PlayerInfo;
import com.github.haseoo.simplequizjava.gui.projections.TableRecord;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class ScoreTableController {
    private final Map<PlayerInfo, Integer> scoreMap;

    @FXML
    private TableView<TableRecord> scoreTable;

    @FXML
    private void initialize() {
        scoreMap.forEach(this::addRecordToTable);
    }

    private void addRecordToTable(PlayerInfo playerInfo, Integer score) {
        scoreTable.getItems().add(new TableRecord(playerInfo.getName(), score));
    }

}
