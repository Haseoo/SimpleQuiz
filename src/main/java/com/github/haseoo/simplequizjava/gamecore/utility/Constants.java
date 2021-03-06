package com.github.haseoo.simplequizjava.gamecore.utility;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String QUESTION_PATH_FORMATTER = "%s/%s";
    public static final boolean CATEGORIES_LIST_INITIALIZATION_DEFAULT_POLICY = false;
    public static final Integer BEGIN_INDEX = 0;
    public static final Integer DEFAULT_SCORE_INCREMENT_VALUE = 1;
    @Getter
    private static final Integer[] GAME_MODE_WITH_CHOOSING_CATEGORY_INDEXES = {1, 3};
    @Getter
    private static final Integer[] GAME_MODE_WITHOUT_CHOOSING_CATEGORY_INDEXES = {0, 2};
}
