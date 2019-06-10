package org.pva;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.pva.controllers.LoginController;
import org.pva.utils.locale.Lang;
import org.pva.utils.locale.LocaleManager;

import java.io.IOException;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class Main extends Application implements Observer {
    //todo add stupid music and music-button
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Parent root;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        Locale locale = new Locale("ru", "RU");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("locale", locale);

        LocaleManager.setCurrentLang(new Lang("ru", resourceBundle.getString("ru"), LocaleManager.RU_LOCALE,0));
//        Application.setUserAgentStylesheet(getClass().getResource("/css/style.css").toExternalForm());
        fxmlLoader.setResources(ResourceBundle.getBundle("locale", LocaleManager.getCurrentLang().getLocale()));
        fxmlLoader.setLocation(getClass().getResource("/login.fxml"));

        root = fxmlLoader.load();

        primaryStage.setTitle(fxmlLoader.getResources().getString("app.name"));
        LoginController loginController = fxmlLoader.getController();
        loginController.fillLangCombo();
        loginController.addObserver(this);

        Scene scene = new Scene(root, 430, 90);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

        @Override
    public void update(Observable o, Object arg) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(ResourceBundle.getBundle("locale", LocaleManager.getCurrentLang().getLocale()));
            AnchorPane anchorPane = loader.load(this.getClass().getResource("/login.fxml").openStream());
//            getChildrenUnmodifiable().setAll(anchorPane.getChildren());
            StackPane content = (StackPane) ((AnchorPane) stage.getScene().getRoot()).getChildren().get(1);
            content.getChildren().clear();
            content.getChildren().add(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
