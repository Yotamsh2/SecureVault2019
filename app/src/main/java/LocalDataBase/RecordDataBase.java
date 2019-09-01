package LocalDataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.securevault19.securevault2019.Record.BankAccount;
import com.securevault19.securevault2019.Record.CreditCard;
import com.securevault19.securevault2019.Record.Cryptocurrency;
import com.securevault19.securevault2019.Record.DrivingLicence;
import com.securevault19.securevault2019.Record.Email;
import com.securevault19.securevault2019.Record.OnlineShoppingApp;
import com.securevault19.securevault2019.Record.Passport;
import com.securevault19.securevault2019.Record.SocialMediaApp;
import com.securevault19.securevault2019.Record.Website;

import LocalDataBase.DAO.DaoBankAccount;
import LocalDataBase.DAO.DaoCreditCard;
import LocalDataBase.DAO.DaoCryptocurrency;
import LocalDataBase.DAO.DaoDrivingLicence;
import LocalDataBase.DAO.DaoEmail;
import LocalDataBase.DAO.DaoOnlineShoppingApp;
import LocalDataBase.DAO.DaoPassport;
import LocalDataBase.DAO.DaoSocialMediaApp;
import LocalDataBase.DAO.DaoUser;
import LocalDataBase.DAO.DaoWebsite;

@Database(entities = {BankAccount.class, CreditCard.class, Cryptocurrency.class, DrivingLicence.class, Email.class,
        OnlineShoppingApp.class, Passport.class, SocialMediaApp.class, Website.class},version = 1)
public abstract class RecordDataBase extends RoomDatabase {

    private static RecordDataBase instance;       // singleton

    public abstract DaoUser daoUser();
    public abstract DaoBankAccount daoBankAccount();
    public abstract DaoCreditCard daoCreditCard();
    public abstract DaoCryptocurrency daoCryptocurrency();
    public abstract DaoDrivingLicence daoDrivingLicence();
    public abstract DaoEmail daoEmail();
    public abstract DaoOnlineShoppingApp daoOnlineShoppingApp();
    public abstract DaoPassport daoPassport();
    public abstract DaoSocialMediaApp daoSocialMediaApp();
    public abstract DaoWebsite daoWebsite();

    public static synchronized RecordDataBase getInstance(Context context){
        if (instance == null){              // if database doesnt exists, make it as single ton
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RecordDataBase.class,"RecordDataBase")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;            // if exists just return it
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private DaoUser daoUser;
        private DaoBankAccount daoBankAccount;
        private DaoCreditCard daoCreditCard;
        private DaoCryptocurrency daoCryptocurrency;
        private DaoDrivingLicence daoDrivingLicence;
        private DaoEmail daoEmail;
        private DaoOnlineShoppingApp daoOnlineShoppingApp;
        private DaoPassport daoPassport;
        private DaoSocialMediaApp daoSocialMediaApp;
        private DaoWebsite daoWebsite;

        private PopulateDbAsyncTask(RecordDataBase dataBase) {
            daoUser = dataBase.daoUser();
            daoBankAccount = dataBase.daoBankAccount();
            daoCreditCard = dataBase.daoCreditCard();
            daoCryptocurrency = dataBase.daoCryptocurrency();
            daoDrivingLicence = dataBase.daoDrivingLicence();
            daoEmail = dataBase.daoEmail();
            daoOnlineShoppingApp = dataBase.daoOnlineShoppingApp();
            daoPassport = dataBase.daoPassport();
            daoSocialMediaApp = dataBase.daoSocialMediaApp();
            daoWebsite = dataBase.daoWebsite();

            }

            @Override
        protected Void doInBackground(Void... voids){
            // noteDao.insert(new Note("Title 1", "Description 1", 1));
            // etc.
            return null;
        }
    }


}
