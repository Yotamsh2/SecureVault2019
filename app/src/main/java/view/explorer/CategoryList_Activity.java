package view.explorer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.securevault19.securevault2019.R;

import view.preferences.SecurityLevel_Activity;
import view.records.AddNewRecord_Activity;
import view.records.RecordRecycler_Activity;

import static view.records.RecordRecycler_Activity.ADD_RECORD_REQUEST;

public class CategoryList_Activity extends AppCompatActivity {
    public static final int ADD_RECORD_REQUEST = 1;
    public static final String EXTRA_FOLDER =
            "com.securevault19.securevault2019.EXTRA_FOLDER";
    public static final String EXTRA_SEARCH =
            "com.securevault19.securevault2019.EXTRA_SEARCH";
    public static final String EXTRA_ORIGIN =
            "com.example.architectureexample.EXTRA_ORIGIN";

    Typeface myFont;
    TextView category1, category2, category3, category4, category5, category6, category7, category8, category9, category10;
    GridLayout mainGrid;
    CardView bankAccounts, creditCard, socialMedia, webAccounts, onlineShopping, cryptocurrency, drivingLicence, passports, allRecords, notes;
    Animation animation1, animation2, animation3, animation4;
    MediaPlayer mediaPlayer;
    RelativeLayout search_layout;
    Button search_icon;
    ImageButton search_btn;
    EditText search_bar;
    TextView activityTitle;
    String nameOfFolder;


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
        allRecords =  findViewById(R.id.allRecords);
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

        floatingActionButton();
    }


    public void floatingActionButton(){

        final FloatingActionButton buttonAddRecord = findViewById(R.id.button_add_record);
        buttonAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                buttonAddRecord.startAnimation(animation3);
                Intent intent = new Intent(getApplicationContext(), AddNewRecord_Activity.class);
                intent.putExtra(EXTRA_FOLDER, nameOfFolder); //to know which folder we came from
                intent.putExtra(EXTRA_ORIGIN, "buttonAddRecord"); //EXTRA_ORIGIN gets the current position in the code
                Toast.makeText(CategoryList_Activity.this, nameOfFolder, Toast.LENGTH_SHORT).show();
                startActivityForResult(intent, ADD_RECORD_REQUEST);
            }
        });
    }


        public void goToFolderRecords(View view) {
        if (view == bankAccounts)
            nameOfFolder = "Bank Accounts";
        else if (view==creditCard)
            nameOfFolder = "Credit Cards";
        else if (view==socialMedia)
            nameOfFolder = "Social Media";
        else if (view==webAccounts)
            nameOfFolder = "Website & Email";
        else if (view==onlineShopping)
            nameOfFolder = "Online Shopping";
        else if (view==cryptocurrency)
            nameOfFolder = "Cryptocurrency";
        else if (view==drivingLicence)
            nameOfFolder = "Driving Licence";
        else if (view==passports)
            nameOfFolder = "Passports";
        else if (view==allRecords)
            nameOfFolder = "All Records";
        else if (view==notes)
            nameOfFolder = "Notes";

        mediaPlayer.start();
        view.startAnimation(animation3);
        Intent intent = new Intent(this, RecordRecycler_Activity.class);
        intent.putExtra(EXTRA_FOLDER, nameOfFolder);
        this.startActivity(intent);
//        startActivityForResult(intent, ADD_RECORD_REQUEST);

    }

    public void search(View view) {
        mediaPlayer.start();
        search_btn.startAnimation(animation3);
        if (search_bar.getText().toString().equals("")){
            goToFolderRecords(allRecords);
        }
        else {
            String searchString = search_bar.getText().toString();

            Intent intent = new Intent(this, RecordRecycler_Activity.class);
            intent.putExtra(EXTRA_SEARCH,searchString);
            intent.putExtra(EXTRA_FOLDER, "Search");
            this.startActivity(intent);
            overridePendingTransition(0, 0);

        }
    }

    public void openSearch(View view) {
        mediaPlayer.start();
        search_icon.startAnimation(animation3);

        if (search_layout.getVisibility() == View.GONE) {
            search_layout.startAnimation(animation1);
            search_layout.setVisibility(View.VISIBLE);
            search_bar.setVisibility(View.VISIBLE);
            search_bar.requestFocus();
            search_btn.setVisibility(View.VISIBLE);
        } else {
            search_layout.setVisibility(View.GONE);
            search_bar.setVisibility(View.GONE);
            search_btn.setVisibility(View.GONE);
            search_bar.clearFocus();
//            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE); //Hide keyboard
//            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }

    }

    public void openMenu(View view) {
    }

    public void openOptions(View view) {
        Intent intent = new Intent(this, SecurityLevel_Activity.class);
        this.startActivity(intent);
    }
}
