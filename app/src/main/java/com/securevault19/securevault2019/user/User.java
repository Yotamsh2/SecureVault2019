package com.securevault19.securevault2019.user;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    //    private int ID;   // no need ? because there is only one user to the app.

    @PrimaryKey         // no need?
    @NonNull
    @ColumnInfo(name = "first_name")
    private String firstName;
    @ColumnInfo(name = "lastName") private String lastName;
    @ColumnInfo(name = "DateOfBirth") private String DateOfBirth;
    @ColumnInfo(name = "email")private String email;
    @ColumnInfo(name = "optionalQuestion")private String optionalQuestion;
    @ColumnInfo(name = "optionalAnswer") private String optionalAnswer;

    @ColumnInfo(name = "master_password")
    private String masterPassword;
    @ColumnInfo(name = "secureLevel")  private String secureLevel;

    public User(String firstName, String lastName, String DateOfBirth, String email, String optionalQuestion, String optionalAnswer, String masterPassword, String secureLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DateOfBirth = DateOfBirth;
        this.email = email;
        this.optionalQuestion = optionalQuestion;
        this.optionalAnswer = optionalAnswer;
        this.masterPassword = masterPassword;
        this.secureLevel = secureLevel;

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
