package view.entrance;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.securevault19.securevault2019.MainActivity;
import com.securevault19.securevault2019.R;

import view.records.RecordRecycler_Activity;

import static view.explorer.CategoryList_Activity.EXTRA_FOLDER;


public class FirebaseTest_SignIn_Activity extends AppCompatActivity implements
        View.OnClickListener {

    public static final String EXTRA_USER =
            "com.securevault19.securevault2019.EXTRA_USER";

    private static final String TAG = "db";

    private EditText mEmailField , mPasswordField, mPasswordVerify;
    private TextView mStatusTextView, mDetailTextView;
    private ProgressBar progress_dialog;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_test_signin);
        mPasswordField = findViewById(R.id.EditText_Password);
        mPasswordVerify = findViewById(R.id.EditText_PasswordVerify);
        mEmailField = findViewById(R.id.EditText_Email);
        mStatusTextView = findViewById(R.id.mStatusTextView);
        mDetailTextView = findViewById(R.id.mDetailTextView);
        progress_dialog = findViewById(R.id.ProgressBar);

        mAuth = FirebaseAuth.getInstance();

    }

    public void startWorking() {
        currentUser = mAuth.getCurrentUser();

        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), FirebaseTest_Activity.class);
        intent.putExtra(EXTRA_USER, currentUser.getEmail());
        Toast.makeText(FirebaseTest_SignIn_Activity.this, currentUser.getEmail(), Toast.LENGTH_SHORT).show();
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        //findViewById(R.id.verifyEmailButton).setEnabled(!currentUser.isEmailVerified());
        updateUI(currentUser,"onStart");
    }
    // [END on_start_check_user]

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm("create account")) {
            return;
        }

        showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user,"create account");
                            // Send verification message to the user's email address
                            Toast.makeText(getApplicationContext(), "Account created", Toast.LENGTH_LONG).show();

                            sendEmailVerification();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(FirebaseTest_SignIn_Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null, "create account");
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

    private void hideProgressDialog() {
        progress_dialog.setVisibility(View.GONE);
    }

    private void showProgressDialog() {
        progress_dialog.setVisibility(View.VISIBLE);
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm("sign in")) {
            return;
        }
        currentUser = mAuth.getCurrentUser();
        if (currentUser!=null && !currentUser.isEmailVerified()) {
            mDetailTextView.setText(currentUser.getEmail() + " Please verify your email.");
            findViewById(R.id.verifyEmailButton).setVisibility(View.VISIBLE);
            return;
        }
        findViewById(R.id.verifyEmailButton).setVisibility(View.GONE);
        findViewById(R.id.EditText_PasswordVerify).setVisibility(View.GONE);
        showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user, "sign in");
                            startWorking();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(FirebaseTest_SignIn_Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null, "sign in");
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            mStatusTextView.setText("auth failed");
                        }
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private void signOut() {
        mAuth.signOut();
        //currentUser = mAuth.getCurrentUser();
        updateUI(currentUser, "sign out");
    }

    private void sendEmailVerification() {
        // Disable button
        findViewById(R.id.verifyEmailButton).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        findViewById(R.id.verifyEmailButton).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(FirebaseTest_SignIn_Activity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(FirebaseTest_SignIn_Activity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }

    private boolean validateForm(String action) {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else if (action.equals("create account")){
            mPasswordField.setError(null);
            if (!password.equals(mPasswordVerify.getText().toString())){
                mPasswordVerify.setVisibility(View.VISIBLE);
                mPasswordVerify.setError("Enter The Password");
                valid = false;
            }
        }
        return valid;
    }

    private void updateUI(FirebaseUser user, String action) {
        hideProgressDialog();
        if (user == null){
            if (action.equals("onStart")){
                return;
            }
            else {
                mStatusTextView.setText("action not completed");
                mDetailTextView.setText(null);
            }
        }
        else {
            mStatusTextView.setText("Account: \t" + user.getEmail() + "\n" + "Verified: \t" + user.isEmailVerified());
            if (action.equals("create account")) {
                mDetailTextView.setText("Verification message sent to your email");
                findViewById(R.id.EditText_PasswordVerify).setVisibility(View.GONE);
                findViewById(R.id.verifyEmailButton).setVisibility(View.VISIBLE);
            }
            else if (action.equals("onStart")){
                if (user.isEmailVerified()){
                    //mDetailTextView.setText("Email verified!");
                    findViewById(R.id.verifyEmailButton).setVisibility(View.GONE);
                    mEmailField.setText(user.getEmail());
                }
            }
            else if (action.equals("sign in")){
                findViewById(R.id.emailSignInButton).setVisibility(View.GONE);
                findViewById(R.id.signOutButton).setVisibility(View.VISIBLE);
                findViewById(R.id.emailCreateAccountButton).setVisibility(View.GONE);
                findViewById(R.id.EditText_Email).setEnabled(false);
                findViewById(R.id.EditText_Password).setEnabled(false);
                mDetailTextView.setText(user.getEmail() + " - \bSigned in.");
            }
            else if (action.equals("sign out")){
                findViewById(R.id.emailSignInButton).setVisibility(View.VISIBLE);
                findViewById(R.id.signOutButton).setVisibility(View.GONE);
                findViewById(R.id.emailCreateAccountButton).setVisibility(View.VISIBLE);
                findViewById(R.id.EditText_Email).setEnabled(true);
                findViewById(R.id.EditText_Password).setEnabled(true);
                mDetailTextView.setText(user.getEmail() + " - \bSigned out.");
            }
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.emailCreateAccountButton) {
            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.emailSignInButton) {
            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.signOutButton) {
            signOut();
        } else if (i == R.id.verifyEmailButton) {
            sendEmailVerification();
        }
    }
}