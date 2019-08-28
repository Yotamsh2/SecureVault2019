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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity {

//    private EditText userName;
//    private EditText password;
//    private Button signIn ;
   private int counter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         final EditText userName = findViewById(R.id.userName);
         final EditText password = findViewById(R.id.password);
         final Button buttontnsignIn = findViewById(R.id.signIn);
        counter = 3;

        buttontnsignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (userName.getText().toString().equals("admin") && password.getText().toString().equals("1234")) {
                    Toast.makeText(getApplicationContext(), "UserName & Password Correct", Toast.LENGTH_LONG).show();
                    return;
                    //correct password
                } else {
                    counter--;
                    Toast.makeText(getApplicationContext(), "UserName & Password Incorrect", Toast.LENGTH_LONG).show();

                    if (counter==0){
                        Toast.makeText(getApplicationContext(), "Application Login Blocked", Toast.LENGTH_LONG).show();
                        buttontnsignIn.setClickable(false);

                        buttontnsignIn.setBackground(Drawable.createFromPath("btn_not_clickable_style"));
                    }
                    //wrong password
                }
            }
        });

    }

    public void signUp(View view) {

    }

//    public void signIn(View view) {
//        if (userName.getText().toString().equals("admin") && password.getText().toString().equals("1234")) {
//            Toast.makeText(getApplicationContext(), "UserName & Password Correct", Toast.LENGTH_LONG).show();
//            return;
//            //correct password
//        } else {
//            counter--;
//            Toast.makeText(getApplicationContext(), "UserName & Password Incorrect", Toast.LENGTH_LONG).show();
//
//            if (counter==0){
//                Toast.makeText(getApplicationContext(), "Application Login Blocked", Toast.LENGTH_LONG).show();
//                signIn.setClickable(false);
//
//                signIn.setBackground(Drawable.createFromPath("btn_not_clickable_style"));
//            }
//            //wrong password
//        }
//    }
}
