package view.records;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.record.Record;
import com.securevault19.securevault2019.user.CurrentUser;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;

import cryptography.Cryptography;
import view.explorer.PatternLockView_Activity;
import view_model.records.Record_ViewModel;

public class AddNewRecord_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, Serializable {
    private Record_ViewModel viewModel;

    private Cryptography cryptography;
    private CountDownTimer timer;
    private String encryptedTitle, encryptedUsername, encryptedPassword, encryptedAccountNumber,
            encryptedIBAN, encryptedBankNumber, encryptedBankAddress, encryptedCardNumber, encryptedCVV,
            encryptedCardExpireMonth, encryptedCardExpireYear, encryptedWebsite, encryptedEmail, encryptedWalletGenerationSeed,
            encryptedPrivateKey, encryptedPublicKey, encryptedLicenceNumber, encryptedLicenceExpireDate, encryptedPassportNumber,
            encryptedIssuanceDate, encryptedIssuancePlace, encryptedNote, encryptedExpireDateNote, encryptedTagsNote;
    private String CRYPTO_KEY;
    private String nameOfFolder;
    private int userSecurityLevel = Integer.parseInt(CurrentUser.getInstance().getSecureLevel());


    public static final String EXTRA_FOLDER =
            "com.securevault19.securevault2019.EXTRA_FOLDER";
    public static final String EXTRA_TYPE =
            "com.securevault19.securevault2019.EXTRA_TYPE";
    public static final String EXTRA_ORIGIN =
            "com.securevault19.securevault2019.EXTRA_ORIGIN";

    // -------------------------------------------------------------------- //

    private Button addChooseIconBtn;
    private ImageView chooseIcon;
    private Button starBtn, starFullBtn;
    private EditText custom1_EditText, custom2_EditText, custom3_EditText, note_EditText, expireDateNote_EditText, tagsNote_EditText;

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

    Record currentRecord;
    private boolean isFavorite = false; //as default, a record is not a favorite.

    Field[] allDrawablesfromRes_drawable = com.securevault19.securevault2019.R.drawable.class.getFields();
    ArrayList<Drawable> drawableResources = new ArrayList<>();
    //ArrayList<Integer> drawableResourcesIDs = new ArrayList<>();
    int drawabaleID; //Default Icon(Secure Vault Black)
    String drawableName;
    int currentRecordDrawabaleID;
    String currentRecordDrawableName;


    @SuppressLint({"WrongViewCast", "RestrictedApi"})
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
        // getting the object ( Record ) from RecordRecycler_activity line 326
        Intent i = getIntent();
        currentRecord = (Record) i.getSerializableExtra("recordClassDB");


        viewModel = ViewModelProviders.of(this).get(Record_ViewModel.class);
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
            type = extras.getString(EXTRA_TYPE);            // name of the type ( currentRecord )
            folder_name.setText(folder);
            switch (origin) {
                case "onRecordClick":           // clicked from recycler view
                    if (folder != null) {
                        if (type != null) {
                            setEditMode(false);
                            findViewById(R.id.listOfIcons).setVisibility(View.GONE);
                            editForm.setVisibility(View.VISIBLE);
                            findViewById(R.id.deleteTopBtn).setVisibility(View.VISIBLE);
                            fieldsVisibility(type);  //shows the relevant fields of the clicked Record.
                            switch (type) {
                                case "Bank Accounts":
                                    typeOfRecord_Spinner.setSelection(3);
                                    category_Spinner.setSelection(3);
                                    DisplayRecordDetails(currentRecord);

                                    break;

                                case "Credit Cards":
                                    typeOfRecord_Spinner.setSelection(4);
                                    category_Spinner.setSelection(4);
                                    DisplayRecordDetails(currentRecord);

                                    break;
                                case "Social Media":                // social media && website & email && online shopping all the same
                                    typeOfRecord_Spinner.setSelection(1);
                                    category_Spinner.setSelection(1);
                                    DisplayRecordDetails(currentRecord);

                                    break;

                                case "Website & Email":             // social media && website & email && online shopping all the same
                                    typeOfRecord_Spinner.setSelection(0);
                                    category_Spinner.setSelection(0);
                                    DisplayRecordDetails(currentRecord);

                                    break;

                                case "Online Shopping":             // social media && website & email && online shopping all the same
                                    typeOfRecord_Spinner.setSelection(2);
                                    category_Spinner.setSelection(2);
                                    DisplayRecordDetails(currentRecord);

                                    break;

                                case "Cryptocurrency":
                                    typeOfRecord_Spinner.setSelection(6);
                                    category_Spinner.setSelection(6);
                                    DisplayRecordDetails(currentRecord);

                                    break;

                                case "Driving Licence":
                                    typeOfRecord_Spinner.setSelection(7);
                                    category_Spinner.setSelection(7);
                                    DisplayRecordDetails(currentRecord);

                                    break;

                                case "Passports":
                                    typeOfRecord_Spinner.setSelection(5);
                                    category_Spinner.setSelection(5);
                                    DisplayRecordDetails(currentRecord);

                                    break;

                                default:  //FOR EXAMPLE
                                    typeOfRecord_Spinner.setSelection(8);
                                    category_Spinner.setSelection(8);
                                    DisplayRecordDetails(currentRecord);
                                    break;
                            }
                        }
                    }

                    break; //case: onRecordClick

                //If we came from buttonAddRecord
                default:

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

        timer = new CountDownTimer(15 * 60 * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished == 60 * 1000) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(AddNewRecord_Activity.this);
                    alert.setTitle("Logout timer");
                    alert.setMessage("No action detected. App will be closed in 1 minute.");
                    alert.setPositiveButton("Log out", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Account log out", Toast.LENGTH_SHORT).show();
                            System.exit(0);
                        }
                    });
                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            timer.cancel();
                        }
                    });
                    alert.create().show();
                }
            }

            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Account log out", Toast.LENGTH_SHORT).show();
                System.exit(0);
            }
        };
        timer.start();

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////

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
            case "Website & Email":
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
            title_EditText.setText(cryptography.decrypt(record.getTitle().trim(), CRYPTO_KEY));
            activityTitle.setText(title_EditText.getText().toString().trim());
            password_EditText.setText(cryptography.decrypt(record.getPassword(), CRYPTO_KEY));
            email_EditText.setText(cryptography.decrypt(record.getEmail().trim(), CRYPTO_KEY));
            website_EditText.setText(cryptography.decrypt(record.getWebsite().trim(), CRYPTO_KEY));
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
            note_EditText.setText(cryptography.decrypt(record.getNote(), CRYPTO_KEY));
            if (!cryptography.decrypt(record.getNote(), CRYPTO_KEY).equals("")) {
                addNote(addNote);
            }

            expireDateNote_EditText.setText(cryptography.decrypt(record.getExpitingDateNote(), CRYPTO_KEY));

            if (!cryptography.decrypt(record.getExpitingDateNote(), CRYPTO_KEY).equals("")) {
                addExpiringDate(addExpiringDate);
            }

            tagNames_EditText.setText(cryptography.decrypt(record.getTagsNote(), CRYPTO_KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (record.getFavorite()) {
            setFavorite(true);
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

        final String typeOfRecord = typeOfRecord_Spinner.getSelectedItem().toString().trim();
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
                        encryptedNote = cryptography.encryptWithKey(CRYPTO_KEY, note);
                        encryptedExpireDateNote = cryptography.encryptWithKey(CRYPTO_KEY, expireDateNote);
                        encryptedTagsNote = cryptography.encryptWithKey(CRYPTO_KEY, tagsNote);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    // PAY ATTENTION!                                //
                    // the encryption we using is EncryptWithKey();!//
                    // the KEY will be the userName                 //

                    // creating Record to insert the TextFields and insert to DB

                    Record record;
                    if (origin.equals("onRecordClick")) {
                        record = currentRecord;
                    } else {
                        record = new Record();
                    }

                    // setting the current userName to a record.
                    // when you will try to see the record data, it will check if the user is the one who ca see the info.
                    record.setUserTable(CurrentUser.getInstance().getEmail());


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
                    if (origin.equals("onRecordClick")) {
                        viewModel.update(record);
                    } else {
                        viewModel.insert(record);
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

                }
            }.execute();                // execute for starting the AsyncTask

        }

        saveBtn.startAnimation(animation3);
        mediaPlayer.start();

    }

    @SuppressLint("RestrictedApi")
    public void setEditMode(Boolean editable) {

        int color = 0xff000000;
        title_EditText.setEnabled(editable);
        title_EditText.setTextColor(color);
        username_EditText.setEnabled(editable);
        username_EditText.setTextColor(color);
        password_EditText.setEnabled(editable);
        password_EditText.setTextColor(color);
        accountNumber_EditText.setEnabled(editable);
        accountNumber_EditText.setTextColor(color);
        IBAN_EditText.setEnabled(editable);
        IBAN_EditText.setTextColor(color);
        bankNumber_EditText.setEnabled(editable);
        bankNumber_EditText.setTextColor(color);
        bankAddress_EditText.setEnabled(editable);
        bankAddress_EditText.setTextColor(color);
        cardNumber_EditText.setEnabled(editable);
        cardNumber_EditText.setTextColor(color);
        cvv_EditText.setEnabled(editable);
        cvv_EditText.setTextColor(color);
        cardExpiringMonth_EditText.setEnabled(editable);
        cardExpiringMonth_EditText.setTextColor(color);
        cardExpiringYear_EditText.setEnabled(editable);
        cardExpiringYear_EditText.setTextColor(color);
        website_EditText.setEnabled(editable);
        website_EditText.setTextColor(color);
        email_EditText.setEnabled(editable);
        email_EditText.setTextColor(color);
        publicKey_EditText.setEnabled(editable);
        publicKey_EditText.setTextColor(color);
        privateKey_EditText.setEnabled(editable);
        privateKey_EditText.setTextColor(color);
        walletGenerationSeed_EditText.setEnabled(editable);
        walletGenerationSeed_EditText.setTextColor(color);
        licenceNumber_EditText.setEnabled(editable);
        licenceNumber_EditText.setTextColor(color);
        licenceExpiringDate_EditText.setEnabled(editable);
        licenceExpiringDate_EditText.setTextColor(color);
        passportNumber_EditText.setEnabled(editable);
        passportNumber_EditText.setTextColor(color);
        issuanceDate_EditText.setEnabled(editable);
        issuanceDate_EditText.setTextColor(color);
        issuancePlace_EditText.setEnabled(editable);
        issuancePlace_EditText.setTextColor(color);
        calendarBtn.setClickable(editable);
        expireDateNote_EditText.setEnabled(editable);
        note_EditText.setEnabled(editable);
        note_EditText.setTextColor(color);
        tagNames_EditText.setEnabled(editable);
        chooseIcon.setClickable(editable);
        starBtn.setClickable(editable);
        starFullBtn.setClickable(editable);
        if (editable) {
            copyPass.setVisibility(View.GONE);
            saveBtn.setVisibility(View.VISIBLE);
            cancelBtn.setVisibility(View.VISIBLE);
            editForm.setVisibility(View.GONE);
        } else {
            copyPass.setVisibility(View.VISIBLE);
            saveBtn.setVisibility(View.GONE);
            cancelBtn.setVisibility(View.GONE);
            editForm.setVisibility(View.VISIBLE);

        }
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

        if (view == showPass || view == hidePass) {

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

        if (editForm.getVisibility() == View.VISIBLE) {
            back(view);
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.cancelation_request);
        alert.setMessage(R.string.cancelation_message);
        alert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Not saved", Toast.LENGTH_SHORT).show();
                setEditMode(false);
            }
        });
        alert.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.create().show();
    }

    @SuppressLint("RestrictedApi")
    public void editForm(View view) {
        mediaPlayer.start();
        Intent intent = new Intent();

        if (userSecurityLevel > 2) {  //if SecurityLevel is 3(the highest) then ask the user to draw the Pattern again for verification.
            Log.d("secureLevel_pattern", "userSecurityLevel: " + userSecurityLevel);
            intent.setClass(getApplicationContext(), PatternLockView_Activity.class);
            intent.putExtra(EXTRA_ORIGIN, "AddNewRecord_Activity");
            intent.putExtra("CRYPTO_KEY", CRYPTO_KEY);

            startActivity(intent);

            setEditMode(true);
        } else {
            setEditMode(true);
        }


    }

    public void back(View view) {
        finish();
        overridePendingTransition(0, 0);

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

        CountDownTimer timer = new CountDownTimer(1 * 60 * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            @RequiresApi(api = Build.VERSION_CODES.P)
            public void onFinish() {
                ClipboardManager mCbm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                mCbm.clearPrimaryClip();
                Toast.makeText(getApplicationContext(), "Clipboard cleared", Toast.LENGTH_SHORT).show();
            }
        };
        timer.start();
    }

    public void chooseIcon(View view) {     //makes icons visible
        mediaPlayer.start();
        if (listOfIcons.getVisibility() == View.GONE) {
            listOfIcons.setVisibility(View.VISIBLE);
        } else
            listOfIcons.setVisibility(View.GONE);

    }

    public void changeIcon(View view) throws IllegalAccessException {    //after CLICKING an icon
        chooseIcon.setBackground(view.getBackground()); //sets the icon after clicking it

        if (chooseIcon != null) {
            for (Drawable drawables : drawableResources) {

                if (view.getBackground() != null &&
                        view.getBackground().getConstantState()
                                .equals(drawables.getCurrent().getConstantState())) {

                    Log.d("icon", "found(.getConstantState()): " + drawables.getCurrent().getConstantState());

                    //get the needed drawable id
                    new getDrawableIDAsyncTask(drawables.getCurrent(), drawableResources).execute(); //Initializing 'drawableID' inside doInBackGround() method.

                }
            }
        }

        mediaPlayer.start();
        listOfIcons.setVisibility(View.GONE);
    }


    public void addChooseIcon(View view) {  //Opens the option to choose icon - shows all the icons
        addChooseIconBtn.setVisibility(View.GONE);
        chooseIcon.setVisibility(View.VISIBLE);
        try {
            Drawable drawable = getDrawable(currentRecordDrawabaleID);
            chooseIcon.setBackground(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (editForm.getVisibility() == View.VISIBLE) {
            listOfIcons.setVisibility(View.GONE);
        }

    }


    //Add to favorites
    public void addToFavorites(View view) {
        if (!isFavorite) {
            setFavorite(true);
        } else {
            setFavorite(false);
        }
    }

    public void setFavorite(Boolean status) {
        isFavorite = status;
        if (status) {
            starFullBtn.setVisibility(View.VISIBLE);
            starBtn.setVisibility(View.GONE);
        } else {
            starFullBtn.setVisibility(View.GONE);
            starBtn.setVisibility(View.VISIBLE);
        }
    }


    public void openWebsiteLink(View view) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("http://" + website_EditText.getText().toString()));
        startActivity(i);
    }

    public void deleteRecord(final View view) {
        mediaPlayer.start();
        findViewById(R.id.deleteTopBtn).startAnimation(animation3);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.delete_request);
        alert.setMessage(R.string.delete_message);
        alert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                viewModel.delete(currentRecord);
                back(view);
            }
        });
        alert.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.create().show();

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
                        drawabaleID = field.getInt(null);
                        drawableName = field.getName();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                if (currentRecord != null) {
                    if (field.getName().equals(currentRecord.getIcon())) {
                        try {
                            currentRecordDrawabaleID = field.getInt(null);

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }


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

                        Drawable d = null;
                        try {
                            d = getResources().getDrawable(field.getInt(null));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                        int id;
                        if ((currentDrawable.getConstantState().equals(d.getConstantState()))) {
                            Log.d("icon", "getName().....: " + field.getName());
                            drawableName = field.getName();

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
                + (bonus * 2) + (requirements * 2) - (lettersonly * length * 2)
                - (numbersonly * length * 3) - (cuc * 2) - (clc * 2);

        System.out.println("Total" + Total);

        if (Total < 30) {
            progressBar.setProgress(Total - 15);
        } else if (Total >= 40 && Total < 50) {
            progressBar.setProgress(Total - 20);
        } else if (Total >= 56 && Total < 70) {
            progressBar.setProgress(Total - 25);
        } else if (Total >= 76) {
            progressBar.setProgress(Total - 30);
        } else {
            progressBar.setProgress(Total - 20);
        }

    }

}



