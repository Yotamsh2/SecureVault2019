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

    public void insert(Record record) {
        repository.insert(record);     }

    public void update(Record record) {
        repository.update(record);     }

    public void delete(Record record) {
        repository.delete(record);     }

    public LiveData<List<Record>> getAllRecords(String userEmail) {
        return repository.getAllRecords(userEmail);    }

    public LiveData<List<Record>> getAllFolder(String nameOfFolder,String userEmail) {
        return repository.getAllFolder(nameOfFolder,userEmail);    }

    public LiveData<List<Record>> getSearchRecords(String searchString,String userEmail) {
        return repository.getSearchRecords(searchString,userEmail);    }

    public Record getRecordDetails(int recordID) {
        return repository.getRecordDetails(recordID);    }

    public LiveData<List<Record>> getFavoritesRecords(String userEmail) {
        return repository.getFavoritesRecords(userEmail);    }

}
