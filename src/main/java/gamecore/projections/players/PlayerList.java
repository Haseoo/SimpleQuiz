package gamecore.projections.players;

import exceptions.questions.players.PlayerNotFound;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PlayerList {
    private List<Player> players;

    public PlayerList(List<String> playerNames) {
        AtomicInteger id = new AtomicInteger();
        players = playerNames
                .stream()
                .map(playerName -> new Player(id.getAndIncrement(), playerName))
                .collect(Collectors.toList());
    }

    public List<Player.PlayerInfo> getPlayerList() {
        return players.stream()
                .map(player -> player.new PlayerInfo())
                .collect(Collectors.toList());
    }

    public boolean isAllPlayerLost() {
        return !players.stream().map(Player::isPlaying).reduce(false, (r, a) -> r || a);
    }

    public void setPlayerLost(Player.PlayerInfo playerInfo) {
        getPlayerByInfo(playerInfo).playerLost();
    }

    public void addPointsToPlayerScore(Player.PlayerInfo playerInfo, Integer score) {
        getPlayerByInfo(playerInfo).addScore(score);
    }

    private Player getPlayerByInfo(Player.PlayerInfo playerInfo) {
        return players
                .stream()
                .filter(player -> player.getId().equals(playerInfo.getId()))
                .findAny().orElseThrow(() -> new PlayerNotFound(playerInfo.getName()));
    }

}