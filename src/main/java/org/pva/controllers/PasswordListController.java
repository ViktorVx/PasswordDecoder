package org.pva.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PasswordListController {

    @FXML
    public void addNewBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage passwordsList = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/passwordEdit.fxml"));
        passwordsList.setTitle("New account");
        passwordsList.setResizable(false);
        passwordsList.setScene(new Scene(root, 230, 150));
        passwordsList.show();
    }
}
