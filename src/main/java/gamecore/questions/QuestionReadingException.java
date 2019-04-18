package gamecore.questions;

import java.io.IOException;

public class QuestionReadingException extends IOException {
    public QuestionReadingException(String label) {
        super(label);
    }
}
