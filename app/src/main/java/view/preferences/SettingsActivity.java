package view.preferences;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.UserDictionary;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.user.CurrentUser;
import com.securevault19.securevault2019.user.User;

import cryptography.Cryptography;
import local_database.DatabaseClient;
import local_database.dao.DaoUser;
import view_model.records.User_ViewModel;

public class SettingsActivity extends AppCompatActivity {
    private String MP;
    private Fragment aboutFragment;
    private static Cryptography cryptography = new Cryptography();
    private static User_ViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

userViewModel = ViewModelProviders.of(this).get(User_ViewModel.class);

        //Load settings Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        private static User_ViewModel userViewModel;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            userViewModel = ViewModelProviders.of(this).get(User_ViewModel.class);

            bindSummaryValue(findPreference("change_mp"));
            bindSummaryValue(findPreference("security_level"));

            bindSummaryValue(findPreference("about"));

        }
    }

    private static void bindSummaryValue(Preference preference) {
        preference.setOnPreferenceChangeListener(listener);
        listener.onPreferenceChange(preference,
                PreferenceManager.getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    private static final Preference.OnPreferenceChangeListener listener = new Preference.OnPreferenceChangeListener() {
        DaoUser daoUser;
        User_ViewModel userViewModel;
// = ViewModelProviders.of(Preference.getContext().).get(User_ViewModel.class);

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);
                //set the summary to reflect the new value
                preference.setSummary(index >= 0
                        ? listPreference.getEntries()[index]
                        : null);
                if (preference.getKey().equals("security_level")) {        //if security leves has changed
                    new changeSecurityLevelAsyncTask(stringValue,CurrentUser.getInstance().getEmail()).execute();
                }
            } else if (preference instanceof EditTextPreference) {
                preference.setSummary(stringValue);
            }

            return true;
        }
    };

    /*---------------------------------------------------------------------------------------
//    TO ADD:
//    check if MasterPassword is pressed; call function that rquires entering again the pattern.ListPreference. and then change in DB.
//    check if SecurityLevel is pressed; call function to change preferences in DB.
//    check if Autofill is checked; call function to change preferences in DB.
//    need to work on Notifications
     */


    public static class changeSecurityLevelAsyncTask extends AsyncTask<Void, Void, Void> { //experiment
        private String newSecurityLevel;
        private String currentUser;

        changeSecurityLevelAsyncTask(String newSecurityLevel, String currentUser){
            this.newSecurityLevel = newSecurityLevel;
            this.currentUser = currentUser;
            Log.d("secureLevel", "constructor: " + currentUser + " , "+ newSecurityLevel);

        }
//
        @Override
        protected Void doInBackground(Void... voids)
        {
            userViewModel.updateSecureLevel(newSecurityLevel, currentUser);
            CurrentUser.getInstance().setSecureLevel(newSecurityLevel);
            userViewModel.update(CurrentUser.getInstance());
                return null;
        }
    }
}
