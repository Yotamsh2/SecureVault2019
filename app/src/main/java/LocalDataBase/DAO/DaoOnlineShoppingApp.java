package LocalDataBase.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.securevault19.securevault2019.Record.CreditCard;
import com.securevault19.securevault2019.Record.Email;
import com.securevault19.securevault2019.Record.OnlineShoppingApp;

import java.util.List;

@Dao
public interface DaoOnlineShoppingApp {
    @Insert
    void insert(OnlineShoppingApp onlineShoppingApp);


    @Update
    void update(OnlineShoppingApp onlineShoppingApp);

    @Delete
    void delete(OnlineShoppingApp onlineShoppingApp);

    @Query("DELETE FROM  onlineShoppingApp_table")
    void deleteAllOnlineShoppingAppRecords();

    @Query("SELECT * FROM onlineShoppingApp_table")
    LiveData<List<OnlineShoppingApp>> getAllOnlineShoppingAppRecords();

}
