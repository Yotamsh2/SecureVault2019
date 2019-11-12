package view.entrance;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.util.Patterns;
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
import androidx.room.Database;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.securevault19.securevault2019.MainActivity;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.record.Record;
import com.securevault19.securevault2019.user.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cryptography.Cryptography;
import local_database.DatabaseClient;
import view.explorer.PatternLockView_Activity;
import view.preferences.SecurityLevel_Activity;
import view.records.RecordRecycler_Activity;

import static view.records.RecordRecycler_Activity.EXTRA_ORIGIN;

@SuppressLint("Registered")
public class NewUser_Activity extends AppCompatActivity {

    private String returnedPattern;
    private String returnedSecurityLevel;
    private String firstNameUser;
    private String masterPasswordUser;
    private String emailUser;
    private String verifyPasswordUser;
    private String lastNameUser;
    private String dateOfBirthUser;
    private String optionalQuestion;
    private String optionalAnswer;
    private Cryptography cryptography;


//    private static final Pattern PASSWORD_PATTERN =
//            Pattern.compile("^" +
//                    "(?=.*[0-9])" +                     //at least 1 digit
//                    "(?=.*[a-z])" +                    //at least 1 lower case letter
//                    "(?=.*[A-Z])" +                   //at least 1 upper case letter
//                    //"(?=.*[a-zA-Z])" +             //any letter
//                    "(?=.*[!@,)#&_'$*(%~$%^&+=])" + //at least 1 special character
//                    "(?=\\S+$)" +                  //no white spaces
//                    ".{8,24}" +                   //at least 8 characters, less than 24
//                    "$");


    private ImageView logo;
    private ImageButton saveBtn, cancelBtn;
    private ImageButton showPass, hidePass, copyPass;
    private MediaPlayer mediaPlayer;
    private Animation animation1, animation2, animation3;
    private ScrollView scrollView;
    private TextView activityTitle;
    private Typeface myFont;
    private EditText password_EditText, userName_EditText, email_EditText, verifyPassword_EditText;
    //////////////////////////// for YOTAM ////////////////////////////
    private EditText lastName_editText, dateOfBirth_EditText, optionalQuestion_EditText, optionalAnswer_EditText;
    ///////////////////////////////////////////////////////////////////

    private LinearLayout userName, password, email;
    private String encryptedPassword, encryptedUserName, encryptedEmail, encryptedPattenr, encryptedSecurityLevel, encryptedLastName, encryptedDateOfBirth, encryptedOptionalQuestion, encryptedOptionalAnswer;
    private User user = null;


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


        // for testing
        userName_EditText.setText("A");
        password_EditText.setText("1");

    }

    @Override
    public void onBackPressed() {
        cancelWarningMessage(null);

    }

    //https://www.youtube.com/watch?v=cnD_7qFeZcY

//    private boolean validatePassword() {
//        String passwordInput = password_EditText.getText().toString().trim();
//
//        if (passwordInput.isEmpty()) {
//            password_EditText.setError("Field can't be empty");
//            return false;
//        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
//            password_EditText.setError("Password too weak");
//            return false;
//        } else {
//            password_EditText.setError(null);
//            return true;
//        }
//    }

//    private boolean validateEmail() {
//        String emailInput = email_EditText.getText().toString().trim();
//
//        if (emailInput.isEmpty()) {
//            email_EditText.setError("Field can't be empty");
//            return false;
//        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
//            email_EditText.setError("Please enter a valid email address");
//            return false;
//        } else {
//            email_EditText.setError(null);
//            return true;
//        }
//    }

//    private boolean validateUsername() {
//        String usernameInput = userName_EditText.getText().toString().trim();
//
//        if (usernameInput.isEmpty()) {
//            userName_EditText.setError("Field can't be empty");
//            return false;
//        } else if (usernameInput.length() > 15) {
//            userName_EditText.setError("Username too long");
//            return false;
//        } else {
//            userName_EditText.setError(null);
//            return true;
//        }
//    }
//






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

    public void choosePattern(View view) {

//        Intent intent = new Intent(this, PatternLockView_Activity.class);
//        this.startActivity(intent);
//        //view.setBackground(R.drawable.ic_dialpad_green);
        Intent intent = new Intent(this, PatternLockView_Activity.class);
        startActivityForResult(intent, 1);

    }

    public void chooseSecurityLevel(View view) {
//        Intent intent = new Intent(this, SecurityLevel_Activity.class);
//        this.startActivity(intent);
        Intent intent = new Intent(this, SecurityLevel_Activity.class);
        startActivityForResult(intent, 2);
    }

    @SuppressLint("StaticFieldLeak")
    public void createNewAccount(View view) {           // onClick func


        if (returnedSecurityLevel == null || returnedPattern == null) {

            // check if the user is sure that he saving his account without secureLevel and pattern!
            // maybe warning window.
        }

        cryptography = new Cryptography();
        firstNameUser = userName_EditText.getText().toString();
        emailUser = email_EditText.getText().toString();
        masterPasswordUser = password_EditText.getText().toString();
        verifyPasswordUser = verifyPassword_EditText.getText().toString();

        // when there will be field ready for it. open this comment.

//        lastNameUser = lastName_editText.getText().toString();
//        dateOfBirthUser = dateOfBirth_EditText.getText().toString();
//        optionalQuestion = optionalAnswer_EditText.getText().toString();
//        optionalAnswer = optionalAnswer_EditText.getText().toString();
        /////////////////////////////////////////////////////////////////////
        // checking if the password and the verify password are the same
        if ((!masterPasswordUser.equals(verifyPasswordUser) || masterPasswordUser.equals(""))) {
            Toast.makeText(getApplicationContext(), "Password is not verified", Toast.LENGTH_LONG).show();
            verifyPassword_EditText.requestFocus();
            return;

        }

        try {
            encryptedUserName = cryptography.encrypt(firstNameUser);
            encryptedPassword = cryptography.encryptWithKey(firstNameUser, masterPasswordUser);
            encryptedEmail = cryptography.encryptWithKey(firstNameUser, emailUser);
            encryptedPattenr = cryptography.encryptWithKey(firstNameUser, returnedPattern);
            encryptedSecurityLevel = cryptography.encryptWithKey(firstNameUser, returnedSecurityLevel);

            // to be change after Yotam make XML fields for lastName,DateOfBirth,Question,Answer.
            encryptedLastName = cryptography.encryptWithKey(firstNameUser, "CHANGE_ME!");
            encryptedDateOfBirth = cryptography.encryptWithKey(firstNameUser, "CHANGE_ME!");
            encryptedOptionalQuestion = cryptography.encryptWithKey(firstNameUser, "CHANGE_ME!");
            encryptedOptionalAnswer = cryptography.encryptWithKey(firstNameUser, "CHANGE_ME!");


        } catch (Exception e) {
            e.printStackTrace();
        }
        if (firstNameUser.isEmpty()) {
            Toast.makeText(this, "Name is Empty", Toast.LENGTH_LONG).show();
            return;
        } else {


            // @SuppressLint("StaticFieldLeak") preventing memory leak
            new AsyncTask<Void, Void, Void>() {

                // here you should run of the BD and check for userName.
                // if the userName all ready exists, Toast a message
                private int flag = 0;


                @Override
                protected Void doInBackground(Void... voids) {

                    user = new User(encryptedUserName, encryptedLastName, encryptedDateOfBirth, encryptedEmail, encryptedOptionalQuestion, encryptedOptionalAnswer, encryptedPassword, encryptedSecurityLevel, encryptedPattenr);
                    try {
                        DatabaseClient.getInstance(getApplication()).getRecordDatabase2().daoUser().insert(user);
                        Log.d("enteredCatch", "enteredTRY");
                        flag = 1;
                    } catch (Exception e) {
                        Log.d("enteredCatch", "enteredCatch");
                        e.printStackTrace();
                    }

                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    if (flag == 1) {
                        Toast.makeText(getApplicationContext(), "" + firstNameUser + " Created!", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "User all ready exists", Toast.LENGTH_LONG).show();
                        return;
                    }


                }


            }.execute();

        }
        mediaPlayer.start();
        saveBtn.startAnimation(animation3);


    }

    // maybe need to delete the super
    // this func is getting value from other activities.
    // it get pattern from PatternLockActivity              ( requestCode 1 )
    // it get securityLevel from SecurityLevelActivity      ( requestCode 2 )
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnedIntent) {
        super.onActivityResult(requestCode, resultCode, returnedIntent);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                returnedPattern = returnedIntent.getStringExtra("PATTERN_LOCK");
                Log.d("patterenGotBack", "" + returnedPattern);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "You didnt chose Patternt", Toast.LENGTH_LONG).show();
                Log.d("patterenGotBack", "no pattern came back");
            }

        }
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                returnedSecurityLevel = returnedIntent.getStringExtra("SECURITY_LEVEL");
                Toast.makeText(getApplicationContext(), "" + returnedSecurityLevel, Toast.LENGTH_LONG).show();
                Log.d("returnedSecurityLevel ", "" + returnedSecurityLevel);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "You didnt chose Secure Level", Toast.LENGTH_LONG).show();
                Log.d("returnedSecurityLevel ", "no patteren returned");
            }

        }

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isValidEmail(String emailInput){
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile( EMAIL_PATTERN );
        Matcher matcher = pattern.matcher( emailInput );
        return matcher.matches();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}



