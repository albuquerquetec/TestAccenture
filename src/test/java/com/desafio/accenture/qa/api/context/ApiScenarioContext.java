package com.desafio.accenture.qa.api.context;

import java.util.ArrayList;
import java.util.List;

public class ApiScenarioContext {

    private String userName;
    private String password;
    private String userId;
    private String token;
    private final List<String> availableIsbns = new ArrayList<>();
    private final List<String> rentedIsbns = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getAvailableIsbns() {
        return availableIsbns;
    }

    public List<String> getRentedIsbns() {
        return rentedIsbns;
    }
}
