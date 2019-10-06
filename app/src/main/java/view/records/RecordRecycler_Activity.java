package view.records;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import java.util.Objects;

import view.explorer.CategoryList_Activity;
import view_model.records.Record_ViewModel;

import static com.securevault19.securevault2019.R.raw.button;
import static view.explorer.CategoryList_Activity.EXTRA_FOLDER;
import static view.explorer.CategoryList_Activity.EXTRA_SEARCH;

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
    String nameOfFolder;
    MediaPlayer mediaPlayer;
    RelativeLayout search_layout;
    Button search_btn;
    EditText search_bar;
    Animation animation3;
    RecyclerView recyclerView;
    String searchString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_recycler);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        mediaPlayer = MediaPlayer.create(this, button);
        search_layout =  findViewById(R.id.search_layout);
        search_btn =  findViewById(R.id.search_btn);
        search_bar =  findViewById(R.id.search_bar);
        activityTitle = findViewById(R.id.activityTitle);

        //Set logo's font to category's text
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/OutlierRail.ttf");
        activityTitle.setTypeface(myFont);

        //Animation Sets
        Animation animation1 = AnimationUtils.loadAnimation(RecordRecycler_Activity.this,R.anim.zoomin);
        final Animation animation2 = AnimationUtils.loadAnimation(RecordRecycler_Activity.this,R.anim.bottomtotop);
        animation3 = AnimationUtils.loadAnimation(RecordRecycler_Activity.this,R.anim.buttonpush_anim);


        recycler();

        floatingActionButton();

    }

    public void recycler (){

        final RecordAdapter recordAdapter = new RecordAdapter((ArrayList<Record>) records, this);
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

                if (nameOfFolder.equals("All Records")){
                    viewModel.getAllRecords().observe(this, new Observer<List<Record>>() {
                        @Override
                        public void onChanged(List<Record> records) {
                            recordAdapter.setRecords(records);
                            recordAdapter.notifyDataSetChanged();
                            activityTitle.setText(nameOfFolder);
                        }
                    });
                }
                else if (nameOfFolder.equals("Search")){
                    search_layout.setVisibility(View.VISIBLE);
                    search_bar.setText(searchString);
                    viewModel.getSearchRecords(searchString).observe(this, new Observer<List<Record>>() {
                        @Override
                        public void onChanged(List<Record> records) {
                            recordAdapter.setRecords(records);
                            recordAdapter.notifyDataSetChanged();
                            activityTitle.setText(nameOfFolder);
                        }
                    });
                }
                else {
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

                startActivityForResult(intent, ADD_RECORD_REQUEST);

            }
        });

    }


    @Override
    public void onBackPressed() {
        back(null);    }

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
        String folder = Objects.requireNonNull(getIntent().getExtras()).getString(EXTRA_FOLDER);
        String type;

        mediaPlayer.start();

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
        overridePendingTransition(0, 0);
        finish();
    }



    public void search(View view) {
        mediaPlayer.start();
        search_btn.startAnimation(animation3);
        if (search_bar.getText().toString().equals(searchString)){
            return;        }
        else if (search_bar.getText().toString().equals("")) {
            searchString = "%";        }
        else {
            searchString = search_bar.getText().toString();
        }

        Intent intent = new Intent(this, RecordRecycler_Activity.class);
        intent.putExtra(EXTRA_SEARCH,searchString);
        intent.putExtra(EXTRA_FOLDER, "Search");
        this.startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);


    }

}