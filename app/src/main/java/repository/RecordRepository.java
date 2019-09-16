package repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.securevault19.securevault2019.record.Record;
import com.securevault19.securevault2019.user.User;

import java.util.List;

import local_database.dao.DaoRecord;
import local_database.dao.DaoRecord;
import local_database.DatabaseClient;
import local_database.RecordDatabase2;

public class RecordRepository {

    private DaoRecord DaoRecord;
    private LiveData<List<Record>> allRecords;


    public RecordRepository(Application application) {
        RecordDatabase2 database2 = DatabaseClient.getInstance(application).getRecordDatabase2();      // getting the single ton database

        DaoRecord = database2.daoRecord();
        allRecords = DaoRecord.getAllRecords();

    }

    public void insert(Record record){
        new InsertRecordAsyncTask(DaoRecord).execute(record);
    }

    public LiveData<List<Record>> getAllRecords() {
        return allRecords;
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




