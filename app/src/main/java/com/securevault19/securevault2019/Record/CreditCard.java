package com.securevault19.securevault2019.Record;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CreditCard extends Record {

    @PrimaryKey(autoGenerate = true)
    public int recordID;

    //exclusive variables
    private int cardNumber;

    /* ------------------------------------
     A DILEMA:
    make a new object that will return the card-expiring-date?
    for example:

    private ValidThru VT.year
    private ValidThru VT.month

    or

    private int expireYear
    private int expireMonth

    or

    private String expiringDate
    ---------------------------------------*/

    public CreditCard(int cardNumber) { //more components to be added
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
