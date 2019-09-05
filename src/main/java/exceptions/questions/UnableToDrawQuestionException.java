package exceptions.questions;

import static exceptions.ExceptionMessages.UNABLE_TO_DRAW_QUESTION_EXCEPTION;

public class UnableToDrawQuestionException extends RuntimeException {
    public UnableToDrawQuestionException() {
        super(UNABLE_TO_DRAW_QUESTION_EXCEPTION);
    }
}
