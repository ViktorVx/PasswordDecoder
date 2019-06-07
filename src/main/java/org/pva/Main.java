package org.pva;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    //todo add locales
    //todo add stupid music and music-button
    //todo add i18n
    private FXMLLoader fxmlLoader = new FXMLLoader();

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Application.setUserAgentStylesheet(getClass().getResource("/css/style.css").toExternalForm());

        Locale locale = new Locale("ru", "RU");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("locale", locale);
        fxmlLoader.setResources(resourceBundle);
        fxmlLoader.setLocation(getClass().getResource("/login.fxml"));

        Parent root = fxmlLoader.load();

        primaryStage.setTitle(fxmlLoader.getResources().getString("app.name"));

        Scene scene = new Scene(root, 430, 55);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
