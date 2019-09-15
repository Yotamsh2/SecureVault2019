package local_database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.securevault19.securevault2019.record.Record;
import com.securevault19.securevault2019.user.User;

import local_database.dao.RecordDao;
import local_database.dao.UserDao;

@Database(entities = {Record.class, User.class},version = 1)
public abstract class RecordDatabase3 extends RoomDatabase {

    private static RecordDatabase3 instace;      // =null?

    public abstract RecordDao recordDao();
    public abstract UserDao userDao();

    public static synchronized RecordDatabase3 getInstance(Context context){
        if (instace == null){
            instace = Room.databaseBuilder(context.getApplicationContext(),RecordDatabase3.class, "RecordDatabase3.db").fallbackToDestructiveMigration().build();
            // consider to remove fallBackToMigration func
        }
        return instace;
    }
}
