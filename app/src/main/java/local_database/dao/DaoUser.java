package local_database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.securevault19.securevault2019.user.User;

import java.util.List;

@Dao
public interface DaoUser {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM user_table ORDER BY first_name DESC")
    LiveData<List<User>> getAllUsers();

    // check if LogIn is ok
    @Query("SELECT  * FROM user_table WHERE email = :email AND master_Password = :masterPassword")
    User LogInConfirmation(String email,String masterPassword);

    // getting the userName name for check if one all ready exists.
    @Query("SELECT email from user_table WHERE email = :email")
    String CheckForUserName(String email);

    // updating User Details
    @Query("UPDATE user_table SET secureLevel = :newSecureLevel WHERE email = :email")
    void updateSecureLevel(String newSecureLevel, String email);

}
