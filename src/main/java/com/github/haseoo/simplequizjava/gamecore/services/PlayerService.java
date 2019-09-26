package com.github.haseoo.simplequizjava.gamecore.services;

import com.github.haseoo.simplequizjava.exceptions.gamecore.players.PlayerNotFound;
import com.github.haseoo.simplequizjava.gamecore.projections.players.Player;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PlayerService implements IPlayerService{
    private List<Player> players;

    public PlayerService(List<String> playerNames) {
        AtomicInteger id = new AtomicInteger();
        players = playerNames
                .stream()
                .map(playerName -> new Player(id.getAndIncrement(), playerName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Player.PlayerInfo> getPlayerList() {
        return players.stream()
                .map(player -> player.new PlayerInfo())
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAllPlayerLost() {
        return players.stream().noneMatch(Player::isPlaying);
    }

    @Override
    public void setPlayerLost(Player.PlayerInfo player) {
        getPlayerByInfo(player).playerLost();
    }

    @Override
    public void addPointsToPlayerScore(Player.PlayerInfo player, Integer score) {
        getPlayerByInfo(player).addScore(score);
    }

    private Player getPlayerByInfo(Player.PlayerInfo playerInfo) {
        return players
                .stream()
                .filter(Player::isPlaying)
                .filter(player -> player.getId().equals(playerInfo.getId()))
                .findAny().orElseThrow(() -> new PlayerNotFound(playerInfo.getName()));
    }

}
