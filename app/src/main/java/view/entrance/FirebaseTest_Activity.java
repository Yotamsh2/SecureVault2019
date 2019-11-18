package view.entrance;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.securevault19.securevault2019.R;

import org.w3c.dom.DocumentType;

import java.util.HashMap;
import java.util.Map;

import cryptography.Cryptography;
import local_database.DatabaseClient;

import static view.entrance.FirebaseTest_SignIn_Activity.EXTRA_USER;
import static view.explorer.CategoryList_Activity.EXTRA_FOLDER;


public class FirebaseTest_Activity extends AppCompatActivity {

    private static final String TAG = "NewRecord";
    //Names of the columns of each document in the collection:
    private static final String USER_NAME = "User Name";
    private static final String PASSWORD = "Password";
    private static final String WEBSITE = "Website";
    private static final String MAIN_COLLECTION = "All Data";
    private static String USER_ID = "";

    private EditText Name, Password, Website;
    private TextView data_textView;
    private String NameText, PasswordText, WebsiteText, CategoryText;
    private Spinner Category;

    private FirebaseFirestore FSRef = FirebaseFirestore.getInstance();// Access a Cloud Firestore instance


    //---For live data in the activity:---//
    /*@Override
    protected void onStart() {
        super.onStart();
        FSRef.document(USER + "/" + USER_ID).addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {
                    NameText = documentSnapshot.getString(USER_NAME);
                    PasswordText = documentSnapshot.getString(MASTER_PASSWORD);
                    WebsiteText = documentSnapshot.getString(WEBSITE);
                    data_textView.setVisibility(View.VISIBLE);
                    data_textView.setText("User Name: " + NameText + "\nMaster Password: " + PasswordText + "\nWebsite: " + WebsiteText);
                }
            }
        });
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_test);

        Name = findViewById(R.id.TextView_Name);
        Password = findViewById(R.id.TextView_Password);
        Website = findViewById(R.id.EditText_Website);
        Category = findViewById(R.id.category_Spinner);
        data_textView = findViewById(R.id.data_textView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            USER_ID = extras.getString(EXTRA_USER);}
    }

    public void saveRecord(View view) {
        fieldsAndExtrasToStrings();
        if (NameText.isEmpty() || PasswordText.isEmpty() || WebsiteText.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }

        Map<String, Object> record = new HashMap<String, Object>();      // Create a new record with a Name and Password
        record.put(USER_NAME, NameText);
        record.put(PASSWORD, PasswordText);
        record.put(WEBSITE, WebsiteText);

        FSRef.document(MAIN_COLLECTION + "/" + USER_ID + "/" + CategoryText +"/" + WebsiteText).set(record).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Document was saved");
                Toast.makeText(getApplicationContext(), WebsiteText +" SAVED", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Document was not saved");
                Toast.makeText(getApplicationContext(), "NOT SAVED", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void FetchData(View view) {
        fieldsAndExtrasToStrings();
        if (WebsiteText.equals("")){
            Toast.makeText(this, "Please fill 'Website' field", Toast.LENGTH_LONG).show();
            return;
        }
        FSRef.document(MAIN_COLLECTION + "/" + USER_ID + "/" + CategoryText +"/" + WebsiteText).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    NameText = documentSnapshot.getString(USER_NAME);
                    PasswordText = documentSnapshot.getString(PASSWORD);
                    Name.setText(NameText);
                    Password.setText(PasswordText);
                    data_textView.setVisibility(View.VISIBLE);
                    data_textView.setText("User Name: \t" + NameText + "\nPassword: \t\t" + PasswordText + "\nWebsite: \t\t\t\t"
                            + WebsiteText);
                }
            }
        });
    }

    public void fieldsAndExtrasToStrings() {
        NameText = Name.getText().toString();
        PasswordText = Password.getText().toString();
        WebsiteText = Website.getText().toString();
        CategoryText = Category.getSelectedItem().toString();

    }
}