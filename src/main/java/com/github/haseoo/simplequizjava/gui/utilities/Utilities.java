package com.github.haseoo.simplequizjava.gui.utilities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utilities {
    public static URL getResourceURL(Class<?> clazz, String relativePath) {
        return Objects.requireNonNull(clazz.getClassLoader().getResource(relativePath));
    }

    public static String getErrorMessage(Throwable throwable) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(throwable.getMessage());
        Throwable cause = throwable.getCause();
        while(cause != null) {
            stringBuilder.append("\n");
            stringBuilder.append(cause.getMessage());
            cause = cause.getCause();
        }
        return stringBuilder.toString();
    }
}
