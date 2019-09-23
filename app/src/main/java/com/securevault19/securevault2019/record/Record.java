package com.securevault19.securevault2019.record;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import cryptography.Cryptography;

@Entity(tableName = "record_table")
public class Record {





    @PrimaryKey(autoGenerate = true)
    public int recordID;

    public String title ;
    public String category  ;
    public String dateCreated  ; //create dateCreated class and make a toString method.
    public String lastModified  ; //create lastModified class and make a toString method.
    public String password   ;
    public String email  ;
    public String website  ;
    public String expiringDate  ; //addition to the class diagram
    private String userName;
    private int accountNumber;
    private long IBAN;
    private int bankNumber;
    private String address;
    private int cardNumber;
    private int CVV;
    private int expireYear;
    private int expireMonth;
    private int expireDay;
    private String publicKey;
    private String privateKey;
    private String walletGenerationSeed;
    private int licenceNumber;
    private String serviceName; //email
    private int passportNumber;
    private String issuanceDate;
    //to add expiring date
    private String issuancePlace;
    private String secret_question;


    //public void setRecordID(int recordID) {
    //    this.recordID = recordID;
    //}
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public void setExpiringDate(String expiringDate) {
        this.expiringDate = expiringDate;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setIBAN(long IBAN) {
        this.IBAN = IBAN;
    }
    public void setBankNumber(int bankNumber) {
        this.bankNumber = bankNumber;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setCVV(int CVV) {
        this.CVV = CVV;
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
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
    public void setWalletGenerationSeed(String walletGenerationSeed) {
        this.walletGenerationSeed = walletGenerationSeed;
    }
    public void setLicenceNumber(int licenceNumber) {
        this.licenceNumber = licenceNumber;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }
    public void setIssuanceDate(String issuanceDate) {
        this.issuanceDate = issuanceDate;
    }
    public void setIssuancePlace(String issuancePlace) {
        this.issuancePlace = issuancePlace;
    }
    public void setSecret_question(String secret_question) {
        this.secret_question = secret_question;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public int getRecordID() {
        return recordID;
    }
    public String getUserName() {
        return userName;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public long getIBAN() {
        return IBAN;
    }
    public int getBankNumber() {
        return bankNumber;
    }
    public String getAddress() {
        return address;
    }
    public int getCardNumber() {
        return cardNumber;
    }
    public int getCVV() {
        return CVV;
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
    public String getPublicKey() {
        return publicKey;
    }
    public String getPrivateKey() {
        return privateKey;
    }
    public String getWalletGenerationSeed() {
        return walletGenerationSeed;
    }
    public int getLicenceNumber() {
        return licenceNumber;
    }
    public String getServiceName() {
        return serviceName;
    }
    public int getPassportNumber() {
        return passportNumber;
    }
    public String getIssuanceDate() {
        return issuanceDate;
    }
    public String getIssuancePlace() {
        return issuancePlace;
    }
    public String getSecret_question() {
        return secret_question;
    }
    public String getTitle() {
        return title;
    }
    public String getCategory() {
        return category;
    }
    public String getDateCreated() {
        return dateCreated;
    }
    public String getLastModified() {
        return lastModified;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getWebsite() {
        return website;
    }
    public String getExpiringDate() {
        return expiringDate;
    }

}

