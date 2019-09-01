package LocalDataBase.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.securevault19.securevault2019.Record.CreditCard;
import com.securevault19.securevault2019.Record.Cryptocurrency;
import com.securevault19.securevault2019.Record.DrivingLicence;

import java.util.List;

@Dao
public interface DaoDrivingLicence {

    @Insert
    void insert(DrivingLicence drivingLicence);


    @Update
    void update(DrivingLicence drivingLicence);

    @Delete
    void delete(DrivingLicence drivingLicence);

    @Query("DELETE FROM drivingLicence_table")
    void deleteAllDrivingLicenceRecords();

    @Query("SELECT * FROM drivingLicence_table")
    LiveData<List<DrivingLicence>> getAllDrivingLicenceRecords();


}
