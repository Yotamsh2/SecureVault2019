package view.records;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.record.Record;
import com.securevault19.securevault2019.record.RecordAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cryptography.Cryptography;
import local_database.DatabaseClient;
import repository.RecordRepository;
import view.explorer.CategoryList_Activity;
import view_model.records.Record_ViewModel;

import static com.securevault19.securevault2019.R.raw.button;
import static view.explorer.CategoryList_Activity.EXTRA_FOLDER;
import static view.explorer.CategoryList_Activity.EXTRA_SEARCH;

public class RecordRecycler_Activity extends AppCompatActivity implements RecordAdapter.OnRecordListener,Serializable {
    private String user;
    public static final int ADD_RECORD_REQUEST = 1;

    public static final String EXTRA_TYPE =
            "com.securevault19.securevault2019.EXTRA_TYPE";
    public static final String EXTRA_ORIGIN =
            "com.securevault19.securevault2019.EXTRA_ORIGIN";



    private Record_ViewModel viewModel;
    private List<Record> records = new ArrayList<>();
    private TextView activityTitle;
    private Typeface myFont;
    String nameOfFolder;
    MediaPlayer mediaPlayer;
    RelativeLayout search_layout;
    ImageButton search_btn;
    EditText search_bar;
    Animation animation3;
    RecyclerView recyclerView;
    String searchString;
    FloatingActionButton button_add_record;
    ImageView clearSearch;


    String encryptedSearchString;
    Cryptography cryptography = new Cryptography();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_recycler);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        mediaPlayer = MediaPlayer.create(this, button);
        search_layout = findViewById(R.id.search_layout);
        search_btn = findViewById(R.id.search_btn);
        search_bar = findViewById(R.id.search_bar);
        activityTitle = findViewById(R.id.activityTitle);
        button_add_record = findViewById(R.id.button_add_record);
        clearSearch = findViewById(R.id.clearSearch);


        //Set logo's font to category's text
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/OutlierRail.ttf");
        activityTitle.setTypeface(myFont);

        //Animation Sets
        Animation animation1 = AnimationUtils.loadAnimation(RecordRecycler_Activity.this, R.anim.zoomin);
        final Animation animation2 = AnimationUtils.loadAnimation(RecordRecycler_Activity.this, R.anim.bottomtotop);
        animation3 = AnimationUtils.loadAnimation(RecordRecycler_Activity.this, R.anim.buttonpush_anim);

        //////////////////////////////////////////////////////////
        user = getIntent().getStringExtra("CRYPTO_KEY");

        Log.d("userTest2Get", "" + user);
        //////////////////////////////////////////////////////////
        recycler();

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
    }



    @SuppressLint("StaticFieldLeak")
    public void recycler() {

        final RecordAdapter recordAdapter = new RecordAdapter((ArrayList<Record>) records, this, user);
        recyclerView.setAdapter(recordAdapter);

        viewModel = ViewModelProviders.of(this).get(Record_ViewModel.class);
        //showCurrentCategory(recordAdapter);
        //getting the String to know which Category to show
        Bundle extras = getIntent().getExtras();

//////////checking the extras is not null -> to get rid of "null object reference"/////////////////////////////////////
        if (extras != null) {
            nameOfFolder = extras.getString(EXTRA_FOLDER);
            searchString = extras.getString(EXTRA_SEARCH);

            if (nameOfFolder != null) {
                Log.d("back", "folder is not null:  " + nameOfFolder);

                if (nameOfFolder.equals("All Records")) {
                    viewModel.getAllRecords().observe(this, new Observer<List<Record>>() {
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onChanged(List<Record> records) {
                            recordAdapter.setRecords(records);
                            recordAdapter.notifyDataSetChanged();
                            activityTitle.setText(nameOfFolder);
                            button_add_record.setVisibility(View.GONE);
                        }
                    });
                } else if (nameOfFolder.equals("Search")) {
                    search_layout.setVisibility(View.VISIBLE);
                    search_bar.setText(searchString);
                    new AsyncTask<Void, Void, Void>() {
                        @Override  //try for Encryption
                        protected Void doInBackground(Void... voids) {
                            try {
                                encryptedSearchString = cryptography.encryptWithKey(user, searchString);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    };
                    Toast.makeText(this, encryptedSearchString, Toast.LENGTH_LONG).show();
                    viewModel.getSearchRecords(encryptedSearchString).observe(this, new Observer<List<Record>>() {

                        @Override
                        public void onChanged(List<Record> records) {
                            recordAdapter.setRecords(records);
                            recordAdapter.notifyDataSetChanged();
                            activityTitle.setText(nameOfFolder);
                        }
                    });
                } else if (nameOfFolder.equals("Favorites")) {
                    viewModel.getFavoritesRecords().observe(this, new Observer<List<Record>>() {
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onChanged(List<Record> records) {
                            recordAdapter.setRecords(records);
                            recordAdapter.notifyDataSetChanged();
                            activityTitle.setText(nameOfFolder);
                            button_add_record.setVisibility(View.GONE);
                        }
                    });
                } else {
                    viewModel.getAllFolder(nameOfFolder).observe(this, new Observer<List<Record>>() {
                        @Override
                        public void onChanged(List<Record> records) {
                            recordAdapter.setRecords(records);
                            recordAdapter.notifyDataSetChanged();
                            activityTitle.setText(nameOfFolder);
                        }
                    });
                }
            }
        }

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
                Log.d("userTest3Send", "" + user);
                intent.putExtra("CRYPTO_KEY", user);
                Toast.makeText(RecordRecycler_Activity.this, nameOfFolder, Toast.LENGTH_SHORT).show();
                startActivityForResult(intent, ADD_RECORD_REQUEST);
                finish();
                overridePendingTransition(0, 0);

            }
        });

    }


    @Override
    public void onBackPressed() {
        back(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_RECORD_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddNewRecord_Activity.EXTRA_TITLE);
            String userName = data.getStringExtra(AddNewRecord_Activity.EXTRA_USERNAME);
            String password = data.getStringExtra(AddNewRecord_Activity.EXTRA_PASSWORD);
            String email = data.getStringExtra(AddNewRecord_Activity.EXTRA_EMAIL);
            String website = data.getStringExtra(AddNewRecord_Activity.EXTRA_WEBSITE);
            String expiringDateDay = data.getStringExtra(AddNewRecord_Activity.EXTRA_EXPIRING_DATE_DAY);
            String expiringDateMonth = data.getStringExtra(AddNewRecord_Activity.EXTRA_EXPIRING_DATE_MONTH);
            String expiringDateyear = data.getStringExtra(AddNewRecord_Activity.EXTRA_EXPIRING_DATE_YEAR);
            String other = data.getStringExtra(AddNewRecord_Activity.EXTRA_OTHER);


        }
    }

    //Creating new Record
    @Override
    public void onRecordClick(final int position, final List<Record> records) {   // clicked or exiting record
        Log.d("onRecordClick", "clicked. " + position);
        // ------------------------------------------------------------------------------- //
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! //
        Log.d("onRecordClick", "clicked " + records.get(position).getRecordID());
        // ------------------------------------------------------------------------------- //
        // records.get(position);
        final String folder = Objects.requireNonNull(getIntent().getExtras()).getString(EXTRA_FOLDER);
        //  String type;
        int clickedRecordIdPosition = records.get(position).getRecordID();

        // the class is Record and not LiveData because we just want to show the details in fileds and not use them as Live Data

        // Record record = viewModel.getRecordDetails(records.get(position).getRecordID());
        //Log.d("recordTest321","" +record.getUserName());
//        new AsyncTask<Void, Void, Void>() {
//
//            String recordUserName;
//
//            @Override
//            protected Void doInBackground(Void... voids) {
//                Record record = DatabaseClient.getInstance(getApplication()).getRecordDatabase2().daoRecord().getRecordDetails(records.get(position).getRecordID());
//                recordUserName = record.getUserName();
//                Log.d("recordTest", "" + record.getUserName() + " " + record.getPassword());
//                return null;
//            }
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//
//
//            }
//
//
//        }.execute();


        new AsyncTask<Void, Void, Void>() {

            Record record;

//            String titleRecord;
//            String dateCreatedRecord;
//            String lastModifiedRecord;
//            String passwordRecord;
//            String emailRecord;
//            String websiteRecord;
//            String expiringDate;
//            String userNameRecord;
//            String accountNumberRecord;
//            String IBANRecord;
//            int bankNumberRecord;
//            String adressRecord;
//            int cardNumberRecord;
//            int CVVRecord;
//            int expireYearRecord;
//            int expireMonthRecord;
//            int expireDayRecord;
//            String publicKeyRecord;
//            String privateKeyRecord;
//            String walletGenerationSeedRecord;
//            int licenceNumberRecord;
//            String serviceNameRecord; //email
//            int passportNumberRecord;
//            String issuanceDateRecord;
                 String type;

            @Override
            protected Void doInBackground(Void... voids) {
                record = DatabaseClient.getInstance(getApplication()).getRecordDatabase2().daoRecord()
                        .getRecordDetails(records.get(position).getRecordID());
//                titleRecord = record.getTitle();
//                dateCreatedRecord = record.getDateCreated();
//                lastModifiedRecord = record.getLastModified();
//                passwordRecord = record.getPassword();
//                emailRecord = record.getEmail();
//                websiteRecord = record.getWebsite();
//                expiringDate = record.getExpiringDate();
//                userNameRecord = record.getUserName();
//                accountNumberRecord = record.getAccountNumber();
//                IBANRecord = record.getIBAN();
//                bankNumberRecord = record.getBankNumber();
//                adressRecord = record.getAddress();
//                cardNumberRecord = record.getCardNumber();
//                CVVRecord = record.getCVV();
//                expireYearRecord = record.getExpireYear();
//                expireMonthRecord = record.getExpireMonth();
//                expireDayRecord = record.getExpireDay();
//                publicKeyRecord = record.getPublicKey();
//                privateKeyRecord = record.getPrivateKey();
//                walletGenerationSeedRecord = record.getWalletGenerationSeed();
//                licenceNumberRecord = record.getLicenceNumber();
//                serviceNameRecord = record.getServiceName();
//                passportNumberRecord = record.getPassportNumber();
//                issuanceDateRecord = record.getIssuanceDate();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mediaPlayer.start();
                Intent intent = new Intent(getApplicationContext(), AddNewRecord_Activity.class);
                intent.putExtra("CRYPTO_KEY", user);
                intent.putExtra(EXTRA_FOLDER, folder);
                //intent.putExtra("userNameRecord", userNameRecord);
                intent.putExtra("recordClassDB",  record);

                if (!records.isEmpty()) {       //we have to check if the 'records' ArrayList is not empty.

                    type = records.get(position).type;

                    intent.putExtra(EXTRA_TYPE, type);
                }

        //passing to Add_New_Record where we came from - to decide what type of screen to show.
        intent.putExtra(EXTRA_ORIGIN, "onRecordClick"); //EXTRA_ORIGIN gets the current position in the code
        startActivity(intent);
        overridePendingTransition(0, 0);


            }

        }.execute();


    }

    public void openOptions(View view) {
    }

    public void back(View view) {
        mediaPlayer.start();
        finish();
        overridePendingTransition(0, 0);

    }


    public void search(View view) {
        mediaPlayer.start();
        search_btn.startAnimation(animation3);
        if (search_bar.getText().toString().equals(searchString)) {
            return;
        } else if (search_bar.getText().toString().equals("")) {
            searchString = "%";
        } else {
            searchString = search_bar.getText().toString();
        }

        Intent intent = new Intent(this, RecordRecycler_Activity.class);
        intent.putExtra(EXTRA_SEARCH, searchString);
        intent.putExtra(EXTRA_FOLDER, "Search");
        this.startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
    }

    public void openMenu(View view) {
    }

    public void clearSearch(View view) {
        search_bar.setText("");
    }
}