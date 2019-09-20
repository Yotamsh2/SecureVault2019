package local_database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.securevault19.securevault2019.record.Record;

import java.util.List;

@Dao
public interface DaoRecord {

    @Insert
    void insert(Record record);

    @Update
    void update(Record record);

    @Delete
    void delete(Record record);

    @Query("DELETE FROM record_table")
    void deleteAllRecords();

    @Query("SELECT * FROM record_table")
    LiveData<List<Record>> getAllRecords();
    @Query("SELECT * FROM record_table WHERE category LIKE :bankAccount")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllBankAccounts(String bankAccount);
    @Query("SELECT * FROM record_table WHERE category LIKE :CreditCard")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllCreditCards(String CreditCard);
    @Query("SELECT * FROM record_table WHERE category LIKE :SocialMedia")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllSocialMedia(String SocialMedia);
    @Query("SELECT * FROM record_table WHERE category LIKE :Website")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllWebAccounts(String Website);
    @Query("SELECT * FROM record_table WHERE category LIKE :OnLineShopping")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllOnlineShopping(String OnLineShopping);
    @Query("SELECT * FROM record_table WHERE category LIKE :CryptoCurrency")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllCryptocurrency(String CryptoCurrency);
    @Query("SELECT * FROM record_table WHERE category LIKE :DrivingLicence")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllDrivingLicence(String DrivingLicence);
    @Query("SELECT * FROM record_table WHERE category LIKE :Passport")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllPassports(String Passport);
    @Query("SELECT * FROM record_table WHERE category LIKE :Customized")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllCustomized(String Customized);
    @Query("SELECT * FROM record_table WHERE category LIKE :Notes")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllNotes(String Notes);

    @Query("SELECT * FROM record_table WHERE category LIKE :email")   // can change to SELECT title and category only because this is the main use of in in the recycled view
    LiveData<List<Record>> getAllEmail(String email);

}
