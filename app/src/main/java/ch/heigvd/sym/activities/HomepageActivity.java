package ch.heigvd.sym.activities;

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

import ch.heigvd.sym.entities.UserAccount;

public class HomepageActivity extends AppCompatActivity {

    public final static String EXTRA_USER_ACCOUNT = "userAccount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        // UI elements
        final TextView emailTextView = findViewById(R.id.emailUser);
        final TextView imeiPhoneTextView = findViewById(R.id.imeiPhone);
        final ImageView pictureView = findViewById(R.id.picture);

        //get info from Main Activity
        Intent intent = getIntent();
        UserAccount userAccount = (UserAccount)intent.getSerializableExtra(EXTRA_USER_ACCOUNT);

        // set email
        emailTextView.setText(userAccount.getEmail());

        // set EMEI
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        imeiPhoneTextView.setText(tm.getImei());

        // set avatar picture
        File imgFile = new  File("/sdcard/Download/" + userAccount.getAvatarFilename());
        if(imgFile.exists() == true) {
            Bitmap picture = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            pictureView.setImageBitmap(picture);
        }
    }
}


