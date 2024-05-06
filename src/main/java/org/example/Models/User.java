package org.example.Models;

public class User {
    private int userId;
    private String name;
    private String email;
    private String password;

    public User(int userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
