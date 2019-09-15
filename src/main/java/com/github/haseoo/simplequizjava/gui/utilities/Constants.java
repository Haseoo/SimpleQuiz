package com.github.haseoo.simplequizjava.gui.utilities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String APPLICATION_NAME = "Simple Quiz";
    public static final String ON_ABOUT_TITLE = "On about";
    public static final String LOAD_REPOSITORY_TITLE = "Load repository";
    public static final String ICON_FILE_PATH = "icon.png";
    public static final String GITHUB_PROJECT_LINK = "https://github.com/Haseoo/SimpleQuizJava/";
    public static final String FONT_CREDIT_LINK = "https://www.dafont.com/good-times.font";
    public static final String NUMBER_OF_QUESTIONS_COMPONENT_LABEL_TEXT = "Number of rounds: ";
    public static final Integer SPINNER_INITIAL_VALUE = 0;
    public static final Integer SPINNER_INCREMENT_VALUE = 1;
    public static final Integer FIXED_NUMBER_OF_QUESTION_GM_START_INDEX = 2;
    public static final Boolean REINITIALISATION_POLICY = true;
    public static final String ERROR_DIALOG_CONTEXT_TEXT = "Read readme.txt for information about proper file format.";
    public static final String FILE_FILTER_DESCRIPTION = "Json file";
    public static final String FILE_FILTER_EXTENSION = "*.json";

    /*FXML RESOURCES*/
    public static final String MAIN_WINDOW_FXML_PATH = "mainWindow.fxml";
    public static final String GAME_MODES_FXML_PATH = "gameModes.fxml";
    public static final String QUESTION_LOADED_FXML_PATH = "questionLoaded.fxml";
    public static final String QUESTION_NOT_LOADED_FXML_PATH = "questionNotLoaded.fxml";
    public static final String ON_ABOUT_FXML_PATH = "onAbout.fxml";
    public static final String REPOSITORY_INITIALIZATION_DIALOG_FXML_PATH = "loadRepository.fxml";
    public static final String  PLAYER_INFO_FXML_PATH = "playerInfo.fxml";
}
