package org.pva.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class LoginController {

    @FXML
    TextField filePathField;

    @FXML
    PasswordField passwordField;

    @FXML
    public void selectFileOnClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file!=null)
            filePathField.setText(file.getAbsolutePath());
    }

    @FXML
    public void decryptBtnOnClick() throws IOException {
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
        String fileContent = new String(fileBytes, StandardCharsets.UTF_8);
        System.out.println(fileContent);

    }

    @FXML
    public void createNewBtnOnClick() throws IOException {
        Stage passwordsList = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/passwordsList.fxml"));
        passwordsList.setTitle("Passwords");
        passwordsList.setScene(new Scene(root, 460, 280));
        passwordsList.show();
    }



}
