package com.github.haseoo.simplequizjava.testutils;

import com.github.haseoo.simplequizjava.gamecore.projections.questions.Question;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.QuestionCoords;
import com.github.haseoo.simplequizjava.gamecore.projections.questions.Category;

import java.util.Objects;

import static com.github.haseoo.simplequizjava.testutils.Constants.*;

public class QuestionRepositoryTestDataGenerator {
    public static Question generateFirstQuestion() {
        return new Question(FIRST_TEST_QUESTION_CONTENT,
                            new String[] {FIRST_TEST_QUESTION_ANSWER1, FIRST_TEST_QUESTION_ANSWER2, FIRST_TEST_QUESTION_ANSWER3},
                FIRST_TEST_QUESTION_CORRECT_ANSWER);
    }
    public static QuestionCoords generateQuestionCoords() {
        return new QuestionCoords(TEST_CATEGORY_INDEX, TEST_QUESTION_INDEX);
    }

    public static String generateCategoryListFilePath(Class<?> objClass) {
        return Objects.requireNonNull(objClass.getClassLoader().getResource(CATEGORY_LIST_FILE_RELATIVE_PATH)).getFile();
    }

    public static Category generateCategory() {
        return new Category(CATEGORY_TEST_NAME, new Question[] {generateFirstQuestion()});
    }
}
