package com.project.ui_eats.model;

public class User {
    public String username;
    public String password;
    public String email;
    public String full_name;

    @Override
    public String toString() {
        return "User{" +
                " Username =" + username +
                ", Email ='" + email + '\'' +
                ", Password ='" + password + '\'' +
                ", Full Name ='" + full_name + '\'' +
                '}';
    }
}