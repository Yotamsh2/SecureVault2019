package view.explorer;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.securevault19.securevault2019.R;
import com.securevault19.securevault2019.user.CurrentUser;

import cryptography.Cryptography;
import view.entrance.NewUser_Activity;
import view.preferences.SecurityLevel_Activity;

public class MainScreen_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private String ORIGIN= "MainScreen";
    private DrawerLayout drawer;
    private NavigationView navigationView;
private String CRYPTO_KEY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        CRYPTO_KEY = getIntent().getStringExtra("CRYPTO_KEY");
        Log.d("CurrentUserCheck", CurrentUser.getInstance().getEmail());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CategoryList_Fragment()).commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.about:
                Intent intent1 = new Intent(getApplicationContext(),Team_Activity.class);
                startActivity(intent1);
                overridePendingTransition(0, 0);
                break;
            case R.id.securityLevel:
                Intent intent2 = new Intent(getApplicationContext(), SecurityLevel_Activity.class);
                intent2.putExtra("ORIGIN",ORIGIN);
                startActivity(intent2);
                overridePendingTransition(0, 0);
                break;
            case R.id.profile:
                Intent intent3 = new Intent(getApplicationContext(), NewUser_Activity.class);
                intent3.putExtra("ORIGIN",ORIGIN);
                intent3.putExtra("CRYPTO_KEY",CRYPTO_KEY);
                startActivity(intent3);
                overridePendingTransition(0, 0);
                break;
            case R.id.log_out:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Log out");
                alert.setMessage("Are you sure you want to log out?");
                alert.setPositiveButton("Log out", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.create().show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}