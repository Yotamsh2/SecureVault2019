package LocalDataBase.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.securevault19.securevault2019.Record.CreditCard;
import com.securevault19.securevault2019.Record.Passport;
import com.securevault19.securevault2019.Record.SocialMediaApp;

import java.util.List;

public interface DaoSocialMediaApp {


    @Insert
    void insert(SocialMediaApp socialMediaApp);


    @Update
    void update(SocialMediaApp socialMediaApp);

    @Delete
    void delete(SocialMediaApp socialMediaApp);

    @Query("DELETE FROM  socialMediaApp_table")
    void deleteAllRecords();

    @Query("SELECT * FROM socialMediaApp_table ORDER BY priority DESC")
    LiveData<List<CreditCard>> getAllRecords();

}
