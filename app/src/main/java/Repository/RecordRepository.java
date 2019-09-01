package Repository;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;

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

import java.util.List;

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
import LocalDataBase.RecordDataBase;

public class RecordRepository {

    private DaoUser daoUser;
    private LiveData<List<User>> allUserRecords;

    private DaoBankAccount daoBankAccount;
    private LiveData<List<BankAccount>> allBankAccountRecords;

    private DaoCreditCard daoCreditCard;
    private LiveData<List<CreditCard>> allCreditCardRecords;

    private DaoCryptocurrency daoCryptocurrency;
    private LiveData<List<Cryptocurrency>> allCryptocurrencyRecords;

    private DaoDrivingLicence daoDrivingLicence;
    private LiveData<List<DrivingLicence>> allDrivingLicenceRecords;

    private DaoEmail daoEmail;
    private LiveData<List<Email>> allEmailRecords;

    private DaoOnlineShoppingApp daoOnlineShoppingApp;
    private LiveData<List<OnlineShoppingApp>> allOnlineShoppingAppRecords;

    private DaoPassport daoPassport;
    private LiveData<List<Passport>> allPassportRecords;

    private DaoSocialMediaApp daoSocialMediaApp;
    private LiveData<List<SocialMediaApp>> allSocialMediaAppRecords;

    private DaoWebsite daoWebsite;
    private LiveData<List<Website>> allWebsiteRecords;


    public RecordRepository(Application application) {
        RecordDataBase dataBase = RecordDataBase.getInstance(application);      // getting the singleton database
        daoBankAccount = dataBase.daoBankAccount();
        allBankAccountRecords = daoBankAccount.getAllBankAccountRecords();

        daoCreditCard = dataBase.daoCreditCard();
        allCreditCardRecords = daoCreditCard.getAllCreditCardRecords();

        daoCryptocurrency = dataBase.daoCryptocurrency();
        allCryptocurrencyRecords = daoCryptocurrency.getAllCryptocurrencyRecords();

        daoDrivingLicence = dataBase.daoDrivingLicence();
        allDrivingLicenceRecords = daoDrivingLicence.getAllDrivingLicenceRecords();

        daoEmail = dataBase.daoEmail();
        allEmailRecords = daoEmail.getAllEmailRecords();

        daoOnlineShoppingApp = dataBase.daoOnlineShoppingApp();
        allOnlineShoppingAppRecords = daoOnlineShoppingApp.getAllOnlineShoppingAppRecords();

        daoPassport = dataBase.daoPassport();
        allPassportRecords = daoPassport.getAllPassportRecords();

        daoSocialMediaApp = dataBase.daoSocialMediaApp();
        allSocialMediaAppRecords = daoSocialMediaApp.getAllSocialMediaAppRecords();

        daoUser = dataBase.daoUser();
        allUserRecords = daoUser.getAllUserRecords();

        daoWebsite = dataBase.daoWebsite();
        allWebsiteRecords = daoWebsite.getAllWebsiteRecords();
    }

}
