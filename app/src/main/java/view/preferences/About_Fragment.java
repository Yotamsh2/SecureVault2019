package view.preferences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.securevault19.securevault2019.R;

public class About_Fragment extends Fragment {
    private TextView textView;
    private Button backbtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);

//        backbtn = v.findViewById(R.id.back_icon_about_fragment);
        textView = v.findViewById(R.id.about_textview);
        textView.setText(R.string.about);
        return v;
    }

}
