package repository;


import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.securevault19.securevault2019.user.User;

import java.util.List;

import local_database.DatabaseClient;
import local_database.RecordDatabase2;
import local_database.dao.DaoUser;


public class UserRepository {

    private DaoUser DaoUser;
    private LiveData<List<User>> allUsers;


    public UserRepository(Application application) {
        RecordDatabase2 database2 = DatabaseClient.getInstance(application).getRecordDatabase2();
        DaoUser = database2.daoUser();
    }

    public User LogInConfirmation(String firstName, String masterPassword) {
        return DaoUser.LogInConfirmation(firstName, masterPassword);
    }

    public String CheckForUserName(String email) {
        return DaoUser.CheckForUserName(email);
    }

    public void insert(User user) {
        new InsertAsyncTask(DaoUser).execute(user);
    }

    public void update(User user) {
        new UpdateAsyncTask(DaoUser).execute(user);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    @Nullable
    public void updateSecureLevel(String secureLevel, String currentUser) {
        new UpdateSecureLevel(DaoUser, secureLevel, currentUser).execute();
    }


    private static class InsertAsyncTask extends AsyncTask<User, Void, Void> {
        private DaoUser daoUser;

        private InsertAsyncTask(DaoUser daoUser) {
            this.daoUser = daoUser;
        }

        @Override
        protected Void doInBackground(User... users) {
            daoUser.insert(users[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<User, Void, Void> {
        private DaoUser daoUser;

        private UpdateAsyncTask(DaoUser daoUser) {
            this.daoUser = daoUser;
        }

        @Override
        protected Void doInBackground(User... users) {
            daoUser.update(users[0]);
            return null;
        }
    }

    private static class UpdateSecureLevel extends AsyncTask<User, Void, Void> {
        private DaoUser daoUser;
        String secureLevel;
        String currentUser;

        private UpdateSecureLevel(DaoUser daoUser, String secureLevel, String currentUser) {
            this.daoUser = daoUser;
            this.currentUser = currentUser;
            this.secureLevel = secureLevel;
            Log.d("secureLevel", "currentUser: " + currentUser);
        }

        @Override
        protected Void doInBackground(User... users) {
            Log.d("secureLevel", "(doInBackground) currentUser: " + currentUser);

            daoUser.updateSecureLevel(secureLevel, currentUser);
            return null;
        }
    }
}
