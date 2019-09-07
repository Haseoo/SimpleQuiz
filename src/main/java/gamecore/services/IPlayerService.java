package gamecore.services;

import gamecore.projections.players.Player;
import gamecore.projections.players.Player.PlayerInfo;
import java.util.List;

public interface IPlayerService {
    List<Player.PlayerInfo> getPlayerList();
    boolean isAllPlayerLost();
    void setPlayerLost(PlayerInfo player);
    void addPointsToPlayerScore(PlayerInfo playerInfo, Integer score);
}
