package view.entrance;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.securevault19.securevault2019.MainActivity;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.record.Record;
import com.securevault19.securevault2019.user.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;

import cryptography.Cryptography;
import local_database.DatabaseClient;
import view.explorer.PatternLockView_Activity;
import view.preferences.SecurityLevel_Activity;
import view.records.RecordRecycler_Activity;

import static view.records.RecordRecycler_Activity.EXTRA_ORIGIN;

@SuppressLint("Registered")
public class NewUser_Activity extends AppCompatActivity {

    private ImageView logo;
    private ImageButton saveBtn, cancelBtn;
    private ImageButton showPass, hidePass, copyPass;
    private MediaPlayer mediaPlayer;
    private Animation animation1, animation2, animation3;
    private ScrollView scrollView;
    private TextView activityTitle;
    private Typeface myFont;
    private EditText password_EditText, userName_EditText, email_EditText, verifyPassword_EditText;
    private LinearLayout userName, password, email;
    private String encryptedPassword,encryptedUserName;
    private User user;


    @Override
    protected void onCreate(Bundle saveBtndInstanceState) {
        super.onCreate(saveBtndInstanceState);
        setContentView(R.layout.activity_new_user);

        mediaPlayer = MediaPlayer.create(this, R.raw.button);
        logo = findViewById(R.id.logo);
        saveBtn = findViewById(R.id.saveBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        showPass = findViewById(R.id.showPass);
        hidePass = findViewById(R.id.hidePass);
        copyPass = findViewById(R.id.copyPass);
        scrollView = findViewById(R.id.frame);
        activityTitle = findViewById(R.id.activityTitle);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        password_EditText = findViewById(R.id.password_EditText);
        email = findViewById(R.id.email);
        userName_EditText = findViewById(R.id.username_EditText);
        email_EditText = findViewById(R.id.email_EditText);
        verifyPassword_EditText = findViewById(R.id.verifyPassword_EditText);

        //Animation Sets
        animation1 = AnimationUtils.loadAnimation(NewUser_Activity.this, R.anim.zoomin);
        animation2 = AnimationUtils.loadAnimation(NewUser_Activity.this, R.anim.zoomin_fade);
        animation3 = AnimationUtils.loadAnimation(NewUser_Activity.this, R.anim.buttonpush_anim);
        scrollView.startAnimation(animation2);


        //        Set logo's font to category's text
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/OutlierRail.ttf");
        activityTitle.setTypeface(myFont);


    }

    @Override
    public void onBackPressed() {
        cancelWarningMessage(null);

    }



    public void showPass(View view) {
        if (showPass.getVisibility() == View.VISIBLE) {
            password_EditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            hidePass.setVisibility(View.VISIBLE);
            showPass.setVisibility(View.GONE);
            showPass.startAnimation(animation3);
        } else {
            password_EditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            hidePass.setVisibility(View.GONE);
            showPass.setVisibility(View.VISIBLE);
            hidePass.startAnimation(animation3);
        }
        password_EditText.requestFocus();
        password_EditText.setSelection(password_EditText.getText().length());

    }

    public void cancelWarningMessage(final View view) {
        mediaPlayer.start();
        cancelBtn.startAnimation(animation3);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.cancelation_request);
        alert.setMessage(R.string.cancelation_message);
        alert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Data Not saved", Toast.LENGTH_LONG).show();
                back(view);
            }
        });
        alert.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Not Canceled", Toast.LENGTH_LONG).show();
            }
        });
        alert.create().show();
    }

    public void back(View view) {
        finish();
    }

    public void copyPass(View view) {
        mediaPlayer.start();
        copyPass.startAnimation(animation3);
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipboardManager.setText(password_EditText.getText().toString());
        Toast.makeText(this, "Password Copied", Toast.LENGTH_SHORT).show();

    }

    public void choosePatern(View view) {

        Intent intent = new Intent(this, PatternLockView_Activity.class);
        this.startActivity(intent);
        //view.setBackground(R.drawable.ic_dialpad_green);
    }

    public void chooseSecurityLevel(View view) {
        Intent intent = new Intent(this, SecurityLevel_Activity.class);
        this.startActivity(intent);
    }

    public void createNewAccount(View view) {           // onClick func
        Cryptography cryptography = new Cryptography();
        final String firstName = userName_EditText.getText().toString();
        final String masterPassword = password_EditText.getText().toString();

        if (password_EditText==verifyPassword_EditText || password_EditText.toString()==""){
            Toast.makeText(getApplicationContext(), "Password is not verified", Toast.LENGTH_LONG).show();
            verifyPassword_EditText.requestFocus();
            return;
        }


        try {
            encryptedUserName = cryptography.encrypt(firstName);
            encryptedPassword = cryptography.encryptWithKey(firstName, masterPassword);
            Log.e("check", "" + firstName + " " + masterPassword + " " + encryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (firstName.isEmpty()) {
            Toast.makeText(this, "Name is Empty", Toast.LENGTH_LONG).show();
            return;
        } else {

            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... voids) {
                    user = new User(encryptedUserName, null, null, null, null, null, encryptedPassword, null);
                    DatabaseClient.getInstance(getApplication()).getRecordDatabase2().daoUser().insert(user);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(getApplicationContext(),"User "+ user.getFirstName()+" Added", Toast.LENGTH_LONG).show();
                    finish();
                }


            }.execute();

        }
        mediaPlayer.start();
        saveBtn.startAnimation(animation3);
        finish();
    }




}



