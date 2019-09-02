package View.Explorer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.securevault19.securevault2019.R;

import View.Records.NewRecord_Activity;

public class ExplorerMain_Activity extends AppCompatActivity {

    private EditText custom1, custom2, custom3;
    private TextView c1, c2, c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer_main);
    }

    public void createRecord(View view) {
    }

    public void openSearch(View view) {
    }

    public void openMenu(View view) {
    }

    public void openNewRecord(View view) {
//        Intent intent = new Intent(this, NewRecord_Activity.class);
//        this.startActivity(intent);
        Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_LONG).show();


    }

    public void addFields(View view) {
        EditText custom1 = findViewById(R.id.custom1);
        EditText custom2 = findViewById(R.id.custom2);
        EditText custom3 = findViewById(R.id.custom3);
        TextView c1 = findViewById(R.id.c1);
        TextView c2 = findViewById(R.id.c2);
        TextView c3 = findViewById(R.id.c3);

    }
}
