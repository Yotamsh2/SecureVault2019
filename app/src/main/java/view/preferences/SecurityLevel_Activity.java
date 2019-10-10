package view.preferences;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.securevault19.securevault2019.R;

import view.records.RecordRecycler_Activity;

public class SecurityLevel_Activity extends AppCompatActivity {


    Typeface myFont;
   Animation animation1, animation2, animation3, animation4;
    MediaPlayer mediaPlayer;
    TextView activityTitle;
    RelativeLayout level1, level2, level3;
    ImageView level1_logo, level2_logo, level3_logo;
    ImageView level1_chosen;

    @Override
    protected void onCreate(Bundle saveBtndInstanceState) {
        super.onCreate(saveBtndInstanceState);
        setContentView(R.layout.activity_security_level);

        mediaPlayer = MediaPlayer.create(this, R.raw.button);
        level1 = findViewById(R.id.level1);
        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);
        level1_logo = findViewById(R.id.level1_logo);
        level2_logo = findViewById(R.id.level2_logo);
        level3_logo = findViewById(R.id.level3_logo);
        level1_chosen = findViewById(R.id.level1_chosen);
        activityTitle = findViewById(R.id.activityTitle);


        //Animation Sets
        animation1 = AnimationUtils.loadAnimation(SecurityLevel_Activity.this, R.anim.zoomin_fast);
        animation2 = AnimationUtils.loadAnimation(SecurityLevel_Activity.this, R.anim.categories_anim);
        animation3 = AnimationUtils.loadAnimation(SecurityLevel_Activity.this, R.anim.buttonpush_anim);
        animation4 = AnimationUtils.loadAnimation(SecurityLevel_Activity.this, R.anim.zoomout);
        level1.startAnimation(animation2);
        level2.startAnimation(animation2);
        level3.startAnimation(animation2);


//        Set logo's font to category's text
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/OutlierRail.ttf");
        activityTitle.setTypeface(myFont);

    }

    public void openMenu(View view) {
    }

    public void openOptions(View view) {
    }

    public void back(View view) {
        finish();
    }

    public void level_clicked(View view) {
        mediaPlayer.start();
        if (view == level1){
            if (level1_logo.getBackground() != level1_chosen.getBackground()){
                level1_logo.setBackground(level1_chosen.getBackground());
            }
            else{
                level1_logo.setBackground(((getDrawable(R.drawable.level1_logo)) ));
            }
        }
    }
}
