package com.securevault19.securevault2019.record;

import androidx.room.Entity;

@Entity(tableName = "password_table")
public class Passport extends Record {

//    @PrimaryKey(autoGenerate = true)
//    public int recordID;

    //exclusive variables
    private int passportNumber;
    private String issuanceDate;
    //to add expiring date
    private String issuancePlace;

    public Passport() {
        setPassportNumber(passportNumber);
        setIssuanceDate(issuanceDate);
        setIssuancePlace(issuancePlace);
        setDateCreated(dateCreated);
        setLastModified(lastModified);
        setExpiringDate(expiringDate);
    }

    //setters
    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setIssuanceDate(String issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    public void setIssuancePlace(String issuancePlace) {
        this.issuancePlace = issuancePlace;
    }

    //override setters
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

    //getters
    public int getPassportNumber() {
        return passportNumber;
    }

    public String getIssuanceDate() {
        return issuanceDate;
    }

    public String getIssuancePlace() {
        return issuancePlace;
    }

    //override getters
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
