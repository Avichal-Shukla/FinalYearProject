package com.example.smartpark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class AdminLogin extends AppCompatActivity {

    public static final String PREF_NAME_ADMIN = "MyPrefFileForAdmin";          // This constant file name will be used whenever & wherever we'll be using values saved in SharedPreferences

    TextInputLayout adminID;
    Button loginAd, btnSignUpAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adminID = findViewById(R.id.tilLoginPwdAd);
        loginAd = findViewById(R.id.btnLoginAd);
        btnSignUpAdmin = findViewById(R.id.btnAskSignUpAdmin);

        loginAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAdmin();
            }
        });

        btnSignUpAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLogin.this, AdminSignup.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private Boolean validateID()
    {
        String val = Objects.requireNonNull(adminID.getEditText()).getText().toString();

        if(val.isEmpty())
        {
            adminID.setError("This is a required field");
            return false;
        }
        else
        {
            adminID.setError(null);
            adminID.setErrorEnabled(false);
            return true;
        }
    }

    public void loginAdmin()
    {
        // validate login info
        if(validateID())
        {
            isAdmin();         // This fun checks whether any user exists with the provided username or not in the database and also matches its password
        }
    }

    private void isAdmin()
    {
        String adminEnteredID = Objects.requireNonNull(adminID.getEditText()).getText().toString().trim();

        FirebaseDatabase rootnode = FirebaseDatabase.getInstance();                                 // get the instance (i.e, parent or root node)
        DatabaseReference reference = rootnode.getReference(AdminClass.class.getSimpleName());            // AdminClass will be used as a name for our reference

        Query checkAdmin = reference.orderByChild("id").equalTo(adminEnteredID);                  // orderBy(field_name) means search for the field_name

        checkAdmin.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)  {
                if(snapshot.exists())                                           // if admin exists it means there is some data in snapShot
                {
                    String idFromDB = snapshot.child(adminEnteredID).child("id").getValue(String.class);      // String.class converts id into string type

                    if(Objects.equals(idFromDB, adminEnteredID))                      // if admin enters correct id
                    {
                        adminID.setError(null);
                        adminID.setErrorEnabled(false);

                        String adminNameFromDB = snapshot.child(adminEnteredID).child("adminName").getValue(String.class);
                        String phoneFromDB = snapshot.child(adminEnteredID).child("phone").getValue(String.class);
                        String locationFromDB = snapshot.child(adminEnteredID).child("location").getValue(String.class);

                        // To ensure if admin has already logged in, don't show logIn page again
                        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREF_NAME, 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("hasLoggedIn", true);
                        editor.putString("nameOfAdmin", adminNameFromDB);                   // storing name of admin so that it'll be available throughout the running of app
                        editor.putString("location", locationFromDB);
                        editor.apply();

                        Intent intent = new Intent(getApplicationContext(), HomePageAdmin.class);

                        startActivity(intent);

                        Toast.makeText(AdminLogin.this, "Admin Logged in successfully", Toast.LENGTH_SHORT).show();

                        finish();
                    }
                }
                else                                                // if admin with entered id doesn't exist
                {
                    adminID.setError("No such admin exists");
                    adminID.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}