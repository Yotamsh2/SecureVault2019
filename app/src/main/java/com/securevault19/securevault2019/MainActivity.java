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

import local_database.DatabaseClient;
import view.entrance.SignUp_Activity;
import view.explorer.CategoryList_Activity;


@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity {

    private int counter;
    // added for testing //
    private User user;
    private int flag =0;
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


//        buttonSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signIn(userName, password, buttonSignIn);
//                buttonSignIn.startAnimation(animation3);
//                mediaPlayer.start();
//            }
//        });
//

    }

    public void signUp(View view) {

        Intent intent = new Intent(this, SignUp_Activity.class);
        this.startActivity(intent);
    }

    public void forgotPass(View view) {

    }
//
//    @SuppressLint("StaticFieldLeak")
//    public void signIn(final EditText userName, final EditText password, Button buttonSignIn) {
//        final String firstName = userName.getText().toString();
//        final String masterPassword = password.getText().toString();
//
//
//        // the AsyncTask is crucial! Without it, we will get MEMORY LEAK!
//        new AsyncTask<User, Void, Void>() {
//
//
//            @SuppressLint("WrongThread")
//            @Override
//            protected Void doInBackground(User... users) {
//                String firstName = userName.getText().toString();
//                String masterPassword = password.getText().toString();
//                Log.e("check1", "" + firstName + " " + masterPassword);
//                User test = DatabaseClient.getInstance(getApplication()).getRecordDatabase2().daoUser().LogInConfirmation(firstName, masterPassword);
//                Log.e("check1", "" + test.toString());
//                Log.e("check1", "" + test.getFirstName());
//
////                if (firstName.equals(test.getFirstName()) && masterPassword.equals(test.getMasterPassword())) {
////
////
////                }
//
//                return null;
//
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//                User test = DatabaseClient.getInstance(getApplication()).getRecordDatabase2().daoUser().LogInConfirmation(firstName, masterPassword);
//                if (firstName.equals(test.getFirstName()) && masterPassword.equals(test.getMasterPassword())) {
//                    finish();
//                }
//                else
//
//            }
//        }.execute();
//
//
//
//        //boolean test = DatabaseClient.getInstance(getApplication()).getRecordDatabase2().daoUser().LogInConfirmation(firstName, masterPassword);
//        //Log.e("check1", "" + test);
//
//
//    }
//        if (userName.getText().toString().equals("A") && password.getText().toString().equals("1")) {
//            //Checking if the user wants a pattern password:
//            if (true) {
//                Intent intent = new Intent(this, PatternLockView_Activity.class);
//                this.startActivity(intent);
//                //overridePendingTransition(R.anim.fadein_fast, 0);
//                finish();
//            } else {
//                Intent intent = new Intent(this, CategoryList_Activity.class);
//                this.startActivity(intent);
//                finish();
//            }
//        }
//        else{
//            counter--;
//            Toast.makeText(getApplicationContext(), "UserName & Password Incorrect" + "\n" + "Still Have " + counter + " To Try", Toast.LENGTH_LONG).show();
//
//            if (counter == 0) {
//                Toast.makeText(getApplicationContext(), "Application Login Blocked", Toast.LENGTH_LONG).show();
//                buttonSignIn.setClickable(false);
//
//                buttonSignIn.setBackground(Drawable.createFromPath("btn_not_clickable_style"));
//            }
//            //wrong password
//        }
//
//    }


    @SuppressLint("StaticFieldLeak")
    public void signIn(View view) {

        Toast.makeText(getApplicationContext(), " clicked ", Toast.LENGTH_LONG).show();

        final EditText userName = findViewById(R.id.userName);
        final EditText password = findViewById(R.id.password_EditText);
        final String firstName = userName.getText().toString();
        final String masterPassword = password.getText().toString();
        Log.e("check2", " " + firstName + " " + masterPassword);
        //user =  new User("Test", null, null, null, null, null, null, null);
        Log.e("test2","" +flag);

        new AsyncTask<User,Void,Void>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.e("test2","" +flag);
            }

            @Override
            protected Void doInBackground(User... users) {
                user = DatabaseClient.getInstance(getApplication()).getRecordDatabase2().daoUser().LogInConfirmation(firstName, masterPassword);
                if ((firstName.equals(user.getFirstName())) && (masterPassword.equals(user.getMasterPassword()))) {
                    Log.e("test2","firstName: "+ firstName + " User.GetFirstName: " + user.getFirstName() + " masterPassword: "+ masterPassword + " user.getMasterPassword():" + user.getMasterPassword());
                    flag =1;
                }
                else
                    flag =2;
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(flag ==1 ){
                    //  Toast.makeText(getApplicationContext(), " flag = " + flag, Toast.LENGTH_LONG).show();
                    Intent intent= new Intent();
                    intent.setClass(getApplicationContext(),CategoryList_Activity.class);
                    startActivity(intent);
                }
                else
                    return;
            }
        }.execute();




//


//        if (firstName.equals(user[0].getFirstName()) && masterPassword.equals(user[0].getMasterPassword())) {
//            Toast.makeText(getApplicationContext(), " if worked ", Toast.LENGTH_LONG).show();
//        }
//        else
//            Toast.makeText(getApplicationContext(), " if DOSENT worked ", Toast.LENGTH_LONG).show();

//                User user = DatabaseClient.getInstance(getApplication()).getRecordDatabase2().daoUser().LogInConfirmation(firstName, masterPassword);
//        if (firstName.equals(test.getFirstName()) && masterPassword.equals(test.getMasterPassword())) {
//            Log.e("check2", "" + test.getFirstName() + " " + test.getMasterPassword());
//            Intent intent = new Intent(this, CategoryList_Activity.class);
//            this.startActivity(intent);
//        } else
//            Toast.makeText(getApplicationContext(), "Wrong UserName OR PassWord", Toast.LENGTH_LONG).show();
//
//        return;
    }


}
