package repository;


import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.securevault19.securevault2019.user.User;

import java.util.List;

import local_database.DatabaseClient;
import local_database.RecordDatabase2;
import local_database.dao.DaoUser;


public class UserRepository {


    private DaoUser DaoUser;


    public UserRepository(Application application) {
        RecordDatabase2 database2 = DatabaseClient.getInstance(application).getRecordDatabase2();
        DaoUser = database2.daoUser();
    }

    public User LogInConfirmation(String firstName,String masterPassword){
        return DaoUser.LogInConfirmation(firstName,masterPassword);
    }

    public void insert(User user) {
        new InsertAsyncTask(DaoUser).execute(user);
    }

    private class InsertAsyncTask extends AsyncTask<User, Void, Void> {
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
}
