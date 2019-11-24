package view.records;

import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.record.Record;
import com.securevault19.securevault2019.record.RecordAdapter;
import com.securevault19.securevault2019.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cryptography.Cryptography;
import view.explorer.CategoryList_Activity;
import view_model.records.Record_ViewModel;
import view_model.records.User_ViewModel;

import static com.securevault19.securevault2019.R.raw.button;
import static view.explorer.CategoryList_Activity.EXTRA_FOLDER;
import static view.explorer.CategoryList_Activity.EXTRA_SEARCH;

public class RecordRecycler_Activity extends AppCompatActivity implements RecordAdapter.OnRecordListener,Serializable {
    private String CRYPTO_KEY;
    public static final int ADD_RECORD_REQUEST = 1;
    public static final String EXTRA_TYPE =
            "com.securevault19.securevault2019.EXTRA_TYPE";
    public static final String EXTRA_ORIGIN =
            "com.securevault19.securevault2019.EXTRA_ORIGIN";


    private Record_ViewModel recordViewModel;
    private User_ViewModel userViewModel;
    private List<Record> records = new ArrayList<>();
    private TextView activityTitle;
    private Typeface myFont;
    private CountDownTimer timer;
    private String nameOfFolder;
    private MediaPlayer mediaPlayer;
    private RelativeLayout search_layout;
    private ImageButton search_btn;
    private EditText search_bar;
    private Animation animation3;
    private RecyclerView recyclerView;
    private String searchString;
    private FloatingActionButton button_add_record;
    private ImageView clearSearch;


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
        CRYPTO_KEY = getIntent().getStringExtra("CRYPTO_KEY");
        Log.d("userTest2Get", "" + CRYPTO_KEY);
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

        timer = new CountDownTimer(10 *60 * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished == 60*1000){
                    AlertDialog.Builder alert = new AlertDialog.Builder(RecordRecycler_Activity.this);
                    alert.setTitle("Logout timer");
                    alert.setMessage("No action detected. App will be closed in 1 minute.");
                    alert.setPositiveButton("Log out", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Account log out", Toast.LENGTH_SHORT).show();
                            System.exit(0);
                        }
                    });
                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            timer.cancel();
                        }
                    });
                    alert.create().show();                }
            }

            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Account log out", Toast.LENGTH_SHORT).show();
                System.exit(0);
            }
        };
        timer.start();
    }



    @SuppressLint("StaticFieldLeak")
    public void recycler() {

        final RecordAdapter recordAdapter = new RecordAdapter((ArrayList<Record>) records, this, CRYPTO_KEY);
        recyclerView.setAdapter(recordAdapter);

        recordViewModel = ViewModelProviders.of(this).get(Record_ViewModel.class);
        userViewModel = ViewModelProviders.of(this).get(User_ViewModel.class);
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
                    recordViewModel.getAllRecords().observe(this, new Observer<List<Record>>() {
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
                                encryptedSearchString = cryptography.encryptWithKey(CRYPTO_KEY, searchString);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    };
                    recordViewModel.getSearchRecords(encryptedSearchString).observe(this, new Observer<List<Record>>() {

                        @Override
                        public void onChanged(List<Record> records) {
                            recordAdapter.setRecords(records);
                            recordAdapter.notifyDataSetChanged();
                            activityTitle.setText(nameOfFolder);
                        }
                    });
                } else if (nameOfFolder.equals("Favorites")) {
                    recordViewModel.getFavoritesRecords().observe(this, new Observer<List<Record>>() {
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
                    recordViewModel.getAllFolder(nameOfFolder).observe(this, new Observer<List<Record>>() {
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
                Log.d("userTest3Send", "" + CRYPTO_KEY);
                intent.putExtra("CRYPTO_KEY", CRYPTO_KEY);
                startActivityForResult(intent, ADD_RECORD_REQUEST);
                overridePendingTransition(0, 0);

            }
        });

    }


    @Override
    public void onBackPressed() {
        back(null);
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



        new AsyncTask<Void, Void, Void>() {

            Record record;
            String type;

            @Override
            protected Void doInBackground(Void... voids) {
                record = recordViewModel.getRecordDetails(records.get(position).getRecordID());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent(getApplicationContext(), AddNewRecord_Activity.class);
                intent.putExtra("CRYPTO_KEY", CRYPTO_KEY);
                intent.putExtra(EXTRA_FOLDER, folder);
                intent.putExtra("recordClassDB",  record);

                if (!records.isEmpty()) {       //we have to check if the 'records' ArrayList is not empty.

                    type = records.get(position).type;

                    intent.putExtra(EXTRA_TYPE, type);
                }

                timer.cancel();
                timer.start();
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
        timer.cancel();
        timer.start();
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