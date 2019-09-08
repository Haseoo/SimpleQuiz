package com.github.haseoo.simplequizjava.exceptions.gamecore.questions;

import com.github.haseoo.simplequizjava.exceptions.ExceptionMessages;

public class ReadingQuestionFromFileException extends RuntimeException {
    public ReadingQuestionFromFileException(Exception exception, String fileName) {
        super(String.format(ExceptionMessages.READING_QUESTION_FROM_FILE_FORMAT, fileName));
        super.initCause(exception);
    }
}
