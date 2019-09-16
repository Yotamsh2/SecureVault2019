package view.explorer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.securevault19.securevault2019.R;

import view.records.RecordRecycler_Activity;

public class CategoryList_Activity extends AppCompatActivity {
    Typeface myFont;
    TextView category1, category2, category3, category4, category5, category6, category7, category8, category9, category10;
    GridLayout mainGrid;
    CardView bankAccounts, creditCard, socialMedia, webAccounts, onlineShopping, cryptocurrency, drivingLicence, passports, customized, notes;
    Animation animation1, animation2, animation3, animation4;
    MediaPlayer mediaPlayer;
    RelativeLayout search_layout;
    Button search_icon;
    Button search_btn;
    EditText search_bar;
    TextView activityTitle;


    @Override
    protected void onCreate(Bundle saveBtndInstanceState) {
        super.onCreate(saveBtndInstanceState);
        setContentView(R.layout.activity_category_list2);

        mediaPlayer = MediaPlayer.create(this, R.raw.button);
        bankAccounts =  findViewById(R.id.bankAccount);
        creditCard =  findViewById(R.id.creditCard);
        socialMedia =  findViewById(R.id.socialMedia);
        webAccounts =  findViewById(R.id.webAccounts);
        onlineShopping =  findViewById(R.id.onlineShopping);
        cryptocurrency =  findViewById(R.id.cryptocurrency);
        drivingLicence = findViewById(R.id.drivingLicence);
        passports =  findViewById(R.id.passport);
        customized =  findViewById(R.id.customized);
        notes =  findViewById(R.id.notes);
        search_layout =  findViewById(R.id.search_layout);
        search_btn =  findViewById(R.id.search_btn);
        search_bar =  findViewById(R.id.search_bar);
        search_icon =  findViewById(R.id.search_icon);
        activityTitle = findViewById(R.id.activityTitle);


        mainGrid =  findViewById(R.id.mainGrid);
        //Animation Sets
        animation1 = AnimationUtils.loadAnimation(CategoryList_Activity.this, R.anim.zoomin_fast);
        animation2 = AnimationUtils.loadAnimation(CategoryList_Activity.this, R.anim.categories_anim);
        animation3 = AnimationUtils.loadAnimation(CategoryList_Activity.this, R.anim.buttonpush_anim);
        animation4 = AnimationUtils.loadAnimation(CategoryList_Activity.this, R.anim.zoomout);
        mainGrid.startAnimation(animation2);


//        Set logo's font to category's text
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/OutlierRail.ttf");
        activityTitle.setTypeface(myFont);
        category1 =  findViewById(R.id.category1);
        category2 =  findViewById(R.id.category2);
        category3 =  findViewById(R.id.category3);
        category4 =  findViewById(R.id.category4);
        category5 =  findViewById(R.id.category5);
        category6 =  findViewById(R.id.category6);
        category7 =  findViewById(R.id.category7);
        category8 =  findViewById(R.id.category8);
        category9 =  findViewById(R.id.category9);
        category10 =  findViewById(R.id.category10);
        category1.setTypeface(myFont);
        category2.setTypeface(myFont);
        category3.setTypeface(myFont);
        category4.setTypeface(myFont);
        category5.setTypeface(myFont);
        category6.setTypeface(myFont);
        category7.setTypeface(myFont);
        category8.setTypeface(myFont);
        category9.setTypeface(myFont);
        category10.setTypeface(myFont);

    }

    public void goToBankAccounts(View view) {
        mediaPlayer.start();
        bankAccounts.startAnimation(animation3);
        Intent intent = new Intent(this, RecordRecycler_Activity.class);
        this.startActivity(intent);
    }

    public void goToCreditCard(View view) {
        mediaPlayer.start();
        creditCard.startAnimation(animation3);
        Intent intent = new Intent(this, RecordRecycler_Activity.class);
        this.startActivity(intent);
    }

    public void goToSocialMedia(View view) {
        mediaPlayer.start();
        socialMedia.startAnimation(animation3);
        Intent intent = new Intent(this, RecordRecycler_Activity.class);
        this.startActivity(intent);
    }

    public void goToWebAccounts(View view) {
        mediaPlayer.start();
        webAccounts.startAnimation(animation3);
        Intent intent = new Intent(this, RecordRecycler_Activity.class);
        this.startActivity(intent);
    }

    public void goToOnlineShopping(View view) {
        mediaPlayer.start();
        onlineShopping.startAnimation(animation3);
        Intent intent = new Intent(this, RecordRecycler_Activity.class);
        this.startActivity(intent);
    }

    public void goToCryptocurrency(View view) {
        mediaPlayer.start();
        cryptocurrency.startAnimation(animation3);
        Intent intent = new Intent(this, RecordRecycler_Activity.class);
        this.startActivity(intent);
    }

    public void goToDrivingLicence(View view) {
        mediaPlayer.start();
        drivingLicence.startAnimation(animation3);
        Intent intent = new Intent(this, RecordRecycler_Activity.class);
        this.startActivity(intent);
    }

    public void goToPassports(View view) {
        mediaPlayer.start();
        passports.startAnimation(animation3);
        Intent intent = new Intent(this, RecordRecycler_Activity.class);
        this.startActivity(intent);
    }

    public void goToCustomized(View view) {
        mediaPlayer.start();
        customized.startAnimation(animation3);
        Intent intent = new Intent(this, RecordRecycler_Activity.class);
        this.startActivity(intent);
    }

    public void goToNotes(View view) {
        mediaPlayer.start();
        notes.startAnimation(animation3);
        Intent intent = new Intent(this, RecordRecycler_Activity.class);
        this.startActivity(intent);
    }


    public void search(View view) {
        mediaPlayer.start();
        search_btn.startAnimation(animation3);
    }

    public void openSearch(View view) {
        mediaPlayer.start();
        search_icon.startAnimation(animation3);

        if (search_layout.getVisibility() == View.GONE) {
            search_layout.startAnimation(animation1);
            search_layout.setVisibility(View.VISIBLE);
            search_bar.setVisibility(View.VISIBLE);
            search_btn.setVisibility(View.VISIBLE);
        } else {
            search_layout.setVisibility(View.GONE);
            search_bar.setVisibility(View.GONE);
            search_btn.setVisibility(View.GONE);
        }

    }

    public void openMenu(View view) {
    }
}
