package exceptions.players;

import static exceptions.ExceptionMessages.PLAYER_NOT_FOUND_FORMAT;

public class PlayerNotFound extends RuntimeException {
    public PlayerNotFound(String playerNickname) {
        super(String.format(PLAYER_NOT_FOUND_FORMAT, playerNickname));
    }
}
