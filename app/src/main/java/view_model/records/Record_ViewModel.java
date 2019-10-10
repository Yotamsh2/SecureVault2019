package view_model.records;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.securevault19.securevault2019.record.Record;

import java.util.List;

import repository.RecordRepository;

public class Record_ViewModel extends AndroidViewModel {
    private RecordRepository repository;

    public Record_ViewModel(@NonNull Application application) {
        super(application);
        repository = new RecordRepository(application);
    }

    public LiveData<List<Record>> getAllRecords() {
        return repository.getAllRecords();    }

    public LiveData<List<Record>> getAllFolder(String nameOfFolder) {
        return repository.getAllFolder(nameOfFolder);    }

    public LiveData<List<Record>> getSearchRecords(String searchString) {
        return repository.getSearchRecords(searchString);    }

    public LiveData<List<Record>> getFavoritesRecords() {
        return repository.getFavoritesRecords();    }

}
