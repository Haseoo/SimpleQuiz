package com.github.haseoo.simplequizjava.gui.utilities;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import lombok.Getter;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.*;

@Getter
public class NumberOfQuestionsComponent {
    private final HBox component;
    private final Spinner<Integer> spinner;
    public NumberOfQuestionsComponent(Integer maxNumberOfQuestions) {
        component = new HBox();
        spinner = new Spinner<>(SPINNER_INITIAL_VALUE, maxNumberOfQuestions, SPINNER_INCREMENT_VALUE);
        component.setAlignment(Pos.CENTER);
        component.getChildren().addAll(new Label(NUMBER_OF_QUESTIONS_COMPONENT_LABEL_TEXT),
                                       spinner);
    }
}
