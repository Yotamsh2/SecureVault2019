//package local_database;
//
//import androidx.room.Database;
//import androidx.room.RoomDatabase;
//
//import com.securevault19.securevault2019.record.BankAccount;
//import com.securevault19.securevault2019.record.CreditCard;
//import com.securevault19.securevault2019.record.Cryptocurrency;
//import com.securevault19.securevault2019.record.DrivingLicence;
//import com.securevault19.securevault2019.record.Email;
//import com.securevault19.securevault2019.record.OnlineShoppingApp;
//import com.securevault19.securevault2019.record.Passport;
//import com.securevault19.securevault2019.record.SocialMediaApp;
//import com.securevault19.securevault2019.record.Website;
//import com.securevault19.securevault2019.user.User;
//
//import local_database.dao.DaoBankAccount;
//import local_database.dao.DaoCreditCard;
//import local_database.dao.DaoCryptocurrency;
//import local_database.dao.DaoDrivingLicence;
//import local_database.dao.DaoEmail;
//import local_database.dao.DaoOnlineShoppingApp;
//import local_database.dao.DaoPassport;
//import local_database.dao.DaoSocialMediaApp;
//import local_database.dao.DaoUser;
//import local_database.dao.DaoWebsite;
//
//@Database(entities = {User.class, BankAccount.class, CreditCard.class, Cryptocurrency.class, DrivingLicence.class, Email.class,
//        OnlineShoppingApp.class, Passport.class, SocialMediaApp.class, Website.class},version = 1)
//public abstract class RecordDatabase2 extends RoomDatabase {
//
//    public abstract DaoUser daoUser();
//    public abstract DaoBankAccount daoBankAccount();
//    public abstract DaoCreditCard daoCreditCard();
//    public abstract DaoCryptocurrency daoCryptocurrency();
//    public abstract DaoDrivingLicence daoDrivingLicence();
//    public abstract DaoEmail daoEmail();
//    public abstract DaoOnlineShoppingApp daoOnlineShoppingApp();
//    public abstract DaoPassport daoPassport();
//    public abstract DaoSocialMediaApp daoSocialMediaApp();
//    public abstract DaoWebsite daoWebsite();
//
//}
