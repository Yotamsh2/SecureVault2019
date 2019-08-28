package LocalDataBase.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.securevault19.securevault2019.Record.CreditCard;
import com.securevault19.securevault2019.Record.SocialMediaApp;
import com.securevault19.securevault2019.Record.Website;

import java.util.List;

public interface DaoWebsite {



    @Insert
    void insert(Website website);


    @Update
    void update(Website website);

    @Delete
    void delete(Website website);

    @Query("DELETE FROM  website_table")
    void deleteAllWebsiteRecords();

    @Query("SELECT * FROM website_table ORDER BY priority DESC")
    LiveData<List<CreditCard>> getAllWebsiteRecords();

}
