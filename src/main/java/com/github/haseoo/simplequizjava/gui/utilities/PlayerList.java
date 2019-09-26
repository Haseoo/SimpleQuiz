package com.github.haseoo.simplequizjava.gui.utilities;

import com.github.haseoo.simplequizjava.gamecore.projections.players.Player.PlayerInfo;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.BEGIN_INDEX;

public class PlayerList {
    public PlayerList(PlayerInfo[] players) {
        this.players = Arrays.asList(players);
        currentPlayer = null;
    }

    private final List<PlayerInfo> players;
    @Getter
    private PlayerInfo currentPlayer;

    public void setNext() {
        if (currentPlayer == null) {
            currentPlayer = players.get(BEGIN_INDEX);
        } else {
            currentPlayer = players
                    .stream()
                    .filter(this::activePlayerWithGreaterId)
                    .findFirst()
                    .orElse(players
                            .stream()
                            .filter(PlayerInfo::isPlaying)
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Nidyrdy")));
        }
    }

    public boolean allPlayerLost() {
        return players.stream().noneMatch(PlayerInfo::isPlaying);
    }

    public boolean willIterationOccur() {
        if (currentPlayer == null) {
            return true;
        }
        return players.stream().noneMatch(this::activePlayerWithGreaterId);
    }

    private boolean activePlayerWithGreaterId(PlayerInfo playerInfo) {
        return playerInfo.isPlaying() && playerInfo.getId() > currentPlayer.getId();
    }

}
