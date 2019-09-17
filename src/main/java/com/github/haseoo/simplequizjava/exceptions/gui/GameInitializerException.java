package com.github.haseoo.simplequizjava.exceptions.gui;

import static com.github.haseoo.simplequizjava.exceptions.ExceptionMessages.GAME_INITIALIZER_EXCEPTION;

public class GameInitializerException extends RuntimeException{
    public GameInitializerException() {
        super(GAME_INITIALIZER_EXCEPTION);
    }
}
