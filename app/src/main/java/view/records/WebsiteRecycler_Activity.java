package view.records;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.record.Website;
import com.securevault19.securevault2019.record.WebsiteAdapter;

import java.util.ArrayList;
import java.util.List;

import view.explorer.CategoryList_Activity;
import view_model.records.Record_ViewModel;
import view.records.AddNewWebsite_Activity;

public class WebsiteRecycler_Activity extends AppCompatActivity implements WebsiteAdapter.OnWebsiteListener {
    public static final int ADD_NOTE_REQUEST = 1;


    private Record_ViewModel viewModel;
    private List<Website> websites = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website_recycler);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        final WebsiteAdapter websiteAdapter = new WebsiteAdapter((ArrayList<Website>) websites, this);
        recyclerView.setAdapter(websiteAdapter);

        viewModel = ViewModelProviders.of(this).get(Record_ViewModel.class);

        viewModel.getAllWebsiteRecords().observe(this, new Observer<List<Website>>() {
            @Override
            public void onChanged(List<Website> websites) {
                websiteAdapter.setWebsites(websites);
                websiteAdapter.notifyDataSetChanged();

            }
        });

        FloatingActionButton buttonAddWebsiteRecord = findViewById(R.id.button_add_website_record);
        buttonAddWebsiteRecord.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddNewWebsite_Activity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddNewWebsite_Activity.EXTRA_TITLE);
            String userName = data.getStringExtra(AddNewWebsite_Activity.EXTRA_USERNAME);
            String password = data.getStringExtra(AddNewWebsite_Activity.EXTRA_PASSWORD);
            String email = data.getStringExtra(AddNewWebsite_Activity.EXTRA_EMAIL);
            String website = data.getStringExtra(AddNewWebsite_Activity.EXTRA_WEBSITE);
            String expiringDateDay = data.getStringExtra(AddNewWebsite_Activity.EXTRA_EXPIRING_DATE_DAY);
            String expiringDateMonth = data.getStringExtra(AddNewWebsite_Activity.EXTRA_EXPIRING_DATE_MONTH);
            String expiringDateyear = data.getStringExtra(AddNewWebsite_Activity.EXTRA_EXPIRING_DATE_YEAR);
            String other = data.getStringExtra(AddNewWebsite_Activity.EXTRA_OTHER);


        }
    }

    @Override
    public void onWebsiteClick(int position) {
        Log.d("onwebsiteclick", "clicked. " + position);
//        websites.get(position);

    }

    public void openOptions(View view) {
    }


    public void back(View view) {
        Intent intent = new Intent(this, CategoryList_Activity.class);
        this.startActivity(intent);
    }
}