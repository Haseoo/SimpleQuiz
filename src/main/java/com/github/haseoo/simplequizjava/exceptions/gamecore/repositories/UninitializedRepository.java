package com.github.haseoo.simplequizjava.exceptions.gamecore.repositories;

import static com.github.haseoo.simplequizjava.exceptions.ExceptionMessages.UNINITIALIZED_REPOSITORY_EXCEPTION;

public class UninitializedRepository extends RuntimeException {
    public UninitializedRepository() {
        super(UNINITIALIZED_REPOSITORY_EXCEPTION);
    }
}
