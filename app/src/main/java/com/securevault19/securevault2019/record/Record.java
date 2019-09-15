package com.securevault19.securevault2019.record;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "record_table")
public class Record {

    // Records Origin Variables
    @PrimaryKey (autoGenerate = true)
    private int recordID;
    private String title = null;
    private String category = null;
    private String dateCreated = null; //create dateCreated class and make a toString method.
    private String lastModified = null; //create lastModified class and make a toString method.
    private String password = null;
    private String email = null;
    private String website = null;
    private String expiringDate = null; //addition to the class diagram

    // Bank Account Variables
    private String userName;
    private int accountNumber;
    private long IBAN;
    private int bankNumber;
    private String address;

    // CreditCard Variables
    private int cardNumber;
    private int CVV;
    private int expireYear;
    private int expireMonth;

    // CryptoCurrency Variables
    private String publicKey;
    private String privateKey;
    private String walletGenerationSeed;

    // DriverLicence Variables
    private int licenceNumber;
    // private int expireYear;            allready exists at top
    //  private int expireMonth;           allready exists at top
    private int expireDay;

    // email Variables
    //private String userName;            allready exists at top
    private String serviceName;

    // Online Shopping app Variables
    //private String userName;          allready exists at top

    //Passport Variables
    private int passportNumber;
    private String issuanceDate;
    //to add expiring date
    private String issuancePlace;


    // socialMedia App
    // private String userName;         allready exists at top

    // Website Variables
    // private String userName;         allready exists at top
    private String other;          // for additional signing details such as asved authentications question\answers


    public Record(String category, String dateCreated, String lastModified, String password, String email, String website, String expiringDate, String userName, int accountNumber, long IBAN, int bankNumber, String address, int cardNumber, int CVV, int expireYear, int expireMonth, String publicKey, String privateKey, String walletGenerationSeed, int licenceNumber, int expireDay, String serviceName, int passportNumber, String issuanceDate, String issuancePlace, String other, int recordID) {
        // no need for id
        this.category = category;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;
        this.password = password;
        this.email = email;
        this.website = website;
        this.expiringDate = expiringDate;
        this.userName = userName;
        this.accountNumber = accountNumber;
        this.IBAN = IBAN;
        this.bankNumber = bankNumber;
        this.address = address;
        this.cardNumber = cardNumber;
        this.CVV = CVV;
        this.expireYear = expireYear;
        this.expireMonth = expireMonth;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.walletGenerationSeed = walletGenerationSeed;
        this.licenceNumber = licenceNumber;
        this.expireDay = expireDay;
        this.serviceName = serviceName;
        this.passportNumber = passportNumber;
        this.issuanceDate = issuanceDate;
        this.issuancePlace = issuancePlace;
        this.other = other;
        this.recordID = recordID;
    }


    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public int getRecordID() {
        return recordID;
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

    public int getExpireDay() {
        return expireDay;
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

    public String getOther() {
        return other;
    }


//
//    public abstract void setRecordID(int recordID);
//    public abstract void setTitle(String title); //void instead of 'int'
//    public abstract void setPassword(String password); ////void instead of 'int'
//    public abstract void setWebsite(String website); //void instead of 'int'
//    public abstract void setEmail(String email); //void instead of 'int'
//    public abstract void setExpiringDate(String expiringDate); //addition to the class diagram
//
//    public abstract void setDateCreated(String dateCreated);
//    public abstract void setLastModified(String lastModified);
//    public abstract void setCategory(String category);
//
//    public abstract int getRecord_ID();
//    public abstract String getTitle();
//    public abstract String getPassword();
//    public abstract String getWebsite();
//    public abstract String getEmail();
//    public abstract String getExpiringDate();
//    public abstract String getDateCreated();
//    public abstract String getLastModified();
//    public abstract String getCategory();
//
//
//    public abstract void addCategory(); //void instead of 'int'
//    public abstract void addTextInbox(); //void instead of 'int'

}

