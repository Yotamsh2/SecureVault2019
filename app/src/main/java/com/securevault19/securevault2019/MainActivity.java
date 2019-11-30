package com.securevault19.securevault2019;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.securevault19.securevault2019.user.CurrentUser;
import com.securevault19.securevault2019.user.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cryptography.Cryptography;
import view.entrance.NewUser_Activity;
import view.explorer.CategoryList_Activity;
import view.explorer.MainScreen_Activity;
import view.explorer.PatternLockView_Activity;
import view_model.records.User_ViewModel;


@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity {

    private String CRYPTO_KEY;
    private String ORIGIN = "MainActivity";
    private String pattern;
    private int counter;
    private Button signup,buttonSignIn,firebaseTest;
    private Animation animation2, animation3;
    private MediaPlayer mediaPlayer;
    private EditText email_EditText, password;
    // added for testing //
    private User user;
    private int flag = 0;
    private Cryptography cryptography = new Cryptography();
    private User_ViewModel viewModel;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +                        //at least 1 digit
                    "(?=\\S+$)" );                        //no white spaces
//                    "(?=.*[a-z])" +                    //at least 1 lower case letter
//                    "(?=.*[A-Z])" +                   //at least 1 upper case letter
//                    //"(?=.*[a-zA-Z])" +             //any letter
//                    "(?=.*[!@,)#&_'$*(%~$%^&+=])" + //at least 1 special character
//                    ".{8,24}" +                   //at least 8 characters, less than 24
//                    "$");

    //                  //
    @Override
    protected void onCreate(Bundle saveBtndInstanceState) {
        super.onCreate(saveBtndInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.button);
        ImageView logo = findViewById(R.id.logo);
        Button forgotPass = findViewById((R.id.forgotPass));
        signup = findViewById(R.id.newAccount);
        LinearLayout signInForm = findViewById(R.id.signInForm);
        email_EditText = findViewById(R.id.email);
        password = findViewById(R.id.password_EditText);
        buttonSignIn = findViewById(R.id.signIn);
        counter = 3;
        viewModel = ViewModelProviders.of(this).get(User_ViewModel.class);


        //////////////// for testing only ///////////////////////
//        email_EditText.setText("securevault2019@gmail.com");
//        password.setText("111222333");


        //Animation Sets
        Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoomin);
        animation2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bottomtotop);
        animation3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.buttonpush_anim);
        logo.startAnimation(animation1);
        signInForm.startAnimation(animation2);
        signup.startAnimation(animation2);
    }

    public void signUp(View view) {
        signup.startAnimation(animation3);
        mediaPlayer.start();
        Intent intent = new Intent(this, NewUser_Activity.class);
        this.startActivity(intent);
        overridePendingTransition(0, 0);
    }

    // At this onClick method
    // we are checking if the user is exists .
    // if he is, great
    // if the user name correct and pass not, incorrect
    // if the user dosent exist, incorrect
    // works the same as user name correct and pass not.
    @SuppressLint("StaticFieldLeak")
    public void signIn(View view) {
        buttonSignIn.startAnimation(animation3);
        mediaPlayer.start();
        final String email = this.email_EditText.getText().toString().trim();
        final String masterPassword = password.getText().toString();
        Log.e("check2", " " + email + " " + masterPassword);
        //user =  new User("Test", null, null, null, null, null, null, null);
        Log.e("test2", "" + flag);


        new AsyncTask<User, Void, Void>() {

            @Override
            protected Void doInBackground(User... users) {


                // searching for user //
                try {
                    Log.d("emailTest","user: " + cryptography.encrypt(email) + " password: " +
                            cryptography.encryptWithKey(email, masterPassword) );
                    // we are encrypting the text that the user entered and searching it in the DB
                    user = viewModel.LogInConfirmation(cryptography.encrypt(email), cryptography.encryptWithKey(email, masterPassword));
                    // getting the encrypted pattern!
                    pattern = user.getPatternLock();
                    flag = 1; // if user found.
                    Log.d("patternLockFromUser ",pattern);
                    Log.d("emailTest","user -" + cryptography.encrypt(email) + " password " +
                            cryptography.encryptWithKey(email, masterPassword) );
                   // new CurrentUser(cryptography.decrypt(user.getFirstName(),email));
                  //  CurrentUser.getInstance(user);
                    //Log.d("CurrentUserTest","Current UserMail is: "+ CurrentUser.getInstance().getEmail());



                } catch (Exception e) {
                    e.printStackTrace();
                }
                // ------------------ //



                // can take this if out
                // if user != null so we found match and should procced to the next Intent.
//                if (user != null) {  // if user found.
//                    try {
//                        if ((firstName.equals(cryptography.decrypt(user.getFirstName(), firstName))) && (masterPassword.equals(cryptography.decrypt(user.getMasterPassword(), firstName)))) {
//                            flag = 1;
//                        } else {
//                            // one of the params are not correct
//                            //flag = 2;
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } else            // user not found, flag = 0;
//                    Log.e("phase5", "User = null ");
                return null;
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (flag == 1) {
                    Intent intent = new Intent();
                    CRYPTO_KEY = MainActivity.this.email_EditText.getText().toString();
                    Log.d("patternCheck",pattern);
                    intent.putExtra("PATTERN",pattern);
                    intent.putExtra("CRYPTO_KEY", CRYPTO_KEY);
                    intent.putExtra("ORIGIN",ORIGIN);
                    CurrentUser.getInstance(user);

                    int userSecurityLevel = Integer.parseInt(user.getSecureLevel());

                    //if security level is higher than 1, open PatternLock Activity
                    if(userSecurityLevel > 1)
                        intent.setClass(getApplicationContext(),PatternLockView_Activity.class);
                    else         //if security level is at the lowest level(level 1) -> jump straight to MainScreen Activity
                        intent.setClass(getApplicationContext(), MainScreen_Activity.class);

                    password.setText("");
                    startActivity(intent);
                    overridePendingTransition(0, 0);

                } else {
                    Toast.makeText(getApplicationContext(), "Wrong UserName or Password", Toast.LENGTH_LONG).show();
                }

            }
        }.execute();


    }

    public boolean validatePassword(String Password) {
        String passwordInput = Password;

        if (passwordInput.isEmpty()) {
            return false;
        }
        if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            return false;
        }

        return true;
    }

    public boolean isValidEmail(String emailInput) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailInput);
        return matcher.matches();
    }

}
