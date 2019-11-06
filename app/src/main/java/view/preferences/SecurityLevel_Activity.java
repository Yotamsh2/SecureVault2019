package view.preferences;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    ImageView level1_logo, level2_logo, level3_logo, box_level1,box_level2,box_level3;
    Drawable level1_chosen,level2_chosen, level3_chosen,level1_notchosen,level2_notchosen, level3_notchosen;
    Drawable boxStyle, boxChosenStyle;
    ImageButton cancelBtn, saveBtn;


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
        level1_chosen = getDrawable(R.drawable.level1_logo_chosen);
        level2_chosen = getDrawable(R.drawable.level2_logo_chosen);
        level3_chosen = getDrawable(R.drawable.level3_logo_chosen);
        level1_notchosen = getDrawable(R.drawable.level1_logo);
        level2_notchosen = getDrawable(R.drawable.level2_logo);
        level3_notchosen = getDrawable(R.drawable.level3_logo);
        box_level1 = findViewById(R.id.box_level1);
        box_level2 = findViewById(R.id.box_level2);
        box_level3 = findViewById(R.id.box_level3);
        boxStyle = getDrawable(R.drawable.text_box_style);
        boxChosenStyle = getDrawable(R.drawable.text_box_style_chosen);
        activityTitle = findViewById(R.id.activityTitle);
        saveBtn = findViewById(R.id.saveBtn);
        cancelBtn = findViewById(R.id.cancelBtn);


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
        mediaPlayer.start();
        finish();
        Toast.makeText(SecurityLevel_Activity.this, "Not Saved", Toast.LENGTH_SHORT).show();

    }

    public void level_clicked(View view) {
        mediaPlayer.start();

        // if level1 clicked
        if (view == level1) {

            //if the logo is different than gold, switch to gold
            if (level1_logo.getDrawable() != level1_chosen) {
                level1_logo.setImageResource( R.drawable.level1_logo_chosen );
                box_level1.setBackground( boxChosenStyle );

                level2_logo.setImageResource( R.drawable.level2_logo );
                box_level2.setBackground( boxStyle );

                level3_logo.setImageResource( R.drawable.level3_logo );
                box_level3.setBackground( boxStyle );

            }
        }

        if (view == level2) {

            //if the logo is different than gold, switch to gold
            if (level2_logo.getDrawable() != level2_chosen) {
                level2_logo.setImageResource( R.drawable.level2_logo_chosen );
                box_level2.setBackground( boxChosenStyle );

                level1_logo.setImageResource( R.drawable.level1_logo );
                box_level1.setBackground( boxStyle );

                level3_logo.setImageResource( R.drawable.level3_logo );
                box_level3.setBackground( boxStyle );

            }
        }

        if (view == level3) {

            //if the logo is different than gold, switch to gold
            if (level3_logo.getDrawable() != level3_chosen) {
                level3_logo.setImageResource( R.drawable.level3_logo_chosen );
                box_level3.setBackground( boxChosenStyle );

                level1_logo.setImageResource( R.drawable.level1_logo );
                box_level1.setBackground( boxStyle );

                level2_logo.setImageResource( R.drawable.level2_logo );
                box_level2.setBackground( boxStyle );

            }
        }
//        if (view == level1){
//            if (level1_logo.getDrawable() != level1_chosen){
//                level1_logo.setImageResource(R.drawable.level1_logo_chosen);
//                box_level1.setBackground(boxChosenStyle);
//            }
//            else{
//                level1_logo.setImageResource(R.drawable.level1_logo);
//                box_level1.setBackground(boxStyle);
//                Toast.makeText(SecurityLevel_Activity.this, "Not Chosen", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//        if (view == level2){
//            if (level2_logo.getDrawable() != level2_chosen){
//                level2_logo.setImageResource(R.drawable.level2_logo_chosen);
//            }
//            else{
//                level2_logo.setImageResource(R.drawable.level2_logo);
//            }
//        }
    }

    public void chooseLevel(View view) {
        mediaPlayer.start();
        saveBtn.startAnimation(animation3);
        Toast.makeText(SecurityLevel_Activity.this, "Security Level Saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}
