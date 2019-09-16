package view_model.records;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.securevault19.securevault2019.record.Record;

import java.util.List;

import repository.RecordRepository;

public class Record_ViewModel extends AndroidViewModel {
    private RecordRepository repository;
    private LiveData<List<Record>> allRecords;

    public Record_ViewModel(@NonNull Application application) {
        super(application);
        repository = new RecordRepository(application);
        allRecords = repository.getAllRecords();

    }
    //public void insert(Record record){
    //     Log.d("insert(view_model)", "called." );
    //     repository.insert(record);
    //}

    public LiveData<List<Record>> getAllRecords() {
        return allRecords;
    }
}
