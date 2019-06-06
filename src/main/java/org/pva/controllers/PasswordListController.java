package org.pva.controllers;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.pva.domain.Account;
import org.pva.domain.AccountsMapStorage;

import java.io.IOException;

public class PasswordListController {

    private AccountsMapStorage accountsMapStorage;

    @FXML
    private TableView tableAccounts;

    @FXML
    private TableColumn columnResource;

    @FXML
    private TableColumn columnLogin;

    @FXML
    private TableColumn columnPassword;

    @FXML
    private void initialize() {
        columnResource.setCellValueFactory(new PropertyValueFactory<Account, String>("resource"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<Account, String>("login"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));

//        if (accountsMapStorage == null) return;
//        accountsMapStorage.getAccounts().addListener(new ListChangeListener<Account>() {
//            @Override
//            public void onChanged(Change<? extends Account> c) {
//                System.out.println("Observable list has been changed!!!");
//            }
//        });
//
//        tableAccounts.setItems(accountsMapStorage.getAccounts());
    }

    public void setAccountsMapStorage(AccountsMapStorage accountsMapStorage) {
        this.accountsMapStorage = accountsMapStorage;
        if (accountsMapStorage == null) return;
        accountsMapStorage.getAccounts().addListener((ListChangeListener<Account>) c -> System.out.println("Observable list has been changed!!!"));

        tableAccounts.setItems(accountsMapStorage.getAccounts());
    }

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
