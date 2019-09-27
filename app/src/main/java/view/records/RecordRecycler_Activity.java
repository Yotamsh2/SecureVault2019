package view.records;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.record.Record;
import com.securevault19.securevault2019.record.RecordAdapter;

import java.util.ArrayList;
import java.util.List;

import view.explorer.CategoryList_Activity;
import view_model.records.Record_ViewModel;

import static com.securevault19.securevault2019.R.raw.button;
import static view.explorer.CategoryList_Activity.EXTRA_FOLDER;

public class RecordRecycler_Activity extends AppCompatActivity implements RecordAdapter.OnRecordListener {
    public static final int ADD_RECORD_REQUEST = 1;

    public static final String EXTRA_TYPE =
            "com.example.architectureexample.EXTRA_TYPE";
    public static final String EXTRA_ORIGIN =
            "com.example.architectureexample.EXTRA_ORIGIN";


    private Record_ViewModel viewModel;
    private List<Record> records = new ArrayList<>();
    private TextView activityTitle;
    private Typeface myFont;
    String folder;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_recycler);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        mediaPlayer = MediaPlayer.create(this, button);

        //Animation Sets
        Animation animation1 = AnimationUtils.loadAnimation(RecordRecycler_Activity.this,R.anim.zoomin);
        final Animation animation2 = AnimationUtils.loadAnimation(RecordRecycler_Activity.this,R.anim.bottomtotop);
        final Animation animation3 = AnimationUtils.loadAnimation(RecordRecycler_Activity.this,R.anim.buttonpush_anim);
        //button.startAnimation(animation1);


        final RecordAdapter recordAdapter = new RecordAdapter((ArrayList<Record>) records, this);
        recyclerView.setAdapter(recordAdapter);

        viewModel = ViewModelProviders.of(this).get(Record_ViewModel.class);
//        showCurrentCategory(recordAdapter);
        //getting the String to know which Category to show
        Bundle extras = getIntent().getExtras();

        //checking the extras is not null -> to get rid of "null object reference"
        if (extras != null) {
            folder = extras.getString(EXTRA_FOLDER);

            if (folder != null) {
                Log.d("back", "folder is not null:  " + folder);

                //Switch case for each Category to show
                switch (folder) {
                    case "BankAccounts":
                        viewModel.getAllBankAccounts().observe(this, new Observer<List<Record>>() {
                            @Override
                            public void onChanged(List<Record> records) {
                                recordAdapter.setRecords(records);
                                recordAdapter.notifyDataSetChanged();

                            }
                        });
                        break;
                    case "CreditCards":
                        viewModel.getAllCreditCards().observe(this, new Observer<List<Record>>() {
                            @Override
                            public void onChanged(List<Record> records) {
                                recordAdapter.setRecords(records);
                                recordAdapter.notifyDataSetChanged();

                            }
                        });
                        break;
                    case "SocialMedia":
                        viewModel.getAllSocialMedia().observe(this, new Observer<List<Record>>() {
                            @Override
                            public void onChanged(List<Record> records) {
                                recordAdapter.setRecords(records);
                                recordAdapter.notifyDataSetChanged();

                            }
                        });
                        break;
                    case "WebAccounts":
                        viewModel.getAllWebAccounts().observe(this, new Observer<List<Record>>() {
                            @Override
                            public void onChanged(List<Record> records) {
                                recordAdapter.setRecords(records);
                                recordAdapter.notifyDataSetChanged();

                            }
                        });
                        break;
                    case "OnlineShopping":
                        viewModel.getAllOnlineShopping().observe(this, new Observer<List<Record>>() {
                            @Override
                            public void onChanged(List<Record> records) {
                                recordAdapter.setRecords(records);
                                recordAdapter.notifyDataSetChanged();

                            }
                        });
                        break;
                    case "Cryptocurrency":
                        viewModel.getAllCryptocurrency().observe(this, new Observer<List<Record>>() {
                            @Override
                            public void onChanged(List<Record> records) {
                                recordAdapter.setRecords(records);
                                recordAdapter.notifyDataSetChanged();

                            }
                        });
                        break;
                    case "DrivingLicence":
                        viewModel.getAllDrivingLicence().observe(this, new Observer<List<Record>>() {
                            @Override
                            public void onChanged(List<Record> records) {
                                recordAdapter.setRecords(records);
                                recordAdapter.notifyDataSetChanged();

                            }
                        });
                        break;
                    case "Passports":
                        viewModel.getAllPassports().observe(this, new Observer<List<Record>>() {
                            @Override
                            public void onChanged(List<Record> records) {
                                recordAdapter.setRecords(records);
                                recordAdapter.notifyDataSetChanged();

                            }
                        });
                        break;
                    case "Customized":
                        viewModel.getAllCustomized().observe(this, new Observer<List<Record>>() {
                            @Override
                            public void onChanged(List<Record> records) {
                                recordAdapter.setRecords(records);
                                recordAdapter.notifyDataSetChanged();

                            }
                        });
                        break;
                    case "Notes":
                        viewModel.getAllNotes().observe(this, new Observer<List<Record>>() {
                            @Override
                            public void onChanged(List<Record> records) {
                                recordAdapter.setRecords(records);
                                recordAdapter.notifyDataSetChanged();

                            }
                        });
                        break;
                    default: //shows all records
//                   Meanwhile, untill we know to show the last category used, we show the category list.
                        //------>>>>>>>>>>>>>>>>>>>>>THE PROBLEM: after adding new record: allways getting to this default case.<<<<<<<<<<<<<<<<<<---------------------------
                        Intent intent = new Intent(this, CategoryList_Activity.class);
                        startActivity(intent);
                        break;
                }
            }

        }


        final FloatingActionButton buttonAddRecord = findViewById(R.id.button_add_record);
        buttonAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                buttonAddRecord.startAnimation(animation3);
                Intent intent = new Intent(getApplicationContext(), AddNewRecord_Activity.class);
                intent.putExtra(EXTRA_FOLDER, folder); //to know which folder we came from
                intent.putExtra(EXTRA_ORIGIN, "buttonAddRecord"); //EXTRA_ORIGIN gets the current position in the code
                finish();

                startActivityForResult(intent, ADD_RECORD_REQUEST);

            }
        });


        activityTitle = findViewById(R.id.activityTitle);
        //        Set logo's font to category's text
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/OutlierRail.ttf");
        activityTitle.setTypeface(myFont);

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
    public void onRecordClick(int position) {
        Log.d("onRecordClick", "clicked. " + position);
        Toast.makeText(this, "clicked.", Toast.LENGTH_SHORT).show();
        // records.get(position);
        String folder = getIntent().getExtras().getString(EXTRA_FOLDER);
        String type;

        Intent intent = new Intent(getApplicationContext(), AddNewRecord_Activity.class);
        intent.putExtra(EXTRA_FOLDER, folder);

        if (!records.isEmpty()) {       //we have to check if the 'records' ArrayList is not empty.
            type = records.get(position).type;
            intent.putExtra(EXTRA_TYPE, type);
        }

        //passing to Add_New_Record where we came from - to decide what type of screen to show.
        intent.putExtra(EXTRA_ORIGIN, "onRecordClick"); //EXTRA_ORIGIN gets the current position in the code
        startActivity(intent);


    }

    public void openOptions(View view) {
    }


    public void back(View view) {
        mediaPlayer.start();
        Intent intent = new Intent(this, CategoryList_Activity.class);
        this.startActivity(intent);
    }


}