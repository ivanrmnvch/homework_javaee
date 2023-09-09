package com.entities.store.data;

import java.io.Serializable;

public class User implements Serializable {
    String userId;
    String text;
    String login;
    boolean userIsAuthorized;

    public User() {
        userId = "";
        login = "";
        text = "Hello!";
        userIsAuthorized = false;
    }
    public User(String login) {
        userId = "";
        this.login = login;
        text = "Hello!";
        userIsAuthorized = false;
    }
    public User(String userId, String login, boolean userIsAuthorized) {
        this.userId = userId;
        this.login = login;
        this.userIsAuthorized = userIsAuthorized;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public boolean getUserIsAuthorized() {
        return userIsAuthorized;
    }
    public void setUserIsAuthorized(boolean userIsAuthorized) {
        this.userIsAuthorized = userIsAuthorized;
    }
}
