package LocalDataBase.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.securevault19.securevault2019.Record.BankAccount;
import com.securevault19.securevault2019.User.User;

import java.util.List;

public interface DaoUser {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user_table")
    void deleteAllUserRecords();

    @Query("SELECT * FROM user_table ORDER BY priority DESC")
    LiveData<List<User>> getAllUserRecords();

}
