package local_database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.securevault19.securevault2019.record.Record;

import java.util.List;

@Dao
public interface DaoRecord {

    @Insert
    void insert(Record record);

    @Update
    void update(Record record);

    @Delete
    void delete(Record record);

    @Query("DELETE FROM record_table")
    void deleteAllRecords();

    @Query("SELECT * FROM record_table")
    LiveData<List<Record>> getAllRecords();
    @Query("SELECT * FROM record_table WHERE type LIKE 'Bank%'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllBankAccounts();
    @Query("SELECT * FROM record_table WHERE type LIKE 'Credit%'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllCreditCards();
    @Query("SELECT * FROM record_table WHERE type LIKE 'Social Media'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllSocialMedia();
    @Query("SELECT * FROM record_table WHERE type LIKE 'Website%'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllWebAccounts();
    @Query("SELECT * FROM record_table WHERE type LIKE 'OnLine%'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllOnlineShopping();
    @Query("SELECT * FROM record_table WHERE type LIKE 'Crypto%'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllCryptocurrency();
    @Query("SELECT * FROM record_table WHERE type LIKE 'Driving%'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllDrivingLicence();
    @Query("SELECT * FROM record_table WHERE type LIKE 'Passport'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllPassports();
    @Query("SELECT * FROM record_table WHERE type LIKE 'Customized'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllCustomized();
    @Query("SELECT * FROM record_table WHERE type LIKE 'Note'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllNotes();

    @Query("SELECT * FROM record_table WHERE type LIKE '%email%'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllEmail();

}
