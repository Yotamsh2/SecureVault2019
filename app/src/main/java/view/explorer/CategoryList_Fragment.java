package view.explorer;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.user.CurrentUser;
import cryptography.Cryptography;
import view.records.RecordRecycler_Activity;

public class CategoryList_Fragment extends Fragment implements View.OnClickListener {

    // mainScreenActivity shows the categotyListFragment
    private String CRYPTO_KEY;
    private String pattern;
    public static final String EXTRA_FOLDER =
            "com.securevault19.securevault2019.EXTRA_FOLDER";
    public static final String EXTRA_SEARCH =
            "com.securevault19.securevault2019.EXTRA_SEARCH";


    private String encryptedSearchString;
    private String searchString;

    private Typeface myFont;
    public CountDownTimer timer;
    private TextView category1, category2, category3, category4, category5, category6, category7, category8, category9, category10;
    private GridLayout mainGrid;
    private CardView bankAccounts, creditCard, socialMedia, webAccounts, onlineShopping, cryptocurrency, drivingLicence, passports, allRecords, notes;
    private Animation animation1, animation2, animation3, animation4;
    private MediaPlayer mediaPlayer;
    private RelativeLayout search_layout;
    private Button options_icon,menu_icon;
    private ImageButton search_btn, favorites;
    private EditText search_bar;
    private TextView activityTitle;
    private String nameOfFolder, firstName;
    private ImageView clearSearch;
    private Cryptography cryptography;
    private View v;
    private DrawerLayout drawerLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_list2, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        CRYPTO_KEY = getActivity().getIntent().getStringExtra("CRYPTO_KEY");
        pattern = CurrentUser.getInstance().getPatternLock();
        cryptography = new Cryptography();
        // welcomig the user
        try {
            firstName = cryptography.decrypt(CurrentUser.getInstance().getFirstName(), CRYPTO_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        v = getView();
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.button);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);
        favorites = v.findViewById(R.id.favorites);
        bankAccounts = v.findViewById(R.id.bankAccount);
        creditCard = v.findViewById(R.id.creditCard);
        socialMedia = v.findViewById(R.id.socialMedia);
        webAccounts = v.findViewById(R.id.webAccounts);
        onlineShopping = v.findViewById(R.id.onlineShopping);
        cryptocurrency = v.findViewById(R.id.cryptocurrency);
        drivingLicence = v.findViewById(R.id.drivingLicence);
        passports = v.findViewById(R.id.passport);
        allRecords = v.findViewById(R.id.allRecords);
        notes = v.findViewById(R.id.notes);
        search_layout = v.findViewById(R.id.search_layout);
        search_btn = v.findViewById(R.id.search_btn);
        search_bar = v.findViewById(R.id.search_bar);
        activityTitle = v.findViewById(R.id.activityTitle);
        clearSearch = v.findViewById(R.id.clearSearch);
        options_icon = v.findViewById(R.id.options_icon);
        menu_icon = v.findViewById(R.id.menu_icon);
        favorites.setOnClickListener(this);
        bankAccounts.setOnClickListener(this);
        creditCard.setOnClickListener(this);
        socialMedia.setOnClickListener(this);
        webAccounts.setOnClickListener(this);
        onlineShopping.setOnClickListener(this);
        cryptocurrency.setOnClickListener(this);
        drivingLicence.setOnClickListener(this);
        passports.setOnClickListener(this);
        allRecords.setOnClickListener(this);
        notes.setOnClickListener(this);
        search_btn.setOnClickListener(this);
        clearSearch.setOnClickListener(this);
        options_icon.setOnClickListener(this);
        menu_icon.setOnClickListener(this);


        mainGrid = v.findViewById(R.id.mainGrid);
        //Animation Sets
        animation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.zoomin_fast);
        animation2 = AnimationUtils.loadAnimation(getActivity(), R.anim.categories_anim);
        animation3 = AnimationUtils.loadAnimation(getActivity(), R.anim.buttonpush_anim);
        animation4 = AnimationUtils.loadAnimation(getActivity(), R.anim.zoomout);
        mainGrid.startAnimation(animation2);


//        Set logo's font to category's text
        myFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OutlierRail.ttf");
        activityTitle.setTypeface(myFont);
        if (!firstName.equals(""))
            activityTitle.setText(firstName);
        else
            activityTitle.setText(R.string.welcome);
        category1 = v.findViewById(R.id.category1);
        category2 = v.findViewById(R.id.category2);
        category3 = v.findViewById(R.id.category3);
        category4 = v.findViewById(R.id.category4);
        category5 = v.findViewById(R.id.category5);
        category6 = v.findViewById(R.id.category6);
        category7 = v.findViewById(R.id.category7);
        category8 = v.findViewById(R.id.category8);
        category9 = v.findViewById(R.id.category9);
        category10 = v.findViewById(R.id.category10);
        category1.setTypeface(myFont);
        category2.setTypeface(myFont);
        category3.setTypeface(myFont);
        category4.setTypeface(myFont);
        category5.setTypeface(myFont);
        category6.setTypeface(myFont);
        category7.setTypeface(myFont);
        category8.setTypeface(myFont);
        category9.setTypeface(myFont);
        category10.setTypeface(myFont);



        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!search_bar.getText().toString().equals("")) {
                    clearSearch.setVisibility(View.VISIBLE);
                } else if (search_bar.getText().toString().equals("")) {
                    clearSearch.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (search_bar.getText().toString().equals("")) {
                    clearSearch.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (search_bar.getText().toString().equals("")) {
                    clearSearch.setVisibility(View.GONE);
                }
            }
        });

        timer = new CountDownTimer(5 * 60 * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Toast.makeText(getActivity().getApplicationContext(), "No activity detected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity().getApplicationContext(), PatternLockView_Activity.class);
                intent.putExtra("PATTERN", pattern);
                intent.putExtra("CRYPTO_KEY", CRYPTO_KEY);
                startActivity(intent);
            }
        };
        timer.start();
    }



    @Override
    public void onClick(View view) {
        if (view == search_btn){
            search(view);
        } else if (view == options_icon){
            openOptions(view);
        } else if (view == clearSearch) {
            clearSearch(view);
        } else if (view == menu_icon){
            drawerLayout.openDrawer(GravityCompat.START);
            TextView nav_title = getActivity().findViewById(R.id.nav_title);
            nav_title.setTypeface(myFont);
            nav_title.setText(activityTitle.getText().toString());
            TextView nav_email = getActivity().findViewById(R.id.nav_email);
            nav_email.setText(CRYPTO_KEY);

        } else {
            goToFolderRecords(view);
        }
    }


    // sets the view as the name of the folder we choose
    public void goToFolderRecords(View view) {
        if (view == bankAccounts)
            nameOfFolder = "Bank Accounts";
        else if (view == creditCard)
            nameOfFolder = "Credit Cards";
        else if (view == socialMedia)
            nameOfFolder = "Social Media";
        else if (view == webAccounts)
            nameOfFolder = "Website & Email";
        else if (view == onlineShopping)
            nameOfFolder = "Online Shopping";
        else if (view == cryptocurrency)
            nameOfFolder = "Cryptocurrency";
        else if (view == drivingLicence)
            nameOfFolder = "Driving Licence";
        else if (view == passports)
            nameOfFolder = "Passports";
        else if (view == allRecords)
            nameOfFolder = "All Records";
        else if (view == notes)
            nameOfFolder = "Notes";
        else if (view == favorites)
            nameOfFolder = "Favorites";


        timer.cancel();
        timer.start();
        mediaPlayer.start();
        view.startAnimation(animation3);
        Intent intent = new Intent(getActivity().getApplicationContext(), RecordRecycler_Activity.class);
        // pass extra the name of the folder that clicked
        intent.putExtra("CRYPTO_KEY",CRYPTO_KEY);
        intent.putExtra(EXTRA_FOLDER, nameOfFolder);
        this.startActivity(intent);

    }

    public void search(View view) {
        final Cryptography cryptography = new Cryptography();
        timer.cancel();
        timer.start();
        mediaPlayer.start();
        view.startAnimation(animation3);
        if (search_bar.getText().toString().equals("")) {
            goToFolderRecords(allRecords);
        } else {
            // searching the title that entered to the search bar.
            // all the Database is encrypted so we encrypt the seachText and searching it encrypted
             searchString = search_bar.getText().toString();
              new AsyncTask<Void,Void,Void>(){

                 @Override
                 protected Void doInBackground(Void... voids) {
                     try {
                         encryptedSearchString = cryptography.encryptWithKey(CRYPTO_KEY,searchString.trim());
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                     return null;
                 }
                 @Override
                 protected void onPostExecute(Void aVoid) {
                     super.onPostExecute(aVoid);
                     Intent intent = new Intent(getActivity().getApplicationContext(), RecordRecycler_Activity.class);
                     intent.putExtra("encryptedSearchString", encryptedSearchString);
                     intent.putExtra(EXTRA_SEARCH, searchString.trim());
                     intent.putExtra(EXTRA_FOLDER, "Search");
                     intent.putExtra("CRYPTO_KEY",CRYPTO_KEY);
                     Log.d("encryptedSearchString", "entering to next Activity With " + encryptedSearchString);
                     startActivity(intent);
                     getActivity().overridePendingTransition(0, 0);
                 }
             }.execute();
        }
    }


    public void openOptions(View view) {

        Intent intent = new Intent(getActivity().getApplicationContext(), Team_Activity.class);
        this.startActivity(intent);
    }

    public void clearSearch(View view) {
        search_bar.setText("");
    }
}
