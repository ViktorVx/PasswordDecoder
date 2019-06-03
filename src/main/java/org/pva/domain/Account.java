package org.pva.domain;

import javafx.beans.property.SimpleStringProperty;

public class Account {

    private SimpleStringProperty resource;

    private SimpleStringProperty login;

    private SimpleStringProperty password;

    public Account() {
    }

    public Account(String resource, String login, String password) {
        this.resource.set(resource);
        this.login.set(login);
        this.password.set(password);
    }

    public String getResource() {
        return resource.get();
    }

    public SimpleStringProperty resourceProperty() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource.set(resource);
    }

    public String getLogin() {
        return login.get();
    }

    public SimpleStringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
