package org.pva.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.pva.domain.AccountsMapStorage;
import org.pva.utils.locale.Lang;
import org.pva.utils.locale.LocaleManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.ResourceBundle;

public class LoginController extends Observable implements Initializable {

    @FXML
    public ComboBox comboLocales;

    @FXML
    TextField filePathField;

    @FXML
    PasswordField passwordField;

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private Parent root;

    private Parent passwordListFxml;
    private PasswordListController passwordListController;
    private Stage passwordListStage;
    private ResourceBundle resourceBundle;

    private static final String RU_CODE = "ru";
    private static final String EN_CODE = "en";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.resourceBundle = resources;
    }

    //******************************************************************************************************************

    public void selectFileOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file!=null)
            filePathField.setText(file.getAbsolutePath());
    }

    public void decryptBtnOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Data entry error");
        alert.setTitle(resourceBundle.getString("message.error.title.data-entry-error"));
        if (filePathField == null || filePathField.getText() == null || filePathField.getText().equals("")) {
            alert.setHeaderText("Empty file path");
            alert.setContentText("Please, choose file path");
            alert.show();
            return;
        }

        if (passwordField == null || passwordField.getText() == null || passwordField.getText().equals("")) {
            alert.setHeaderText("Empty password");
            alert.setContentText("Please, enter password");
            alert.show();
            return;
        }

        File file = new File(filePathField.getText());
        // *** NoEncryption ***
        String fileContent = new String(Files.readAllBytes(Paths.get(filePathField.getText())));
        // *** Enctyption ***
//        byte[] fileBytes = Files.readAllBytes(file.toPath());
//
//        char[] pass = passwordField.getText().toCharArray();
//
////        String fileContent = AES.encrypt(new String(fileBytes, StandardCharsets.UTF_8), pass);
//        String fileContent = AES.decrypt(new String(fileBytes, StandardCharsets.UTF_8), pass);
//        System.out.println(fileContent);
        //****************************************************
        AccountsMapStorage accountsMapStorage = new AccountsMapStorage(fileContent);
        openPasswordList(actionEvent, accountsMapStorage);
    }

    public void createNewBtnOnAction(ActionEvent actionEvent) throws IOException {
        openPasswordList(actionEvent, new AccountsMapStorage());
    }

    //******************************************************************************************************************

    public void fillLangCombo() {
        Lang langRU = new Lang(RU_CODE, resourceBundle.getString("ru"), LocaleManager.RU_LOCALE,0);
        Lang langEN = new Lang(EN_CODE, resourceBundle.getString("en"), LocaleManager.EN_LOCALE, 1);

        comboLocales.getItems().add(langRU);
        comboLocales.getItems().add(langEN);

        if (LocaleManager.getCurrentLang() == null) {
            comboLocales.getSelectionModel().select(0);
        } else {
            comboLocales.getSelectionModel().select(LocaleManager.getCurrentLang().getIndex());
        }

        comboLocales.setOnAction(event -> {
            Lang selectLang = (Lang) comboLocales.getSelectionModel().getSelectedItem();
            LocaleManager.setCurrentLang(selectLang);
            setChanged();
            notifyObservers(selectLang);
        });

    }

    private void openPasswordList(ActionEvent actionEvent, AccountsMapStorage accountsMapStorage) throws IOException {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("locale", LocaleManager.getCurrentLang().getLocale());
        fxmlLoader.setLocation(getClass().getResource("/passwordsList.fxml"));
        fxmlLoader.setResources(resourceBundle);

        passwordListFxml = fxmlLoader.load();
        passwordListController = fxmlLoader.getController();

        passwordListController.setAccountsMapStorage(accountsMapStorage);
        passwordListController.setFilePath(filePathField.getText());

        if (passwordListStage == null) {
            passwordListStage = new Stage();
            passwordListStage.setTitle("Passwords");
            passwordListStage.setScene(new Scene(passwordListFxml, 460, 280));
            passwordListStage.setMinWidth(460);
            passwordListStage.setMinHeight(280);
        }
        passwordListStage.show();

        Node source = (Node) actionEvent.getSource();
        Stage stg = (Stage) source.getScene().getWindow();
        stg.close();
    }

//    @Override
//    public void update(Observable o, Object arg) {
////        AnchorPane newNode = loadFXML(LocaleManager.getCurrentLang().getLocale());
////        .getChildrenUnmodifiable().setAll(newNode.getChildren());
////        System.out.println("change locale on login form");
////        resourceBundle = ResourceBundle.getBundle("locale", LocaleManager.getCurrentLang().getLocale());
////        fxmlLoader.setResources(resourceBundle);
////        try {
////            FXMLLoader loader = new FXMLLoader();
////            loader.setResources(ResourceBundle.getBundle("locale", LocaleManager.getCurrentLang().getLocale()));
////            AnchorPane anchorPane = loader.load(this.getClass().getResource("/login.fxml").openStream());
//////            .getChildren().clear();
////            anchorPane.getChildren().setAll(anchorPane.getChildren());
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//    }

//    private AnchorPane loadFXML(Locale locale) {
//        FXMLLoader loader = new FXMLLoader();
//
//        loader.setLocation(getClass().getResource("/login.fxml"));
//        loader.setResources(ResourceBundle.getBundle("locale", LocaleManager.getCurrentLang().getLocale()));
//
//        AnchorPane node = null;
//
//        try {
//            node = loader.load();
//            LoginController loginController = loader.getController();
//            loginController.addObserver(this);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return node;
//    }
}
