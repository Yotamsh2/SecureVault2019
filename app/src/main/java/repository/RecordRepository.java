package repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

//import com.securevault19.securevault2019.record.BankAccount;
//import com.securevault19.securevault2019.record.CreditCard;
//import com.securevault19.securevault2019.record.Cryptocurrency;
//import com.securevault19.securevault2019.record.DrivingLicence;
//import com.securevault19.securevault2019.record.Email;
//import com.securevault19.securevault2019.record.OnlineShoppingApp;
//import com.securevault19.securevault2019.record.Passport;
import com.securevault19.securevault2019.record.Record;
//import com.securevault19.securevault2019.record.SocialMediaApp;
//import com.securevault19.securevault2019.record.Website;
import com.securevault19.securevault2019.user.User;

import java.util.List;

import local_database.RecordDatabase3;
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
//import local_database.DatabaseClient;
//import local_database.RecordDatabase2;
import local_database.dao.RecordDao;
import local_database.dao.UserDao;

public class RecordRepository {

    private static String bankAccountString = "bankAccount";
    private static String crediCardString = "crediCard";
    private static String cryptocurrencyString = "cryptocurrency";
    private static String drivingLicenceString = "drivingLicence";
    private static String emailString = "email";
    private static String onLineShoppingAppString = "onLineShoppingApp";
    private static String passportString = "passport";
    private static String socialMediaString = "socialMedia";
    private static String websiteString = "website";

    private static String userString = "";


    private RecordDao recordDao;
    private LiveData<List<Record>> allBankAccounts;
    private LiveData<List<Record>> allCreditCards;
    private LiveData<List<Record>> allCryptocurrencys;
    private LiveData<List<Record>> allDrivingLicences;
    private LiveData<List<Record>> allEmails;
    private LiveData<List<Record>> allOnlineShoppingApps;
    private LiveData<List<Record>> allPassports;
    private LiveData<List<Record>> allSocialMediaApps;
    private LiveData<List<Record>> allWebsites;

    private UserDao userDao;
    private LiveData<List<User>> allUsers;


    public RecordRepository(Application application) {
        RecordDatabase3 recordDatabase3 = RecordDatabase3.getInstance(application);
        recordDao = recordDatabase3.recordDao();
        RecordDatabase3 recordDatabase3User = RecordDatabase3.getInstance(application);
        userDao = recordDatabase3User.userDao();

        allBankAccounts = recordDao.getAllBankAccounts(bankAccountString);
        allCreditCards = recordDao.getAllCreditCard(crediCardString);
        allCryptocurrencys = recordDao.getAllCryptoCurrencys(cryptocurrencyString);
        allDrivingLicences = recordDao.getAllDrivingLicence(drivingLicenceString);
        allEmails = recordDao.getAllEmail(emailString);
        allOnlineShoppingApps = recordDao.getAllOnLineShoppingApp(onLineShoppingAppString);
        allPassports = recordDao.getAllPassPort(passportString);
        allSocialMediaApps = recordDao.getAllSocialMedia(socialMediaString);
        allWebsites = recordDao.getAllWebsite(websiteString);

        allUsers = userDao.getAllUsers();


    }


    // ----------------------------------- Bank Account --------------------------------------- //

    public void insertBankAccount(Record record){
        new InsertBankAccountRecordAsyncTask(recordDao).execute(record);

    }
    public void updateBankAccount(Record record){
        new UpdateBankAccountRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteBankAccount(Record record){
        new DeleteBankAccountRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteAllBankAccount(){
        new DeleteAllBankAccountRecordAsyncTask(recordDao).execute();
    }

    public LiveData<List<Record>> getAllBankAccounts(){
        return allBankAccounts;
    }

    private static class InsertBankAccountRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private InsertBankAccountRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.insertBankAccount(records[0]);
            return null;
        }
    }

    private static class UpdateBankAccountRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private UpdateBankAccountRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.updateBankAccount(records[0]);
            return null;
        }
    }

    private static class DeleteBankAccountRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private DeleteBankAccountRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.deleteBankAccount(records[0]);
            return null;
        }
    }

    private static class DeleteAllBankAccountRecordAsyncTask extends AsyncTask<Void,Void,Void>{
        private RecordDao recordDao;

        private DeleteAllBankAccountRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            recordDao.deleteAllBankAccounts(bankAccountString);
            return null;
        }
    }
    // ------------------------------------------------------------------------------------------ //



    // ----------------------------------- Credit Card --------------------------------------- //

    public void insertCreditCard(Record record){
        new InsertCreditCardRecordAsyncTask(recordDao).execute(record);

    }
    public void updateCreditCard(Record record){
        new UpdateCreditCardRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteCreditCard(Record record){
        new DeleteCreditCardRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteAllCreditCard(){
        new DeleteAllCreditCardRecordAsyncTask(recordDao).execute();
    }

    public LiveData<List<Record>> getAllCreditCards(){
        return allCreditCards;
    }

    private static class InsertCreditCardRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private InsertCreditCardRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.insertCreditCard(records[0]);
            return null;
        }
    }

    private static class UpdateCreditCardRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private UpdateCreditCardRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.updateCreditCard(records[0]);
            return null;
        }
    }

    private static class DeleteCreditCardRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private DeleteCreditCardRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.deleteCreditCard(records[0]);
            return null;
        }
    }

    private static class DeleteAllCreditCardRecordAsyncTask extends AsyncTask<Void,Void,Void>{
        private RecordDao recordDao;

        private DeleteAllCreditCardRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            recordDao.deleteAllCreditCard(crediCardString);
            return null;
        }
    }
    // ------------------------------------------------------------------------------------------ //



    // ----------------------------------- CryptoCurrency --------------------------------------- //

    public void insertCryptoCurrency(Record record){
        new InsertCryptoCurrencyRecordAsyncTask(recordDao).execute(record);

    }
    public void updateCryptoCurrency(Record record){
        new InsertCryptoCurrencyRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteCryptoCurrency(Record record){
        new DeleteCryptoCurrencyRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteAllCryptoCurrency(){
        new DeleteAllCryptoCurrencyAsyncTask(recordDao).execute();
    }

    public LiveData<List<Record>> getAllCryptoCurrencys(){                  //? maybe some problem
        return allCryptocurrencys;
    }

    private static class InsertCryptoCurrencyRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private InsertCryptoCurrencyRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.insertCryptoCurrency(records[0]);
            return null;
        }
    }

    private static class UpdateCryptoCurrencyRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private UpdateCryptoCurrencyRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.updateCryptoCurrency(records[0]);
            return null;
        }
    }

    private static class DeleteCryptoCurrencyRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private DeleteCryptoCurrencyRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.deleteCryptoCurrency(records[0]);
            return null;
        }
    }

    private static class DeleteAllCryptoCurrencyAsyncTask extends AsyncTask<Void,Void,Void>{
        private RecordDao recordDao;

        private DeleteAllCryptoCurrencyAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            recordDao.deleteAllCryptoCurrency(cryptocurrencyString);
            return null;
        }
    }
    // ------------------------------------------------------------------------------------------ //



    // ---------------------------------- Driving Licence --------------------------------------- //

    public void insertDrivingLicence(Record record){
        new InsertDrivingLicenceRecordAsyncTask(recordDao).execute(record);

    }
    public void updateDrivingLicence(Record record){
        new UpdateDrivingLicenceRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteDrivingLicence(Record record){
        new DeleteDrivingLicenceRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteAllDrivingLicence(){
        new DeleteAllDrivingLicenceAsyncTask(recordDao).execute();
    }

    public LiveData<List<Record>> getAllDrivingLicences(){
        return allDrivingLicences;
    }

    private static class InsertDrivingLicenceRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private InsertDrivingLicenceRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.insertDrivingLicence(records[0]);
            return null;
        }
    }

    private static class UpdateDrivingLicenceRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private UpdateDrivingLicenceRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.updateDrivingLicence(records[0]);
            return null;
        }
    }

    private static class DeleteDrivingLicenceRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private DeleteDrivingLicenceRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.deleteDrivingLicence(records[0]);
            return null;
        }
    }

    private static class DeleteAllDrivingLicenceAsyncTask extends AsyncTask<Void,Void,Void>{
        private RecordDao recordDao;

        private DeleteAllDrivingLicenceAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            recordDao.deleteAllDrivingLicence(drivingLicenceString);
            return null;
        }
    }
    // ------------------------------------------------------------------------------------------ //


    // --------------------------------------- Email -------------------------------------------- //

    public void insertEmail(Record record){
        new InsertEmailRecordAsyncTask(recordDao).execute(record);

    }
    public void updateEmail(Record record){
        new UpdateEmailRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteEmail(Record record){
        new DeleteEmailRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteAllEmail(){
        new DeleteAllEmailAsyncTask(recordDao).execute();
    }

    public LiveData<List<Record>> getAllEmails(){
        return allEmails;
    }

    private static class InsertEmailRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private InsertEmailRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.insertEmail(records[0]);
            return null;
        }
    }

    private static class UpdateEmailRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private UpdateEmailRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.updateEmail(records[0]);
            return null;
        }
    }

    private static class DeleteEmailRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private DeleteEmailRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.deleteEmail(records[0]);
            return null;
        }
    }

    private static class DeleteAllEmailAsyncTask extends AsyncTask<Void,Void,Void>{
        private RecordDao recordDao;

        private DeleteAllEmailAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            recordDao.deleteAllEmail(emailString);
            return null;
        }
    }
    // ------------------------------------------------------------------------------------------ //



    // --------------------------------- OnLineShoppingApp -------------------------------------- //

    public void insertOnLineShoppingApp(Record record){
        new InsertOnLineShoppingAppRecordAsyncTask(recordDao).execute(record);

    }
    public void updateOnLineShoppingApp(Record record){
        new UpdateOnLineShoppingAppRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteOnLineShoppingApp(Record record){
        new DeleteOnLineShoppingAppRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteAllOnLineShoppingApp(){
        new DeleteAllOnLineShoppingAppAsyncTask(recordDao).execute();
    }

    public LiveData<List<Record>> getAllOnLineShoppingApp(){
        return allOnlineShoppingApps;
    }

    private static class InsertOnLineShoppingAppRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private InsertOnLineShoppingAppRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.insertOnLineShoppingApp(records[0]);
            return null;
        }
    }

    private static class UpdateOnLineShoppingAppRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private UpdateOnLineShoppingAppRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.updateOnLineShoppingApp(records[0]);
            return null;
        }
    }

    private static class DeleteOnLineShoppingAppRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private DeleteOnLineShoppingAppRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.deleteOnLineShoppingApp(records[0]);
            return null;
        }
    }

    private static class DeleteAllOnLineShoppingAppAsyncTask extends AsyncTask<Void,Void,Void>{
        private RecordDao recordDao;

        private DeleteAllOnLineShoppingAppAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            recordDao.deleteAllOnLineShoppingApp(onLineShoppingAppString);
            return null;
        }
    }
    // ------------------------------------------------------------------------------------------ //




    // -------------------------------------- PassPort ------------------------------------------ //

    public void insertPassPort(Record record){
        new InsertPassPortRecordAsyncTask(recordDao).execute(record);

    }
    public void updatePassPort(Record record){
        new UpdatePassPortRecordAsyncTask(recordDao).execute(record);
    }

    public void deletePassPort(Record record){
        new DeletePassPortRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteAllPassPort(){
        new DeleteAllPassPortAsyncTask(recordDao).execute();
    }

    public LiveData<List<Record>> getAllPassPorts(){                    //getAllPassPort???
        return allPassports;
    }

    private static class InsertPassPortRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private InsertPassPortRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.insertPassPort(records[0]);
            return null;
        }
    }

    private static class UpdatePassPortRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private UpdatePassPortRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.updatePassPort(records[0]);
            return null;
        }
    }

    private static class DeletePassPortRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private DeletePassPortRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.deletePassPort(records[0]);
            return null;
        }
    }

    private static class DeleteAllPassPortAsyncTask extends AsyncTask<Void,Void,Void>{
        private RecordDao recordDao;

        private DeleteAllPassPortAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            recordDao.deleteAllPassPort(passportString);
            return null;
        }
    }
    // ------------------------------------------------------------------------------------------ //
/**/


    // ------------------------------------ Social Media ---------------------------------------- //

    public void insertSocialMedia(Record record){
        new InsertSicoalMediaRecordAsyncTask(recordDao).execute(record);

    }
    public void updateSocialMedia(Record record){
        new UpdateSocialMediaAsyncTask(recordDao).execute(record);
    }

    public void deleteSocialMedia(Record record){
        new DeleteSocialMediaRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteAllSocialMedia(){
        new DeleteAllSocialMediaAsyncTask(recordDao).execute();
    }

    public LiveData<List<Record>> getAllSocialMediaApps(){                          // ? maybe the name is wrong
        return allSocialMediaApps;
    }

    private static class InsertSicoalMediaRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private InsertSicoalMediaRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.insertSocialMedia(records[0]);
            return null;
        }
    }

    private static class UpdateSocialMediaAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private UpdateSocialMediaAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.updateSocialMedia(records[0]);
            return null;
        }
    }

    private static class DeleteSocialMediaRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private DeleteSocialMediaRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.deleteSocialMedia(records[0]);
            return null;
        }
    }

    private static class DeleteAllSocialMediaAsyncTask extends AsyncTask<Void,Void,Void>{
        private RecordDao recordDao;

        private DeleteAllSocialMediaAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            recordDao.deleteAllSocialMedia(socialMediaString);
            return null;
        }
    }
    // ------------------------------------------------------------------------------------------ //



    // -------------------------------------- Website ------------------------------------------- //

    public void insertWebsite(Record record){
        new InsertWebsiteRecordAsyncTask(recordDao).execute(record);

    }
    public void updateWebsite(Record record){
        new UpdateWebsiteRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteWebsite(Record record){
        new DeleteWebsiteRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteAllWebsite(){
        new DeleteAllWebsiteAsyncTask(recordDao).execute();
    }

    public LiveData<List<Record>> getAllWebsite(){
        return allWebsites;
    }

    private static class InsertWebsiteRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private InsertWebsiteRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.insertWebsite(records[0]);
            return null;
        }
    }

    private static class UpdateWebsiteRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private UpdateWebsiteRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.updateWebsite(records[0]);
            return null;
        }
    }

    private static class DeleteWebsiteRecordAsyncTask extends AsyncTask<Record,Void,Void>{
        private RecordDao recordDao;

        private DeleteWebsiteRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Record... records) {
            recordDao.deleteWebsite(records[0]);
            return null;
        }
    }

    private static class DeleteAllWebsiteAsyncTask extends AsyncTask<Void,Void,Void>{
        private RecordDao recordDao;

        private DeleteAllWebsiteAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            recordDao.deleteAllWebsite(websiteString);
            return null;
        }
    }
    // ------------------------------------------------------------------------------------------ //


    // ---------------------------------------- User -------------------------------------------- //

    public void insertUser(User user){
        new InsertUserAsyncTask(userDao).execute(user);

    }
    public void updateUser(User user){
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void deleteUser(User user){
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public void deleteAllUser(){
        new DeleteAllUserAsyncTask(userDao).execute();
    }

    public LiveData<List<User>> getAllUser(){
        return allUsers;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.updateUser(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.deleteUser(users[0]);
            return null;
        }
    }

    private static class DeleteAllUserAsyncTask extends AsyncTask<Void,Void,Void>{
        private UserDao userDao;

        private DeleteAllUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }
    }
    // ------------------------------------------------------------------------------------------ //




//    private DaoUser daoUser;
//    private LiveData<List<User>> allUserRecords;
//
//    private DaoBankAccount daoBankAccount;
//    private LiveData<List<BankAccount>> allBankAccountRecords;
//
//    private DaoCreditCard daoCreditCard;
//    private LiveData<List<CreditCard>> allCreditCardRecords;
//
//    private DaoCryptocurrency daoCryptocurrency;
//    private LiveData<List<Cryptocurrency>> allCryptocurrencyRecords;
//
//    private DaoDrivingLicence daoDrivingLicence;
//    private LiveData<List<DrivingLicence>> allDrivingLicenceRecords;
//
//    private DaoEmail daoEmail;
//    private LiveData<List<Email>> allEmailRecords;
//
//    private DaoOnlineShoppingApp daoOnlineShoppingApp;
//    private LiveData<List<OnlineShoppingApp>> allOnlineShoppingAppRecords;
//
//    private DaoPassport daoPassport;
//    private LiveData<List<Passport>> allPassportRecords;
//
//    private DaoSocialMediaApp daoSocialMediaApp;
//    private LiveData<List<SocialMediaApp>> allSocialMediaAppRecords;
//
//    private DaoWebsite daoWebsite;
//    private LiveData<List<Website>> allWebsiteRecords;          // LivaData<List<Website>> return only where Strnig cateroty = website..
//
//
//    public RecordRepository(Application application) {
//        RecordDatabase2 database2 = DatabaseClient.getInstance(application).getRecordDatabase2();      // getting the single ton database
//
//        daoBankAccount = database2.daoBankAccount();
//        allBankAccountRecords = daoBankAccount.getAllBankAccountRecords();
//
//        daoCreditCard = database2.daoCreditCard();
//        allCreditCardRecords = daoCreditCard.getAllCreditCardRecords();
//
//        daoCryptocurrency = database2.daoCryptocurrency();
//        allCryptocurrencyRecords = daoCryptocurrency.getAllCryptocurrencyRecords();
//
//        daoDrivingLicence = database2.daoDrivingLicence();
//        allDrivingLicenceRecords = daoDrivingLicence.getAllDrivingLicenceRecords();
//
//        daoEmail = database2.daoEmail();
//        allEmailRecords = daoEmail.getAllEmailRecords();
//
//        daoOnlineShoppingApp = database2.daoOnlineShoppingApp();
//        allOnlineShoppingAppRecords = daoOnlineShoppingApp.getAllOnlineShoppingAppRecords();
//
//        daoPassport = database2.daoPassport();
//        allPassportRecords = daoPassport.getAllPassportRecords();
//
//        daoSocialMediaApp = database2.daoSocialMediaApp();
//        allSocialMediaAppRecords = daoSocialMediaApp.getAllSocialMediaAppRecords();
//
//        daoUser = database2.daoUser();
//        allUserRecords = daoUser.getAllUserRecords();
//
//        daoWebsite = database2.daoWebsite();
//        allWebsiteRecords = daoWebsite.getAllWebsiteRecords();
//
//    }
//
////    public void insert(Website website){
////        new InsertWebsiteAsyncTask(daoWebsite).execute(website);
////    }
//
//    public LiveData<List<User>> getAllUserRecords() {
//        return allUserRecords;
//    }
//
//public void show(Website website) {
//        new ShowWebsiteRecordAsyncTask(daoWebsite).execute(website);
//}
//    public LiveData<List<Website>> getAllWebsiteRecords() {
//        return allWebsiteRecords;
//    }
//
//    public LiveData<List<BankAccount>> getAllBankAccountRecordsRecords() {
//        return allBankAccountRecords;
//    }
//
//    public LiveData<List<CreditCard>> getAllCreditCardRecords() {
//        return allCreditCardRecords;
//    }
//
//    public LiveData<List<Cryptocurrency>> getAllCryptocurrencyRecords() {
//        return allCryptocurrencyRecords;
//    }
//
//    public LiveData<List<DrivingLicence>> getAllDrivingLicenceRecords() {
//        return allDrivingLicenceRecords;
//    }
//
//    public LiveData<List<Email>> getAllEmailRecords() {
//        return allEmailRecords;
//    }
//
//    public LiveData<List<OnlineShoppingApp>> getAllOnlineShoppingAppRecords() {
//        return allOnlineShoppingAppRecords;
//    }
//
//    public LiveData<List<SocialMediaApp>> getAllSocialMediaAppRecords() {
//        return allSocialMediaAppRecords;
//    }
//
//    public LiveData<List<Passport>> getAllPassportRecords() {
//        return allPassportRecords;
//    }
//
//    private static class ShowWebsiteRecordAsyncTask extends AsyncTask<Website, Void, Void> {
//        private DaoWebsite daoWebsite;
//
//        private ShowWebsiteRecordAsyncTask(DaoWebsite daoWebsite) {
//            this.daoWebsite = daoWebsite;
//        }
//
//        @Override
//        protected Void doInBackground(Website... websites) {
//            daoWebsite.delete(websites[0]);
//            return null;
//        }
//
//    }
//
//

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




