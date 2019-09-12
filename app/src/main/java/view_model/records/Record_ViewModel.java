package view_model.records;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
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

import repository.RecordRepository;

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
        allUserRecords = repository.getAllUserRecords();
        allBankAccountRecords = repository.getAllBankAccountRecordsRecords();
        allCreditCardRecords = repository.getAllCreditCardRecords();
        allCryptocurrencyRecords = repository.getAllCryptocurrencyRecords();
        allWebsiteRecords = repository.getAllWebsiteRecords();
        allDrivingLicenceRecords = repository.getAllDrivingLicenceRecords();
        allEmailRecords = repository.getAllEmailRecords();
        allOnlineShoppingAppRecords = repository.getAllOnlineShoppingAppRecords();
        allPassportRecords = repository.getAllPassportRecords();
        allSocialMediaAppRecords = repository.getAllSocialMediaAppRecords();


    }
//    public void insert(Website website){
//        Log.d("insert(view_model)", "called." );
//        repository.insert(website);
//    }

    public LiveData<List<User>> getAllUserRecords() {
        return allUserRecords;
    }

public void show(Website website){
    repository.show(website);
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
