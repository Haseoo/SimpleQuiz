package gamecore.projections.questions;

import gamecore.models.QuestionModel;
import lombok.Value;

@Value
public class Question {
    private String content;
    private String[] answers;
    private Integer correctAnswerIndex;

    public static Question of(QuestionModel questionModel) {
        return new Question(questionModel.getContent(),
                            questionModel.getAnswers(),
                            questionModel.getCorrectAnswerIndex()
        );
    }
}
