package com.github.haseoo.simplequizjava.gamecore.services;

import com.github.haseoo.simplequizjava.gamecore.projections.players.Player;

import java.util.List;

public interface IPlayerService {
    List<Player.PlayerInfo> getPlayerList();

    boolean isAllPlayerLost();

    void setPlayerLost(Player.PlayerInfo player);

    void addPointsToPlayerScore(Player.PlayerInfo playerInfo, Integer score);
}
