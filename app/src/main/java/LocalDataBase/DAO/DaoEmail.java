package LocalDataBase.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.securevault19.securevault2019.Record.CreditCard;
import com.securevault19.securevault2019.Record.DrivingLicence;
import com.securevault19.securevault2019.Record.Email;

import java.util.List;

@Dao
public interface DaoEmail {

    @Insert
    void insert(Email email);


    @Update
    void update(Email email);

    @Delete
    void delete(Email email);

    @Query("DELETE FROM email_table")
    void deleteAllEmailRecords();

    @Query("SELECT * FROM email_table")
    LiveData<List<Email>> getAllEmailRecords();

}
