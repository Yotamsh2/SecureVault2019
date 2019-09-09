package LocalDataBase;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.securevault19.securevault2019.Record.BankAccount;
import com.securevault19.securevault2019.Record.CreditCard;
import com.securevault19.securevault2019.Record.Cryptocurrency;
import com.securevault19.securevault2019.Record.DrivingLicence;
import com.securevault19.securevault2019.Record.Email;
import com.securevault19.securevault2019.Record.OnlineShoppingApp;
import com.securevault19.securevault2019.Record.Passport;
import com.securevault19.securevault2019.Record.SocialMediaApp;
import com.securevault19.securevault2019.Record.Website;
import com.securevault19.securevault2019.User.User;

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

@Database(entities = {User.class, BankAccount.class, CreditCard.class, Cryptocurrency.class, DrivingLicence.class, Email.class,
        OnlineShoppingApp.class, Passport.class, SocialMediaApp.class, Website.class},version = 1)
public abstract class RecordDatabase2 extends RoomDatabase {

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

}
