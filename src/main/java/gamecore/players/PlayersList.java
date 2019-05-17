package gamecore.players;

import java.util.ArrayList;

public class PlayersList {
    private ArrayList<PlayerInternal> players;
    private int counter = 0;

    public PlayersList() {
        players = new ArrayList<>();
    }

    public void addPlayer(String playerName) {
        players.add(new PlayerInternal(playerName, counter++));
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public PlayerInternal.Player[] getPlayersList() {
        PlayerInternal.Player externalPlayersList[] = new PlayerInternal.Player[players.size()];
        for(int i = 0; i < players.size(); i++) {
            externalPlayersList[i] = players.get(i).new Player();
        }
        return externalPlayersList;
    }

    public PlayerInternal getPlayer(PlayerInternal.Player player) {
        //TODO exception when player not found
        PlayerInternal retVal = null;
        for (PlayerInternal it : players) {
            if (it.getId() == player.getId()) {
                retVal = it;
            }
        }
        return retVal;
    }


    public boolean allPlayerHasLost() {
        boolean retVal = true;
        for (PlayerInternal it : players) {
            retVal = retVal && !(it.isPlayerPlaying());
        }
        return retVal;
    }
    

    static public void main(String[] args) {
        PlayersList pl = new PlayersList();
        pl.addPlayer("Anka z wanka");
        pl.addPlayer("Wanka z anka");

        PlayerInternal.Player[] pls = pl.getPlayersList();

        for(PlayerInternal.Player i : pls) {
            System.out.println(i.getId() + " " + i.getPlayerName() + " " + i.getPlayerScore());
        }

    }

}