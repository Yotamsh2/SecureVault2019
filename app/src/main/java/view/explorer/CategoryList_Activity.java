package view.explorer;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.user.CurrentUser;

import cryptography.Cryptography;
import local_database.DatabaseClient;
import view.preferences.SettingsActivity;
import view.records.AddNewRecord_Activity;
import view.records.RecordRecycler_Activity;

public class CategoryList_Activity extends AppCompatActivity {
    //-----------------//
    private String CRYPTO_KEY;
    private String pattern;
    //----------------//
    public static final int ADD_RECORD_REQUEST = 1;
    public static final String EXTRA_FOLDER =
            "com.securevault19.securevault2019.EXTRA_FOLDER";
    public static final String EXTRA_SEARCH =
            "com.securevault19.securevault2019.EXTRA_SEARCH";
    public static final String EXTRA_ORIGIN =
            "com.securevault19.securevault2019.EXTRA_ORIGIN";
    public static final String EXTRA_PATTERN =
            "com.securevault19.securevault2019.EXTRA_PATTERN";


    private String encryptedSearchString;
    private String searchString;

    private Typeface myFont;
    public CountDownTimer timer;
    private TextView category1, category2, category3, category4, category5, category6, category7, category8, category9, category10;
    private GridLayout mainGrid;
    private CardView bankAccounts, creditCard, socialMedia, webAccounts, onlineShopping, cryptocurrency, drivingLicence, passports, allRecords, notes;
    private Animation animation1, animation2, animation3, animation4;
    private MediaPlayer mediaPlayer;
    private RelativeLayout search_layout;
    private Button search_icon;
    private ImageButton search_btn, favorites;
    private EditText search_bar;
    private TextView activityTitle;
    private String nameOfFolder, firstName;
    private ImageView clearSearch;
    private Cryptography cryptography;


    @Override
    protected void onCreate(Bundle saveBtndInstanceState) {
        super.onCreate(saveBtndInstanceState);
        setContentView(R.layout.activity_category_list2);
// ----------------------------------------------------------- //
// drawing the user name from the log in activity ( the user is out KEY as encryptedWIthKey method.

        CRYPTO_KEY = getIntent().getStringExtra("CRYPTO_KEY");


        Log.d("userTest1Get"," "+CRYPTO_KEY);
        pattern = CurrentUser.getInstance().getPatternLock();
        cryptography = new Cryptography();
        try {
            firstName = cryptography.decrypt(CurrentUser.getInstance().getFirstName(), CRYPTO_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }

// ----------------------------------------------------------- //

        mediaPlayer = MediaPlayer.create(this, R.raw.button);
        favorites = findViewById(R.id.favorites);
        bankAccounts = findViewById(R.id.bankAccount);
        creditCard = findViewById(R.id.creditCard);
        socialMedia = findViewById(R.id.socialMedia);
        webAccounts = findViewById(R.id.webAccounts);
        onlineShopping = findViewById(R.id.onlineShopping);
        cryptocurrency = findViewById(R.id.cryptocurrency);
        drivingLicence = findViewById(R.id.drivingLicence);
        passports = findViewById(R.id.passport);
        allRecords = findViewById(R.id.allRecords);
        notes = findViewById(R.id.notes);
        search_layout = findViewById(R.id.search_layout);
        search_btn = findViewById(R.id.search_btn);
        search_bar = findViewById(R.id.search_bar);
        search_icon = findViewById(R.id.search_icon);
        activityTitle = findViewById(R.id.activityTitle);
        clearSearch = findViewById(R.id.clearSearch);


        mainGrid = findViewById(R.id.mainGrid);
        //Animation Sets
        animation1 = AnimationUtils.loadAnimation(CategoryList_Activity.this, R.anim.zoomin_fast);
        animation2 = AnimationUtils.loadAnimation(CategoryList_Activity.this, R.anim.categories_anim);
        animation3 = AnimationUtils.loadAnimation(CategoryList_Activity.this, R.anim.buttonpush_anim);
        animation4 = AnimationUtils.loadAnimation(CategoryList_Activity.this, R.anim.zoomout);
        mainGrid.startAnimation(animation2);


//        Set logo's font to category's text
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/OutlierRail.ttf");
        activityTitle.setTypeface(myFont);
        if (!firstName.equals(null))
            activityTitle.setText(firstName);
        else
            activityTitle.setText("Welcome");
        category1 = findViewById(R.id.category1);
        category2 = findViewById(R.id.category2);
        category3 = findViewById(R.id.category3);
        category4 = findViewById(R.id.category4);
        category5 = findViewById(R.id.category5);
        category6 = findViewById(R.id.category6);
        category7 = findViewById(R.id.category7);
        category8 = findViewById(R.id.category8);
        category9 = findViewById(R.id.category9);
        category10 = findViewById(R.id.category10);
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

        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!search_bar.getText().toString().equals("")){
                    clearSearch.setVisibility(View.VISIBLE);}
                else if (search_bar.getText().toString().equals("")){
                    clearSearch.setVisibility(View.GONE); }
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (search_bar.getText().toString().equals("")){
                    clearSearch.setVisibility(View.VISIBLE);}
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (search_bar.getText().toString().equals("")){
                    clearSearch.setVisibility(View.GONE);}
            }
        });

        timer = new CountDownTimer(5 *60 * 1000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                Toast.makeText(getApplicationContext(), "No activity detected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),PatternLockView_Activity.class);
                intent.putExtra("PATTERN",pattern);
                intent.putExtra("CRYPTO_KEY", CRYPTO_KEY);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        };
        timer.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "Resumed", Toast.LENGTH_SHORT).show();

    }

    public void floatingActionButton() {

        final FloatingActionButton buttonAddRecord = findViewById(R.id.button_add_record);
        buttonAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                buttonAddRecord.startAnimation(animation3);
                Intent intent = new Intent(getApplicationContext(), AddNewRecord_Activity.class);
                intent.putExtra(EXTRA_FOLDER, nameOfFolder); //to know which folder we came from
                intent.putExtra(EXTRA_ORIGIN, "buttonAddRecord"); //EXTRA_ORIGIN gets the current position in the code
                intent.putExtra("CRYPTO_KEY",CRYPTO_KEY);    // passing the userName for the KEY encryption
                Toast.makeText(CategoryList_Activity.this, nameOfFolder, Toast.LENGTH_SHORT).show();
                startActivityForResult(intent, ADD_RECORD_REQUEST);
                overridePendingTransition(0, 0);
            }
        });
    }


    public void goToFolderRecords(View view) {               // on click func       // the view is what appears on top of the recycler view
        if (view == bankAccounts)
            nameOfFolder = "Bank Accounts";
        else if (view == creditCard)
            nameOfFolder = "Credit Cards";
        else if (view == socialMedia)
            nameOfFolder = "Social Media";
        else if (view == webAccounts)
            nameOfFolder = "Website & Email";
        else if (view == onlineShopping)
            nameOfFolder = "Online Shopping";
        else if (view == cryptocurrency)
            nameOfFolder = "Cryptocurrency";
        else if (view == drivingLicence)
            nameOfFolder = "Driving Licence";
        else if (view == passports)
            nameOfFolder = "Passports";
        else if (view == allRecords)
            nameOfFolder = "All Records";
        else if (view == notes)
            nameOfFolder = "Notes";
        else if (view == favorites)
            nameOfFolder = "Favorites";


        timer.cancel();
        timer.start();
        mediaPlayer.start();
        view.startAnimation(animation3);
        Intent intent = new Intent(this, RecordRecycler_Activity.class);
        // pass extra the name of the folder that clicked
        Log.d("userTest2Send",""+CRYPTO_KEY);
        intent.putExtra("CRYPTO_KEY",CRYPTO_KEY);
        Log.d("userCheck", "!!!" + CRYPTO_KEY);
        intent.putExtra(EXTRA_FOLDER, nameOfFolder);
        this.startActivity(intent);
        overridePendingTransition(0, 0);
//        startActivityForResult(intent, ADD_RECORD_REQUEST);
    }

    public void search(View view) {                                             // on click method
        final Cryptography cryptography = new Cryptography();
        timer.cancel();
        timer.start();
        mediaPlayer.start();
        search_btn.startAnimation(animation3);
        if (search_bar.getText().toString().equals("")) {
            goToFolderRecords(allRecords);
        } else {
             searchString = search_bar.getText().toString();
              new AsyncTask<Void,Void,Void>(){

                 @Override
                 protected Void doInBackground(Void... voids) {
                     try {
                         Log.d("encryptedSearchString", "before encryption");
                         encryptedSearchString = cryptography.encryptWithKey(CRYPTO_KEY,searchString.trim());
                         Log.d("encryptedSearchString", "after encryption");
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                     return null;
                 }
                 @Override
                 protected void onPostExecute(Void aVoid) {
                     super.onPostExecute(aVoid);
                     Intent intent = new Intent(getApplicationContext(), RecordRecycler_Activity.class);
                     intent.putExtra("encryptedSearchString", encryptedSearchString);
                     intent.putExtra(EXTRA_SEARCH, searchString.trim());
                     intent.putExtra(EXTRA_FOLDER, "Search");
                     intent.putExtra("CRYPTO_KEY",CRYPTO_KEY);
                     Log.d("encryptedSearchString", "entering to next Activity With " + encryptedSearchString);
                     startActivity(intent);
                     overridePendingTransition(0, 0);
                 }
             }.execute();
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
//        Intent intent = new Intent(this, SecurityLevel_Activity.class);
        Intent intent = new Intent(this, Team_Activity.class);
        this.startActivity(intent);
    }

    public void clearSearch(View view) {
        search_bar.setText("");
    }
}
