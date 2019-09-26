package com.github.haseoo.simplequizjava.gui;

import com.github.haseoo.simplequizjava.gui.controllers.MainWindowController;
import com.github.haseoo.simplequizjava.gui.utilities.Secret;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

import static com.github.haseoo.simplequizjava.gui.utilities.Constants.*;
import static com.github.haseoo.simplequizjava.gui.utilities.Utilities.getResourceURL;

public class Game extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainWindow = new FXMLLoader(getResourceURL(MAIN_WINDOW_FXML_PATH));
        mainWindow.setController(new MainWindowController(this));
        Parent root = mainWindow.load();

        Scene scene = new Scene(root);
        prepareSecret(scene);

        stage.setTitle(APPLICATION_NAME);
        stage.getIcons().add(new Image(ICON_FILE_PATH));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void prepareSecret(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.H) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, event.getText());
                alert.setHeaderText(event.getText());
                alert.setTitle(event.getText());
                alert.setGraphic(new ImageView(new Image(new ByteArrayInputStream(Base64.getDecoder().decode(Secret.MY_SECRET)))));
                alert.showAndWait();
            }
        });
    }

}
