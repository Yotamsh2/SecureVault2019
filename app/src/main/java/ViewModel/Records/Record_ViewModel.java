package ViewModel.Records;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
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

import Repository.RecordRepository;

public class Record_ViewModel extends AndroidViewModel {
    private RecordRepository repository;
    private LiveData<List<Website>> allWebsiteRecords;
    private LiveData<List<User>> allUserRecords;
    private LiveData<List<BankAccount>> allBankAccountRecords;
    private LiveData<List<CreditCard>> allCreditCardRecords;
    private LiveData<List<Cryptocurrency>> allCryptocurrencyRecords;
    private LiveData<List<DrivingLicence>> allDrivingLicenceRecords;
    private LiveData<List<Email>> allEmailRecords;
    private LiveData<List<OnlineShoppingApp>> allOnlineShoppingAppRecords;
    private LiveData<List<Passport>> allPassportRecords;
    private LiveData<List<SocialMediaApp>> allSocialMediaAppRecords;


    public Record_ViewModel(@NonNull Application application) {
        super(application);
        repository = new RecordRepository(application);
        allWebsiteRecords = repository.getAllWebsiteRecords();

    }
//    public void insert(Website website){
//        Log.d("insert(ViewModel)", "called." );
//        repository.insert(website);
//    }

    public LiveData<List<User>> getAllUserRecords() {
        return allUserRecords;
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
}
