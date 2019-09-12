package repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.securevault19.securevault2019.record.BankAccount;
import com.securevault19.securevault2019.record.CreditCard;
import com.securevault19.securevault2019.record.Cryptocurrency;
import com.securevault19.securevault2019.record.DrivingLicence;
import com.securevault19.securevault2019.record.Email;
import com.securevault19.securevault2019.record.OnlineShoppingApp;
import com.securevault19.securevault2019.record.Passport;
import com.securevault19.securevault2019.record.SocialMediaApp;
import com.securevault19.securevault2019.record.Website;
import com.securevault19.securevault2019.user.User;

import java.util.List;

import local_database.dao.DaoBankAccount;
import local_database.dao.DaoCreditCard;
import local_database.dao.DaoCryptocurrency;
import local_database.dao.DaoDrivingLicence;
import local_database.dao.DaoEmail;
import local_database.dao.DaoOnlineShoppingApp;
import local_database.dao.DaoPassport;
import local_database.dao.DaoSocialMediaApp;
import local_database.dao.DaoUser;
import local_database.dao.DaoWebsite;
import local_database.DatabaseClient;
import local_database.RecordDatabase2;

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
        RecordDatabase2 database2 = DatabaseClient.getInstance(application).getRecordDatabase2();      // getting the single ton database

        daoBankAccount = database2.daoBankAccount();
        allBankAccountRecords = daoBankAccount.getAllBankAccountRecords();

        daoCreditCard = database2.daoCreditCard();
        allCreditCardRecords = daoCreditCard.getAllCreditCardRecords();

        daoCryptocurrency = database2.daoCryptocurrency();
        allCryptocurrencyRecords = daoCryptocurrency.getAllCryptocurrencyRecords();

        daoDrivingLicence = database2.daoDrivingLicence();
        allDrivingLicenceRecords = daoDrivingLicence.getAllDrivingLicenceRecords();

        daoEmail = database2.daoEmail();
        allEmailRecords = daoEmail.getAllEmailRecords();

        daoOnlineShoppingApp = database2.daoOnlineShoppingApp();
        allOnlineShoppingAppRecords = daoOnlineShoppingApp.getAllOnlineShoppingAppRecords();

        daoPassport = database2.daoPassport();
        allPassportRecords = daoPassport.getAllPassportRecords();

        daoSocialMediaApp = database2.daoSocialMediaApp();
        allSocialMediaAppRecords = daoSocialMediaApp.getAllSocialMediaAppRecords();

        daoUser = database2.daoUser();
        allUserRecords = daoUser.getAllUserRecords();

        daoWebsite = database2.daoWebsite();
        allWebsiteRecords = daoWebsite.getAllWebsiteRecords();

    }

//    public void insert(Website website){
//        new InsertWebsiteAsyncTask(daoWebsite).execute(website);
//    }

    public LiveData<List<User>> getAllUserRecords() {
        return allUserRecords;
    }

public void show(Website website) {
        new ShowWebsiteRecordAsyncTask(daoWebsite).execute(website);
}
    public LiveData<List<Website>> getAllWebsiteRecords() {
        return allWebsiteRecords;
    }

    public LiveData<List<BankAccount>> getAllBankAccountRecordsRecords() {
        return allBankAccountRecords;
    }

    public LiveData<List<CreditCard>> getAllCreditCardRecords() {
        return allCreditCardRecords;
    }

    public LiveData<List<Cryptocurrency>> getAllCryptocurrencyRecords() {
        return allCryptocurrencyRecords;
    }

    public LiveData<List<DrivingLicence>> getAllDrivingLicenceRecords() {
        return allDrivingLicenceRecords;
    }

    public LiveData<List<Email>> getAllEmailRecords() {
        return allEmailRecords;
    }

    public LiveData<List<OnlineShoppingApp>> getAllOnlineShoppingAppRecords() {
        return allOnlineShoppingAppRecords;
    }

    public LiveData<List<SocialMediaApp>> getAllSocialMediaAppRecords() {
        return allSocialMediaAppRecords;
    }

    public LiveData<List<Passport>> getAllPassportRecords() {
        return allPassportRecords;
    }

    private static class ShowWebsiteRecordAsyncTask extends AsyncTask<Website, Void, Void> {
        private DaoWebsite daoWebsite;

        private ShowWebsiteRecordAsyncTask(DaoWebsite daoWebsite) {
            this.daoWebsite = daoWebsite;
        }

        @Override
        protected Void doInBackground(Website... websites) {
            daoWebsite.delete(websites[0]);
            return null;
        }

    }



//    private static class InsertWebsiteAsyncTask extends AsyncTask<Website, Void, Void> {
//        private DaoWebsite daoWebsite;
//
//        private InsertWebsiteAsyncTask(DaoWebsite daoWebsite){
//            this.daoWebsite = daoWebsite;
//        } //Constructor because we cannot access the dao of the repository direcly.
//          // (because here it's a class by itself)
//
//        @Override
//        protected Void doInBackground(Website... websites) {
//            daoWebsite.insert(websites[0]);
//            return null;
//        }
}




