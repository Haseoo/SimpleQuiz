package com.github.haseoo.simplequizjava.exceptions.gamecore.questions;

import com.github.haseoo.simplequizjava.exceptions.ExceptionMessages;

public class UnableToDrawQuestionException extends RuntimeException {
    public UnableToDrawQuestionException() {
        super(ExceptionMessages.UNABLE_TO_DRAW_QUESTION_EXCEPTION);
    }
}
