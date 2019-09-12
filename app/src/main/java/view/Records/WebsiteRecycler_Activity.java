package view.Records;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.Record.Website;
import com.securevault19.securevault2019.Record.WebsiteAdapter;

import java.util.ArrayList;
import java.util.List;

import ViewModel.Records.Record_ViewModel;

public class WebsiteRecycler_Activity extends AppCompatActivity implements WebsiteAdapter.OnWebsiteListener {
public static final String EXTRA_TEXT = "com.securevault19.securevault2019.EXTRA_TEXT";


    private Record_ViewModel viewModel;
    private List<Website> websites = new ArrayList<>();
    private ItemTouchHelper.Callback itemTouchHelperCallback;


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
                websiteAdapter.notifyDataSetChanged();

            }
        });


    }

    @Override
    public void onWebsiteClick(int position) {
        Log.d("onwebsiteclick", "clicked. " + position);
//        websites.get(position);
        String text = "text";
        Intent intent = new Intent(this, ShowWebsite_Activity.class);
        intent.putExtra(EXTRA_TEXT, text);
        startActivity(intent);
    }
}