package com.securevault19.securevault2019;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import view.explorer.CategoryList_Activity;


@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity {

    private int counter;

    @Override
    protected void onCreate(Bundle saveBtndInstanceState) {
        super.onCreate(saveBtndInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.button);
        ImageView logo = findViewById(R.id.logo);
        Button forgotPass =  findViewById((R.id.forgotPass));
        Button signUp =  findViewById(R.id.signUp);
        final EditText userName = findViewById(R.id.userName);
        final EditText password = findViewById(R.id.password);
        final Button buttonSignIn = findViewById(R.id.signIn);
        counter = 3;


    // for testing //
    userName.setText("A");
    password.setText("1");
    //------------//


        //Animation Sets
        Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.zoomin);
        final Animation animation2 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.bottomtotop);
        final Animation animation3 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.buttonpush_anim);
        logo.startAnimation(animation1);
        userName.startAnimation(animation2);
        password.startAnimation(animation2);
        buttonSignIn.startAnimation(animation2);
        signUp.startAnimation(animation2);
        forgotPass.startAnimation(animation2);


        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(userName, password, buttonSignIn);
                buttonSignIn.startAnimation(animation3);
                mediaPlayer.start();
            }
        });

    }

    public void signUp(View view) {

    }

    public void signIn(EditText userName, EditText password, Button buttonSignIn) {
        if (userName.getText().toString().equals("A") && password.getText().toString().equals("1")) {
            Intent intent = new Intent(this, CategoryList_Activity.class);
            this.startActivity(intent);

        } else {
            counter--;
            Toast.makeText(getApplicationContext(), "UserName & Password Incorrect"+"\n"+"Still Have "+counter+" To Try", Toast.LENGTH_LONG).show();

            if (counter == 0) {
                Toast.makeText(getApplicationContext(), "Application Login Blocked", Toast.LENGTH_LONG).show();
                buttonSignIn.setClickable(false);

                buttonSignIn.setBackground(Drawable.createFromPath("btn_not_clickable_style"));
            }
            //wrong password
        }
    }
}
