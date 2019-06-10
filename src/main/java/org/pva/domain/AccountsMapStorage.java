package org.pva.domain;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.pva.domain.interfaces.IAccauntManipulations;

import java.io.IOException;
import java.util.*;

public class AccountsMapStorage implements IAccauntManipulations {

    private ObservableList<Account> accounts = FXCollections.observableArrayList();

    public AccountsMapStorage() {
    }

    public AccountsMapStorage(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Class<?> clz = Class.forName("org.pva.domain.Account");
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, clz);
            accounts = FXCollections.observableArrayList((Collection<? extends Account>) objectMapper.readValue(jsonString, type));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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
