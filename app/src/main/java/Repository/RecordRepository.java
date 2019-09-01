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
import com.securevault19.securevault2019.Record.Record;
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
import LocalDataBase.NoteDataBase;
import LocalDataBase.RecordDataBase;

public class RecordRepository {

    private DaoUser daoUser;
    private LiveData<List<User>> allUserNotes;
    private DaoBankAccount daoBankAccount;
    private LiveData<List<BankAccount>> allBankAccountNotes;
    private DaoCreditCard daoCreditCard;
    private LiveData<List<CreditCard>> allCreditCardNotes;
    private DaoCryptocurrency daoCryptocurrency;
    private LiveData<List<Cryptocurrency>> allCryptocurrencyNotes;
    private DaoDrivingLicence daoDrivingLicence;
    private LiveData<List<DrivingLicence>> allDrivingLicenceNotes;
    private DaoEmail daoEmail;
    private LiveData<List<Email>> allEmailNotes;
    private DaoOnlineShoppingApp daoOnlineShoppingApp;
    private LiveData<List<OnlineShoppingApp>> allOnlineShoppingAppNotes;
    private DaoPassport daoPassport;
    private LiveData<List<Passport>> allPassportNotes;
    private DaoSocialMediaApp daoSocialMediaApp;
    private LiveData<List<SocialMediaApp>> allSocialMediaAppNotes;
    private DaoWebsite daoWebsite;
    private LiveData<List<Website>> allWebsiteNotes;

    public RecordRepository(Application application) {
        RecordDataBase dataBase = RecordDataBase.getInstance(application);      // getting the single ton database
        daoBankAccount = dataBase.daoBankAccount();
        allBankAccountNotes = daoBankAccount.getAllBankAccountRecords();
        daoCreditCard = dataBase.daoCreditCard();
        allCreditCardNotes = daoCreditCard.getAllCreditCardRecords();
        daoCryptocurrency = dataBase.daoCryptocurrency();
        allCryptocurrencyNotes = daoCryptocurrency.getAllCryptocurrencyRecords();
        daoDrivingLicence = dataBase.daoDrivingLicence();
        allDrivingLicenceNotes = daoDrivingLicence.getAllDrivingLicenceRecords();
        daoEmail = dataBase.daoEmail();
        allEmailNotes = daoEmail.getAllEmailRecords();
        daoOnlineShoppingApp = dataBase.daoOnlineShoppingApp();
        allOnlineShoppingAppNotes = daoOnlineShoppingApp.getAllOnlineShoppingAppRecords();
        daoPassport = dataBase.daoPassport();
        allPassportNotes = daoPassport.getAllPassportRecords();
        daoSocialMediaApp = dataBase.daoSocialMediaApp();
        allSocialMediaAppNotes = daoSocialMediaApp.getAllSocialMediaAppRecords();
        daoUser = dataBase.daoUser();
        allUserNotes = daoUser.getAllUserRecords();
        daoWebsite = dataBase.daoWebsite();
        allWebsiteNotes = daoWebsite.getAllWebsiteRecords();

    }

}
