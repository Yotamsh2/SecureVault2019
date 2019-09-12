package com.securevault19.securevault2019.record;


import androidx.room.PrimaryKey;

public abstract class Record {

    @PrimaryKey(autoGenerate = true)
    public int recordID;


    public String title = null;
    public String category = null;
    public String dateCreated = null; //create dateCreated class and make a toString method.
    public String lastModified = null; //create lastModified class and make a toString method.
    public String password = null;
    public String email = null;
    public String website = null;
    public String expiringDate = null; //addition to the class diagram


    public abstract void setRecordID(int recordID);
    public abstract void setTitle(String title); //void instead of 'int'
    public abstract void setPassword(String password); ////void instead of 'int'
    public abstract void setWebsite(String website); //void instead of 'int'
    public abstract void setEmail(String email); //void instead of 'int'
    public abstract void setExpiringDate(String expiringDate); //addition to the class diagram

    public abstract void setDateCreated(String dateCreated);
    public abstract void setLastModified(String lastModified);
    public abstract void setCategory(String category);

    public abstract int getRecord_ID();
    public abstract String getTitle();
    public abstract String getPassword();
    public abstract String getWebsite();
    public abstract String getEmail();
    public abstract String getExpiringDate();
    public abstract String getDateCreated();
    public abstract String getLastModified();
    public abstract String getCategory();


    public abstract void addCategory(); //void instead of 'int'
    public abstract void addTextInbox(); //void instead of 'int'

}

