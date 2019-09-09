package ViewModel.Records;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.securevault19.securevault2019.Record.Record;
import com.securevault19.securevault2019.Record.Website;

import java.util.List;

import Repository.RecordRepository;

public class Record_ViewModel extends AndroidViewModel {
    private RecordRepository repository;
    private LiveData<List<Website>> allWebsiteRecords;
   // private Website website;


    public Record_ViewModel(@NonNull Application application) {
        super(application);
        repository = new RecordRepository(application);
        allWebsiteRecords = repository.getAllWebsiteRecords();

    }
    public void insert(Website website){
        Log.d("insert(ViewModel)", "called." );
        repository.insert(website);
    }

    public LiveData<List<Website>> getAllWebsiteRecords()    {
        return allWebsiteRecords;
    }
}
