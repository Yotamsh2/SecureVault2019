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
    @Query("SELECT * FROM record_table WHERE folder LIKE :nameOfFolder")   // '%' because we are looking for two words(and there's no duplicates with the folders names
    LiveData<List<Record>> getAllFolder(String nameOfFolder);
    @Query("SELECT * FROM record_table WHERE title LIKE :searchString")
    LiveData<List<Record>> getSearchRecords(String searchString);
    @Query("SELECT * FROM record_table WHERE favorite LIKE '1'")
    LiveData<List<Record>> getFavoritesRecords();
    @Query("SELECT * FROM record_table WHERE recordID =:recordID")
    Record getRecordDetails(int recordID);


}
