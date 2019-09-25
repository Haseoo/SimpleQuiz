package com.github.haseoo.simplequizjava.gui.utilities;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.layout.HBox;
import lombok.Getter;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.NUMBER_OF_QUESTIONS_COMPONENT_LABEL_TEXT;
import static com.github.haseoo.simplequizjava.gui.utilities.Constants.SPINNER_INITIAL_VALUE;

@Getter
public class NumberOfQuestionsComponent {
    private final HBox component;
    private final Spinner<Integer> spinner;
    public NumberOfQuestionsComponent(Integer maxNumberOfQuestions) {
        component = new HBox();
        spinner = new Spinner<>();
        spinner.setValueFactory(new IntegerSpinnerValueFactory(SPINNER_INITIAL_VALUE, maxNumberOfQuestions));
        component.setAlignment(Pos.CENTER);
        component.getChildren().addAll(new Label(NUMBER_OF_QUESTIONS_COMPONENT_LABEL_TEXT),
                                       spinner);
    }
}
