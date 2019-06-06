package org.pva.domain;

import javafx.beans.property.SimpleStringProperty;

import java.util.UUID;

public class Account {

    private UUID id;

    private SimpleStringProperty resource = new SimpleStringProperty();

    private SimpleStringProperty login = new SimpleStringProperty();

    private SimpleStringProperty password = new SimpleStringProperty();

    public Account() {
    }

    public Account(String resource, String login, String password) {
        this.resource.set(resource);
        this.login.set(login);
        this.password.set(password);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getResource() {
        return resource.get();
    }

    public void setResource(String resource) {
        this.resource.set(resource);
    }

    public SimpleStringProperty resourceProperty() {
        return resource;
    }



    public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public SimpleStringProperty loginProperty() {
        return login;
    }



    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }
}
