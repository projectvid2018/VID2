package com.example.explorer.vid.start.activity;

public class User {

    String userId;
    String userNID;
    String userBirthDate;
    String userEmail;
    String userPassword;

    public User() {
    }

    public User(String userId, String userNID, String userBirthDate, String userEmail, String userPassword) {
        this.userId = userId;
        this.userNID = userNID;
        this.userBirthDate = userBirthDate;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNID() {
        return userNID;
    }

    public void setUserNID(String userNID) {
        this.userNID = userNID;
    }

    public String getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(String userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
