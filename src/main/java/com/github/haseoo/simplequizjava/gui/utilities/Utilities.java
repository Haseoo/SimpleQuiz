package com.github.haseoo.simplequizjava.gui.utilities;

import com.github.haseoo.simplequizjava.gui.Game;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utilities {
    public static URL getResourceURL(String relativePath) {
        return Objects.requireNonNull(Game.class.getClassLoader().getResource(relativePath));
    }

    public static String getErrorMessage(Throwable throwable) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(throwable.getMessage());
        Throwable cause = throwable.getCause();
        while (cause != null) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(cause.getMessage());
            cause = cause.getCause();
        }
        return stringBuilder.toString();
    }
}
