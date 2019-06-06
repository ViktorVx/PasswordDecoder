package org.pva.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.pva.domain.interfaces.IAccauntManipulations;

import java.util.*;

public class AccountsMapStorage implements IAccauntManipulations {

    private ObservableList<Account> accounts = FXCollections.observableArrayList();

    public AccountsMapStorage() {
    }

    public AccountsMapStorage(String jsonString) {
        //todo delete fulfilling - make real json-deserialization
        for (int i = 1; i < 10; i++) {
            Account account = new Account();
            account.setResource("google.com");
            account.setLogin(String.format("helloworld%d@gmail.com", i));
            account.setPassword(String.valueOf(i*370));
            add(account);
        }
    }

    public ObservableList<Account> getAccounts() {
        return accounts;
    }

    @Override
    public void add(Account account) {
        account.setId(UUID.randomUUID());
        accounts.add(account);
    }

    @Override
    public void update(Account account) {
//        if (account.getId() == null || !accounts.containsKey(account.getId())) add(account);
//        accounts.put(account.getId().toString(), account);
    }

    @Override
    public void delete(Account account) {
        accounts.remove(account);
    }

    public String toJson() {
        //todo make real json-serialization
        return "{}";
    }

}
