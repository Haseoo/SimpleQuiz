package com.github.haseoo.simplequizjava.gamecore.projections.questions;

import com.github.haseoo.simplequizjava.gamecore.models.QuestionModel;
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
