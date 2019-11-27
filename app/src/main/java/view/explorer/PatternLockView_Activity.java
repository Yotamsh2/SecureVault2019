package view.explorer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.user.CurrentUser;
import com.securevault19.securevault2019.user.User;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cryptography.Cryptography;
import local_database.DatabaseClient;

import static com.andrognito.patternlockview.PatternLockView.*;

public class PatternLockView_Activity extends AppCompatActivity {

    public static final String EXTRA_ORIGIN =
            "com.securevault19.securevault2019.EXTRA_ORIGIN";

    private String patternLock;
    private String verifyedLock;
    private Intent returnIntent;
    private String CRYPTO_KEY = null;
    private String patternFromUser;
    private int verifyedPattern = 0;
    private User userCheck;
    private Cryptography cryptography;
    private String encryptedPatternLock;
    private String origin; //which Activity we came from


    private TextView patternTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_lockview);
        patternTextView = findViewById(R.id.patternTextView);

        cryptography = new Cryptography();
        CRYPTO_KEY = getIntent().getStringExtra("CRYPTO_KEY");
        patternFromUser = getIntent().getStringExtra("PATTERN");
        Log.d("patternLockFromUser", patternFromUser + "  passed ");
        returnIntent = new Intent();

        final Bundle extras = getIntent().getExtras();
        origin = extras.getString(EXTRA_ORIGIN);


        if (CRYPTO_KEY != null) {             // if user passed from other activity
            Log.d("patternCheck", "filled PatternTextView");
            patternTextView.setText(getString(R.string.Pattern));
        }
        // https://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.button);

        final PatternLockView patternLockView;


        patternLockView = findViewById(R.id.patternView);
        patternLockView.setDotCount(3);
        patternLockView.setCorrectStateColor(getResources().getColor(R.color.colorCorrectLine));
        patternLockView.addPatternLockListener(new PatternLockViewListener() {

            @Override
            public void onStarted() {
                Log.d("patterntTesting", "onCStart");
            }

            @Override
            public void onProgress(List progressPattern) {
                Log.d("patterntTesting", "onProgress");
            }

            @SuppressLint("StaticFieldLeak")            // ignoring memory leak
            @Override
            public void onComplete(final List pattern) {

                // if we came from SignIn activity
                if (CRYPTO_KEY != null) {
                    Log.d("encryptedPattern", "extras: " + extras + ", origin: " + origin);

                    String currentPattern = PatternLockUtils.patternToString(patternLockView, pattern);
                    String encryptedPattern = null;
                    try {
                        encryptedPattern = cryptography.encryptWithKey(CRYPTO_KEY, currentPattern);
                        Log.d("encryptedPattern", "reched encryptedPattern");
                        Log.d("---------", encryptedPattern + "     -----      " + patternFromUser);
                    } catch (Exception e) {
                        Log.d("encryption", "encryption problem");
                        e.printStackTrace();
                    }
                    if (extras != null) {
                        if (origin != null) {  //To avoid NullPointerException
                            if (origin.equals("AddNewRecord_Activity")) { //if we came from this activity
                                Log.d("encryptedPattern", " origin: " + origin);
                                if (encryptedPattern.equals(CurrentUser.getInstance().getPatternLock())) {
                                    returnIntent.putExtra("PATTERN", 1);
                                    finish();
                                    return;
                                }
                            }
                        }
                    }
                    if (encryptedPattern.equals(patternFromUser)) {
                        Log.d("encryptedPattern", "entered if ");


                        Intent intent = new Intent(getApplicationContext(), CategoryList_Activity.class);
                        intent.putExtra("CRYPTO_KEY", CRYPTO_KEY);
                        startActivity(intent);
                        finish();

                    } else {
                        incorrectPattern(patternLockView);
                        clearPattern(patternLockView);
                        //Toast.makeText(getApplicationContext(),"Wrong Pattern!",Toast.LENGTH_LONG).show();
                    }

                }


//
//                    Log.d("patternCheck", "user is filled");
//                    new AsyncTask<Void, Void, Void>() {
//
//                        @Override
//                        protected Void doInBackground(Void... voids) {
//                            try {
//                                userCheck = DatabaseClient.getInstance(getApplication()).getRecordDatabase2().daoUser().CheckForUserName(cryptography.encrypt(user));
//                                Log.d("patternCheck", "userCheck returned!" + userCheck.getFirstName());
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            if (userCheck == null) {
//                                Log.d("patternCheck", "User dosent exists.");
//                                // show problem message
//                                return null;
//                            } else {
//                                Log.d("patternCheck", "userCheck != null");
//                                verifyedLock = PatternLockUtils.patternToString(patternLockView, pattern);
//                                try {
//                                    encryptedPatternLock = cryptography.encryptWithKey(user, verifyedLock);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                                if (encryptedPatternLock.equals(userCheck.getPatternLock())) {
//                                    Log.d("patternCheck", "equals");
//                                    finish();
//                                    // maybe will be a problem
//                                    Intent intent = new Intent(getApplicationContext(), CategoryList_Activity.class);
//                                    intent.putExtra("CRYPTO_KEY", user);
//                                    startActivity(intent);
//                                } else {
//                                    Log.d("patternCheck", "clear pattern");
//                                    // the pattern inCorrect, so we will exit and make a clear + Toast.
//                                    // there is a problem making Toast inSide AsyncTask, so we will make it outside
//
//                                    Log.d("patternCheck", "cleared pattern");
//                                }
//                            }
//                            return null;
//                        }
//
//
//                    }.execute();


//                    try {
//                        userCheck = DatabaseClient.getInstance(getApplication()).getRecordDatabase2().daoUser().CheckForUserName(user);
//                        Log.d("patternCheck", "userCheck returned!");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        return;
//                    }
//                    if (userCheck == null) {
//                        Log.d("userExistance", "User dosent exists.");
//                        return;
//                    } else {
//                        Log.d("patternCheck", "userCheck != null");
//                        verifyedLock = PatternLockUtils.patternToString(patternLockView, pattern);
//                        try {
//                            encryptedPatternLock = cryptography.encryptWithKey(user, verifyedLock);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        if (encryptedPatternLock.equals(userCheck.getPatternLock())) {
//                            Log.d("patternCheck", "equals");
//                            finish();
//                            // maybe will be a problem
//                            Intent intent = new Intent(getApplicationContext(), CategoryList_Activity.class);
//                            intent.putExtra("CRYPTO_KEY", user);
//                            startActivity(intent);
//                        } else {
//                            Log.d("patternCheck", "clear pattern");
//                            incorrectPattern(patternLockView);
//
//                        }
//                        return;
//                    }

//                }
                // if we came from SingUp activity
                else {

                    if (verifyedPattern == 0) {
                        verifyedLock = PatternLockUtils.patternToString(patternLockView, pattern);
                        clearPattern(patternLockView);
                        Toast.makeText(PatternLockView_Activity.this, "Re-enter pattern", Toast.LENGTH_SHORT).show();
                        verifyedPattern = 1;
                        patternTextView.setText(getString(R.string.enterPatternAgain));
                    } else {
                        patternLock = PatternLockUtils.patternToString(patternLockView, pattern);
                        if (patternLock.equals(verifyedLock)) {
                            setResult(Activity.RESULT_OK, returnIntent);
                            Toast.makeText(PatternLockView_Activity.this, "pattenrLockSet" + patternLock, Toast.LENGTH_SHORT).show();
                            returnIntent.putExtra("PATTERN_LOCK", patternLock);
                            finish();
                        } else {
                            incorrectPattern(patternLockView);

                        }
                    }

                }


//
//
//
//                Log.d("patterntTesting", "onProonComlete" );
//                patternLock = PatternLockUtils.patternToString(patternLockView, pattern);
//                if (returnIntent != null) {
//                    setResult(Activity.RESULT_OK, returnIntent);
//                    Toast.makeText(PatternLockView_Activity.this, "pattenrLockSet" + patternLock, Toast.LENGTH_SHORT).show();
//                    returnIntent.putExtra("PATTERN_LOCK", patternLock);
//                    //finish();
//                } else {
//                    Toast.makeText(PatternLockView_Activity.this, "PATTERN NOT SET!", Toast.LENGTH_SHORT).show();
//                    clearPattern(patternLockView);
//                    patternLockView.setViewMode(PatternViewMode.WRONG); //Pattern's Color becomes red
//
//                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//                    vibrator.vibrate(500);
//
//
//                }


//
//
//
//
//                Log.d(getClass().getName(), "Pattern complete: " +
//                        PatternLockUtils.patternToString(patternLockView, pattern));
//                if (PatternLockUtils.patternToString(patternLockView, pattern).equalsIgnoreCase("012")) {
//                    //Clears the Pattern from the screen
//                    clearPattern(patternLockView);
//
//                    Toast.makeText(getApplicationContext(), "Welcome back, User_Name", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), CategoryList_Activity.class);
//                    startActivity(intent);
//                    overridePendingTransition(0, 0);
//                    mediaPlayer.start();
//                    finish();
//                    overridePendingTransition(0, 0);
//
//
//                } else {
//                    //Clears the Pattern from the screen
//                    clearPattern(patternLockView);
//                    patternLockView.setViewMode(PatternViewMode.WRONG); //Pattern's Color becomes red
//
//                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//                    vibrator.vibrate(500);
//                    Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onCleared() {

            }
        });

    }


    private void incorrectPattern(PatternLockView patternLockView) {
        Toast.makeText(PatternLockView_Activity.this, "PATTERN INCORRECT!", Toast.LENGTH_SHORT).show();
        clearPattern(patternLockView);
        patternLockView.setViewMode(PatternViewMode.WRONG); //Pattern's Color becomes red

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);

    }


    //Clears the Pattern from the screen
    private void clearPattern(final PatternLockView patternLockView) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                patternLockView.clearPattern();
            }
        }, 1500);

    }
}

