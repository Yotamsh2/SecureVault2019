package LocalDataBase.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.securevault19.securevault2019.Record.CreditCard;
import com.securevault19.securevault2019.Record.Email;
import com.securevault19.securevault2019.Record.OnlineShoppingApp;

import java.util.List;

public interface DaoOnlineShoppingApp {
    @Insert
    void insert(OnlineShoppingApp onlineShoppingApp);


    @Update
    void update(OnlineShoppingApp onlineShoppingApp);

    @Delete
    void delete(OnlineShoppingApp onlineShoppingApp);

    @Query("DELETE FROM  onlineShoppingApp_table")
    void deleteAllRecords();

    @Query("SELECT * FROM onlineShoppingApp_table ORDER BY priority DESC")
    LiveData<List<CreditCard>> getAllRecords();

}
