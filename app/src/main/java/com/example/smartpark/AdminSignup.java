package com.example.smartpark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class AdminSignup extends AppCompatActivity {

    public static final String PARKING_FEE = "myFee";

    TextInputLayout regAdminName, regAdminPhn, regLocation, regAdID, parkingFee;

    Button btnSignUp;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);

        regAdminName = findViewById(R.id.tilSignupAdmin);
        regAdminPhn = findViewById(R.id.tilSignupPhoneAd);
        regLocation = findViewById(R.id.tilSignupLocationAd);
        regAdID = findViewById(R.id.tilSignupAdminID);
        parkingFee = findViewById(R.id.tilSignupParkingFee);
        btnSignUp = findViewById(R.id.btnSignup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateAdminName() && validatePhone() && validateLocation())
                {
                    rootNode = FirebaseDatabase.getInstance();          // get the root

                    reference = rootNode.getReference(AdminClass.class.getSimpleName());         // AdminClass will be used as a name for our reference

                    // Get all the values
                    String adminName = Objects.requireNonNull(regAdminName.getEditText()).getText().toString();
                    String adminPhone = Objects.requireNonNull(regAdminPhn.getEditText()).getText().toString();
                    String adminLocation = Objects.requireNonNull(regLocation.getEditText()).getText().toString();
                    String adminID = Objects.requireNonNull(regAdID.getEditText()).getText().toString();
                    String parkingLotFee = Objects.requireNonNull(parkingFee.getEditText()).getText().toString();

                    AdminClass adminClass = new AdminClass(adminName, adminPhone, adminLocation, adminID);                  // whatever is passed here will be stored in our database

                    reference.child(adminID).setValue(adminClass);           // on basis of this field admins will be distinguished from each other (here, on basis of adminID)

                    // To ensure if admin has once signed up & Home page is reached, don't show logIn page until he himself signs out
                    SharedPreferences sharedPreferences = getSharedPreferences(AdminLogin.PREF_NAME_ADMIN, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("hasLoggedIn", true);
                    editor.putString("nameOfAdmin", adminName);                       // storing name of admin so that it'll be available throughout the running of app
                    editor.putString("idAdmin", adminID);              // storing id of admin so that it'll be available throughout the running of app
                    editor.apply();

                    SharedPreferences sharedPreferences1 = getSharedPreferences(PARKING_FEE, 0);
                    SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                    editor1.putString("ParkingLotFee", parkingLotFee);
                    editor1.apply();

                    Toast.makeText(AdminSignup.this, "Admin Account created successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AdminSignup.this, HomePageAdmin.class);                // now redirecting to home page
                    intent.putExtra("nameOfadmin", adminName);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private Boolean validateAdminName()
    {
        String val = Objects.requireNonNull(regAdminName.getEditText()).getText().toString();
        if(val.isEmpty())
        {
            regAdminName.setError("Admin name cannot be empty");
            regAdminName.requestFocus();                                 // start pointing to this particular field
            return false;
        }
        else
        {
            regAdminName.setError(null);                         // remove the error if it is corrected
            regAdminName.setErrorEnabled(false);                       // to remove extra space used by error msg as we are using material design
            return true;
        }
    }
    private Boolean validatePhone()
    {
        String num = Objects.requireNonNull(regAdminPhn.getEditText()).getText().toString();
        if(num.isEmpty())
        {
            regAdminPhn.setError("This field cannot be empty");
            regAdminPhn.requestFocus();
            return false;
        }
        else if(num.length() != 10)
        {
            regAdminPhn.setError("Invalid contact number");
            regAdminPhn.requestFocus();
            return false;
        }
        else
        {
            regAdminPhn.setError(null);
            regAdminPhn.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateLocation()
    {
        String val = Objects.requireNonNull(regAdminName.getEditText()).getText().toString();
        if(val.isEmpty())
        {
            regLocation.setError("Location cannot be empty");
            regLocation.requestFocus();                                 // start pointing to this particular field
            return false;
        }
        else
        {
            regLocation.setError(null);                         // remove the error if it is corrected
            regLocation.setErrorEnabled(false);                       // to remove extra space used by error msg as we are using material design
            return true;
        }
    }
}