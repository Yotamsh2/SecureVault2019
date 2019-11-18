//package cryptography;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import java.util.Random;
//
//public class Generate_Passwords extends AppCompatActivity {
//
//    Button GeneratePassword;
//    TextView password;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate( savedInstanceState );
//        setContentView( R.layout.activity_main );
//        GeneratePassword = (Button) findViewById( R.id.button );
//        password = (TextView)findViewById( R.id.textView );
//        GeneratePassword.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                password.setText( generateString( 24 ) );
//            }
//        } );
//
//    }
//
//    private String generateString(int length){
//        char[] chars = "Qm1W~nw2),Ev3bR4!ux5T6c7&Y@z7U/^8IuOlk#]0.pPA$j*=SD9%Fo[(hGHJg|iaKL's+ZX_dCVy-fBtN*reM~".toCharArray();
//        StringBuilder stringBuilder = new StringBuilder(  );
//        Random random = new Random(  );
//        for(int i=0; i<length ; i++){
//            char c = chars[random.nextInt(chars.length)];
//            stringBuilder.append(c);
//        }
//
//        return stringBuilder.toString();
//    }
//}
