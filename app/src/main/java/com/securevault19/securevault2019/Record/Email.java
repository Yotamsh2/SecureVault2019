package com.securevault19.securevault2019.Record;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "email_table")
public class Email extends Record {

    @PrimaryKey(autoGenerate = true)
    private int recordID;

    //exclusive variables
    private String userName;
    private String serviceName;


    public Email() {
        setUserName(userName);
        setWebsite(website);
        setTitle(title);
        setDateCreated(dateCreated);
        setLastModified(lastModified);
        setExpiringDate(expiringDate);
    }

    //setters
    public void setUserName(String userName) {
        this.userName = userName;
    }



    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    //override setters
    @Override
    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setPassword(String password) {
        //sends to complicated functions(Cryptographic module)
        this.password = password;
    }

    @Override
    public void setWebsite(String website) {
        //invokes String from suitable text field
        this.website = website;
    }

    @Override
    public void setEmail(String email) {
        //invokes String from suitable text field
        this.email = email;

    }

    @Override
    public void setExpiringDate(String expiringDate) {
        //by whatever way we choose
        this.expiringDate = expiringDate;
    }

    @Override
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public void setCategory(String category) { //NO NEED

    }

    //getters
    public String getUserName() {
        return userName;
    }




    public String getServiceName() {
        return serviceName;
    }

    //override getters
    @Override
    public int getRecord_ID() {
        return recordID;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getWebsite() {
        return website;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getExpiringDate() {
        return expiringDate;
    }

    @Override
    public String getDateCreated() {
        return dateCreated;
    }

    @Override
    public String getLastModified() {
        return lastModified;
    }

    @Override
    public String getCategory() {
        return category;
    }


    @Override
    public void addCategory() {

    }

    @Override
    public void addTextInbox() {

    }
}
