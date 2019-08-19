package com.securevault19.securevault2019.Record;

import androidx.room.PrimaryKey;

public class DrivingLicence extends Record {

    @PrimaryKey(autoGenerate = true)
    public int recordID;

    //exclusive variables
    private int licenceNumber;


    /* ---For valid-thru date and issuance-date-------
     A DILEMA:
    make a new object that will return the date?
    for example:

    private ValidThru;   (that includes: VT.year , VT.month , VT.day)

    or

    private int expireYear
    private int expireMonth
    private int expireDay

    or

    private String expiringDate
    ---------------------------------------*/

    public DrivingLicence(){
        //setters

    }

    //setters

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
