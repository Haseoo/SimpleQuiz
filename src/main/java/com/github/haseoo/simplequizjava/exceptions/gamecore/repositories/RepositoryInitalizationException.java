package com.github.haseoo.simplequizjava.exceptions.gamecore.repositories;

import com.github.haseoo.simplequizjava.exceptions.ExceptionMessages;

import java.io.IOException;

public class RepositoryInitalizationException extends IOException {
    public RepositoryInitalizationException(Exception exception) {
        super(ExceptionMessages.REPOSITORY_INITIALIZATION_EXCEPTION);
        super.initCause(exception);
    }
}
