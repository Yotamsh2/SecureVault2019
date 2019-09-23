//package local_database.dao;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import com.securevault19.securevault2019.record.Record;
//import com.securevault19.securevault2019.user.User;
//
//import java.util.List;
//
//@Dao
//public interface RecordDao {
//
//    // Insert
//
//    @Insert
//    void insertBankAccount(Record bankAccount);
//    @Insert
//    void insertCreditCard(Record creditCard);
//    @Insert
//    void insertCryptoCurrency(Record cryptoCurrency);
//    @Insert
//    void insertDrivingLicence (Record drivingLicence);
//    @Insert
//    void insertEmail(Record email);
//    @Insert
//    void insertOnLineShoppingApp(Record onLineShoppingApp);
//    @Insert
//    void insertPassPort(Record passPort);
//    @Insert
//    void insertSocialMedia(Record socialMedia);
//    @Insert
//    void insertWebsite(Record website);
//    // --- ?
//    //@Insert
//    //void insertUser(User user);
//
//
//    // Update
//
//    @Update
//    void updateBankAccount(Record bankAccount);
//    @Update
//    void updateCreditCard(Record creditCard);
//    @Update
//    void updateCryptoCurrency(Record cryptoCurrency);
//    @Update
//    void updateDrivingLicence(Record drivingLicence);
//    @Update
//    void updateEmail(Record email);
//    @Update
//    void updateOnLineShoppingApp(Record onLineShoppingApp);
//    @Update
//    void updatePassPort(Record passPort);
//    @Update
//    void updateSocialMedia(Record socialMedia);
//    @Update
//    void updateWebsite(Record website);
//    // --- ?
//    //@Update
//    //void updateUser(User user);
//
//    //Delete Record
//
//    @Delete
//    void deleteBankAccount(Record bankAccount);
//    @Delete
//    void deleteCreditCard(Record creditCard);
//    @Delete
//    void deleteCryptoCurrency(Record cryptoCurrency);
//    @Delete
//    void deleteDrivingLicence (Record drivingLicence);
//    @Delete
//    void deleteEmail(Record email);
//    @Delete
//    void deleteOnLineShoppingApp(Record onLineShoppingApp);
//    @Delete
//    void deletePassPort(Record passPort);
//    @Delete
//    void deleteSocialMedia(Record socialMedia);
//    @Delete
//    void deleteWebsite(Record website);
//    // --- ?
//    //@Delete
//    //void deleteUser(User user);
//
//
//    // Delete all Record with the same category
//
//    @Query("DELETE FROM record_table WHERE category LIKE :bankAccount")         // deleting all colums where category \ title = bankAccount => String
//    void deleteAllBankAccounts(String bankAccount);
//    @Query("DELETE FROM record_table WHERE category LIKE :creditCard")         // deleting all colums where category \ title = crediCard => String
//    void deleteAllCreditCard(String creditCard);
//    @Query("DELETE FROM record_table WHERE category LIKE :cryptoCurrentcy")         // deleting all colums where category \ title = CryptoCurrentcy => String
//    void deleteAllCryptoCurrency(String cryptoCurrentcy);
//    @Query("DELETE FROM record_table WHERE category LIKE :drivingLicence")         // deleting all colums where category \ title = DrivingLicence => String
//    void deleteAllDrivingLicence(String drivingLicence);
//    @Query("DELETE FROM record_table WHERE category LIKE :email")         // deleting all colums where category \ title = crediCard => String
//    void deleteAllEmail(String email);
//    @Query("DELETE FROM record_table WHERE category LIKE :onLineShoppingApp")         // deleting all colums where category \ title = crediCard => String
//    void deleteAllOnLineShoppingApp(String onLineShoppingApp);
//    @Query("DELETE FROM record_table WHERE category LIKE :passPort")         // deleting all colums where category \ title = crediCard => String
//    void deleteAllPassPort(String passPort);
//    @Query("DELETE FROM record_table WHERE category LIKE :socialMedia")         // deleting all colums where category \ title = crediCard => String
//    void deleteAllSocialMedia(String socialMedia);
//    @Query("DELETE FROM record_table WHERE category LIKE :website")         // deleting all colums where category \ title = crediCard => String
//    void deleteAllWebsite(String website);
//    // --- ?
//    //@Query("DELETE FROM record_table WHERE category LIKE :user")         // deleting all colums where category \ title = crediCard => String
//    //void deleteAllUser(String user);
//
//
//    // Get all Records with the same Category
//
//    @Query("SELECT * FROM record_table WHERE category LIKE :bankAccount")   // can change to SELECT title and category only because this is the main use of in in the recycled view
//    LiveData<List<Record>> getAllBankAccounts(String bankAccount);
//    @Query("SELECT * FROM record_table WHERE category LIKE :creditCard")   // can change to SELECT title and category only because this is the main use of in in the recycled view
//    LiveData<List<Record>> getAllCreditCard(String creditCard);
//    @Query("SELECT * FROM record_table WHERE category LIKE :cryptoCurrency")   // can change to SELECT title and category only because this is the main use of in in the recycled view
//    LiveData<List<Record>> getAllCryptoCurrencys(String cryptoCurrency);
//    @Query("SELECT * FROM record_table WHERE category LIKE :drivingLicence")   // can change to SELECT title and category only because this is the main use of in in the recycled view
//    LiveData<List<Record>> getAllDrivingLicence(String drivingLicence);
//    @Query("SELECT * FROM record_table WHERE category LIKE :email")   // can change to SELECT title and category only because this is the main use of in in the recycled view
//    LiveData<List<Record>> getAllEmail(String email);
//    @Query("SELECT * FROM record_table WHERE category LIKE :onLineShoppingApp")   // can change to SELECT title and category only because this is the main use of in in the recycled view
//    LiveData<List<Record>> getAllOnLineShoppingApp(String onLineShoppingApp);
//    @Query("SELECT * FROM record_table WHERE category LIKE :passPort")   // can change to SELECT title and category only because this is the main use of in in the recycled view
//    LiveData<List<Record>> getAllPassPort(String passPort);
//    @Query("SELECT * FROM record_table WHERE category LIKE :socialMedia")   // can change to SELECT title and category only because this is the main use of in in the recycled view
//    LiveData<List<Record>> getAllSocialMedia(String socialMedia);
//    @Query("SELECT * FROM record_table WHERE category LIKE :website")   // can change to SELECT title and category only because this is the main use of in in the recycled view
//    LiveData<List<Record>> getAllWebsite(String website);
//    // --- ?
//   // @Query("SELECT * FROM record_table WHERE category LIKE :user")   // can change to SELECT title and category only because this is the main use of in in the recycled view
//    //LiveData<List<Record>> getAllUser(String user);
//
//
//
//
//
//
//
//
//
//
//
//}
