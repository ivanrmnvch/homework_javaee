package com.entities.store.data;

import java.io.Serializable;

public class User implements Serializable {
    String userId;
    String text;
    String login;
    String email;
    String role;
    boolean userIsAuthorized;

    public User() {
        userId = "";
        text = "Hello!";
        login = "";
        email = "";
        role = "user";
        userIsAuthorized = false;
    }
    public User(String login) {
        userId = "";
        text = "Hello!";
        this.login = login;
        email = "";
        role = "user";
        userIsAuthorized = false;
    }
    public User(String userId, String login, String email, String role, boolean userIsAuthorized) {
        this.userId = userId;
        text = "Hello!";
        this.login = login;
        this.email = email;
        this.role = role;
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
        if (userIsAuthorized) {
            return "Hello " + login + "!";
        }
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
