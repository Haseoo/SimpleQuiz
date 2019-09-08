package com.github.haseoo.simplequizjava.gamecore.views;

import com.github.haseoo.simplequizjava.gamecore.projections.questions.Question;
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
