package com.github.haseoo.simplequizjava.gui.controllers;

import com.github.haseoo.simplequizjava.gamecore.game.IGameWCC;
import com.github.haseoo.simplequizjava.gamecore.projections.players.Player.PlayerInfo;
import com.github.haseoo.simplequizjava.gamecore.views.CategoryView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.function.IntConsumer;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.DESIRED_NUMBER_OF_CATEGOIRES;

@RequiredArgsConstructor
public class CategoryController {
    private final IGameWCC game;
    private final IntConsumer onChosenCategoryAction;
    private final PlayerInfo choosingPlayer;

    @FXML
    private Text choosingPlayerNickname;
    @FXML
    private VBox categoryVBox;
    @FXML
    private Button applyButton;

    private ToggleGroup categoriesToggle;

    @FXML
    void initialize() {
        categoriesToggle = new ToggleGroup();
        choosingPlayerNickname.setText(choosingPlayer.getName());
        CategoryView[] availableCategories = game.getAvailableCategoriesIndexes();
        CategoryView[] categories = Arrays.copyOf(availableCategories, getDesiredLength(availableCategories));
        Arrays.stream(categories).forEach(this::setupRadioButton);
    }

    @FXML
    private void onApply() {
        Toggle toggle = categoriesToggle.getSelectedToggle();
        if (toggle != null) {
            Integer selectedCategoryIndex = (Integer) toggle.getUserData();
            onChosenCategoryAction.accept(selectedCategoryIndex);
            applyButton.setDisable(true);
        }
    }

    private void setupRadioButton(CategoryView categoryView) {
        RadioButton radioButton = new RadioButton();
        radioButton.setText(categoryView.getName());
        radioButton.setToggleGroup(categoriesToggle);
        radioButton.setUserData(categoryView.getIndex());
        categoryVBox.getChildren().add(radioButton);
    }

    private int getDesiredLength(CategoryView[] availableCategories) {
        return (availableCategories.length > DESIRED_NUMBER_OF_CATEGOIRES)
                ? DESIRED_NUMBER_OF_CATEGOIRES
                : availableCategories.length;
    }
}
