package com.github.haseoo.simplequizjava.gamecore.projections.questions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.haseoo.simplequizjava.exceptions.gamecore.questions.ReadingQuestionFromFileException;
import com.github.haseoo.simplequizjava.gamecore.models.CategoryModel;
import com.github.haseoo.simplequizjava.gamecore.models.QuestionModel;
import lombok.Value;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Value
public class Category {
    private String name;
    private Question[] questions;

    public Category (CategoryModel categoryModel) {
        ObjectMapper objectMapper = new ObjectMapper();
        name = categoryModel.getName();
        QuestionModel[] questionModels;
        try {
            questionModels = objectMapper.readValue(new File(categoryModel.getJsonFilePath()), QuestionModel[].class);
        } catch (IOException e) {
            throw new ReadingQuestionFromFileException(e, categoryModel.getJsonFilePath());
        }
        questions = Arrays.stream(questionModels).map(Question::of).toArray(Question[]::new);
    }
}
