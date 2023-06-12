package com.project.ui_eats.model;

public class User {
    public int account_id;
    public String username;
    public String password;
    public String email;
    public String full_name;

    @Override
    public String toString() {
        return "User{" +
                "account_id=" + account_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", full_name='" + full_name + '\'' +
                '}';
    }
}