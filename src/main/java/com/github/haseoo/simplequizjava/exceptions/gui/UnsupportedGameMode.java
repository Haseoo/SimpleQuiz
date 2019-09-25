package com.github.haseoo.simplequizjava.exceptions.gui;

import static com.github.haseoo.simplequizjava.exceptions.ExceptionMessages.UNSUPPORTED_GAME_MODE;

public class UnsupportedGameMode extends RuntimeException{
    public UnsupportedGameMode() {
        super(UNSUPPORTED_GAME_MODE);
    }
}
