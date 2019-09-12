package view.explorer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.securevault19.securevault2019.MainActivity;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.record.Website;

import local_database.DatabaseClient;


public class ExplorerMain_Activity extends AppCompatActivity {
    public static final String EXTRA_TITLE =                   //will be used for the RecyclerView
            "com.example.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_USERNAME =
            "com.example.architectureexample.EXTRA_USERNAME";
    public static final String EXTRA_PASSWORD =
            "com.example.architectureexample.EXTRA_PASSWORD";
    public static final String EXTRA_WEBSITE =
            "com.example.architectureexample.EXTRA_WEBSITE";
    public static final String EXTRA_EMAIL =
            "com.example.architectureexample.EXTRA_EMAIL";
    public static final String EXTRA_EXPIRING_DATE_DAY =
            "com.example.architectureexample.EXTRA_EXPIRING_DATE_DAY";
    public static final String EXTRA_EXPIRING_DATE_MONTH =
            "com.example.architectureexample.EXTRA_EXPIRING_DATE_MONTH";
    public static final String EXTRA_EXPIRING_DATE_YEAR =
            "com.example.architectureexample.EXTRA_EXPIRING_DATE_YEAR";
    public static final String EXTRA_OTHER =
            "com.example.architectureexample.EXTRA_OTHER";


    private EditText editTextUserName;
    private EditText editTextTitle;
    private EditText editTextPassword;
    private EditText editTextWebsite;
    private EditText editTextEmail;
    private EditText editTextExpiringDateDay;
    private EditText editTextExpiringDateMonth;
    private EditText editTextExpiringDateYear;
    private EditText editTextOther;

    private EditText custom1, custom2, custom3;
    private TextView c1, c2, c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_explorer_main);

        editTextUserName = findViewById(R.id.username);
        editTextTitle = findViewById(R.id.title);
        editTextPassword = findViewById(R.id.password);
        editTextEmail = findViewById(R.id.email);
        editTextWebsite = findViewById(R.id.website);
        editTextExpiringDateDay = findViewById(R.id.expiringDate_day);
        editTextExpiringDateMonth = findViewById(R.id.expiringDate_month);
        editTextExpiringDateYear = findViewById(R.id.expiringDate_year);
        editTextOther = findViewById(R.id.note);

        Button button = findViewById(R.id.Save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(ExplorerMain_Activity.this, "button pressed", Toast.LENGTH_SHORT).show();
                openNewRecord(v);


            }
        });

    }

    public void createRecord(View view) {
    }

    public void openSearch(View view) {
    }

    public void openMenu(View view) {
    }

    public void openNewRecord(View view) {

        //Setting the details from the Activity to send to the Website constructor
        final String title = editTextTitle.getText().toString();
        final String userName = editTextUserName.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String website = editTextWebsite.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String expiringDateDay = editTextExpiringDateDay.getText().toString().trim();
        final String expiringDateMonth = editTextExpiringDateMonth.getText().toString().trim();
        final String expiringDateYear = editTextExpiringDateYear.getText().toString().trim();
        final String other = editTextOther.getText().toString();


        //Check if all the needed details are typed.
        //NEED TO UPGRADE A LITTLE
        if (title.isEmpty() || userName.isEmpty() || password.isEmpty()
                || website.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please insert all the requested fields", Toast.LENGTH_SHORT).show();
            return;
        }

//        Intent data = new Intent();
//        data.putExtra(EXTRA_TITLE, title);
//        data.putExtra(EXTRA_USERNAME, userName);
//        data.putExtra(EXTRA_PASSWORD, password);
//        data.putExtra(EXTRA_EMAIL, email);
//        data.putExtra(EXTRA_EXPIRING_DATE_DAY, expiringDateDay);
//        data.putExtra(EXTRA_EXPIRING_DATE_MONTH, expiringDateMonth);
//        data.putExtra(EXTRA_EXPIRING_DATE_YEAR, expiringDateYear);
//        data.putExtra(EXTRA_OTHER, other);


        class SaveNewWebsiteRecord extends AsyncTask<Void, Void, Void> {

            //expiring-date fields are seperated so we concat them into one string.
            String expiringDate_arr[] = {expiringDateDay, expiringDateMonth, expiringDateYear};
            final String expiringDate = expiringDate_arr.toString();

            @Override
            protected Void doInBackground(Void... voids) {
                Website website_record = new Website();
                website_record.setTitle(title);
                website_record.setUserName(userName);
                website_record.setPassword(password);
                website_record.setWebsite(website);
                website_record.setEmail(email);
                website_record.setExpiringDate(expiringDate);
                website_record.setOther(other);

                DatabaseClient.getInstance(getApplicationContext()).getRecordDatabase2()
                        .daoWebsite()
                        .insert(website_record);

                //to deliver to RecyclerView
                Intent data = new Intent();
                data.putExtra(EXTRA_TITLE, title);
                data.putExtra(EXTRA_USERNAME, userName);
                data.putExtra(EXTRA_PASSWORD, password);
                data.putExtra(EXTRA_EMAIL, email);
                data.putExtra(EXTRA_WEBSITE, email);
                data.putExtra(EXTRA_EXPIRING_DATE_DAY, expiringDateDay);
                data.putExtra(EXTRA_EXPIRING_DATE_MONTH, expiringDateMonth);
                data.putExtra(EXTRA_EXPIRING_DATE_YEAR, expiringDateYear);
                data.putExtra(EXTRA_OTHER, other);

                setResult(RESULT_OK, data);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }
        SaveNewWebsiteRecord saveNewWebsiteRecord = new SaveNewWebsiteRecord();
        saveNewWebsiteRecord.execute();



    }


    public void addFields(View view) {
        EditText custom1 = findViewById(R.id.custom1);
        EditText custom2 = findViewById(R.id.custom2);
        EditText custom3 = findViewById(R.id.custom3);
        TextView c1 = findViewById(R.id.c1);
        TextView c2 = findViewById(R.id.c2);
        TextView c3 = findViewById(R.id.c3);

    }

}
