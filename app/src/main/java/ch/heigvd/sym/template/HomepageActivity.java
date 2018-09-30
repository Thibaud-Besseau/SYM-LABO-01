package ch.heigvd.sym.template;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomepageActivity extends AppCompatActivity {

    TextView emailTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show the welcome screen / login authentication dialog
        setContentView(R.layout.authent);

        //get info from Main Activity
        Intent intent = getIntent();
        String mail = intent.getStringExtra("emailEntered");
        String passwd = intent.getStringExtra("passwordGiven");

        emailTextView = findViewById(R.id.EmailUser);
        emailTextView.setText("Your email is : "+mail);

    }

}


