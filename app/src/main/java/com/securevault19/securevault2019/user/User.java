package com.securevault19.securevault2019.user;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")private String email = "not specified";                // email is primary key
    @ColumnInfo(name = "first_name") private String firstName;
    @ColumnInfo(name = "lastName") private String lastName;
    @ColumnInfo(name = "DateOfRegistration",defaultValue = "CURRENT_TIMESTAMP") private String DateOfRegistration;
    @ColumnInfo(name = "optionalQuestion")private String optionalQuestion;
    @ColumnInfo(name = "optionalAnswer") private String optionalAnswer;
    @ColumnInfo(name = "master_password") private String masterPassword;
    @ColumnInfo(name = "secureLevel")  private String secureLevel;
    @ColumnInfo(name = "PatternLock") private String patternLock;

    public User(String firstName, String lastName, String DateOfRegistration, String email, String optionalQuestion,
                String optionalAnswer, String masterPassword, String secureLevel,String patternLock) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DateOfRegistration = DateOfRegistration;
        this.email = email;
        this.optionalQuestion = optionalQuestion;
        this.optionalAnswer = optionalAnswer;
        this.masterPassword = masterPassword;
        this.secureLevel = secureLevel;
        this.patternLock = patternLock;

    }


    //Setters

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfRegistration(String DateOfRegistration) { this.DateOfRegistration = DateOfRegistration; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOptionalQuestion(String optionalQuestion) { this.optionalQuestion = optionalQuestion; }

    public void setOptionalAnswer(String optionalAnswer) {
        this.optionalAnswer = optionalAnswer;
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }

    public void setSecureLevel(String secureLevel) {
        this.secureLevel = secureLevel;
    }

    public void setPatternLock(String patternLock) {
        this.patternLock = patternLock;
    }


    //Getters


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfRegistration() {
        return DateOfRegistration;
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

    public String getPatternLock() {
        return patternLock;
    }


}
