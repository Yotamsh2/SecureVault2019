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
    @Query("SELECT * FROM record_table WHERE folder LIKE 'Bank%'")   // '%' because we are looking for two words(and there's no duplicates with the folders names
    LiveData<List<Record>> getAllBankAccounts();
    @Query("SELECT * FROM record_table WHERE folder LIKE 'Credit%'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllCreditCards();
    @Query("SELECT * FROM record_table WHERE folder LIKE 'Social Media'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllSocialMedia();
    @Query("SELECT * FROM record_table WHERE folder LIKE 'Website%'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllWebAccounts();
    @Query("SELECT * FROM record_table WHERE folder LIKE 'OnLine%'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllOnlineShopping();
    @Query("SELECT * FROM record_table WHERE folder LIKE 'Crypto%'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllCryptocurrency();
    @Query("SELECT * FROM record_table WHERE folder LIKE 'Driving%'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllDrivingLicence();
    @Query("SELECT * FROM record_table WHERE folder LIKE 'Passport'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllPassports();
    @Query("SELECT * FROM record_table WHERE folder LIKE 'Customized'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllCustomized();
    @Query("SELECT * FROM record_table WHERE folder LIKE 'Note'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllNotes();

    @Query("SELECT * FROM record_table WHERE folder  LIKE '%email%'")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllEmail();

}
