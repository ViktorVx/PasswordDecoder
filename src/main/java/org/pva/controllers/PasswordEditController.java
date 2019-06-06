package org.pva.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.pva.domain.Account;

public class PasswordEditController {
    //todo add images to copy to clipboard button and show password-button

    private Account account;

    public void setAccount(Account account) {
        this.account = account;
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
