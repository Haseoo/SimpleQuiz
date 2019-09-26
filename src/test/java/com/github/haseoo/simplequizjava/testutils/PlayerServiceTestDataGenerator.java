package com.github.haseoo.simplequizjava.testutils;

import com.github.haseoo.simplequizjava.gamecore.projections.players.Player;
import com.github.haseoo.simplequizjava.gamecore.projections.players.Player.PlayerInfo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.haseoo.simplequizjava.testutils.Constants.*;


public class PlayerServiceTestDataGenerator {
    public static List<String> generatePlayerNicknames() {
        return Arrays.asList(PLAYER_NICKNAME1, PLAYER_NICKNAME2, PLAYER_NICKNAME3);
    }

    public static List<PlayerInfo> generatePlayerList() {
        return Arrays
                .asList(new Player(1, PLAYER_NICKNAME1))
                .stream()
                .map(player -> player.new PlayerInfo())
                .collect(Collectors.toList());
    }
}
