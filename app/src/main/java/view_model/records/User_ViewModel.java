package view_model.records;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.securevault19.securevault2019.user.User;

import java.util.List;

import repository.UserRepository;


public class User_ViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> allUsers;


    public User_ViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }

    public User LogInConfirmation(String name, String password){
        return userRepository.LogInConfirmation(name,password);
    }

    public String CheckForUserName(String email){
        return userRepository.CheckForUserName(email);
    }

    public void insert(User user) {
        userRepository.insert(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void updateSecureLevel(String secureLevel, String currentUser) { userRepository.updateSecureLevel(secureLevel, currentUser);}

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }
}
