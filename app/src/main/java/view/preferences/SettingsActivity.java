package view.preferences;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.user.CurrentUser;

import view_model.records.User_ViewModel;

public class SettingsActivity extends AppCompatActivity {
    private String MP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
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

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            bindSummaryValue(findPreference("upgrade"));
            bindSummaryValue(findPreference("change_mp"));
            bindSummaryValue(findPreference("accout"));
//            bindSummaryValue(findPreference("autofill"));
//            bindSummaryValue(findPreference("email"));
//            bindSummaryValue(findPreference("userName"));
//            bindSummaryValue(findPreference("attachment"));
//            bindSummaryValue(findPreference("sync"));
            bindSummaryValue(findPreference("about"));
//            bindSummaryValue(findPreference("signature"));
//            bindSummaryValue(findPreference("reply"));


        }
    }

    private static void bindSummaryValue(Preference preference) {
        preference.setOnPreferenceChangeListener(listener);
        listener.onPreferenceChange(preference,
                PreferenceManager.getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    private static Preference.OnPreferenceChangeListener listener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);
                //set the summary to reflect the new value
                preference.setSummary(index > 0
                        ? listPreference.getEntries()[index]
                        : null);
                if (preference.getKey().equals("security_level")) {
                    //call function that changes security level in DB   //NO
                    //new changeSecurityLevelAsyncTask().execute();     //NO

//                    daoUser.updateSecureLevel("3", "12"); //Direct access to DaoUser because 'OnPreferenceChangeListener' can't implement here ViewModel
                    CurrentUser.secureLevel = stringValue;
                    Log.d("userr", "CurrentUser.secureLevel: " + CurrentUser.secureLevel);
                } else if (preference instanceof EditTextPreference) {
                    preference.setSummary(stringValue);
                }
//            else if (preference instanceof RingtonePreference) {
//                if (TextUtils.isEmpty(stringValue)) {
//                    //No ringtone
//                    preference.setSummary("Silent");
//                } else {
//                    Ringtone ringtone = RingtoneManager.getRingtone(preference.getContext()
//                            , Uri.parse(stringValue));
//                    if (ringtone == null) {
//                        //Clear the summary
//                        preference.setSummary("Choose notification ringtone");
//                    } else {
//                        String name = ringtone.getTitle(preference.getContext());
//                        preference.setSummary(name);
//                    }
//                }
//            }

            }
            return false;
        }
    };
}
