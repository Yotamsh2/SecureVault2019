package com.securevault19.securevault2019.Record;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cryptocurrency extends Record {

    @PrimaryKey(autoGenerate = true)
    public int recordID;

    // Exclusive variables
    private String publicKey;
    private String privateKey;
    private String walletGenerationSeed;

    public Cryptocurrency() {
        setPublicKey(publicKey);
        setPrivateKey(privateKey);
        setWalletGenerationSeed(walletGenerationSeed);
        setTitle(title);
        setDateCreated(dateCreated);
        setExpiringDate(expiringDate);
        setEmail(email);
        setPassword(password);
        setLastModified(lastModified);

    }


    // Setters

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setWalletGenerationSeed(String walletGenerationSeed) {
        this.walletGenerationSeed = walletGenerationSeed;
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


    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getWalletGenerationSeed() {
        return walletGenerationSeed;
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
