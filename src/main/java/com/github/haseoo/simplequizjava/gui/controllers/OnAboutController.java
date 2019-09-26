package com.github.haseoo.simplequizjava.gui.controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import lombok.RequiredArgsConstructor;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.FONT_CREDIT_LINK;
import static com.github.haseoo.simplequizjava.gui.utilities.Constants.GITHUB_PROJECT_LINK;

@RequiredArgsConstructor
public class OnAboutController {
    private final Application application;

    @FXML
    void onProjectLink() {
        application.getHostServices().showDocument(GITHUB_PROJECT_LINK);
    }

    @FXML
    void onFontLink() {
        application.getHostServices().showDocument(FONT_CREDIT_LINK);
    }
}
