package local_database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.securevault19.securevault2019.record.Website;

import java.util.List;

@Dao
public interface DaoWebsite {


    @Insert
    void insert(Website website);


    @Update
    void update(Website website);

    @Delete
    void delete(Website website);

    @Query("DELETE FROM  website_table")
    void deleteAllWebsiteRecords();

    @Query("SELECT * FROM website_table")
    LiveData<List<Website>> getAllWebsiteRecords();

}
