package com.securevault19.securevault2019;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity {

    private EditText userName = findViewById(R.id.userName);
    private EditText password = findViewById(R.id.password);
    private Button signIn = findViewById(R.id.signIn);
    private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signUp(View view) {

    }

    public void signIn(View view) {
        if (userName.getText().toString().equals("admin") && password.getText().toString().equals("1234")) {
            Toast toast = Toast.makeText(getApplicationContext(), "UserName & Password Correct", Toast.LENGTH_LONG);
            toast.show();
            //correct password
        } else {
            counter--;
            Toast toast = Toast.makeText(getApplicationContext(), "UserName & Password Incorrect", Toast.LENGTH_LONG);
            toast.show();
            if (counter==0){
                Toast toast2 = Toast.makeText(getApplicationContext(), "Application Login Blocked", Toast.LENGTH_LONG);
                toast2.show();
                signIn.setClickable(false);

                signIn.setBackground(Drawable.createFromPath("btn_not_clickable_style"));
            }
            //wrong password
        }
    }
}
