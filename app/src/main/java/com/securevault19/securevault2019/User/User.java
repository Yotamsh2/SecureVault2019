package com.securevault19.securevault2019.User;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    //    private int ID;   // no need ? because there is only one user to the app.

    @PrimaryKey         // no need?
    private String firstName;

    private String lastName;
    private String DateOfBirth;
    private String email;
    private String optionalQuestion;
    private String optionalAnswer;
    private String masterPassword;
    private String secureLevel;

    public User() {
    }

    //Setters

//    public void setID(int ID) {
//        this.ID = ID;
//    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOptionalQuestion(String optionalQuestion) {
        this.optionalQuestion = optionalQuestion;
    }

    public void setOptionalAnswer(String optionalAnswer) {
        this.optionalAnswer = optionalAnswer;
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }

    public void setSecureLevel(String secureLevel) {
        this.secureLevel = secureLevel;
    }


    //Getters

//    public int getID() {
//        return ID;
//    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getOptionalQuestion() {
        return optionalQuestion;
    }

    public String getOptionalAnswer() {
        return optionalAnswer;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public String getSecureLevel() {
        return secureLevel;
    }
}
