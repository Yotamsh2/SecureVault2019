package repository;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.securevault19.securevault2019.record.Record;
import com.securevault19.securevault2019.user.User;

import java.util.List;

import local_database.dao.DaoRecord;

import local_database.DatabaseClient;
import local_database.RecordDatabase2;
import view.records.AddNewRecord_Activity;
import view.records.RecordRecycler_Activity;

public class RecordRepository {

    private DaoRecord DaoRecord;
    public RecordRepository(Application application) {
        RecordDatabase2 database2 = DatabaseClient.getInstance(application).getRecordDatabase2();      // getting the singleton database
        DaoRecord = database2.daoRecord();
    }


    public void insert(Record record) {
        new InsertRecordAsyncTask(DaoRecord).execute(record);    }

    public void update(Record record) {
        new UpdateRecordAsyncTask(DaoRecord).execute(record);    }

    public void delete(Record record) {
        new DeleteRecordAsyncTask(DaoRecord).execute(record);    }

    public LiveData<List<Record>> getAllRecords(String userEmail) {
        return DaoRecord.getAllRecords(userEmail);    }

    public LiveData<List<Record>> getAllFolder(String nameOfFolder,String userEmail) {
        return DaoRecord.getAllFolder(nameOfFolder,userEmail);    }

    public LiveData<List<Record>> getSearchRecords(String searchString,String userEmail) {
        return DaoRecord.getSearchRecords(searchString,userEmail);    }

    public Record getRecordDetails(int recordID) {
        return DaoRecord.getRecordDetails(recordID);    }

    public LiveData<List<Record>> getFavoritesRecords(String userEmail) {
        return DaoRecord.getFavoritesRecords(userEmail);    }



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

    private static class UpdateRecordAsyncTask extends AsyncTask<Record, Void, Void> {
        private DaoRecord daoRecord;


        private UpdateRecordAsyncTask(DaoRecord daoRecord) {
            this.daoRecord = daoRecord;
        } //Constructor because we cannot access the dao of the repository direcly.
        // (because here it's a class by itself)

        @Override
        protected Void doInBackground(Record... records) {
            daoRecord.update(records[0]);
            return null;
        }
    }

    private static class DeleteRecordAsyncTask extends AsyncTask<Record, Void, Void> {
        private DaoRecord daoRecord;


        private DeleteRecordAsyncTask(DaoRecord daoRecord) {
            this.daoRecord = daoRecord;
        } //Constructor because we cannot access the dao of the repository direcly.
        // (because here it's a class by itself)

        @Override
        protected Void doInBackground(Record... records) {
            daoRecord.delete(records[0]);
            return null;
        }
    }
}




