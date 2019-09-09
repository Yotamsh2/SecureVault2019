package LocalDataBase;

import android.content.Context;

import androidx.room.Room;

public class    DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    //our app database object
    private RecordDatabase2 recordDatabase2;

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        recordDatabase2 = Room.databaseBuilder(mCtx, RecordDatabase2.class, "RecordsDatabse2.db").build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public RecordDatabase2 getRecordDatabase2() {
        return recordDatabase2;
    }
}
