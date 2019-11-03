package view.records;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.record.Record;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;

import cryptography.Cryptography;
import local_database.DatabaseClient;

import static view.records.RecordRecycler_Activity.EXTRA_ORIGIN;

public class AddNewRecord_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Cryptography cryptography;
    private String encryptedUsername;
    private String encryptedPassword;
    private String user;


    // --- test for checking why the recycler view dosent show any thing --- //
    public static final String EXTRA_FOLDER =
            "com.securevault19.securevault2019.EXTRA_FOLDER";
    public static final String EXTRA_TYPE =
            "com.securevault19.securevault2019.EXTRA_TYPE";
    // -------------------------------------------------------------------- //
    //Used by the RecyclerView ////////////////////////////////////////////////////
    public static final String EXTRA_TITLE =
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
    //////////////////////////////////////////////////////////////////////////////////

    private Button addChooseIconBtn;
    private ImageView chooseIcon;
    private Button starBtn, starFullBtn;
    private EditText custom1_EditText, custom2_EditText, custom3_EditText, note;
    //////////////////////////////////////////////////////////////////////////////////

    //    private EditText custom1_EditText, custom2_EditText, custom3_EditText, password, note;
    private TextView custom1_EditText_title, custom2_EditText_title, custom3_EditText_title, note_title;
    private TextView licenceExpiringDate_EditText;
    private TextView issuanceDate_EditText;
    private TextView expiringDate_EditText;
    private TextView expiringDate_title;
    private ImageButton calendarBtn;
    private ImageView logo;
    private Button addFields, addNote, addExpiringDate;
    private ImageButton saveBtn, cancelBtn;
    private ImageButton showPass, hidePass, copyPass, showCategory, showTypeOfRecord;
    private MediaPlayer mediaPlayer;
    private Animation animation1, animation2, animation3;
    private ScrollView scrollView;
    private FloatingActionButton editForm;
    private TextView activityTitle;
    private Typeface myFont;

    private Spinner category_Spinner, typeOfRecord_Spinner;
    private EditText category_EditText, title_EditText, username_EditText, password_EditText, website_EditText, email_EditText;
    //String outputString;

    private LinearLayout category, typeOfRecord;
    private LinearLayout registerDetails;
    private LinearLayout userName, password, website, email, bankAccount, creditCard, cryptocurrency, drivingLicence, passport;
    private HorizontalScrollView listOfIcons;

    private GridLayout gridLayout; //TO DELETE

    private boolean isFavorite = false; //as default, a record is not a favorite.

    Field[] allDrawablesfromRes_drawable = com.securevault19.securevault2019.R.drawable.class.getFields();
    ArrayList<Drawable> drawableResources = new ArrayList<>();
    //ArrayList<Integer> drawableResourcesIDs = new ArrayList<>();
    int drawabaleID = 2131230920; //Default Icon(Secure Vault Black)
    Drawable mChooseicon;
    private Drawable currentDrawable;


    @Override
    protected void onCreate(Bundle saveBtndInstanceState) {
        super.onCreate(saveBtndInstanceState);
        setContentView(R.layout.activity_add_new_record);
        user = getIntent().getStringExtra("CRYPTO_KEY");
        Log.d("userCheck", "---" + user);


        listOfIcons = findViewById(R.id.listOfIcons);
        addChooseIconBtn = findViewById(R.id.addChooseIconBtn);
        chooseIcon = findViewById(R.id.chooseIcon);
        starBtn = findViewById(R.id.star_icon);
        starFullBtn = findViewById(R.id.starFull_icon);
        mediaPlayer = MediaPlayer.create(this, R.raw.button);
        logo = findViewById(R.id.logo);
        saveBtn = findViewById(R.id.saveBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        licenceExpiringDate_EditText = findViewById(R.id.licenceExpiringDate_EditText);
        issuanceDate_EditText = findViewById(R.id.issuanceDate_EditText);
        expiringDate_EditText = findViewById(R.id.expiringDate_EditText);
        expiringDate_title = findViewById(R.id.expiringDate_title);
        calendarBtn = findViewById(R.id.calendarBtn);
        addExpiringDate = findViewById(R.id.addExpiringDateBtn);
        showPass = findViewById(R.id.showPass);
        hidePass = findViewById(R.id.hidePass);
        copyPass = findViewById(R.id.copyPass);
        custom1_EditText = findViewById(R.id.custom1_EditText);
        custom2_EditText = findViewById(R.id.custom2_EditText);
        custom3_EditText = findViewById(R.id.custom3_EditText);
        custom1_EditText_title = findViewById(R.id.c1);
        custom2_EditText_title = findViewById(R.id.c2);
        custom3_EditText_title = findViewById(R.id.c3);
        addFields = findViewById(R.id.addFieldsBtn);
        scrollView = findViewById(R.id.frame);
        addNote = findViewById(R.id.addNoteBtn);
        note = findViewById(R.id.note_editText);
//        note_title =  findViewById(R.id.note_title);
        category_Spinner = findViewById(R.id.category_Spinner); //Folder
        typeOfRecord_Spinner = findViewById(R.id.typeOfRecord_Spinner);
        title_EditText = findViewById(R.id.title_EditText);
        username_EditText = findViewById(R.id.username_EditText);
        password_EditText = findViewById(R.id.password_EditText);
        website_EditText = findViewById(R.id.website_EditText);
        email_EditText = findViewById(R.id.email_EditText);
        editForm = findViewById(R.id.editForm);
        activityTitle = findViewById(R.id.activityTitle);
        category = findViewById(R.id.category);
        typeOfRecord = findViewById(R.id.typeOfRecord);
        showCategory = findViewById(R.id.showCategory);
        showTypeOfRecord = findViewById(R.id.showTypeOfRecord);
        registerDetails = findViewById(R.id.registerDetails);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        website = findViewById(R.id.website);
        email = findViewById(R.id.email);
        bankAccount = findViewById(R.id.bankAccountDetails);
        creditCard = findViewById(R.id.creditCardDetails);
        cryptocurrency = findViewById(R.id.cryptocurrencyDetails);
        drivingLicence = findViewById(R.id.drivingLicenceDetails);
        passport = findViewById(R.id.passportDetails);

        //Animation Sets
        animation1 = AnimationUtils.loadAnimation(AddNewRecord_Activity.this, R.anim.zoomin);
        animation2 = AnimationUtils.loadAnimation(AddNewRecord_Activity.this, R.anim.zoomin_fast);
        animation3 = AnimationUtils.loadAnimation(AddNewRecord_Activity.this, R.anim.buttonpush_anim);
        scrollView.startAnimation(animation2);


        //        Set logo's font to category's text
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/OutlierRail.ttf");
        activityTitle.setTypeface(myFont);


        //will be delivered to AsyncTask
//        try { //DO NOT DELETE
//            getDrawabalesFields();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        new getAllDrawablesResourcesAsyncTask().execute();


        //Getting all the EXTRAS from all the 'intent.puExtra()'s
        Bundle extras = getIntent().getExtras();
        String folder, type, origin;


        if (extras != null) {
            //Check where we came from:  (Recycler).onRecordClick  OR  (Recycler).buttonAddRecord
            origin = extras.getString(EXTRA_ORIGIN);
            Log.d("AddNewRecord123", "from onRecordClick: origin: " + origin);

            switch (origin) {
                case "onRecordClick":           // clicked from recycler view

                    folder = extras.getString(EXTRA_FOLDER);
                    Log.d("AddNewRecord123", "from folder: " +  folder);
                    if (folder != null) {

                        type = extras.getString(EXTRA_TYPE);
                        Log.d("AddNewRecord123", "type: " +  type);
                        if (type != null) {
                            switch (type) {
                                //NEED TO BE COMPLETED FOR ALL THE RECORD TYPES.

                                case "Bank Accounts": //shows the relevant fields of the clicked Record.
                                    userName.setVisibility(View.VISIBLE);
                                    password.setVisibility(View.VISIBLE);
                                    website.setVisibility(View.VISIBLE);
                                    email.setVisibility(View.VISIBLE);
                                    bankAccount.setVisibility(View.VISIBLE);
                                    creditCard.setVisibility(View.VISIBLE);
                                    cryptocurrency.setVisibility(View.VISIBLE);
                                    drivingLicence.setVisibility(View.VISIBLE);
                                    passport.setVisibility(View.VISIBLE);
                                    Log.d("AddNewRecord123", "bankAccount" );
                                    Toast.makeText(this, "EXTRA_FOLDER: Bank Accounts", Toast.LENGTH_SHORT).show();
                                    break;

                                default:  //FOR EXAMPLE
                                    userName.setVisibility(View.GONE);
                                    password.setVisibility(View.GONE);
                                    website.setVisibility(View.GONE);
                                    email.setVisibility(View.GONE);
                                    bankAccount.setVisibility(View.GONE);
                                    creditCard.setVisibility(View.GONE);
                                    cryptocurrency.setVisibility(View.VISIBLE);
                                    drivingLicence.setVisibility(View.GONE);
                                    passport.setVisibility(View.GONE);
                                    break;
                            }
                        }
                    }

                    break; //case: onRecordClick

                //If we came from buttonAddRecord
                default:
                    //Show fields after selecting item in spinner
                    typeOfRecord_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //(0)Website/Email Account, (1)Social Media, (2)Online Shopping
                            if (position == 0 || position == 1 || position == 2) {
                                userName.setVisibility(View.VISIBLE);
                                password.setVisibility(View.VISIBLE);
                                website.setVisibility(View.VISIBLE);
                                email.setVisibility(View.VISIBLE);
                                bankAccount.setVisibility(View.GONE);
                                creditCard.setVisibility(View.GONE);
                                cryptocurrency.setVisibility(View.GONE);
                                drivingLicence.setVisibility(View.GONE);
                                passport.setVisibility(View.GONE);
                            }
                            //(3)Bank Account
                            if (position == 3) {
                                userName.setVisibility(View.VISIBLE);
                                password.setVisibility(View.VISIBLE);
                                website.setVisibility(View.GONE);
                                email.setVisibility(View.GONE);
                                bankAccount.setVisibility(View.VISIBLE);
                                creditCard.setVisibility(View.GONE);
                                cryptocurrency.setVisibility(View.GONE);
                                drivingLicence.setVisibility(View.GONE);
                                passport.setVisibility(View.GONE);
                            }
                            //(4)Credit Card
                            if (position == 4) {
                                userName.setVisibility(View.GONE);
                                password.setVisibility(View.VISIBLE);
                                website.setVisibility(View.GONE);
                                email.setVisibility(View.GONE);
                                bankAccount.setVisibility(View.GONE);
                                creditCard.setVisibility(View.VISIBLE);
                                cryptocurrency.setVisibility(View.GONE);
                                drivingLicence.setVisibility(View.GONE);
                                passport.setVisibility(View.GONE);
                            }
                            //(5)Passport
                            if (position == 5) {
                                userName.setVisibility(View.GONE);
                                password.setVisibility(View.GONE);
                                website.setVisibility(View.GONE);
                                email.setVisibility(View.GONE);
                                bankAccount.setVisibility(View.GONE);
                                creditCard.setVisibility(View.GONE);
                                cryptocurrency.setVisibility(View.GONE);
                                drivingLicence.setVisibility(View.GONE);
                                passport.setVisibility(View.VISIBLE);
                            }
                            //(6)Cryptocurrency
                            if (position == 6) {
                                userName.setVisibility(View.GONE);
                                password.setVisibility(View.GONE);
                                website.setVisibility(View.GONE);
                                email.setVisibility(View.GONE);
                                bankAccount.setVisibility(View.GONE);
                                creditCard.setVisibility(View.GONE);
                                cryptocurrency.setVisibility(View.VISIBLE);
                                drivingLicence.setVisibility(View.GONE);
                                passport.setVisibility(View.GONE);
                            }
                            //(7)Driving Licence
                            if (position == 7) {
                                userName.setVisibility(View.GONE);
                                password.setVisibility(View.GONE);
                                website.setVisibility(View.GONE);
                                email.setVisibility(View.GONE);
                                bankAccount.setVisibility(View.GONE);
                                creditCard.setVisibility(View.GONE);
                                cryptocurrency.setVisibility(View.GONE);
                                drivingLicence.setVisibility(View.VISIBLE);
                                passport.setVisibility(View.GONE);
                            }
                            //(8)NOTE
                            if (position == 8) {
                                userName.setVisibility(View.GONE);
                                password.setVisibility(View.GONE);
                                website.setVisibility(View.GONE);
                                email.setVisibility(View.GONE);
                                bankAccount.setVisibility(View.GONE);
                                creditCard.setVisibility(View.GONE);
                                cryptocurrency.setVisibility(View.GONE);
                                drivingLicence.setVisibility(View.GONE);
                                passport.setVisibility(View.GONE);
                                addNote(addNote);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
            }
        }


        //position of: 'typeOfRecord_Spinner.setOnItemSelectedListener'   before switch-case
/*
//        //Show fields after selecting item in spinner
//        typeOfRecord_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //(0)Website/Email Account, (1)Social Media, (2)Online Shopping
//                if (position == 0 || position == 1 || position == 2) {
//                    userName.setVisibility(View.VISIBLE);
//                    password.setVisibility(View.VISIBLE);
//                    website.setVisibility(View.VISIBLE);
//                    email.setVisibility(View.VISIBLE);
//                    bankAccount.setVisibility(View.GONE);
//                    creditCard.setVisibility(View.GONE);
//                    cryptocurrency.setVisibility(View.GONE);
//                    drivingLicence.setVisibility(View.GONE);
//                    passport.setVisibility(View.GONE);
//                }
//                //(3)Bank Account
//                if (position == 3) {
//                    userName.setVisibility(View.VISIBLE);
//                    password.setVisibility(View.VISIBLE);
//                    website.setVisibility(View.GONE);
//                    email.setVisibility(View.GONE);
//                    bankAccount.setVisibility(View.VISIBLE);
//                    creditCard.setVisibility(View.GONE);
//                    cryptocurrency.setVisibility(View.GONE);
//                    drivingLicence.setVisibility(View.GONE);
//                    passport.setVisibility(View.GONE);
//                }
//                //(4)Credit Card
//                if (position == 4) {
//                    userName.setVisibility(View.GONE);
//                    password.setVisibility(View.VISIBLE);
//                    website.setVisibility(View.GONE);
//                    email.setVisibility(View.GONE);
//                    bankAccount.setVisibility(View.GONE);
//                    creditCard.setVisibility(View.VISIBLE);
//                    cryptocurrency.setVisibility(View.GONE);
//                    drivingLicence.setVisibility(View.GONE);
//                    passport.setVisibility(View.GONE);
//                }
//                //(5)Passport
//                if (position == 5) {
//                    userName.setVisibility(View.GONE);
//                    password.setVisibility(View.GONE);
//                    website.setVisibility(View.GONE);
//                    email.setVisibility(View.GONE);
//                    bankAccount.setVisibility(View.GONE);
//                    creditCard.setVisibility(View.GONE);
//                    cryptocurrency.setVisibility(View.GONE);
//                    drivingLicence.setVisibility(View.GONE);
//                    passport.setVisibility(View.VISIBLE);
//                }
//                //(6)Cryptocurrency
//                if (position == 6) {
//                    userName.setVisibility(View.GONE);
//                    password.setVisibility(View.GONE);
//                    website.setVisibility(View.GONE);
//                    email.setVisibility(View.GONE);
//                    bankAccount.setVisibility(View.GONE);
//                    creditCard.setVisibility(View.GONE);
//                    cryptocurrency.setVisibility(View.VISIBLE);
//                    drivingLicence.setVisibility(View.GONE);
//                    passport.setVisibility(View.GONE);
//                }
//                //(7)Driving Licence
//                if (position == 7) {
//                    userName.setVisibility(View.GONE);
//                    password.setVisibility(View.GONE);
//                    website.setVisibility(View.GONE);
//                    email.setVisibility(View.GONE);
//                    bankAccount.setVisibility(View.GONE);
//                    creditCard.setVisibility(View.GONE);
//                    cryptocurrency.setVisibility(View.GONE);
//                    drivingLicence.setVisibility(View.VISIBLE);
//                    passport.setVisibility(View.GONE);
//                }
//                //(8)NOTE
//                if (position == 8) {
//                    userName.setVisibility(View.GONE);
//                    password.setVisibility(View.GONE);
//                    website.setVisibility(View.GONE);
//                    email.setVisibility(View.GONE);
//                    bankAccount.setVisibility(View.GONE);
//                    creditCard.setVisibility(View.GONE);
//                    cryptocurrency.setVisibility(View.GONE);
//                    drivingLicence.setVisibility(View.GONE);
//                    passport.setVisibility(View.GONE);
//                    addNote(addNote);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
*/
    }


    @Override
    public void onBackPressed() {
        cancelWarningMessage(null);

    }


    public void openMenu(View view) {
    }


    @SuppressLint({"RestrictedApi", "StaticFieldLeak"})
    public void openNewRecord(View view) {                     // the onClick func (save Button)
        cryptography = new Cryptography();                      // making argument of Cryptography which is private!
        //Setting the details from the Activity to send to the Record constructor
        final String title = title_EditText.getText().toString();
        final String userName = username_EditText.getText().toString().trim();
        final String password = password_EditText.getText().toString().trim();
        final String website = website_EditText.getText().toString().trim();
        final String email = email_EditText.getText().toString().trim();
        final String expiringDate = expiringDate_EditText.getText().toString().trim();
        final String typeOfRecord = typeOfRecord_Spinner.getSelectedItem().toString();
        final String folder = category_Spinner.getSelectedItem().toString();



        //final String note = this.note.getText().toString();
//        final int accountNumber;
//        final long IBAN;
//        final int bankNumber;
//        final String address;
//        final int cardNumber;
//        final int CVV;
//        final int expireYear;
//        final int expireMonth;
//        final int expireDay;
//        final String publicKey;
//        final String privateKey;
//        final String walletGenerationSeed;
//        final int licenceNumber = Integer.parseInt(licenceExpiringDate_EditText.toString());
//        final int passportNumber;
//        final String issuanceDate = issuanceDate_EditText.getText().toString().trim();
//        final String issuancePlace;
//        final String secret_question;


        //Check if all the needed details are typed.
        if (title.isEmpty()) {
            Toast.makeText(this, "Please fill 'title' field", Toast.LENGTH_SHORT).show();
            return;
        } else {
            // If all fields are good, opening AsyncTask
            // We have to use AsyncTask for RecyclerView
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... voids) {
                    //try for Encryption
                    try {
                        // encrypthing the password and the username(username for test)
                        //encryptedUsername = cryptography.encrypt(username_EditText.getText().toString());
                       encryptedUsername = cryptography.encryptWithKey(user,username_EditText.getText().toString());
                          Log.d("userCheck", "" +user);

                        //if (password_EditText.getText().toString() != null) {
                            //encryptedPassword = cryptography.encryptWithKey(user,password_EditText.getText().toString());
                            encryptedPassword = cryptography.encryptWithKey(user,password_EditText.getText().toString());
                        Log.d("userCheck", "" + user);
                          //  Log.d("crypto", "" + encryptedUsername);
                        //}
                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                    // PAY ATTENTION!                                //
                    // the encryption we using is EncryptWithKey();!//
                    // the KEY will be the userName                 //

                    // opening Record to insert the TextFields and insert to DB
                    Record record = new Record();
                    record.setType(typeOfRecord);
                    record.setFolder(folder);
                    record.setTitle(title);
                    record.setUserName(encryptedUsername);
                    record.setPassword(encryptedPassword);
                    record.setWebsite(website);
                    record.setEmail(email);
                    record.setExpiringDate(expiringDate);
                    record.setFavorite(isFavorite);
                    record.setIcon(String.valueOf(drawabaleID));



                    // inserting record to DB
                    DatabaseClient.getInstance(getApplicationContext()).getRecordDatabase2()
                            .daoRecord()
                            .insert(record);

                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(getApplicationContext(), "record created", Toast.LENGTH_SHORT).show();
// after the save button is clicked and finished all his jobs, before exiting completely,
// finishing the activity and open 'new' (refreshed ) RecyclerView with Website Category

                    finish();
                    Intent intent = new Intent(getApplicationContext(), RecordRecycler_Activity.class);
                    intent.putExtra(EXTRA_FOLDER, folder);
                    startActivity(intent);
                }
            }.execute();                // execute for starting the AsyncTask


            // ------------------------- decypher func --------------------------- //
            // ---------------- use in case you need to decypher ---------------- //
//            try {
//                decryptedPassword = decrypt(encryptedPassword, username_EditText.getText().toString());
//                Log.d("decript", decryptedPassword);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            // ------------------------------------------------------------------- //


        }


        saveBtn.startAnimation(animation3);
        mediaPlayer.start();

        category_Spinner.setEnabled(false);
        title_EditText.setEnabled(false);
        username_EditText.setEnabled(false);
        password_EditText.setEnabled(false);
        website_EditText.setEnabled(false);
        email_EditText.setEnabled(false);
        expiringDate_EditText.setEnabled(false);
        custom1_EditText.setEnabled(false);
        custom2_EditText.setEnabled(false);
        custom3_EditText.setEnabled(false);
        calendarBtn.setEnabled(false);
        note.setEnabled(false);
        saveBtn.setVisibility(View.GONE);
        cancelBtn.setVisibility(View.GONE);

        editForm.setVisibility(View.VISIBLE);

    }

    public void addFields(View view) {
        if (custom1_EditText.getVisibility() == View.GONE) {
            custom1_EditText.setVisibility(View.VISIBLE);
            custom1_EditText_title.setVisibility(View.VISIBLE);
            custom1_EditText.requestFocus();
        } else if (custom2_EditText.getVisibility() == View.GONE) {
            custom2_EditText.setVisibility(View.VISIBLE);
            custom2_EditText_title.setVisibility(View.VISIBLE);
            custom2_EditText.requestFocus();
        } else if (custom3_EditText.getVisibility() == View.GONE) {
            custom3_EditText.setVisibility(View.VISIBLE);
            custom3_EditText_title.setVisibility(View.VISIBLE);
            custom3_EditText.requestFocus();
            addFields.setVisibility(View.GONE);
        }

    }

    public void addExpiringDate(View view) {
        expiringDate_EditText.setVisibility(View.VISIBLE);
        calendarBtn.setVisibility(View.VISIBLE);
        expiringDate_title.setVisibility(View.VISIBLE);
        addExpiringDate.setVisibility(View.GONE);
        expiringDate_EditText.requestFocus();

    }

    public void addNote(View view) {
        if (note.getVisibility() == View.GONE) {
            note.setVisibility(View.VISIBLE);
            //note_title.setVisibility(View.VISIBLE);
            addNote.setVisibility(View.GONE);
            note.requestFocus();
        }
        if (typeOfRecord_Spinner.getSelectedItemPosition() == 8) {
            title_EditText.requestFocus();
            note.setMinHeight(800);
        } else {
            note.setMinHeight(200);
        }
    }

    public void openCalendar(View view) {
        mediaPlayer.start();
        calendarBtn.startAnimation(animation3);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = month + "/" + dayOfMonth + "/" + year;
        if (licenceExpiringDate_EditText.isFocused()) {
            licenceExpiringDate_EditText.setText(date);
        } else if (expiringDate_EditText.isFocused()) {
            expiringDate_EditText.setText(date);
        } else if (issuanceDate_EditText.isFocused()) {
            issuanceDate_EditText.setText(date);
        }

    }

    public void showPass(View view) {
//        password.requestFocus();
//        password.setSelection(password.getText().length());
        if (showPass.getVisibility() == View.VISIBLE) {
            password_EditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            hidePass.setVisibility(View.VISIBLE);
            showPass.setVisibility(View.GONE);
            showPass.startAnimation(animation3);
        } else {
            password_EditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            hidePass.setVisibility(View.GONE);
            showPass.setVisibility(View.VISIBLE);
            hidePass.startAnimation(animation3);
        }
        password_EditText.requestFocus();
        password_EditText.setSelection(password_EditText.getText().length());

    }

    public void cancelWarningMessage(final View view) {
        mediaPlayer.start();
        cancelBtn.startAnimation(animation3);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.cancelation_request);
        alert.setMessage(R.string.cancelation_message);
        alert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Data Not saved", Toast.LENGTH_LONG).show();
                back(view);
            }
        });
        alert.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Not Canceled", Toast.LENGTH_LONG).show();
            }
        });
        alert.create().show();
    }

    @SuppressLint("RestrictedApi")
    public void editForm(View view) {
        mediaPlayer.start();
        category_Spinner.setEnabled(true);
        title_EditText.setEnabled(true);
        username_EditText.setEnabled(true);
        password_EditText.setEnabled(true);
        website_EditText.setEnabled(true);
        email_EditText.setEnabled(true);
        expiringDate_EditText.setEnabled(true);
        custom1_EditText.setEnabled(true);
        custom2_EditText.setEnabled(true);
        custom3_EditText.setEnabled(true);
        calendarBtn.setEnabled(true);
        note.setEnabled(true);
        saveBtn.setVisibility(View.VISIBLE);
        cancelBtn.setVisibility(View.VISIBLE);

        editForm.setVisibility(View.GONE);
    }

    public void back(View view) {
        finish(); //finishing current activity
//
//        //get EXTRA_FOLDER that we know where to navigate(which folder we want to return)
//        Bundle extras = getIntent().getExtras();
//        String folder;
//        if (extras != null) {
//            Log.d("back", "inside if ");
//
//            folder = extras.getString(EXTRA_FOLDER);
//            Intent intent = new Intent(this, RecordRecycler_Activity.class);
//            Log.d("back", "folder is :  " + folder);
//
//            intent.putExtra(EXTRA_FOLDER, folder);
//            this.startActivity(intent);
//
//        } else {       //just in case
//            Log.d("back", "inside else ");
//
//            Intent intent = new Intent(this, CategoryList_Activity.class);
//            this.startActivity(intent);
//        }
    }

    public void showCategory(View view) {
        mediaPlayer.start();
        if (showCategory.getVisibility() == View.VISIBLE) {
            category.setVisibility(View.VISIBLE);
            showCategory.setVisibility(View.GONE);
            typeOfRecord.setVisibility(View.GONE);
            showTypeOfRecord.setVisibility(View.VISIBLE);
        } else {
            category.setVisibility(View.GONE);
            showCategory.setVisibility(View.VISIBLE);
            typeOfRecord.setVisibility(View.VISIBLE);
            showTypeOfRecord.setVisibility(View.GONE);
        }
    }

    public void copyPass(View view) {
        mediaPlayer.start();
        copyPass.startAnimation(animation3);
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipboardManager.setText(password_EditText.getText().toString());
        Toast.makeText(this, "Password Copied", Toast.LENGTH_SHORT).show();

    }

    public void chooseIcon(View view) {
        //startActivity(new Intent(getApplicationContext(), ChooseIcon_PopupActivity.class));
        mediaPlayer.start();
        if (listOfIcons.getVisibility() == View.GONE)
            listOfIcons.setVisibility(View.VISIBLE);
        else
            listOfIcons.setVisibility(View.GONE);

    }

    public void changeIcon(View view) throws IllegalAccessException {

        chooseIcon.setBackground(view.getBackground());

        if (chooseIcon != null) {
            //            Log.d("icon", "view.getBackground().getConstantState(): " + view.getBackground().getConstantState());
            for (Drawable drawables : drawableResources) {

                //if view's background found in Drawables Resources
                if (view.getBackground() != null &&
                        view.getBackground().getConstantState()
                                .equals(drawables.getCurrent().getConstantState())) {

                    Log.d("icon", "found(.getConstantState()): " + drawables.getCurrent().getConstantState());

                    //get the needed drawable id
                    new getDrawableIDAsyncTask(drawables.getCurrent(), drawableResources).execute(); //Initializing 'drawableID' inside doInBackGround() method.
                    //getDrawableIDAsyncTask was:
                    //drawabaleID = getDrawabaleID(drawables.getCurrent(), drawableResources);

                }
            }
        }

        mediaPlayer.start();
        listOfIcons.setVisibility(View.GONE);
        //view.startAnimation(animation3);
    }


    public void addChooseIcon(View view) {
        addChooseIconBtn.setVisibility(View.GONE);
        chooseIcon.setVisibility(View.VISIBLE);
        chooseIcon(null);
        new getAllDrawablesResourcesAsyncTask().execute(); //inserts all drawables to ArrayList

    }


    //Add to favorites. WORKS
    public void addToFavorites(View view) {
        if (starBtn.getVisibility() == View.VISIBLE) {
            starBtn.setVisibility(View.GONE);
            starFullBtn.setVisibility(View.VISIBLE);
            isFavorite();
        } else {
            starBtn.setVisibility(View.VISIBLE);
            starFullBtn.setVisibility(View.GONE);
        }
    }

    public void isFavorite() {
        isFavorite = true;
    }


    public void checkPassword(String password) {
        //check if there's minimum 8 characters.

        // check if at least 1 capital letters

        //check if at least 4 numbers

        //check if there's space

        //check if there are "other" characters - like: * , # , < , % ,  etc.

        //calculate "points" and shows current strength level


    }


    public class getAllDrawablesResourcesAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //inserts all the drawables in the 'drawable' folder into ArrayList
            for (Field field : allDrawablesfromRes_drawable) {

                //Add drawable's ConstantState to Arraylist
                try {
                    drawableResources.add(getResources().getDrawable(field.getInt(null))); //returns:  android.graphics.drawable.GradientDrawable@3189a46 (for example)
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

//            Log.d("icon", "field.getName(): "+ field.getName());

            }
            return null;
        }
    }

    public class getDrawableIDAsyncTask extends AsyncTask<Void, Void, Integer> {
        private Drawable drawable;
        private ArrayList<Drawable> drawableResources;
        int id;

        //Constructor
        private getDrawableIDAsyncTask(Drawable drawable, ArrayList<Drawable> drawableResources) {
            this.drawable = drawable; // view.getBackground().getConstantState  from "changeIcon" method.
            this.drawableResources = drawableResources; //initialized in addChooseIcon
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            for (Drawable currentDrawable : drawableResources) {

                //if the drawable we passed here exists to a drawable from the ArrayList(that includes all the drawables in the app)
                //then get it's ID in order to save it in the database.
                if (drawable.getConstantState().equals(currentDrawable.getConstantState())) {

                    //for loop to get the proper drawable's id
                    for (Field field : allDrawablesfromRes_drawable) {
                        //   Log.d("icon", "currentDrawable.getConstantState(): " + currentDrawable.getConstantState()); //TO DELETE
                        Drawable d = null;
                        try {
                            d = getResources().getDrawable(field.getInt(null));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        // Log.d("icon", "d.getConstantState(): " + d.getConstantState()); //TO DELETE
                        int id;
                        if ((currentDrawable.getConstantState().equals(d.getConstantState()))) {
                            try {
                                id = field.getInt(null);
                                drawabaleID = id;
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }

            return 0;
        }
    }


    public void getDrawabalesFields() throws IllegalAccessException { //moved to AsyncTask
        for (Field field : allDrawablesfromRes_drawable) {
            //Add drawable's ConstantState to Arraylist
            drawableResources.add(getResources().getDrawable(field.getInt(null))); //returns:  android.graphics.drawable.GradientDrawable@3189a46 (for example)

//            Log.d("icon", "field.getName(): "+ field.getName());

        }

    }

}



