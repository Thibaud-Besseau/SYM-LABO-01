package ch.heigvd.sym.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import ch.heigvd.sym.entities.UserAccount;

public class MainActivity extends AppCompatActivity {

    // for logging purposes
    private static final String ACTIVITY_NAME = MainActivity.class.getSimpleName();

    // todo replace hard-coded user account by external data source
    private List<UserAccount> userAccounts = Arrays.asList(
            new UserAccount("amel@heig.test", "amel", "pink.jpg"),
            new UserAccount("thibaud@heig.test", "thibaud", "blue.jpg"));

    // UI elements
    private static EditText editEmail;
    private static EditText editPassword;
    private static Button buttonSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "in onCreate() method");

        // request permissions at runtime
        requestAppPermissions();

        setContentView(R.layout.authent);

        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        buttonSignIn = findViewById(R.id.buttOk);

        buttonSignIn.setOnClickListener(v -> {
            String email = editEmail.getText().toString();
            String password = editPassword.getText().toString();

            // validate email format
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.wrongemail), Toast.LENGTH_LONG)
                        .show();

                return;
            }

            // find user based on email/password combination
            Optional<UserAccount> userAccount = tryFindUserAccount(email, password);
            if (!userAccount.isPresent()) {
                showErrorDialog();

                // clear inputs fields
                clearInputFields();

                return;
            }

            // start new activity
            Intent intent = new Intent(MainActivity.this, HomepageActivity.class);
            intent.putExtra(HomepageActivity.EXTRA_USER_ACCOUNT, userAccount.get());
            startActivity(intent);
        });
    }

    private void clearInputFields() {
        editEmail.getText().clear();
        editPassword.getText().clear();
    }

    private Optional<UserAccount> tryFindUserAccount(String email, String password) {
        return userAccounts
                .stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst();
    }

    protected void showErrorDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        alertBuilder.setTitle(R.string.wronglogin);
        alertBuilder.setMessage(R.string.wrong);
        alertBuilder.setPositiveButton(android.R.string.ok, (dialog, which) -> { });
        alertBuilder.create().show();
    }

    private void requestAppPermissions() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        if (hasReadPermissionsExternalStorage() && hasReadPermissionsForPhoneState()) {
            return;
        }

        ActivityCompat.requestPermissions(this,
                new String[] {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE
                }, 1);
    }

    private boolean hasReadPermissionsExternalStorage() {
        return (ContextCompat.checkSelfPermission(getBaseContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean hasReadPermissionsForPhoneState() {
        return (ContextCompat.checkSelfPermission(getBaseContext(),
                Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "in onStart() method");

        clearInputFields();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(ACTIVITY_NAME, "in onRestart() method");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "in onResume() method");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "in onPause() method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "in onStop() method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "in onDestroy() method");
    }
}
