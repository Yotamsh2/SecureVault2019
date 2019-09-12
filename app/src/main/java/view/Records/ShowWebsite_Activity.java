package view.Records;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.securevault19.securevault2019.R;

public class ShowWebsite_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ShowWebsite_ACtivity", "onCreate: called." );

        Intent intent = getIntent();
        String text = intent.getStringExtra(WebsiteRecycler_Activity.EXTRA_TEXT);

        TextView   textView = findViewById(R.id.textview1);
        textView.setText(text);
    }
}
