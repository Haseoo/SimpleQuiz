package com.github.haseoo.simplequizjava.gui.projections;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import lombok.Getter;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.*;

@Getter
public class NumberOfQuestionsComponent {
    public NumberOfQuestionsComponent(Integer maxNumberOfQuestions) {
        component = new HBox();
        component.setAlignment(Pos.CENTER);
        component.getChildren().addAll(new Label(NUMBER_OF_QUESTIONS_COMPONENT_LABEL_TEXT),
                                       new Spinner<Integer>(SPINNER_INITIAL_VALUE, maxNumberOfQuestions, SPINNER_INCREMENT_VALUE));
    }
    private final HBox component;
}
