package com.securevault19.securevault2019.Record;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "drivingLicence_table")
public class DrivingLicence extends Record {

    @PrimaryKey(autoGenerate = true)
    public int recordID;

    //exclusive variables
    private int licenceNumber;
    private int expireYear;
    private int expireMonth;
    private int expireDay;




// Constructor

    public DrivingLicence(){
        setLicenceNumber(licenceNumber);
        setExpireDay(expireDay);
        setExpireMonth(expireMonth);
        setExpireYear(expireYear);
        setTitle(title);
        setWebsite(website);        // website of goverment of driving licence gov.co.il?
        setExpiringDate(expiringDate);
        setLastModified(lastModified);


    }

    // Setters


    public void setLicenceNumber(int licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public void setExpireYear(int expireYear) {
        this.expireYear = expireYear;
    }

    public void setExpireMonth(int expireMonth) {
        this.expireMonth = expireMonth;
    }

    public void setExpireDay(int expireDay) {
        this.expireDay = expireDay;
    }

    // Override setters

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


    public int getLicenceNumber() {
        return licenceNumber;
    }

    public int getExpireYear() {
        return expireYear;
    }

    public int getExpireMonth() {
        return expireMonth;
    }

    public int getExpireDay() {
        return expireDay;
    }

    // Override getters

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
