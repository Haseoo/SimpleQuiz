package exceptions.questions;

import static exceptions.ExceptionMessages.READING_QUESTION_FROM_FILE_FORMAT;

public class ReadingQuestionFromFileException extends RuntimeException {
    public ReadingQuestionFromFileException(Exception exception, String fileName) {
        super(String.format(READING_QUESTION_FROM_FILE_FORMAT, fileName));
        super.initCause(exception);
    }
}
