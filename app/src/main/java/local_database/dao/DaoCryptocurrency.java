//package local_database.dao;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import com.securevault19.securevault2019.record.Cryptocurrency;
//
//import java.util.List;
//
//@Dao
//public interface DaoCryptocurrency {
//
//    @Insert
//    void insert(Cryptocurrency  cryptocurrency);
//
//
//    @Update
//    void update(Cryptocurrency cryptocurrency);
//
//    @Delete
//    void delete(Cryptocurrency  cryptocurrency );
//
//    @Query("DELETE FROM cryptocurrency_table")
//    void deleteAllCryptocurrencyRecords();
//
//    @Query("SELECT * FROM cryptocurrency_table")
//    LiveData<List<Cryptocurrency>> getAllCryptocurrencyRecords();
//
//}
