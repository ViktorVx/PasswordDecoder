package org.pva.controllers;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.pva.domain.Account;
import org.pva.domain.AccountsMapStorage;
import org.pva.utils.locale.LocaleManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PasswordListController implements Initializable {


    @FXML
    private TableView tableAccounts;

    @FXML
    private TableColumn columnResource;

    @FXML
    private TableColumn columnLogin;

    @FXML
    private TableColumn columnPassword;

    @FXML
    private TextField txtSearch;

    private AccountsMapStorage accountsMapStorage;
    private ObservableList<Account> accountsBackUp;

    private String filePath;

    private FXMLLoader loader = new FXMLLoader();
    private Stage editStage;
    private Parent parentEdit;
    private PasswordEditController editController;

    //todo add Unit-tests

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnResource.setCellValueFactory(new PropertyValueFactory<Account, String>("resource"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<Account, String>("login"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));

        try {
            loader.setLocation(getClass().getResource("/passwordEdit.fxml"));
//            loader.setResources(resources);
            ResourceBundle resourceBundle = ResourceBundle.getBundle("locale", LocaleManager.getCurrentLang().getLocale());
//            loader.setResources(resources);
            loader.setResources(resourceBundle);
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

        //***!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        accountsBackUp = FXCollections.observableArrayList();
        accountsBackUp.addAll(accountsMapStorage.getAccounts());
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    private void savePasswordsFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Account> accounts = accountsMapStorage.getAccounts();
        try {
            objectMapper.writeValue(new FileOutputStream(filePath), accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void btnSearchOnAction(ActionEvent actionEvent) {
        accountsMapStorage.getAccounts().clear();
        for (Account account : accountsBackUp) {
            if (account.getResource().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                accountsMapStorage.getAccounts().add(account);
            }
        }
    }

    public void mnSaveOnAction(ActionEvent actionEvent) {
        savePasswordsFile(filePath);
    }
}
