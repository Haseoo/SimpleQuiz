package com.github.haseoo.simplequizjava.gamecore.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.haseoo.simplequizjava.exceptions.gamecore.questions.ReadingQuestionFromFileException;
import com.github.haseoo.simplequizjava.exceptions.gamecore.repositories.RepositoryInitalizationException;
import com.github.haseoo.simplequizjava.exceptions.gamecore.repositories.UninitializedRepository;
import com.github.haseoo.simplequizjava.gamecore.models.CategoryModel;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.Category;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.Question;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.QuestionCoords;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalQuestionRepository {
    private static Category[] categories;
    public static void initRepository(String categoriesListFilePath, boolean doReinitialization) throws RepositoryInitalizationException {
        if (categories == null || doReinitialization) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                CategoryModel[] categoryModels = objectMapper.readValue(new File(categoriesListFilePath), CategoryModel[].class);
                categories = Arrays.stream(categoryModels).map(Category::new).toArray(Category[]::new);
            } catch (IOException | ReadingQuestionFromFileException e) {
                throw new RepositoryInitalizationException(e);
            }
        }
    }
    public static Question getQuestionByCoords(QuestionCoords questionCoords) {
        if (categories == null) {
            throw new UninitializedRepository();
        }
        return categories[questionCoords.getCategoryIndex()]
                .getQuestions()[questionCoords.getQuestionIndex()];
    }

    public static Integer getNumberOfCategories() {
        if (categories == null) {
            throw new UninitializedRepository();
        }
        return categories.length;
    }

    public static Integer getNumberOfQuestionInCategory(Integer categoryIndex) {
        if (categories == null) {
            throw new UninitializedRepository();
        }
        return categories[categoryIndex].getQuestions().length;
    }

    public static void clearRepository() {
        categories = null;
    }
}
