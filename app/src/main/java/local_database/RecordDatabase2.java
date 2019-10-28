package local_database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.securevault19.securevault2019.record.Record;
import com.securevault19.securevault2019.user.User;

import local_database.dao.DaoRecord;

@Database(entities = {Record.class, User.class},version = 4)
public abstract class RecordDatabase2 extends RoomDatabase {

    public abstract DaoRecord daoRecord();
}
