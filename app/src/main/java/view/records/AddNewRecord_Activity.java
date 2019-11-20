package view.records;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.record.Record;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;

import cryptography.Cryptography;
import local_database.DatabaseClient;
import view_model.records.Record_ViewModel;

import static view.records.RecordRecycler_Activity.EXTRA_ORIGIN;

public class AddNewRecord_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, Serializable {
    private Cryptography cryptography;

    private String encryptedTitle, encryptedUsername, encryptedPassword, encryptedBankAccount, encryptedAccountNumber, encryptedIBAN, encryptedBankNumber, encryptedBankAddress, encryptedCardNumber, encryptedCVV, encryptedCardExpireDate, encryptedCardExpireMonth, encryptedCardExpireYear, encryptedWebsite, encryptedEmail, encryptedWalletGenerationSeed, encryptedPrivateKey, encryptedPublicKey, encryptedLicenceNumber, encryptedLicenceExpireDate, encryptedPassportNumber, encryptedIssuanceDate, encryptedIssuancePlace,encryptedNote,encryptedExpireDateNote,encryptedTagsNote;
    private String CRYPTO_KEY;
    private String nameOfFolder;
    private String userNameRecord;


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
    private EditText custom1_EditText, custom2_EditText, custom3_EditText, note_EditText,expireDateNote_EditText,tagsNote_EditText;
    //////////////////////////////////////////////////////////////////////////////////

    private static int i = 1;
    private String folder, type, origin;
    private TextView custom1_EditText_title, custom2_EditText_title, custom3_EditText_title, note_title;
    private TextView licenceExpiringDate_EditText;
    private TextView issuanceDate_EditText;
    private TextView expiringDate_EditText, tagNames_EditText;
    private TextView expiringDate_title;
    private ImageButton calendarBtn;
    private ImageView logo;
    private Button addFields, addNote, addExpiringDate;
    private ImageButton saveBtn, cancelBtn;
    private ImageButton showPass, hidePass, copyPass, showCategory, showTypeOfRecord, linkWebsite;
    private MediaPlayer mediaPlayer;
    private Animation animation1, animation2, animation3;
    private ScrollView scrollView;
    private FloatingActionButton editForm;
    private TextView activityTitle;
    private Typeface myFont;
    private TextView folder_name;

    private Spinner category_Spinner, typeOfRecord_Spinner;
    // for simplisity and exampling:
    private EditText category_EditText, title_EditText, username_EditText, password_EditText, website_EditText,
            email_EditText, accountNumber_EditText, IBAN_EditText, bankNumber_EditText, bankAddress_EditText,
            cardNumber_EditText, cvv_EditText, cardExpiringMonth_EditText, cardExpiringYear_EditText, publicKey_EditText,
            privateKey_EditText, walletGenerationSeed_EditText, licenceNumber_EditText, issuancePlace_EditText, passportNumber_EditText;
    private TextView licenceExpiringDate_title;
    //String outputString;

    private LinearLayout category, typeOfRecord;
    private LinearLayout registerDetails;
    private LinearLayout userName, password, website, email, bankAccount, creditCard, cryptocurrency, drivingLicence, passport;
    private HorizontalScrollView listOfIcons;
    private ProgressBar progressBar;


    private boolean isFavorite = false; //as default, a record is not a favorite.

    Field[] allDrawablesfromRes_drawable = com.securevault19.securevault2019.R.drawable.class.getFields();
    ArrayList<Drawable> drawableResources = new ArrayList<>();
    //ArrayList<Integer> drawableResourcesIDs = new ArrayList<>();
    int drawabaleID; //Default Icon(Secure Vault Black)
    String drawableName;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle saveBtndInstanceState) {
        super.onCreate(saveBtndInstanceState);
        setContentView(R.layout.activity_add_new_record);
        CRYPTO_KEY = getIntent().getStringExtra("CRYPTO_KEY");
        nameOfFolder = getIntent().getStringExtra(EXTRA_FOLDER);
        //Toast.makeText(getApplicationContext(), "folder Name clicked " + nameOfFolder, Toast.LENGTH_SHORT).show();
        Log.d("userCheck", "---" + CRYPTO_KEY);

        //userNameRecord = getIntent().getStringExtra("userNameRecord");
        //Log.d("userNameRecord", "the userNameRecord passed => " + userNameRecord);

        cryptography = new Cryptography();
        // getting the object ( Record ) from RecordRecycler_activiry line 326
        Intent i = getIntent();
        Record record = (Record) i.getSerializableExtra("recordClassDB");

        progressBar = findViewById(R.id.progressBar);
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
        tagNames_EditText = findViewById(R.id.tagNames_EditText);
        issuanceDate_EditText = findViewById(R.id.issuanceDate_EditText);
        expiringDate_EditText = findViewById(R.id.expiringDate_EditText);
        expiringDate_title = findViewById(R.id.expiringDate_title);
        calendarBtn = findViewById(R.id.calendarBtn);
        addExpiringDate = findViewById(R.id.addExpiringDateBtn);
        linkWebsite = findViewById(R.id.openLink);
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
        note_EditText = findViewById(R.id.note_editText);
        expireDateNote_EditText = findViewById(R.id.expiringDate_EditText);
        category_Spinner = findViewById(R.id.category_Spinner); //Folder
        typeOfRecord_Spinner = findViewById(R.id.typeOfRecord_Spinner);
        title_EditText = findViewById(R.id.title_EditText);
        username_EditText = findViewById(R.id.username_EditText);
        password_EditText = findViewById(R.id.password_EditText);
        website_EditText = findViewById(R.id.website_EditText);
        //website_EditText.setText("https://");
        email_EditText = findViewById(R.id.email_EditText);
        accountNumber_EditText = findViewById(R.id.accountNumber_EditText);
        IBAN_EditText = findViewById(R.id.iban_EditText);
        bankNumber_EditText = findViewById(R.id.bankNumber_EditText);
        bankAddress_EditText = findViewById(R.id.bankAddress_EditText);
        cardNumber_EditText = findViewById(R.id.cardNumber_EditText);
        cvv_EditText = findViewById(R.id.cvv_EditText);
        cardExpiringMonth_EditText = findViewById(R.id.cardExpiringMonth_EditText);
        cardExpiringYear_EditText = findViewById(R.id.cardExpiringYear_EditText);
        publicKey_EditText = findViewById(R.id.publicKey_EditText);
        privateKey_EditText = findViewById(R.id.privateKey_EditText);
        walletGenerationSeed_EditText = findViewById(R.id.walletGenerationSeed_EditText);
        licenceNumber_EditText = findViewById(R.id.licenceNumber_EditText);
        passportNumber_EditText = findViewById(R.id.passportNumber_EditText);
        issuancePlace_EditText = findViewById(R.id.issuancePlace_EditText);
        licenceExpiringDate_title = findViewById(R.id.licenceExpiringDate_title);


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
        folder_name = findViewById(R.id.folder_name);

        //Animation Sets
        animation1 = AnimationUtils.loadAnimation(AddNewRecord_Activity.this, R.anim.zoomin);
        animation2 = AnimationUtils.loadAnimation(AddNewRecord_Activity.this, R.anim.zoomin_fade);
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

        new getAllDrawablesResourcesAsyncTask().execute();


        //Getting all the EXTRAS from all the 'intent.puExtra()'s
        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            //Check where we came from:  (Recycler).onRecordClick  OR  (Recycler).buttonAddRecord
            origin = extras.getString(EXTRA_ORIGIN);
            folder = extras.getString(EXTRA_FOLDER);            // name of the folder
            type = extras.getString(EXTRA_TYPE);            // name of the type ( record )
            folder_name.setText(folder);
            switch (origin) {
                case "onRecordClick":           // clicked from recycler view
                    if (folder != null) {
                        if (type != null) {
                            fieldsVisibility(type);  //shows the relevant fields of the clicked Record.
                            findViewById(R.id.deleteTopBtn).setVisibility(View.VISIBLE);
                            switch (type) {
                                case "Bank Accounts":
                                    typeOfRecord_Spinner.setSelection(3);
                                    category_Spinner.setSelection(3);
                                    //addNote(addNote);
                                    //addExpiringDate(addExpiringDate);
                                    //addFields(addFields);

                                    DisplayRecordDetails(record);
//
//                                    try {
//                                        title_EditText.setText(cryptography.decrypt(record.getTitle(), user));
//                                        username_EditText.setText(cryptography.decrypt(record.getUserName(), user));
//                                        password_EditText.setText(cryptography.decrypt(record.getPassword(), user));
//                                        accountNumber_EditText.setText(cryptography.decrypt(record.getAccountNumber(), user));
//                                        IBAN_EditText.setText(cryptography.decrypt(record.getIBAN(), user));
//                                        bankNumber_EditText.setText(cryptography.decrypt(record.getBankNumber(), user));
//                                        bankAddress_EditText.setText(cryptography.decrypt(record.getAddress(),user));
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
                                    break;
                                case "Credit Cards":
                                    typeOfRecord_Spinner.setSelection(4);
                                    category_Spinner.setSelection(4);
                                    DisplayRecordDetails(record);
//                                    try {
//                                        title_EditText.setText(cryptography.decrypt(record.getTitle(), user));
//                                        username_EditText.setText(cryptography.decrypt(record.getUserName(), user));
//                                        password_EditText.setText(cryptography.decrypt(record.getPassword(), user));
//                                        cardNumber_EditText.setText(cryptography.decrypt(record.getCardNumber(), user));
//                                        cvv_EditText.setText(cryptography.decrypt(record.getCVV(),user));
//                                        cardExpiringMonth_EditText.setText(cryptography.decrypt(record.getExpireMonth(),user));
//                                        cardExpiringYear_EditText.setText(cryptography.decrypt(record.getExpireYear(),user));
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }

                                    break;
                                case "Social Media":                // social media && website & email && online shopping all the same
                                    typeOfRecord_Spinner.setSelection(1);
                                    category_Spinner.setSelection(1);
                                    DisplayRecordDetails(record);
//                                    try{
//                                        title_EditText.setText(cryptography.decrypt(record.getTitle(), user));
//                                        username_EditText.setText(cryptography.decrypt(record.getUserName(), user));
//                                        password_EditText.setText(cryptography.decrypt(record.getPassword(), user));
//                                        website_EditText.setText(cryptography.decrypt(record.getWebsite(), user));
//                                        email_EditText.setText(cryptography.decrypt(record.getEmail(),user));
//                                    }catch(Exception e) {e.printStackTrace();}
                                    break;
                                case "Website & Email":             // social media && website & email && online shopping all the same
                                    typeOfRecord_Spinner.setSelection(0);
                                    category_Spinner.setSelection(0);
                                    DisplayRecordDetails(record);
//                                    try {
//                                        title_EditText.setText(cryptography.decrypt(record.getTitle(), user));
//                                        username_EditText.setText(cryptography.decrypt(record.getUserName(), user));
//                                        password_EditText.setText(cryptography.decrypt(record.getPassword(), user));
//                                        website_EditText.setText(cryptography.decrypt(record.getWebsite(), user));
//                                        email_EditText.setText(cryptography.decrypt(record.getEmail(),user));
//                                    }catch(Exception e){e.printStackTrace();}
                                    break;
                                case "Online Shopping":             // social media && website & email && online shopping all the same
                                    typeOfRecord_Spinner.setSelection(2);
                                    category_Spinner.setSelection(2);
                                    DisplayRecordDetails(record);
//                                    try {
//                                        title_EditText.setText(cryptography.decrypt(record.getTitle(), user));
//                                        username_EditText.setText(cryptography.decrypt(record.getUserName(), user));
//                                        password_EditText.setText(cryptography.decrypt(record.getPassword(), user));
//                                        website_EditText.setText(cryptography.decrypt(record.getWebsite(), user));
//                                        email_EditText.setText(cryptography.decrypt(record.getEmail(),user));
//                                    }catch(Exception e){e.printStackTrace();}
                                    break;
                                case "Cryptocurrency":
                                    typeOfRecord_Spinner.setSelection(6);
                                    category_Spinner.setSelection(6);
                                    DisplayRecordDetails(record);
//                                    try{
//                                        title_EditText.setText(cryptography.decrypt(record.getTitle(), user));
//                                        publicKey_EditText.setText(cryptography.decrypt(record.getPublicKey(),user));
//                                        privateKey_EditText.setText(cryptography.decrypt(record.getPrivateKey(),user));;
//                                        walletGenerationSeed_EditText.setText(cryptography.decrypt(record.getWalletGenerationSeed(),user));
//
//
//                                    }catch(Exception e ){e.printStackTrace();}
                                    break;
                                case "Driving Licence":
                                    typeOfRecord_Spinner.setSelection(7);
                                    category_Spinner.setSelection(7);
                                    DisplayRecordDetails(record);
//                                    try{
//                                        title_EditText.setText(cryptography.decrypt(record.getTitle(), user));
//                                        licenceNumber_EditText.setText(cryptography.decrypt(record.getLicenceNumber(), user));
//                                        licenceExpiringDate_EditText.setText(cryptography.decrypt(record.getExpiringDate(),user));
//
//                                    }catch(Exception e){e.printStackTrace();}
                                    break;
                                case "Passports":
                                    typeOfRecord_Spinner.setSelection(5);
                                    category_Spinner.setSelection(5);
                                    DisplayRecordDetails(record);
//                                    try{
//                                        title_EditText.setText(cryptography.decrypt(record.getTitle(), user));
//                                        passportNumber_EditText.setText(cryptography.decrypt(record.getPassportNumber(),user));
//                                        issuanceDate_EditText.setText(cryptography.decrypt(record.getIssuanceDate(),user));
//                                        issuancePlace_EditText.setText(cryptography.decrypt(record.getIssuancePlace(),user));
//
//                                    }catch(Exception e){e.printStackTrace();}
                                    break;

                                default:  //FOR EXAMPLE
                                    typeOfRecord_Spinner.setSelection(8);
                                    category_Spinner.setSelection(8);
                                    DisplayRecordDetails(record);
                                    break;
                            }
                        }
                    }

                    break; //case: onRecordClick

                //If we came from buttonAddRecord
                default:
// ---------------------------------------------------------------------------- //
// for testing,


                    // looks on which folder we clicked and set the spinner in the correct position.
                    fieldsVisibility(nameOfFolder);
                    if (nameOfFolder.equals("Website & Email")) {
                        typeOfRecord_Spinner.setSelection(0);
                        category_Spinner.setSelection(0);
                    } else if (nameOfFolder.equals("Social Media")) {
                        typeOfRecord_Spinner.setSelection(1);
                        category_Spinner.setSelection(1);
                    } else if (nameOfFolder.equals("Online Shopping")) {
                        typeOfRecord_Spinner.setSelection(2);
                        category_Spinner.setSelection(2);
                    } else if (nameOfFolder.equals("Bank Accounts")) {
                        typeOfRecord_Spinner.setSelection(3);
                        category_Spinner.setSelection(3);
                    } else if (nameOfFolder.equals("Credit Cards")) {
                        typeOfRecord_Spinner.setSelection(4);
                        category_Spinner.setSelection(4);
                    } else if (nameOfFolder.equals("Passports")) {
                        typeOfRecord_Spinner.setSelection(5);
                        category_Spinner.setSelection(5);
                    } else if (nameOfFolder.equals("Cryptocurrency")) {
                        typeOfRecord_Spinner.setSelection(6);
                        category_Spinner.setSelection(6);
                    } else if (nameOfFolder.equals("Driving Licence")) {
                        typeOfRecord_Spinner.setSelection(7);
                        category_Spinner.setSelection(7);
                    } else {
                        typeOfRecord_Spinner.setSelection(8);
                        category_Spinner.setSelection(8);
                    }


// ---------------------------------------------------------------------------- //
                    //Show fields after selecting item in spinner
                    typeOfRecord_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Log.e("checkPosition", "entered onItemSElected");
                            //(0)Website/Email Account, (1)Social Media, (2)Online Shopping
                            if (position == 0 || position == 1 || position == 2) {
                                Log.e("checkPosition", "entered to onItemSelected " + position);
                                fieldsVisibility("Social Media");
                            }
                            //(3)Bank Accounts
                            if (position == 3) {
                                Log.e("checkPosition", "entered to onItemSelected " + position);
                                fieldsVisibility("Bank Accounts");
                            }
                            //(4)Credit Card
                            if (position == 4) {
                                Log.e("checkPosition", "entered to onItemSelected " + position);
                                fieldsVisibility("Credit Cards");
                            }
                            //(5)Passports
                            if (position == 5) {
                                Log.e("checkPosition", "entered to onItemSelected " + position);
                                fieldsVisibility("Passports");
                            }
                            //(6)Cryptocurrency
                            if (position == 6) {
                                Log.e("checkPosition", "entered to onItemSelected " + position);
                                fieldsVisibility("Cryptocurrency");
                            }
                            //(7)Driving Licence
                            if (position == 7) {
                                Log.e("checkPosition", "entered to onItemSelected " + position);
                                fieldsVisibility("Driving Licence");
                            }
                            //(8)NOTES
                            if (position == 8) {
                                Log.e("checkPosition", "entered to onItemSelected " + position);
                                fieldsVisibility("Notes");
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
            }
        }


        password_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordCalculation();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordCalculation();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                passwordCalculation();
            }
        });
    }

    public void fieldsVisibility(String type) {
        switch (type) {
            case "Bank Accounts": //shows the relevant fields of the clicked Record.
                userName.setVisibility(View.VISIBLE);
                password.setVisibility(View.VISIBLE);
                website.setVisibility(View.GONE);
                email.setVisibility(View.GONE);
                bankAccount.setVisibility(View.VISIBLE);
                creditCard.setVisibility(View.GONE);
                cryptocurrency.setVisibility(View.GONE);
                drivingLicence.setVisibility(View.GONE);
                passport.setVisibility(View.GONE);
                break;
            case "Credit Cards":
                userName.setVisibility(View.GONE);
                password.setVisibility(View.VISIBLE);
                website.setVisibility(View.GONE);
                email.setVisibility(View.GONE);
                bankAccount.setVisibility(View.GONE);
                creditCard.setVisibility(View.VISIBLE);
                cryptocurrency.setVisibility(View.GONE);
                drivingLicence.setVisibility(View.GONE);
                passport.setVisibility(View.GONE);
                break;
            case "Social Media":
                userName.setVisibility(View.VISIBLE);
                password.setVisibility(View.VISIBLE);
                website.setVisibility(View.VISIBLE);
                email.setVisibility(View.VISIBLE);
                bankAccount.setVisibility(View.GONE);
                creditCard.setVisibility(View.GONE);
                cryptocurrency.setVisibility(View.GONE);
                drivingLicence.setVisibility(View.GONE);
                passport.setVisibility(View.GONE);
                break;
            case "Website & Email":                                         // for simplicity of the code maybe merge with social media * website and online Shopping. they all the same
                userName.setVisibility(View.VISIBLE);
                password.setVisibility(View.VISIBLE);
                website.setVisibility(View.VISIBLE);
                email.setVisibility(View.VISIBLE);
                bankAccount.setVisibility(View.GONE);
                creditCard.setVisibility(View.GONE);
                cryptocurrency.setVisibility(View.GONE);
                drivingLicence.setVisibility(View.GONE);
                passport.setVisibility(View.GONE);
                break;
            case "Online Shopping":
                userName.setVisibility(View.VISIBLE);
                password.setVisibility(View.VISIBLE);
                website.setVisibility(View.VISIBLE);
                email.setVisibility(View.VISIBLE);
                bankAccount.setVisibility(View.GONE);
                creditCard.setVisibility(View.GONE);
                cryptocurrency.setVisibility(View.GONE);
                drivingLicence.setVisibility(View.GONE);
                passport.setVisibility(View.GONE);
                break;
            case "Cryptocurrency":
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
            case "Driving Licence":
                userName.setVisibility(View.GONE);
                password.setVisibility(View.GONE);
                website.setVisibility(View.GONE);
                email.setVisibility(View.GONE);
                bankAccount.setVisibility(View.GONE);
                creditCard.setVisibility(View.GONE);
                cryptocurrency.setVisibility(View.GONE);
                drivingLicence.setVisibility(View.VISIBLE);
                passport.setVisibility(View.GONE);
                break;
            case "Passports":
                userName.setVisibility(View.GONE);
                password.setVisibility(View.GONE);
                website.setVisibility(View.GONE);
                email.setVisibility(View.GONE);
                bankAccount.setVisibility(View.GONE);
                creditCard.setVisibility(View.GONE);
                cryptocurrency.setVisibility(View.GONE);
                drivingLicence.setVisibility(View.GONE);
                passport.setVisibility(View.VISIBLE);
                break;
            case "Notes":
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
                break;
        }
    }

    void DisplayRecordDetails(Record record) {

        try {
            title_EditText.setText(cryptography.decrypt(record.getTitle(), CRYPTO_KEY));
            activityTitle.setText(title_EditText.getText().toString());
            password_EditText.setText(cryptography.decrypt(record.getPassword(), CRYPTO_KEY));
            email_EditText.setText(cryptography.decrypt(record.getEmail(), CRYPTO_KEY));
            website_EditText.setText(cryptography.decrypt(record.getWebsite(), CRYPTO_KEY));
            cardExpiringMonth_EditText.setText(cryptography.decrypt(record.getExpireMonth(), CRYPTO_KEY));
            username_EditText.setText(cryptography.decrypt(record.getUserName(), CRYPTO_KEY));
            accountNumber_EditText.setText(cryptography.decrypt(record.getAccountNumber(), CRYPTO_KEY));
            IBAN_EditText.setText(cryptography.decrypt(record.getIBAN(), CRYPTO_KEY));
            bankNumber_EditText.setText(cryptography.decrypt(record.getBankNumber(), CRYPTO_KEY));
            bankAddress_EditText.setText(cryptography.decrypt(record.getAddress(), CRYPTO_KEY));
            cardNumber_EditText.setText(cryptography.decrypt(record.getCardNumber(), CRYPTO_KEY));
            cvv_EditText.setText(cryptography.decrypt(record.getCVV(), CRYPTO_KEY));
            cardExpiringMonth_EditText.setText(cryptography.decrypt(record.getExpireMonth(), CRYPTO_KEY));
            cardExpiringYear_EditText.setText(cryptography.decrypt(record.getExpireYear(), CRYPTO_KEY));
            publicKey_EditText.setText(cryptography.decrypt(record.getPublicKey(), CRYPTO_KEY));
            privateKey_EditText.setText(cryptography.decrypt(record.getPrivateKey(), CRYPTO_KEY));
            walletGenerationSeed_EditText.setText(cryptography.decrypt(record.getWalletGenerationSeed(), CRYPTO_KEY));
            passportNumber_EditText.setText(cryptography.decrypt(record.getPassportNumber(), CRYPTO_KEY));
            issuanceDate_EditText.setText(cryptography.decrypt(record.getIssuanceDate(), CRYPTO_KEY));
            issuancePlace_EditText.setText(cryptography.decrypt(record.getIssuancePlace(), CRYPTO_KEY));
            licenceNumber_EditText.setText(cryptography.decrypt(record.getLicenceNumber(), CRYPTO_KEY));
            licenceExpiringDate_EditText.setText(cryptography.decrypt(record.getExpiringDate(), CRYPTO_KEY));
            note_EditText.setText(cryptography.decrypt(record.getNote(),CRYPTO_KEY));
            if(!cryptography.decrypt(record.getNote(),CRYPTO_KEY).equals("")){
                addNote(addNote);
            }
            expireDateNote_EditText.setText(cryptography.decrypt(record.getExpitingDateNote(),CRYPTO_KEY));
            if(!cryptography.decrypt(record.getExpitingDateNote(),CRYPTO_KEY).equals("")){
                addExpiringDate(addExpiringDate);
            }
            addChooseIcon(addChooseIconBtn);
            tagNames_EditText.setText(cryptography.decrypt(record.getTagsNote(),CRYPTO_KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onBackPressed() {
        cancelWarningMessage(null);
    }


    public void openMenu(View view) {
    }


    @SuppressLint({"RestrictedApi", "StaticFieldLeak"})
    public void openNewRecord(View view) {                     // the onClick func (save Button)
        //cryptography = new Cryptography();                   // making argument of Cryptography which is private!
        //Setting the details from the Activity to send to the Record constructor
        final String typeOfRecord = typeOfRecord_Spinner.getSelectedItem().toString();
        final String folder = category_Spinner.getSelectedItem().toString();
        final String title = title_EditText.getText().toString().trim();
        final String username = username_EditText.getText().toString().trim();
        final String password = password_EditText.getText().toString().trim();
        final String accountNumber = accountNumber_EditText.getText().toString().trim();
        final String IBAN = IBAN_EditText.getText().toString().trim();
        final String bankNumber = bankNumber_EditText.getText().toString().trim();
        final String bankAddress = bankAddress_EditText.getText().toString().trim();
        final String cardNumber = cardNumber_EditText.getText().toString().trim();
        final String cvv = cvv_EditText.getText().toString().trim();
        final String cardExpiringMonth = cardExpiringMonth_EditText.getText().toString().trim();
        final String cardExpiringYear = cardExpiringYear_EditText.getText().toString().trim();
        final String website = website_EditText.getText().toString().trim();
        final String email = email_EditText.getText().toString().trim();
        final String publicKey = publicKey_EditText.getText().toString().trim();
        final String privateKey = privateKey_EditText.getText().toString().trim();
        final String walletGenerationSeed = walletGenerationSeed_EditText.getText().toString().trim();
        final String licenceNumber = licenceNumber_EditText.getText().toString().trim();
        final String licenceExpiringDate = licenceExpiringDate_EditText.getText().toString().trim();
        final String passportNumber = passportNumber_EditText.getText().toString().trim();
        final String issuanceDate = issuanceDate_EditText.getText().toString().trim();
        final String issuancePlace = issuancePlace_EditText.getText().toString().trim();
        final String note = note_EditText.getText().toString().trim();
        final String expireDateNote = expireDateNote_EditText.getText().toString();
        final String tagsNote = tagNames_EditText.getText().toString();



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
                    //try for Encryption
                protected Void doInBackground(Void... voids) {
                    try {
                        // encrypthing the password and the username(username for test)
                        //encryptedUsername = cryptography.encrypt(username_EditText.getText().toString());
                        encryptedTitle = cryptography.encryptWithKey(CRYPTO_KEY, title);
                        encryptedUsername = cryptography.encryptWithKey(CRYPTO_KEY, username);
                        encryptedPassword = cryptography.encryptWithKey(CRYPTO_KEY, password);
                        encryptedAccountNumber = cryptography.encryptWithKey(CRYPTO_KEY, accountNumber);
                        encryptedIBAN = cryptography.encryptWithKey(CRYPTO_KEY, IBAN);
                        encryptedBankNumber = cryptography.encryptWithKey(CRYPTO_KEY, bankNumber);
                        encryptedBankAddress = cryptography.encryptWithKey(CRYPTO_KEY, bankAddress);
                        encryptedCardNumber = cryptography.encryptWithKey(CRYPTO_KEY, cardNumber);
                        encryptedCVV = cryptography.encryptWithKey(CRYPTO_KEY, cvv);
                        encryptedCardExpireMonth = cryptography.encryptWithKey(CRYPTO_KEY, cardExpiringMonth);
                        encryptedCardExpireYear = cryptography.encryptWithKey(CRYPTO_KEY, cardExpiringYear);
                        encryptedWebsite = cryptography.encryptWithKey(CRYPTO_KEY, website);
                        encryptedEmail = cryptography.encryptWithKey(CRYPTO_KEY, email);
                        encryptedPublicKey = cryptography.encryptWithKey(CRYPTO_KEY, publicKey);
                        encryptedPrivateKey = cryptography.encryptWithKey(CRYPTO_KEY, privateKey);
                        encryptedWalletGenerationSeed = cryptography.encryptWithKey(CRYPTO_KEY, walletGenerationSeed);
                        encryptedLicenceNumber = cryptography.encryptWithKey(CRYPTO_KEY, licenceNumber);
                        encryptedLicenceExpireDate = cryptography.encryptWithKey(CRYPTO_KEY, licenceExpiringDate);
                        encryptedPassportNumber = cryptography.encryptWithKey(CRYPTO_KEY, passportNumber);
                        encryptedIssuanceDate = cryptography.encryptWithKey(CRYPTO_KEY, issuanceDate);
                        encryptedIssuancePlace = cryptography.encryptWithKey(CRYPTO_KEY, issuancePlace);
                        encryptedNote = cryptography.encryptWithKey(CRYPTO_KEY,note);
                        encryptedExpireDateNote = cryptography.encryptWithKey(CRYPTO_KEY,expireDateNote);
                        encryptedTagsNote = cryptography.encryptWithKey(CRYPTO_KEY,tagsNote);

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
                    record.setTitle(encryptedTitle);
                    record.setUserName(encryptedUsername);
                    record.setPassword(encryptedPassword);
                    record.setAccountNumber(encryptedAccountNumber);
                    record.setIBAN(encryptedIBAN);
                    record.setBankNumber(encryptedBankNumber);
                    record.setAddress(encryptedBankAddress);
                    record.setCardNumber(encryptedCardNumber);
                    record.setCVV(encryptedCVV);
                    //record.setExpiringDate(encryptedCardExpireDate);
                    record.setExpireMonth(encryptedCardExpireMonth);
                    record.setExpireYear(encryptedCardExpireYear);
                    record.setWebsite(encryptedWebsite);
                    record.setEmail(encryptedEmail);
                    record.setPublicKey(encryptedPublicKey);
                    record.setPrivateKey(encryptedPrivateKey);
                    record.setWalletGenerationSeed(encryptedWalletGenerationSeed);
                    record.setLicenceNumber(encryptedLicenceNumber);
                    record.setExpiringDate(encryptedLicenceExpireDate);
                    record.setPassportNumber(encryptedPassportNumber);
                    record.setIssuanceDate(encryptedIssuanceDate);
                    record.setIssuancePlace(encryptedIssuancePlace);
                    record.setNote(encryptedNote);
                    record.setExpitingDateNote(encryptedExpireDateNote);
                    record.setTagsNote(encryptedTagsNote);
                    record.setFavorite(isFavorite);
                    record.setIcon(drawableName);


                    // inserting record to DB
                    if (origin.equals("onRecordClick")){
                        DatabaseClient.getInstance(getApplicationContext()).getRecordDatabase2()
                                .daoRecord()
                                .update(record);
                     //   Toast.makeText(getApplicationContext(), "update", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        DatabaseClient.getInstance(getApplicationContext()).getRecordDatabase2()
                                .daoRecord()
                                .insert(record);
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
// after the save button is clicked and finished all his jobs, before exiting completely,
// finishing the activity and open 'new' (refreshed ) RecyclerView with Website Category

                    finish();
                    overridePendingTransition(0, 0);
                    //Intent intent = new Intent(getApplicationContext(), RecordRecycler_Activity.class);
                    //intent.putExtra(EXTRA_FOLDER, folder);
                    //////////intent.putExtra("CRYPTO_KEY", user);
                    //startActivity(intent);
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
        //Set all fields not focusable
        typeOfRecord_Spinner.setFocusable(false);
        category_Spinner.setFocusable(false);
        title_EditText.setFocusable(false);
        username_EditText.setFocusable(false);
        password_EditText.setFocusable(false);
        website_EditText.setFocusable(false);
        email_EditText.setFocusable(false);
        expiringDate_EditText.setFocusable(false);
        custom1_EditText.setFocusable(false);
        custom2_EditText.setFocusable(false);
        custom3_EditText.setFocusable(false);
        calendarBtn.setFocusable(false);
        note_EditText.setFocusable(false);
        tagNames_EditText.setFocusable(false);
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
        if (note_EditText.getVisibility() == View.GONE) {
            note_EditText.setVisibility(View.VISIBLE);
            //note_title.setVisibility(View.VISIBLE);
            addNote.setVisibility(View.GONE);
            note_EditText.requestFocus();
        }
        if (typeOfRecord_Spinner.getSelectedItemPosition() == 8) {
            title_EditText.requestFocus();
            note_EditText.setMinHeight(800);
        } else {
            note_EditText.setMinHeight(200);
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
        if (view == showPass || view == hidePass){

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
        note_EditText.setEnabled(true);
        saveBtn.setVisibility(View.VISIBLE);
        cancelBtn.setVisibility(View.VISIBLE);
        editForm.setVisibility(View.GONE);
    }

    public void back(View view) {
        finish();
        overridePendingTransition(0, 0);
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
//
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
//        new getAllDrawablesResourcesAsyncTask().execute(); //inserts all drawables to ArrayList

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

    public void openWebsiteLink(View view) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("http://" + website_EditText.getText().toString()));
        startActivity(i);
    }

    public void deleteRecord(View view) {

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
                if (field.getName().equals("logo_black")) {  //Initializing "logo_black" as default icon for a every Record
                    try {
                        Log.d("icon", "field.getName(): " + field.getName() + " field.getInt(): " + field.getInt(null));
                        drawabaleID = field.getInt(null);
                        drawableName = field.getName();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    Log.d("icon", "drawabaleID: " + drawabaleID);
                }
            Log.d("icon", "drawableName: "+ drawableName);

            }
            return null;
        }
    }

    public class getDrawableIDAsyncTask extends AsyncTask<Void, Void, String> {
        private Drawable drawable;
        private ArrayList<Drawable> drawableResources;

        //Constructor
        private getDrawableIDAsyncTask(Drawable drawable, ArrayList<Drawable> drawableResources) {
            this.drawable = drawable; // view.getBackground().getConstantState  from "changeIcon" method.
            this.drawableResources = drawableResources; //initialized in addChooseIcon
        }

        @Override
        protected String doInBackground(Void... voids) {
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
//                            try {
//                                id = field.getInt(null);
//                                drawabaleID = id;
                            Log.d("icon", "getName().....: " + field.getName());
                            drawableName = field.getName();
//                            } catch (IllegalAccessException e) {
//                                e.printStackTrace();
//                            }

//                        }
                        }
                    }
                }

            }
            return drawableName;

        }

    }




    protected void passwordCalculation() {
        String temp = password_EditText.getText().toString();
        System.out.println(i + " current password is : " + temp);
        i = i + 1;

        int length = 0, uppercase = 0, lowercase = 0, digits = 0, symbols = 0, bonus = 0, requirements = 0;

        int lettersonly = 0, numbersonly = 0, cuc = 0, clc = 0;

        length = temp.length();
        for (int i = 0; i < temp.length(); i++) {
            if (Character.isUpperCase(temp.charAt(i)))
                uppercase++;
            else if (Character.isLowerCase(temp.charAt(i)))
                lowercase++;
            else if (Character.isDigit(temp.charAt(i)))
                digits++;

            symbols = length - uppercase - lowercase - digits;

        }

        for (int j = 1; j < temp.length() - 1; j++) {

            if (Character.isDigit(temp.charAt(j)))
                bonus++;

        }

        for (int k = 0; k < temp.length(); k++) {

            if (Character.isUpperCase(temp.charAt(k))) {
                k++;

                if (k < temp.length()) {

                    if (Character.isUpperCase(temp.charAt(k))) {

                        cuc++;
                        k--;

                    }

                }

            }

        }

        for (int l = 0; l < temp.length(); l++) {

            if (Character.isLowerCase(temp.charAt(l))) {
                l++;

                if (l < temp.length()) {

                    if (Character.isLowerCase(temp.charAt(l))) {

                        clc++;
                        l--;

                    }

                }

            }

        }

        System.out.println("length" + length);
        System.out.println("uppercase" + uppercase);
        System.out.println("lowercase" + lowercase);
        System.out.println("digits" + digits);
        System.out.println("symbols" + symbols);
        System.out.println("bonus" + bonus);
        System.out.println("cuc" + cuc);
        System.out.println("clc" + clc);

        if (length > 7) {
            requirements++;
        }

        if (uppercase > 0) {
            requirements++;
        }

        if (lowercase > 0) {
            requirements++;
        }

        if (digits > 0) {
            requirements++;
        }

        if (symbols > 0) {
            requirements++;
        }

        if (bonus > 0) {
            requirements++;
        }

        if (digits == 0 && symbols == 0) {
            lettersonly = 1;
        }

        if (lowercase == 0 && uppercase == 0 && symbols == 0) {
            numbersonly = 1;
        }

        int Total = (length * 4) + ((length - uppercase) * 2)
                + ((length - lowercase) * 2) + (digits * 4) + (symbols * 6)
                + (bonus * 2) + (requirements * 2) - (lettersonly * length*2)
                - (numbersonly * length*3) - (cuc * 2) - (clc * 2);

        System.out.println("Total" + Total);

        if(Total<30){
            progressBar.setProgress(Total-15);
        }

        else if (Total>=40 && Total <50)
        {
            progressBar.setProgress(Total-20);
        }

        else if (Total>=56 && Total <70)
        {
            progressBar.setProgress(Total-25);
        }

        else if (Total>=76)
        {
            progressBar.setProgress(Total-30);
        }
        else{
            progressBar.setProgress(Total-20);
        }

    }

}



