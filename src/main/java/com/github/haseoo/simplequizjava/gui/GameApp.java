package com.github.haseoo.simplequizjava.gui;

import com.github.haseoo.simplequizjava.gui.controllers.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.*;
import static com.github.haseoo.simplequizjava.gui.utilities.Utilities.getResourceURL;

public class GameApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainWindow = new FXMLLoader(getResourceURL(getClass(), MAIN_WINDOW_FXML_PATH));
        mainWindow.setController(new MainWindowController(this));
        Parent root = mainWindow.load();

        Scene scene = new Scene(root);

        stage.setTitle(APPLICATION_NAME);
        stage.getIcons().add(new Image(ICON_FILE_PATH));
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
