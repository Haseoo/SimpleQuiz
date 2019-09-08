package com.github.haseoo.simplequizjava.gamecore.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class QuestionModel {
    private String content;
    private String[] answers;
    private Integer correctAnswerIndex;
}
