package org.pva.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.pva.domain.AccountsMapStorage;
import org.pva.encryption.AES;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class LoginController {

    @FXML
    TextField filePathField;

    @FXML
    PasswordField passwordField;

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private Parent passwordListFxml;
    private PasswordListController passwordListController;
    private Stage passwordListStage;

    @FXML
    private void initialize() {
        try {
            fxmlLoader.setLocation(getClass().getResource("/passwordsList.fxml"));
            passwordListFxml = fxmlLoader.load();
            passwordListController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openPasswordList(ActionEvent actionEvent, AccountsMapStorage accountsMapStorage) throws IOException {
        passwordListController.setAccountsMapStorage(accountsMapStorage);
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

    public void selectFileOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file!=null)
            filePathField.setText(file.getAbsolutePath());
    }

    public void decryptBtnOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Data entry error");
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
        byte[] fileBytes = Files.readAllBytes(file.toPath());

        char[] pass = passwordField.getText().toCharArray();

//        String fileContent = AES.encrypt(new String(fileBytes, StandardCharsets.UTF_8), pass);
        String fileContent = AES.decrypt(new String(fileBytes, StandardCharsets.UTF_8), pass);
        System.out.println(fileContent);

        //****************************************************
        AccountsMapStorage accountsMapStorage = new AccountsMapStorage(fileContent);
        openPasswordList(actionEvent, accountsMapStorage);
    }

    public void createNewBtnOnAction(ActionEvent actionEvent) throws IOException {
        openPasswordList(actionEvent, new AccountsMapStorage());
    }
}
