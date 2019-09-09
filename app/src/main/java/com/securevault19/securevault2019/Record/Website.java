package com.securevault19.securevault2019.Record;

import android.util.Log;
import android.widget.EditText;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.securevault19.securevault2019.R;
import java.util.Date;
import java.sql.Timestamp;


@Entity(tableName = "website_table")
public class Website extends Record {


//    @PrimaryKey(autoGenerate = true)
//    public int recordID;


    // Exclusive variables
    private String userName;
    private String other;          // for additional signing details such as asved authentications question\answers



    // Constructor
//    public Website(String title, String email, String userName,
//                   String password, String website, String expiringDate, String dateCreated,
//                   String lastModified, String other) {
//
//        Log.d("Website Constructor", "before Setters");
//        setUserName(userName);
//        setTitle(title);
//        setPassword(password);
//        setWebsite(website);
//        setEmail(email);
//        setExpiringDate(expiringDate);
//        setDateCreated(dateCreated);
//        setLastModified(lastModified);
//        setOther(other);
//
//
//
//    }


    // Setters

    public void setUserName(String userName) {
//        editText = editText.findViewById(R.id.userName);
//        this.userName = editText.getText().toString().trim();
        this.userName = userName;
    }

    public void setOther(String other) {
        this.other = other;
    }


// Override Setters

    @Override
    public void setRecordID(int recordID) {

    }

    @Override
    public void setTitle(String title) {
//        editText = editText.findViewById(R.id.title);
//        this.title = editText.getText().toString().trim();
        this.title = title;
    }

    @Override
    public void setPassword(String password) {
//        editText = editText.findViewById(R.id.password);
//        this.password = editText.getText().toString().trim();
        this.password = password;
    }

    @Override
    public void setWebsite(String website) {
//        editText = editText.findViewById(R.id.website);
//        this.website = editText.getText().toString().trim();
        this.website = website;
    }

    @Override
    public void setEmail(String email) {
//        editText = editText.findViewById(R.id.email);
//        this.email = editText.getText().toString().trim();
        this.email = email;
    }

    @Override
    public void setExpiringDate(String expiringDate) {
//        String[] expireDate = {null, null, null}; //array to include day, month, year
//
//        for (int i = 0; i < 3; i++) {
//            if (i == 0)
//                editText = editText.findViewById(R.id.expiringDate_day); // Initialize editText to expiring day
//            if (i == 1)
//                editText = editText.findViewById(R.id.expiringDate_month); // Initialize editText to expiring month
//            if (i == 2)
//                editText = editText.findViewById(R.id.expiringDate_year); // Initialize editText to expiring year
//
//            expireDate[i] = editText.getText().toString();
//        }
//        this.expiringDate = expireDate.toString();
        this.expiringDate = "1.1.1";
    }


    @Override
    public void setDateCreated(String dateCreated) {
        Date date = new Date();

        Timestamp timeStamp = new Timestamp(date.getTime());

        this.dateCreated = timeStamp.toString();
        Log.d(" ", dateCreated);

    }

    @Override
    public void setLastModified(String lastModified) {
//        editText = editText.findViewById(R.id.userName);
//        this.userName = editText.getText().toString().trim();
        Date date = new Date();

        Timestamp timeStamp = new Timestamp(date.getTime());

        this.lastModified = timeStamp.toString();
        Log.d("Website Constructor", "last modified setter");
    }

    @Override
    public void setCategory(String category) {
        this.category = "Website";
    }


    // Getters


    public String getUserName() {
        return userName;
    }

    public String getOther() {
        return other;
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
