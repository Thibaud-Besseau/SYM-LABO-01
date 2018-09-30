package ch.heigvd.sym.template;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class HomepageActivity extends AppCompatActivity {

    public static String emailEntered;
    // GUI elements
    TextView emailTextView = null;
    TextView imeiPhoneTextView = null;
    ImageView pictureView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show the homepage
        setContentView(R.layout.homepage);

        //get info from Main Activity
        Intent intent = getIntent();
        String mail = intent.getStringExtra(emailEntered);

        emailTextView = findViewById(R.id.emailUser);
        emailTextView.setText(mail);


        imeiPhoneTextView = findViewById(R.id.imeiPhone);

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        imeiPhoneTextView.setText(tm.getImei());

        pictureView = findViewById(R.id.picture);
    //Display the picture
        File imgFile = new  File("/sdcard/Images/test_image.jpg");
        if(imgFile.exists() == true) {
            Bitmap picture = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            pictureView.setImageBitmap(picture);
        }

    }

}


