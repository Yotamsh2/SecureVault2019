//package com.securevault19.securevault2019.record;
//
//import androidx.room.Entity;
//
//@Entity (tableName = "creditCard_table")
//public class CreditCard extends Record {
//
////    @PrimaryKey(autoGenerate = true)
////    //public int recordID;
//
//    //exclusive variables
//    private int cardNumber;
//    private int CVV;
//    private int expireYear;
//    private int expireMonth;
//
//
//    // Constructor
//    public CreditCard(String title, int cardNumber) {
//        setCardNumber(cardNumber);
//        setCVV(CVV);
//        setTitle(title);
//        // Card expiring date
//        setExpireMonth(expireMonth);
//        setExpireYear(expireYear);
//
//        setDateCreated(dateCreated);
//        setLastModified(lastModified);
//
//
//    }
//
//
//    // Setters
//
//
//    public void setCardNumber(int cardNumber) {
//        this.cardNumber = cardNumber;
//    }
//
//    public void setCVV(int CVV) {
//        this.CVV = CVV;
//    }
//
//    public void setExpireYear(int expireYear) {
//        this.expireYear = expireYear;
//    }
//
//    public void setExpireMonth(int expireMonth) {
//        this.expireMonth = expireMonth;
//    }
//
//    //override setters
//    @Override
//    public void setRecordID(int recordID) {
//
//    }
//
//    @Override
//    public void setTitle(String title) {
//
//    }
//
//    @Override
//    public void setPassword(String password) {
//
//    }
//
//    @Override
//    public void setWebsite(String website) {
//
//    }
//
//    @Override
//    public void setEmail(String email) {
//
//    }
//
//    @Override
//    public void setExpiringDate(String expiringDate) {
//
//    }
//
//    @Override
//    public void setDateCreated(String dateCreated) {
//
//    }
//
//    @Override
//    public void setLastModified(String lastModified) {
//
//    }
//
//    @Override
//    public void setCategory(String category) {
//
//    }
//
//    // Getters
//
//
//    public int getCardNumber() {
//        return cardNumber;
//    }
//
//    public int getCVV() {
//        return CVV;
//    }
//
//    public int getExpireYear() {
//        return expireYear;
//    }
//
//    public int getExpireMonth() {
//        return expireMonth;
//    }
//
//    //override getters
//    @Override
//    public int getRecord_ID() {
//        return 0;
//    }
//
//    @Override
//    public String getTitle() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getWebsite() {
//        return null;
//    }
//
//    @Override
//    public String getEmail() {
//        return null;
//    }
//
//    @Override
//    public String getExpiringDate() {
//        return null;
//    }
//
//    @Override
//    public String getDateCreated() {
//        return null;
//    }
//
//    @Override
//    public String getLastModified() {
//        return null;
//    }
//
//    @Override
//    public String getCategory() {
//        return null;
//    }
//
//    @Override
//    public void addCategory() {
//
//    }
//
//    @Override
//    public void addTextInbox() {
//
//    }
//}
