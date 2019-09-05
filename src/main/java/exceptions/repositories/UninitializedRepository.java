package exceptions.repositories;

import static exceptions.ExceptionMessages.UNINITIALIZED_REPOSITORY_EXCEPTION;

public class UninitializedRepository extends RuntimeException{
    public UninitializedRepository() {
        super(UNINITIALIZED_REPOSITORY_EXCEPTION);
    }
}
