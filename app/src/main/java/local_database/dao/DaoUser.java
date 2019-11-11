package local_database.dao;


// updeted DaoUser                      // DaoUser is the correct
// UserDao may be deleted.

import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;

import com.securevault19.securevault2019.user.User;

import java.util.List;

@Dao
public interface DaoUser {

    // Insert
    @Insert
    void insert(User user);

    // check if LogIn is ok
    @Query("SELECT  * FROM user_table WHERE first_Name = :firstName AND master_Password = :masterPassword")
    User LogInConfirmation(String firstName,String masterPassword);

    // getting the userName name for check if one all ready exists.
    @Query("SELECT * from user_table WHERE first_name = :firstName")
    User CheckForUserName(String firstName);


}
