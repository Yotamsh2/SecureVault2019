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

import com.securevault19.securevault2019.user.User;

import javax.crypto.spec.SecretKeySpec;

import cryptography.Cryptography;
import local_database.DatabaseClient;
import view.entrance.SignUp_Activity;
import view.explorer.CategoryList_Activity;
import view.explorer.PatternLockView_Activity;


@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity {

    private int counter;
    // added for testing //
    private User user;
    private int flag = 0;
    private Cryptography cryptography = new Cryptography();
    //                  //
    @Override
    protected void onCreate(Bundle saveBtndInstanceState) {
        super.onCreate(saveBtndInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.button);
        ImageView logo = findViewById(R.id.logo);
        Button forgotPass = findViewById((R.id.forgotPass));
        Button signUp = findViewById(R.id.signUp);
        LinearLayout signInForm = findViewById(R.id.signInForm);
        final EditText userName = findViewById(R.id.userName);
        final EditText password = findViewById(R.id.password_EditText);
        final Button buttonSignIn = findViewById(R.id.signIn);
        counter = 3;


        // for testing //
        userName.setText("A");
        password.setText("1");
        //------------//


        //Animation Sets
        Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoomin);
        final Animation animation2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bottomtotop);
        final Animation animation3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.buttonpush_anim);
        logo.startAnimation(animation1);
        signInForm.startAnimation(animation2);


    }

    public void signUp(View view) {

        Intent intent = new Intent(this, SignUp_Activity.class);
        String a = "test123";
        intent.putExtra("BLABLA",a);
        this.startActivity(intent);
    }

    public void forgotPass(View view) {

    }

    // At this onClick method
    // we are checking if the user is exists .
    // if he is, great
    // if the user name correct and pass not, incorrect
    // if the user dosent exist, incorrect
    // works the same as user name correct and pass not.
    @SuppressLint("StaticFieldLeak")
    public void signIn(View view) {

        final EditText userName = findViewById(R.id.userName);
        final EditText password = findViewById(R.id.password_EditText);
        final String firstName = userName.getText().toString();
        final String masterPassword = password.getText().toString();
        Log.e("check2", " " + firstName + " " + masterPassword);
        //user =  new User("Test", null, null, null, null, null, null, null);
        Log.e("test2", "" + flag);



        new AsyncTask<User, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.e("test2", "" + flag);
            }

            @Override
            protected Void doInBackground(User... users) {
                Log.e("phase1", "Before reading From DATABASE");

                // searching for user //
                try {
                    user = DatabaseClient.getInstance(getApplication()).getRecordDatabase2().daoUser().LogInConfirmation(cryptography.encrypt(firstName), cryptography.encryptWithKey(firstName,masterPassword));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // ------------------ //


                if (user != null) {  // if user found.
                    try {
                        if ((firstName.equals(cryptography.decrypt(user.getFirstName(),firstName))) && (masterPassword.equals(cryptography.decrypt(user.getMasterPassword(),firstName)))) {
                            flag = 1;
                        } else {
                            // one of the params are not correct
                            //flag = 2;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else            // user not found, flag = 0;
                    Log.e("phase5", "User = null ");
                return null;
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (flag == 1) {
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), CategoryList_Activity.class);
                    String user = userName.getText().toString();
                    intent.putExtra("CRYPTO_KEY",user);
                    startActivity(intent);
                } else {
                  Toast.makeText(getApplicationContext(), "Wrong UserName or Password", Toast.LENGTH_LONG).show();
                }

            }
        }.execute();

    }
}
