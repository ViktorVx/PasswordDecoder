package org.pva.controllers;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.pva.domain.Account;
import org.pva.domain.AccountsMapStorage;

import java.io.IOException;

public class PasswordListController {


    @FXML
    private TableView tableAccounts;

    @FXML
    private TableColumn columnResource;

    @FXML
    private TableColumn columnLogin;

    @FXML
    private TableColumn columnPassword;

    private AccountsMapStorage accountsMapStorage;

    private FXMLLoader loader = new FXMLLoader();

    private Stage editStage;
    private Parent parentEdit;
    private PasswordEditController editController;

    //todo add Unit-tests

    @FXML
    private void initialize() {
        columnResource.setCellValueFactory(new PropertyValueFactory<Account, String>("resource"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<Account, String>("login"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));

        try {
            loader.setLocation(getClass().getResource("/passwordEdit.fxml"));
            parentEdit = loader.load();
            editController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        initListeners();
    }

    private void initListeners() {
        tableAccounts.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                openEditWindow(event, (Account) tableAccounts.getSelectionModel().getSelectedItem());
            }
        });
    }

    //******************************************************************************************************************

    public void setAccountsMapStorage(AccountsMapStorage accountsMapStorage) {
        this.accountsMapStorage = accountsMapStorage;
        if (accountsMapStorage == null) return;

        accountsMapStorage.getAccounts().addListener((ListChangeListener<Account>) c -> System.out.println("Observable list has been changed!!!"));
        tableAccounts.setItems(accountsMapStorage.getAccounts());
    }

    //******************************************************************************************************************

    public void btnAddNewOnAction(ActionEvent actionEvent) {
        openEditWindow(actionEvent, null);
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
        Account account = (Account) tableAccounts.getSelectionModel().getSelectedItem();
        openEditWindow(actionEvent, account);
    }

    public void btnDeleteOnAction() {
        Account account = (Account) tableAccounts.getSelectionModel().getSelectedItem();
        accountsMapStorage.delete(account);
    }

    //******************************************************************************************************************

    private void openEditWindow(Event event, Account account) {
        if (editStage == null) {
            editStage = new Stage();
            editStage.setTitle(account == null ? "New account" : "Account");
            editStage.setResizable(false);
            editStage.setScene(new Scene(parentEdit, 350, 120));
            //***
            editStage.initModality(Modality.WINDOW_MODAL);
            editStage.initOwner(((Node) event.getSource()).getScene().getWindow());
        }
        editController.setAccountsMapStorage(accountsMapStorage);
        editController.setAccount(account);
        //***
        editStage.show();
    }
}
