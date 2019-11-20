package com.securevault19.securevault2019.user;

import android.util.Log;

public class CurrentUser {
    public static String currentUser;
    public static String secureLevel;

    public CurrentUser(String email)
    {
        this.currentUser = email;
        Log.d("userr", "CurrentUser class:  "+currentUser);
    }

    public static String getCurrentUser() {
        return currentUser;
    }
}