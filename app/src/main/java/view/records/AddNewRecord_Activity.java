package view.records;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.record.Record;

import java.util.Calendar;

import local_database.DatabaseClient;

public class AddNewRecord_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

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

    private EditText custom1_EditText, custom2_EditText, custom3_EditText, password, note;
    private TextView custom1_EditText_title, custom2_EditText_title, custom3_EditText_title, note_title;
    private TextView expiringDate_EditText;
    private TextView expiringDate_title;
    private ImageButton calendarBtn;
    private ImageView logo;
    private Button addFields, addNote, addExpiringDate, saveBtn, cancelBtn;
    private ImageButton showPass, hidePass;
    private MediaPlayer mediaPlayer;
    private Animation animation1, animation2, animation3;
    private ScrollView scrollView;
    private FloatingActionButton editForm;
    private TextView activityTitle;
    private Typeface myFont;



    private EditText category_EditText, title_EditText, username_EditText, password_EditText, website_EditText, email_EditText;

    @Override
    protected void onCreate(Bundle saveBtndInstanceState) {
        super.onCreate(saveBtndInstanceState);
        setContentView(R.layout.activity_add_new_record);


        mediaPlayer = MediaPlayer.create(this, R.raw.button);
        logo =  findViewById(R.id.logo);
        saveBtn =  findViewById(R.id.saveBtn);
        cancelBtn =  findViewById(R.id.cancelBtn);
        expiringDate_EditText =  findViewById(R.id.expiringDate_EditText);
        expiringDate_title =  findViewById(R.id.expiringDate_title);
        calendarBtn =  findViewById(R.id.calendarBtn);
        addExpiringDate =  findViewById(R.id.addExpiringDateBtn);
        password =  findViewById(R.id.password);
        showPass =  findViewById(R.id.showPass);
        hidePass =  findViewById(R.id.hidePass);
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
        note_title =  findViewById(R.id.note_title);
        category_EditText =  findViewById(R.id.category_EditText);
        title_EditText =  findViewById(R.id.title_EditText);
        username_EditText =  findViewById(R.id.username_EditText);
        password_EditText =  findViewById(R.id.password_EditText);
        website_EditText =  findViewById(R.id.website_EditText);
        email_EditText =  findViewById(R.id.email_EditText);
        editForm =  findViewById(R.id.editForm);
        activityTitle = findViewById(R.id.activityTitle);


        //Animation Sets
        animation1 = AnimationUtils.loadAnimation(AddNewRecord_Activity.this, R.anim.zoomin);
        animation2 = AnimationUtils.loadAnimation(AddNewRecord_Activity.this, R.anim.bottomtotop);
        animation3 = AnimationUtils.loadAnimation(AddNewRecord_Activity.this, R.anim.buttonpush_anim);
        scrollView.startAnimation(animation2);


        //        Set logo's font to category's text
        myFont = Typeface.createFromAsset(this.getAssets(), "fonts/OutlierRail.ttf");
        activityTitle.setTypeface(myFont);
    }


    public void openSearch(View view) {
    }

    public void openMenu(View view) {
    }

    @SuppressLint("RestrictedApi")
    public void openNewRecord(View view) {
        //Setting the details from the Activity to send to the Website constructor
        final String title = title_EditText.getText().toString();
        final String userName = username_EditText.getText().toString().trim();
        final String password = password_EditText.getText().toString().trim();
        final String website = website_EditText.getText().toString().trim();
        final String email = email_EditText.getText().toString().trim();
        final String expiringDate = expiringDate_EditText.getText().toString().trim();


        //Check if all the needed details are typed.
        //NEED TO UPGRADE A LITTLE
        if (title.isEmpty() || userName.isEmpty() || password.isEmpty()
                || website.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please insert all the requested fields", Toast.LENGTH_SHORT).show();
            return;
        }


        class SaveNewRecord extends AsyncTask<Void, Void, Void> {

            //expiring-date fields are seperated so we concat them into one string.
            //String expiringDate_arr[] = {expiringDateDay, expiringDateMonth, expiringDateYear};
            //final String expiringDate = expiringDate_arr.toString();

            @Override
            protected Void doInBackground(Void... voids) {
                Record record = new Record();
                record.setTitle(title);
                record.setUserName(userName);
                record.setPassword(password);
                record.setWebsite(website);
                record.setEmail(email);
                record.setExpiringDate(expiringDate);

                DatabaseClient.getInstance(getApplicationContext()).getRecordDatabase2()
                        .daoRecord()
                        .insert(record);

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
                startActivity(new Intent(getApplicationContext(), RecordRecycler_Activity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveNewRecord saveNewRecord = new SaveNewRecord();
        saveNewRecord.execute();



        saveBtn.startAnimation(animation3);
        mediaPlayer.start();

        category_EditText.setEnabled(false);
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
        note.setVisibility(View.VISIBLE);
        note_title.setVisibility(View.VISIBLE);
        addNote.setVisibility(View.GONE);
        note.requestFocus();
    }

    public void openCalendar(View view) {
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
        expiringDate_EditText.setText(date);
    }

    public void showPass(View view) {
//        password.requestFocus();
//        password.setSelection(password.getText().length());
        if (showPass.getVisibility() == View.VISIBLE) {
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            hidePass.setVisibility(View.VISIBLE);
            showPass.setVisibility(View.GONE);
        } else {
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            hidePass.setVisibility(View.GONE);
            showPass.setVisibility(View.VISIBLE);
        }

    }

    public void cancelWarningMessage(final View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.cancelation_request);
        alert.setMessage(R.string.cancelation_message);
        alert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Data Not saveBtnd", Toast.LENGTH_LONG).show();
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
        category_EditText.setEnabled(true);
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
        Intent intent = new Intent(this, RecordRecycler_Activity.class);
        this.startActivity(intent);
    }

    public void openOptions(View view) {
    }
}



