package com.securevault19.securevault2019;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import view.records.WebsiteRecycler_Activity;


@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity {
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText userName = (EditText) findViewById(R.id.userName);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button buttonSignIn = (Button) findViewById(R.id.signIn);
        counter = 3;

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(userName, password, buttonSignIn);
            }
        });

    }

    public void signUp(View view) {

    }

    public void signIn(EditText userName, EditText password, Button buttonSignIn) {
        if (userName.getText().toString().equals("A") && password.getText().toString().equals("1")) {
            Toast.makeText(getApplicationContext(), "UserName & Password Correct", Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(this, ExplorerMain_Activity.class);
//            this.startActivity(intent);
            Intent intent = new Intent(this, WebsiteRecycler_Activity.class);
            this.startActivity(intent);

        } else {
            counter--;
            Toast.makeText(getApplicationContext(), "UserName & Password Incorrect", Toast.LENGTH_LONG).show();

            if (counter == 0) {
                Toast.makeText(getApplicationContext(), "Application Login Blocked", Toast.LENGTH_LONG).show();
                buttonSignIn.setClickable(false);

                buttonSignIn.setBackground(Drawable.createFromPath("btn_not_clickable_style"));
            }
            //wrong password
        }
    }

}
