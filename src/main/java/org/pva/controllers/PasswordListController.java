package org.pva.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PasswordListController {

    public void addNewBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/passwordEdit.fxml"));
        stage.setTitle("New account");
        stage.setResizable(false);
        stage.setScene(new Scene(root, 350, 120));
        //***
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        //***
        stage.show();
    }
}
