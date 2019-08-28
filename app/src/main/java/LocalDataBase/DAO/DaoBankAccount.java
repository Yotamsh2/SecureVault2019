package LocalDataBase.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.securevault19.securevault2019.Record.BankAccount;

import java.util.List;

@Dao
public interface DaoBankAccount {

    @Insert
    void insert(BankAccount bankAccount);

    @Update
    void update(BankAccount bankAccount);

    @Delete
    void delete(BankAccount bankAccount);

    @Query("DELETE FROM bankAccount_table")
    void deleteAllRecords();

    @Query("SELECT * FROM bankAccount_table ORDER BY priority DESC")
    LiveData<List<BankAccount>> getAllRecords();


}
