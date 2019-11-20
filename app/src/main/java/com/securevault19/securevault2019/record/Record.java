package com.securevault19.securevault2019.record;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "record_table")
public class Record implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int recordID;


    public String userTable;
    public String title = null;
    public String type = null;
    public String dateCreated = null; //create dateCreated class and make a toString method.
    public String lastModified = null; //create lastModified class and make a toString method.
    public String password = null;
    public String email = null;
    public String website = null;
    public String expiringDate = null; //addition to the class diagram
    private String userName;
    private String accountNumber;
    private String IBAN;
    private String bankNumber;
    private String address;
    private String cardNumber;
    private String CVV;
    private String expireYear;
    private String expireMonth;
    private String expireDay;
    private String publicKey;
    private String privateKey;
    private String walletGenerationSeed;
    private String licenceNumber;
    private String serviceName; //email
    private String passportNumber;
    private String issuanceDate;
    //to add expiring date
    private String issuancePlace;
    private String secret_question;         // not in use at the record Activity... //
    private String note;
    private String expitingDateNote;
    private String tagsNote;
    //    private String note;
    private String folder; //folder = category
    private boolean favorite;

    private String icon;



    public void setUserTable(String userTable) {
        this.userTable = userTable;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }
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

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public void setExpireYear(String expireYear) {
        this.expireYear = expireYear;
    }

    public void setExpireMonth(String expireMonth) {
        this.expireMonth = expireMonth;
    }

    public void setExpireDay(String expireDay) {
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

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setPassportNumber(String passportNumber) {
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

    public void setType(String type) {
        this.type = type;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }


    public void setNote(String note) {
        this.note = note;
    }


    public void setExpitingDateNote(String expitingDateNote) {
        this.expitingDateNote = expitingDateNote;
    }

    public void setTagsNote(String tagsNote) {
        this.tagsNote = tagsNote;
    }

    public int getRecordID() {
        return recordID;
    }

    public String getUserName() {
        return userName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getIBAN() {
        return IBAN;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCVV() {
        return CVV;
    }

    public String getExpireYear() {
        return expireYear;
    }

    public String getExpireMonth() {
        return expireMonth;
    }

    public String getExpireDay() {
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

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getPassportNumber() {
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

    public String getType() {
        return type;
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

    public String getFolder() {
        return folder;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public String getUserTable() {
        return userTable;
    }

    public String getIcon() {
        return icon;
    }

    public String getNote() {
        return note;
    }

    public String getExpitingDateNote() {
        return expitingDateNote;
    }

    public String getTagsNote() {
        return tagsNote;
    }
}

