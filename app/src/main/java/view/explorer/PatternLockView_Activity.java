package view.explorer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
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
import static com.andrognito.patternlockview.PatternLockView.*;

public class PatternLockView_Activity extends AppCompatActivity {

    public static final String EXTRA_ORIGIN =
          "com.securevault19.securevault2019.EXTRA_ORIGIN";

    private String patternLock;
    private String verifyedLock;
    private Intent returnIntent;
    private Intent returnIntentToRecord;
    private String CRYPTO_KEY = null;
    private String patternFromUser;
    private int verifyedPattern = 0;
    private User userCheck;
    private Cryptography cryptography;
    private String encryptedPatternLock;
    private String origin=null; //which Activity we came from


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
        returnIntentToRecord = new Intent();
        final Bundle extras = getIntent().getExtras();
        origin = extras.getString(EXTRA_ORIGIN);


        if (CRYPTO_KEY != null) {             // if user passed from other activity
            patternTextView.setText(getString(R.string.Pattern));
        }


        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.button);
        final PatternLockView patternLockView;


        patternLockView = findViewById(R.id.patternView);
        patternLockView.setDotCount(3);
        patternLockView.setCorrectStateColor(getResources().getColor(R.color.colorCorrectLine));
        patternLockView.addPatternLockListener(new PatternLockViewListener() {

            @Override
            public void onStarted() {
                Log.d("patternTesting", "onCStart");
            }

            @Override
            public void onProgress(List progressPattern) {
                Log.d("patternTesting", "onProgress");
            }

            @SuppressLint("StaticFieldLeak")            // preventing memory leak
            @Override
            public void onComplete(final List pattern) {

                // if we came from SignIn activity
                if (CRYPTO_KEY != null) {
                    Log.d("encryptedPattern", "extras: " + extras + ", origin: " + origin);
                    String currentPattern = PatternLockUtils.patternToString(patternLockView, pattern);
                    String encryptedPattern = null;
                    try {
                        encryptedPattern = cryptography.encryptWithKey(CRYPTO_KEY, currentPattern);
                    } catch (Exception e) {
                        Log.e("encryption", "encryption problem");
                        e.printStackTrace();
                    }
                    if (extras != null) {
                        if (origin != null) {  //To avoid NullPointerException
                            if (origin.equals("AddNewRecord_Activity")) { //if we came from this activity
                                Log.d("encryptedPattern", " origin: " + origin);
                                if (encryptedPattern.equals(CurrentUser.getInstance().getPatternLock())) {
                                    setResult(Activity.RESULT_OK,returnIntentToRecord);
                                    returnIntentToRecord.putExtra("recordEditForm","1");
                                    finish();
                                    return;

                                }
                            }
                        }
                    }
                    if (encryptedPattern.equals(patternFromUser)) {
                        Intent intent = new Intent(getApplicationContext(), MainScreen_Activity.class);
                        intent.putExtra("CRYPTO_KEY", CRYPTO_KEY);
                        startActivity(intent);
                        finish();

                    } else {
                        incorrectPattern(patternLockView);
                        clearPattern(patternLockView);
                    }

                }
                // if we came from SingUp activity
                else {
// saves the pattern
                    if (verifyedPattern == 0) {
                        verifyedLock = PatternLockUtils.patternToString(patternLockView, pattern);
                        clearPattern(patternLockView);
                        Toast.makeText(PatternLockView_Activity.this, "Re-enter pattern", Toast.LENGTH_SHORT).show();
                        // changing verifyedPattern to 1 for checking the verification
                        verifyedPattern = 1;
                        patternTextView.setText(getString(R.string.enterPatternAgain));
                    } else {
                        // saving the pattern
                        patternLock = PatternLockUtils.patternToString(patternLockView, pattern);
                        if (patternLock.equals(verifyedLock)) {
                            setResult(Activity.RESULT_OK, returnIntent);
                            Toast.makeText(PatternLockView_Activity.this, "Pattern Saved", Toast.LENGTH_SHORT).show();
                            returnIntent.putExtra("PATTERN_LOCK", patternLock);
                            finish();
                        } else {
                            incorrectPattern(patternLockView);

                        }
                    }

                }
                
            }

            @Override
            public void onCleared() {

            }
        });

    }


    private void incorrectPattern(PatternLockView patternLockView) {
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

