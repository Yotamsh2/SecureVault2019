package local_database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.securevault19.securevault2019.record.Record;
import com.securevault19.securevault2019.user.User;

import java.util.List;

@Dao
public interface UserDao {

    // Insert
    @Insert
    void insertUser(User user);

    // Update
    @Update
    void  updateUser(User user);

    // Delete
    @Delete
    void deleteUser(User user);

    // Delete All ?? dont think will be in use, because every phone has one user  ?
    @Query( "DELETE FROM user_table")
    void deleteAllUsers();

    // Get all users. we have only one user
    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getAllUsers();

}
