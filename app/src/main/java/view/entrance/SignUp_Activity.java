package view.entrance;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.user.User;

import cryptography.Cryptography;
import local_database.DatabaseClient;


public class SignUp_Activity extends AppCompatActivity {

    private TextView TextViewName;
    private TextView TextViewPassword;
    private Button SignUpButton;
    private User user;
    private String encryptedUserName;
    private String encryptedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        SignUpButton = findViewById(R.id.SignUp);

        String a = getIntent().getStringExtra("BLABLA");
        Toast.makeText(getApplicationContext(),""+a,Toast.LENGTH_LONG).show();


    }

    @SuppressLint("StaticFieldLeak")
    public void SignUpOnClick(View view) {           // on Click func
        Cryptography cryptography = new Cryptography();
        TextViewName = findViewById(R.id.TextView_Name);
        TextViewPassword = findViewById(R.id.TextView_Password);


        final String firstName = TextViewName.getText().toString();
        final String masterPassword = TextViewPassword.getText().toString();


        try {
            encryptedUserName = cryptography.encrypt(firstName);
            encryptedPassword = cryptography.encryptWithKey(firstName, masterPassword);
            Log.e("check", "" + firstName + " " + masterPassword + " " + encryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (firstName.isEmpty()) {
            Toast.makeText(this, "name is Empty", Toast.LENGTH_LONG).show();
            return;
        } else {

            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... voids) {
                    // you can just do
                    // user = new User(cryptography.encrypt(firstName), null, null, null, null, null, cryptography.encryptWithKey(firstName, masterPassword), null);
                    user = new User(encryptedUserName, null, null, null, null, null, encryptedPassword, null);
                    DatabaseClient.getInstance(getApplication()).getRecordDatabase2().daoUser().insert(user);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(getApplicationContext(),"User "+ user.getFirstName()+" Added", Toast.LENGTH_LONG).show();
                    finish();
                }


            }.execute();

        }


    }


}
