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

    @Query("SELECT * FROM record_table WHERE userTable =:userEmail")
    LiveData<List<Record>> getAllRecords(String userEmail);
    @Query("SELECT * FROM record_table WHERE folder LIKE :nameOfFolder AND userTable =:userEmail")   // '%' because we are looking for two words(and there's no duplicates with the folders names
    LiveData<List<Record>> getAllFolder(String nameOfFolder,String userEmail);
    @Query("SELECT * FROM record_table WHERE title LIKE :searchString AND userTable =:userEmail")
    LiveData<List<Record>> getSearchRecords(String searchString,String userEmail);
    @Query("SELECT * FROM record_table WHERE favorite LIKE '1' AND userTable =:userEmail")
    LiveData<List<Record>> getFavoritesRecords(String userEmail);
    // dont think we need to add the userEmail here, because its drawing according to the record position in the List. so there is no way for errors and mixing with other users.
    @Query("SELECT * FROM record_table WHERE recordID =:recordID")
    Record getRecordDetails(int recordID);


}
