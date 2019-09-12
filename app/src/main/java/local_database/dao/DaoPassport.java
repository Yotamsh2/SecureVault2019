package local_database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.securevault19.securevault2019.record.Passport;

import java.util.List;

@Dao
public interface DaoPassport {

    @Insert
    void insert(Passport passport);


    @Update
    void update(Passport passport);

    @Delete
    void delete(Passport passport);

    @Query("DELETE FROM  password_table")
    void deleteAllPassportRecords();

    @Query("SELECT * FROM password_table")
    LiveData<List<Passport>> getAllPassportRecords();

}
