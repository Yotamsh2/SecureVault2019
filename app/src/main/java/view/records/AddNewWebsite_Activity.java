package view.records;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.securevault19.securevault2019.MainActivity;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.record.Website;

import java.util.Calendar;

import local_database.DatabaseClient;

public class AddNewWebsite_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

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
    private EditText title_EditText, username_EditText, password_EditText, website_EditText, email_EditText;
    private LinearLayout category, typeOfRecord;
    private LinearLayout userName, password, website, email, bankAccount, creditCard, cryptocurrency, drivingLicence, passport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_website);


        addChooseIconBtn = findViewById(R.id.addChooseIconBtn);
        chooseIcon = findViewById(R.id.chooseIcon);
        starBtn = findViewById(R.id.star_icon);
        starFullBtn = findViewById(R.id.starFull_icon);
        mediaPlayer = MediaPlayer.create(this, R.raw.button);
        logo =  findViewById(R.id.logo);
        saveBtn =  findViewById(R.id.saveBtn);
        cancelBtn =  findViewById(R.id.cancelBtn);
        licenceExpiringDate_EditText =  findViewById(R.id.licenceExpiringDate_EditText);
        issuanceDate_EditText =  findViewById(R.id.issuanceDate_EditText);
        expiringDate_EditText =  findViewById(R.id.expiringDate_EditText);
        expiringDate_title =  findViewById(R.id.expiringDate_title);
        calendarBtn =  findViewById(R.id.calendarBtn);
        addExpiringDate =  findViewById(R.id.addExpiringDateBtn);
        showPass =  findViewById(R.id.showPass);
        hidePass =  findViewById(R.id.hidePass);
        copyPass =  findViewById(R.id.copyPass);
        custom1_EditText =  findViewById(R.id.custom1_EditText);
        custom2_EditText =  findViewById(R.id.custom2_EditText);
        custom3_EditText =  findViewById(R.id.custom3_EditText);
        custom1_EditText_title =  findViewById(R.id.c1);
        custom2_EditText_title =  findViewById(R.id.c2);
        custom3_EditText_title =  findViewById(R.id.c3);
        addFields =  findViewById(R.id.addFieldsBtn);
        scrollView =  findViewById(R.id.frame);
        addNote =  findViewById(R.id.addNoteBtn);
        note =  findViewById(R.id.note_editText);
        //note_title =  findViewById(R.id.note_title);
        category_Spinner =  findViewById(R.id.category_Spinner);
        typeOfRecord_Spinner =  findViewById(R.id.typeOfRecord_Spinner);
        title_EditText =  findViewById(R.id.title_EditText);
        username_EditText =  findViewById(R.id.username_EditText);
        password_EditText =  findViewById(R.id.password_EditText);
        website_EditText =  findViewById(R.id.website_EditText);
        email_EditText =  findViewById(R.id.email_EditText);
        editForm =  findViewById(R.id.editForm);
        activityTitle = findViewById(R.id.activityTitle);
        category = findViewById(R.id.category);
        typeOfRecord = findViewById(R.id.typeOfRecord);
        showCategory = findViewById(R.id.showCategory);
        showTypeOfRecord = findViewById(R.id.showTypeOfRecord);
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
        animation1 = AnimationUtils.loadAnimation(AddNewWebsite_Activity.this, R.anim.zoomin);
        animation2 = AnimationUtils.loadAnimation(AddNewWebsite_Activity.this, R.anim.zoomin_fast);
        animation3 = AnimationUtils.loadAnimation(AddNewWebsite_Activity.this, R.anim.buttonpush_anim);
        scrollView.startAnimation(animation2);


        //        Set logo's font to category's text
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/OutlierRail.ttf");
        activityTitle.setTypeface(myFont);

        //Show fields after selecting item in spinner
        typeOfRecord_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //(0)Website/Email Account, (1)Social Media, (2)Online Shopping
                if(position==0 || position==1 || position==2){
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
                if(position==3){
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
                if(position==4){
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
                if(position==5){
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
                if(position==6){
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
                if(position==7){
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
                if(position==8){
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



    @Override
    public void onBackPressed() {
        cancelWarningMessage(null);
    }


    public void openMenu(View view) {
    }

    @SuppressLint("RestrictedApi")
    public void openNewRecord(View view) {
        mediaPlayer.start();
        saveBtn.startAnimation(animation3);

        //Setting the details from the Activity to send to the Website constructor
        final String title = title_EditText.getText().toString();
        final String userName = username_EditText.getText().toString().trim();
        final String password = password_EditText.getText().toString().trim();
        final String website = website_EditText.getText().toString().trim();
        final String email = email_EditText.getText().toString().trim();
        final String expiringDate = expiringDate_EditText.getText().toString().trim();
        //final String other = editTextOther.getText().toString();


        //Check if all the needed details are typed.
        //NEED TO UPGRADE A LITTLE
        if (title.isEmpty()) {
            Toast.makeText(this, "Please insert a title", Toast.LENGTH_SHORT).show();
            return;
        }


        class SaveNewWebsiteRecord extends AsyncTask<Void, Void, Void> {

            //expiring-date fields are seperated so we concat them into one string.
            //String expiringDate_arr[] = {expiringDateDay, expiringDateMonth, expiringDateYear};
            //final String expiringDate = expiringDate_arr.toString();

            @Override
            protected Void doInBackground(Void... voids) {
                Website website_record = new Website();
                website_record.setTitle(title);
                website_record.setUserName(userName);
                website_record.setPassword(password);
                website_record.setWebsite(website);
                website_record.setEmail(email);
                website_record.setExpiringDate(expiringDate);
                //website_record.setOther(other);

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
                data.putExtra(EXTRA_EXPIRING_DATE_DAY, expiringDate); //delete "Day"
                //data.putExtra(EXTRA_OTHER, other);

                setResult(RESULT_OK, data);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), WebsiteRecycler_Activity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveNewWebsiteRecord saveNewWebsiteRecord = new SaveNewWebsiteRecord();
        saveNewWebsiteRecord.execute();



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
        if (note.getVisibility()==View.GONE){
            note.setVisibility(View.VISIBLE);
            //note_title.setVisibility(View.VISIBLE);
            addNote.setVisibility(View.GONE);
            note.requestFocus();
        }
        if (typeOfRecord_Spinner.getSelectedItemPosition()==8){
            title_EditText.requestFocus();
            note.setMinHeight(800);
        }
        else{
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
        if (licenceExpiringDate_EditText.isFocused()){
            licenceExpiringDate_EditText.setText(date);
        }
        else if (expiringDate_EditText.isFocused()){
            expiringDate_EditText.setText(date);
        }
        else if (issuanceDate_EditText.isFocused()){
            issuanceDate_EditText.setText(date);
        }

    }


    public void showPass(View view) {
        mediaPlayer.start();

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
        //Intent intent = new Intent(this, WebsiteRecycler_Activity.class);
        //this.startActivity(intent);
        finish();
    }



    public void showCategory(View view) {
        mediaPlayer.start();
        if (showCategory.getVisibility()== View.VISIBLE){
            category.setVisibility(View.VISIBLE);
            showCategory.setVisibility(View.GONE);
            typeOfRecord.setVisibility(View.GONE);
            showTypeOfRecord.setVisibility(View.VISIBLE);
        }
        else{
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

    }

    public void addToFavorites(View view) {
        if (starBtn.getVisibility()==View.VISIBLE){
            starBtn.setVisibility(View.GONE);
            starFullBtn.setVisibility(View.VISIBLE);
        }
        else{
            starBtn.setVisibility(View.VISIBLE);
            starFullBtn.setVisibility(View.GONE);
        }
    }

    public void addChooseIcon(View view) {
        addChooseIconBtn.setVisibility(View.GONE);
        chooseIcon.setVisibility(View.VISIBLE);
    }
}



