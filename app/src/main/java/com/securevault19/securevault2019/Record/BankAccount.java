package com.securevault19.securevault2019.Record;
import androidx.room.PrimaryKey;
import androidx.room.Entity;


@Entity
public class BankAccount extends Record {

    @PrimaryKey(autoGenerate = true)
    public int recordID;

    //exclusive variables
    private String userName;
    private long IBAN;
    private int accountNumber;
    private int bankNumber;
    private String address;

    public BankAccount(){
        setTitle(title);
        setUserName(userName);
        setPassword(password);
        setAccountNumber(accountNumber);
        setIBAN(IBAN);
        setBankNumber(bankNumber);
        setAddress(address);
        setExpiringDate(expiringDate);
    }

    //setters
    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public void setIBAN(long IBAN) {
        //invokes String from suitable text field
        this.IBAN = IBAN;
    }

    public void setUserName(String userName) {
        //invokes String from suitable text field
        this.userName = userName;
    }

    public void setAccountNumber(int accountNumber) {
        //invokes String from suitable text field
        this.accountNumber = accountNumber;
    }

    public void setBankNumber(int bankNumber) {
        //invokes String from suitable text field
        this.bankNumber = bankNumber;
    }

    public void setAddress(String address) {
        //invokes String from suitable text field
        this.address = address;
    }

    //override setters
    @Override
    public void setTitle(String title) {
         this.title = title;
    }

    @Override
    public void setPassword(String password) {
        //sends to complicated functions(Cryptographic module)
    }

    @Override
    public void setWebsite(String website) {
        //invokes String from suitable text field
    }

    @Override
    public void setEmail(String email) {
        //invokes String from suitable text field
    }

    @Override
    public void setExpiringDate(String expiringDate) {
        //by whatever way we choose
    }

    public void setDateCreated(String dateCreated){}

    public void setLastModified(String lastModified){}

    public void setCategory(String category){} //NO NEED


    //getters
    public String getUserName() {
        return userName;
    }

    public long getIBAN() {
        return IBAN;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getBankNumber() {
        return bankNumber;
    }

    public String getAddress() {
        return address;
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
    public String getEmail() { return email;  }

    @Override
    public String getExpiringDate() {
        return expiringDate;
    }

    @Override
    public String getDateCreated(){return dateCreated; }

    @Override
    public String getLastModified(){ return lastModified; }

    @Override
    public String getCategory(){ return category; }



    @Override
    public void addTextInbox() {

    }

    @Override
    public void addCategory() {

    }
}