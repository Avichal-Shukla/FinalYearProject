package com.example.smartpark;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartpark.CaptureAct;
import com.example.smartpark.LoginActivity;
import com.example.smartpark.MapActivity;
import com.example.smartpark.NavigationActivity;
import com.example.smartpark.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    public static final String FOR_USER_PAYMENT_STATUS = "MyPrefForUserPaymentStatus";
    Button navigate, signOut;
    TextView nameInsideCV, vehicleInsideCV;
    ImageView cardScan;
    CardView payment;
    String nameOfCurrentUser, contactNumber, slotNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Receiving data coming from different activities
        Intent receiverIntent = getIntent();
        String phoneNumber = receiverIntent.getStringExtra("phone");
        contactNumber = phoneNumber;

        TextView userNameForHomePage = findViewById(R.id.Name);                               // getting textview id from XML file

        // Receiving the value (i.e, name of user) saved in our SharedPreferences file in Login & SignUp activities
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREF_NAME, 0);
        String nameOfUser = sharedPreferences.getString("nameOfUser", "User");
        nameOfCurrentUser = nameOfUser;
        String vehicleOfUser = sharedPreferences.getString("vehicleOfUser", "Vehicle ID");

        userNameForHomePage.setText(nameOfUser);

        SharedPreferences sharedPreferences2 = getSharedPreferences(HomePageAdmin.ADMIN_PREF, 0);
        SharedPreferences.Editor editor = sharedPreferences2.edit();
        editor.putString("exitingUserName", nameOfCurrentUser);
        editor.putString("exitingUserPhone", contactNumber);
        editor.apply();

        navigate = findViewById(R.id.btnNavigate);
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
//                intent.putExtra("slotNumber", slotNumber);
                startActivity(intent);
            }
        });

        signOut = findViewById(R.id.btnSignOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // To ensure if user has already logged in, don't show logIn page again
                SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREF_NAME, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("hasLoggedIn", false);
                editor.apply();

                // Moving back to Login Page
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cardScan = findViewById(R.id.ivScanner);
        cardScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanOptions options = new ScanOptions();
                options.setPrompt("Volume up to flash on");
                options.setBeepEnabled(true);
                options.setOrientationLocked(true);
                options.setCaptureActivity(CaptureAct.class);
                launcher.launch(options);
            }
        });

        nameInsideCV = findViewById(R.id.tvNameInsideCV);
        nameInsideCV.setText(nameOfUser);

        vehicleInsideCV = findViewById(R.id.tvVehicleInsideCV);
        vehicleInsideCV.setText(vehicleOfUser);

        payment = findViewById(R.id.cvPay);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
                intent.putExtra("nameOfUser", nameOfCurrentUser);
                intent.putExtra("contactNumberOfUser", contactNumber);
                startActivity(intent);
            }
        });
    }

    // Code for QR scanning
    ActivityResultLauncher<ScanOptions> launcher = registerForActivityResult(new ScanContract(), result ->
    {
        if(result.getContents() != null)
        {
            if(result.getContents().equals("1"))                                                    // 1 is used for entry QR
            {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                intent.putExtra("phoneNumber", contactNumber);
                startActivity(intent);
            }
            else if(result.getContents().equals("0"))                                               // 0 is sued for exit QR
            {
                SharedPreferences sharedPreferences = getSharedPreferences(FOR_USER_PAYMENT_STATUS, 0);
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putBoolean("showUserPaymentStatus",true);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, FinalActivity.class);
                intent.putExtra("phoneNumber", contactNumber);
                intent.putExtra("slotNumber", slotNumber);
                startActivity(intent);
                finish();
            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Result of this QR code is");
                builder.setMessage(result.getContents());

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        }
    });
}