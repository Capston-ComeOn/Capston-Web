package com.spring.capstone.backend.service.dto;

public class LoggedInAccount {
    public static final String SESSION_USER = "sessionAccount";

    private long id;
    private String userName;

    public LoggedInAccount(long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}
