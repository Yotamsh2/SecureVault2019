package view.records;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.record.Record;
import com.securevault19.securevault2019.record.RecordAdapter;

import java.util.ArrayList;
import java.util.List;

import view.explorer.CategoryList_Activity;
import view_model.records.Record_ViewModel;

public class RecordRecycler_Activity extends AppCompatActivity implements RecordAdapter.OnRecordListener {
    public static final int ADD_RECORD_REQUEST = 1;

    private Record_ViewModel viewModel;
    private List<Record> records = new ArrayList<>();
    private TextView activityTitle;
    private Typeface myFont;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_recycler);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        final RecordAdapter recordAdapter = new RecordAdapter((ArrayList<Record>) records, this);
        recyclerView.setAdapter(recordAdapter);

        viewModel = ViewModelProviders.of(this).get(Record_ViewModel.class);
//        showCurrentCategory(recordAdapter);
        //getting the String to know which Category to show
        Bundle extras = getIntent().getExtras();
        String type;

        //checking the extras is not null -> to get rid of "null object reference"
        if (extras != null) {
            type = extras.getString(CategoryList_Activity.EXTRA_CATEGORY);

            if (type != null) {

                //Switch case for each Category to show
            switch (type) {
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

        //right now, getting back to bankAccounts. needs to be changed to the Category
//        viewModel.getAllBankAccounts().observe(this, new Observer<List<Record>>() {
//            @Override
//            public void onChanged(List<Record> records) {
//                recordAdapter.setRecords(records);
//                recordAdapter.notifyDataSetChanged();
//
//            }
//        });

        FloatingActionButton buttonAddRecord = findViewById(R.id.button_add_record);
        buttonAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                finish();
                Intent intent = new Intent(getApplicationContext(), AddNewRecord_Activity.class);
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

    @Override
    public void onRecordClick(int position) {
        Log.d("onRecordClick", "clicked. " + position);
        Toast.makeText(this, "clicked.", Toast.LENGTH_SHORT).show();
       // records.get(position);

    }

    public void openOptions(View view) {
    }


    public void back(View view) {
        Intent intent = new Intent(this, CategoryList_Activity.class);
        this.startActivity(intent);
    }


}