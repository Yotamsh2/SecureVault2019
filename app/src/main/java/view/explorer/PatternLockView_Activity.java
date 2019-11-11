package view.explorer;

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

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.andrognito.patternlockview.PatternLockView.*;

public class PatternLockView_Activity extends AppCompatActivity {

    private String patternLock;
    private String verifyedLock;
    private Intent returnIntent;
    private int flag = 0;
    private int verifyedPattern = 0;

    private TextView patternTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_lockview);
        patternTextView = findViewById(R.id.patternTextView);

        returnIntent = new Intent();


        // https://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.button);

        final PatternLockView patternLockView;


        patternLockView = findViewById(R.id.patternView);
        patternLockView.setDotCount(3);
        patternLockView.setCorrectStateColor(getResources().getColor(R.color.colorCorrectLine));
        patternLockView.addPatternLockListener(new PatternLockViewListener() {

            @Override
            public void onStarted() {
            Log.d("patterntTesting", "onCStart" );
            }

            @Override
            public void onProgress(List progressPattern) {
                Log.d("patterntTesting", "onProgress" );
            }

            @Override
            public void onComplete(List pattern) {
                if (verifyedPattern== 0 ){
                    verifyedLock = PatternLockUtils.patternToString(patternLockView, pattern);
                    clearPattern(patternLockView);
                    Toast.makeText(PatternLockView_Activity.this, "Re-enter pattern", Toast.LENGTH_SHORT).show();
                    verifyedPattern=1;
                    patternTextView.setText(getString(R.string.enterPatternAgain));
                }
                else {
                    patternLock = PatternLockUtils.patternToString(patternLockView, pattern);
                    if (patternLock.equals(verifyedLock)){
                        setResult(Activity.RESULT_OK, returnIntent);
                        Toast.makeText(PatternLockView_Activity.this, "pattenrLockSet" + patternLock, Toast.LENGTH_SHORT).show();
                        returnIntent.putExtra("PATTERN_LOCK", patternLock);
                        finish();
                    }
                    else {
                        Toast.makeText(PatternLockView_Activity.this, "PATTERN INCORRECT!", Toast.LENGTH_SHORT).show();
                        clearPattern(patternLockView);
                        patternLockView.setViewMode(PatternViewMode.WRONG); //Pattern's Color becomes red

                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(500);


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

