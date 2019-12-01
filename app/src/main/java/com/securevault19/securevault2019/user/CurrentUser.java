package com.securevault19.securevault2019.user;

import android.util.Log;

public class CurrentUser {
    public static String currentUserr;
    public static int secureLevel;

    private static CurrentUser currentUser = null;

    private static User user = null ;

    private CurrentUser(User user) {
        //this.currentUserr = email;             // id of user
        //Log.d("userr", "CurrentUser class:  "+currentUserr);

        this.user = user;
        secureLevel = Integer.parseInt(user.getSecureLevel());
        Log.d("CurrentUserTest","entered c'tor");
        Log.d("CurrentUserTest", "this: " + this.user.getEmail() + " user: " + user.getEmail() );
    }

    public static synchronized CurrentUser getInstance(User user){
        // currentUser = null is for the moment when you entered with one user, and exited, and entered with another user.
        // so the currentUser make it null and put the new user in to the currentUser.
      currentUser = null;
        if(currentUser == null)
            currentUser = new CurrentUser(user);
        return currentUser;
    }


    public static synchronized User getInstance(){
        return user;
    }






}