package repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.securevault19.securevault2019.record.Record;
import com.securevault19.securevault2019.user.User;

import java.util.List;

import local_database.dao.DaoRecord;

import local_database.DatabaseClient;
import local_database.RecordDatabase2;

public class RecordRepository {

    private DaoRecord DaoRecord;
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


    public RecordRepository(Application application) {
        RecordDatabase2 database2 = DatabaseClient.getInstance(application).getRecordDatabase2();      // getting the single ton database

        DaoRecord = database2.daoRecord();
        allRecords = DaoRecord.getAllRecords();
        allBankAccounts = DaoRecord.getAllBankAccounts(); //not BankAccounts because we look where the dao searches category name
        allCreditCards = DaoRecord.getAllCreditCards();
        allSocialMedia = DaoRecord.getAllSocialMedia();
        allWebAccounts = DaoRecord.getAllWebAccounts();
        allOnlineShopping = DaoRecord.getAllOnlineShopping();
        allCryptocurrency = DaoRecord.getAllCryptocurrency();
        allDrivingLicence = DaoRecord.getAllDrivingLicence();
        allPassports = DaoRecord.getAllPassports();
        allCustomized = DaoRecord.getAllCustomized();
        allNotes = DaoRecord.getAllNotes();


    }

    public void insert(Record record) {
        new InsertRecordAsyncTask(DaoRecord).execute(record);
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


    public void show(Record record) {
        new ShowRecordAsyncTask(DaoRecord).execute(record);
    }

    private static class ShowRecordAsyncTask extends AsyncTask<Record, Void, Void> {
        private DaoRecord DaoRecord;

        private ShowRecordAsyncTask(DaoRecord daoRecord) {
            this.DaoRecord = DaoRecord;
        }

        @Override
        protected Void doInBackground(Record... records) {
            DaoRecord.delete(records[0]);
            return null;
        }

    }

    private static class InsertRecordAsyncTask extends AsyncTask<Record, Void, Void> {
        private DaoRecord daoRecord;


        private InsertRecordAsyncTask(DaoRecord daoRecord) {
            this.daoRecord = daoRecord;
        } //Constructor because we cannot access the dao of the repository direcly.
        // (because here it's a class by itself)

        @Override
        protected Void doInBackground(Record... records) {
            daoRecord.insert(records[0]);
            return null;
        }
    }
}




