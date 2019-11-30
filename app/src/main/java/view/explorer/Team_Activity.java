package view.explorer;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.securevault19.securevault2019.R;

public class Team_Activity extends AppCompatActivity {
    Typeface myFont;
    TextView details_name, details_email,details_id;
    GridLayout mainGrid;
    CardView ori, idan, yotam, evgeni, details;
    Animation animation1, animation2, animation3, animation4;
    MediaPlayer mediaPlayer;
    TextView activityTitle;

    @Override
    protected void onCreate(Bundle saveBtndInstanceState) {
        super.onCreate(saveBtndInstanceState);
        setContentView(R.layout.activity_team);

        mediaPlayer = MediaPlayer.create(this, R.raw.button);
        ori = findViewById(R.id.ori);
        yotam = findViewById(R.id.yotam);
        evgeni = findViewById(R.id.evgeni);
        idan = findViewById(R.id.idan);
        details = findViewById(R.id.details);
        details_name = findViewById(R.id.details_name);
        details_email = findViewById(R.id.details_email);
        details_id = findViewById(R.id.details_id);
        activityTitle = findViewById(R.id.activityTitle);


        mainGrid = findViewById(R.id.mainGrid);
        //Animation Sets
        animation1 = AnimationUtils.loadAnimation(Team_Activity.this, R.anim.zoomin_fast);
        animation2 = AnimationUtils.loadAnimation(Team_Activity.this, R.anim.categories_anim);
        animation3 = AnimationUtils.loadAnimation(Team_Activity.this, R.anim.buttonpush_anim);
        animation4 = AnimationUtils.loadAnimation(Team_Activity.this, R.anim.zoomout);
        mainGrid.startAnimation(animation2);

        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/OutlierRail.ttf");
        activityTitle.setTypeface(myFont);

    }




    public void showDetails(View view) {               // on click func       // the view is what appears on top of the recycler view
        if (view == ori){
            details_name.setText("Ori Even Chen");
            details_email.setText("Oriech90@gmail.com");
            details_id.setText("305683534");
        }
        else if (view == yotam){
            details_name.setText("Yotam Shoval");
            details_email.setText("Yotamsh2@gmail.com");
            details_id.setText("301811915");
        }
        else if (view == idan){
            details_name.setText("Idan Bardugo");
            details_email.setText("Idanbar93@gmail.com");
            details_id.setText("204525182");
        }
        else if (view == evgeni){
            details_name.setText("Evgeni Iseev");
            details_email.setText("Evgeniseev@gmail.com");
            details_id.setText("319289534");
        }


        ori.setCardBackgroundColor(0xffffffff);
        idan.setCardBackgroundColor(0xffffffff);
        evgeni.setCardBackgroundColor(0xffffffff);
        yotam.setCardBackgroundColor(0xffffffff);
        mediaPlayer.start();
        view.startAnimation(animation3);
        if (view == ori)
            ori.setCardBackgroundColor(0xff00ffff);
        if (view == yotam)
            yotam.setCardBackgroundColor(0xff00ffff);
        if (view == idan)
            idan.setCardBackgroundColor(0xff00ffff);
        if (view == evgeni)
            evgeni.setCardBackgroundColor(0xff00ffff);

    }

    public void back(View view) {
        onBackPressed();
    }
}
