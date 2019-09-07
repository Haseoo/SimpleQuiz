package gamecore.views;

import gamecore.projections.questions.Question;
import lombok.Value;

@Value
public class QuestionView {
    private String content;
    private String[] answers;

    public static QuestionView of(Question questionModel) {
        return new QuestionView(questionModel.getContent(),
                questionModel.getAnswers()
        );
    }
}
