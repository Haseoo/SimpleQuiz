package com.github.haseoo.simplequizjava.exceptions.gamecore.players;

import com.github.haseoo.simplequizjava.exceptions.ExceptionMessages;

public class PlayerNotFound extends RuntimeException {
    public PlayerNotFound(String playerNickname) {
        super(String.format(ExceptionMessages.PLAYER_NOT_FOUND_FORMAT, playerNickname));
    }
}
