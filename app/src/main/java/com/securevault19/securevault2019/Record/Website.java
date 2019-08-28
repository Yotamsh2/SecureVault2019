package com.securevault19.securevault2019.Record;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "website_table")
public class Website extends Record {


    @PrimaryKey(autoGenerate = true)
    public int recordID;


    // Exclusive variables
    private String userName;
    private String other;          // for additional signing details such as asved authentications question\answers



    // Constructor
    public Website() {
        setUserName(userName);
        setTitle(title);
        setOther(other);
        setPassword(password);
        setEmail(email);
        setDateCreated(dateCreated);
        setExpiringDate(expiringDate);
        setLastModified(lastModified);


    }


    // Setters

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOther(String other) {
        this.other = other;
    }


// Override Setters

    @Override
    public void setRecordID(int recordID) {

    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public void setWebsite(String website) {

    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public void setExpiringDate(String expiringDate) {

    }

    @Override
    public void setDateCreated(String dateCreated) {

    }

    @Override
    public void setLastModified(String lastModified) {

    }

    @Override
    public void setCategory(String category) {

    }


    // Getters


    public String getUserName() {
        return userName;
    }

    public String getOther() {
        return other;
    }

    // Override Getters

    @Override
    public int getRecord_ID() {
        return 0;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getWebsite() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getExpiringDate() {
        return null;
    }

    @Override
    public String getDateCreated() {
        return null;
    }

    @Override
    public String getLastModified() {
        return null;
    }

    @Override
    public String getCategory() {
        return null;
    }

    @Override
    public void addCategory() {

    }

    @Override
    public void addTextInbox() {

    }


}
