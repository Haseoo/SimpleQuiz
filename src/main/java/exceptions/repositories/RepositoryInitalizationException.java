package exceptions.repositories;

import java.io.IOException;

import static exceptions.ExceptionMessages.REPOSITORY_INITIALIZATION_EXCEPTION;

public class RepositoryInitalizationException extends IOException {
    public RepositoryInitalizationException(Exception exception) {
        super(REPOSITORY_INITIALIZATION_EXCEPTION);
        super.initCause(exception);
    }
}
