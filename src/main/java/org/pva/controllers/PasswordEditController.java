package org.pva.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.pva.domain.Account;
import org.pva.domain.AccountsMapStorage;

import java.net.URL;
import java.util.ResourceBundle;


public class PasswordEditController implements Initializable {
    //todo add images to copy to clipboard button and show password-button

    @FXML
    private TextField txtResource;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private PasswordField pswPassword;

    private Account account;
    private AccountsMapStorage accountsMapStorage;
    private ResourceBundle resourceBundle;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
    }

    //******************************************************************************************************************

    public void setAccount(Account account) {
        if (account != null) {
            this.account = account;
            txtResource.setText(account.getResource());
            txtLogin.setText(account.getLogin());
            txtPassword.setText(account.getPassword());
            pswPassword.setText(account.getPassword());
        } else {
            this.account = null;
            txtResource.setText(null);
            txtLogin.setText(null);
            txtPassword.setText(null);
            pswPassword.setText(null);
        }

    }

    public void setAccountsMapStorage(AccountsMapStorage accountsMapStorage) {
        this.accountsMapStorage = accountsMapStorage;
    }

    //******************************************************************************************************************

    public void btnCancelOnAction(ActionEvent actionEvent) {
        actionClose(actionEvent);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        //todo add validation of entered data
        if (account == null) {
            Account account = new Account();
            account.setResource(txtResource.getText());
            account.setLogin(txtLogin.getText());
            account.setPassword(txtPassword.getText());
            accountsMapStorage.add(account);
        } else {
            account.setResource(txtResource.getText());
            account.setLogin(txtLogin.getText());
            account.setPassword(txtPassword.getText());
        }
        actionClose(actionEvent);
    }

    //******************************************************************************************************************

    private void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}
