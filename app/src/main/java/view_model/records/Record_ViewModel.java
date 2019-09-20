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


    public Record_ViewModel(@NonNull Application application) {
        super(application);
        repository = new RecordRepository(application);
        allRecords = repository.getAllRecords();
        allBankAccounts = repository.getAllBankAccounts();
        allCreditCards = repository.getAllCreditCards();
        allSocialMedia = repository.getAllSocialMedia();
        allWebAccounts = repository.getAllWebAccounts();
        allOnlineShopping = repository.getAllOnlineShopping();
        allCryptocurrency = repository.getAllCryptocurrency();
        allDrivingLicence = repository.getAllDrivingLicence();
        allPassports = repository.getAllPassports();
        allCustomized = repository.getAllCustomized();
        allNotes = repository.getAllNotes();


    }


    public LiveData<List<Record>> getAllRecords() {
        return allRecords;
    }
    public LiveData<List<Record>> getAllBankAccounts() {
        return allBankAccounts;
    }

    public LiveData<List<Record>> getAllCreditCards() {
        return allCreditCards;
    }

    public LiveData<List<Record>> getAllWebAccounts() {
        return allWebAccounts;
    }

    public LiveData<List<Record>> getAllOnlineShopping() {
        return allOnlineShopping;
    }

    public LiveData<List<Record>> getAllSocialMedia() {
        return allSocialMedia;
    }

    public LiveData<List<Record>> getAllCryptocurrency() {
        return allCryptocurrency;
    }

    public LiveData<List<Record>> getAllDrivingLicence() {
        return allDrivingLicence;
    }

    public LiveData<List<Record>> getAllPassports() {
        return allPassports;
    }

    public LiveData<List<Record>> getAllCustomized() {
        return allCustomized;
    }

    public LiveData<List<Record>> getAllNotes() {
        return allNotes;
    }

}
