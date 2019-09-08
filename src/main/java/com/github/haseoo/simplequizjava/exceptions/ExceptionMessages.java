package com.github.haseoo.simplequizjava.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessages {
    public static final String UNINITIALIZED_REPOSITORY_EXCEPTION = "Attempted to use uninitialized repository";
    public static final String READING_QUESTION_FROM_FILE_FORMAT = "Failed to read questions form %s file";
    public static final String REPOSITORY_INITIALIZATION_EXCEPTION = "Failed to initialize question repository";
    public static final String PLAYER_NOT_FOUND_FORMAT = "Player %s not found on the in-play player list";
    public static final String UNABLE_TO_DRAW_QUESTION_EXCEPTION = "Attempt to generate more question than possible";
}
