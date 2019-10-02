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
    private LiveData<List<Record>> allRecords;
    private LiveData<List<Record>> allBankAccounts;
    private LiveData<List<Record>> allCreditCards;
    private LiveData<List<Record>> allSocialMedia;
    private LiveData<List<Record>> allWebAccounts;
    private LiveData<List<Record>> allOnlineShopping;
    private LiveData<List<Record>> allCryptocurrency;
    private LiveData<List<Record>> allDrivingLicence;
    private LiveData<List<Record>> allPassports;
    private LiveData<List<Record>> allCustomized;
    private LiveData<List<Record>> allNotes;
    private String nameOfFolder;

    public Record_ViewModel(@NonNull Application application) {
        super(application);
        repository = new RecordRepository(application);
    }

    public LiveData<List<Record>> getAllRecords() {
        return repository.getAllRecords();
    }

    public LiveData<List<Record>> getAllFolder(String nameOfFolder) {
        return repository.getAllFolder(nameOfFolder);
    }
}
