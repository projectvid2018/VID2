package com.example.explorer.vid.start.activity;

public class People {

    String peopleId;
    String peopleNID;
    String peopleUserName;
    String peopleBirthDate;
    String peoplePhone;
    String peopleEmail;
    String peoplePassword;

    public People() {
    }

    public People(String peopleId, String peopleNID, String peopleUserName, String peopleBirthDate, String peoplePhone, String peopleEmail, String peoplePassword) {
        this.peopleId = peopleId;
        this.peopleNID = peopleNID;
        this.peopleUserName = peopleUserName;
        this.peopleBirthDate = peopleBirthDate;
        this.peoplePhone = peoplePhone;
        this.peopleEmail = peopleEmail;
        this.peoplePassword = peoplePassword;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public String getPeopleNID() {
        return peopleNID;
    }

    public String getPeopleUserName() {
        return peopleUserName;
    }

    public String getPeopleBirthDate() {
        return peopleBirthDate;
    }

    public String getPeoplePhone() {
        return peoplePhone;
    }

    public String getPeopleEmail() {
        return peopleEmail;
    }

    public String getPeoplePassword() {
        return peoplePassword;
    }
}
