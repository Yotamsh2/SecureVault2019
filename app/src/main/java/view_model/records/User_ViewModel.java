package view_model.records;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.securevault19.securevault2019.user.User;

import java.util.List;

import repository.UserRepository;


// dont think i need it
public class User_ViewModel extends AndroidViewModel {

    private UserRepository userRepository;


    public User_ViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public User LogInConfirmation(String name, String password){
        return userRepository.LogInConfirmation(name,password);
    }
}
