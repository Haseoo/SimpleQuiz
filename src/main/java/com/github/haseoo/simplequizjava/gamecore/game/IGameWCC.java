package com.github.haseoo.simplequizjava.gamecore.game;

import com.github.haseoo.simplequizjava.gamecore.views.CategoryView;
import com.github.haseoo.simplequizjava.gamecore.views.QuestionView;

public interface IGameWCC extends IGame {
    CategoryView[] getAvailableCategoriesIndexes();

    QuestionView getNextQuestion(Integer categoryIndex);
}
