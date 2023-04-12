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

public class LoginActivity extends AppCompatActivity {

    public static final String PREF_NAME = "MyPrefFile";          // This constant file name will be used whenever & wherever we'll be using values saved in SharedPreferences

    TextInputLayout userPhone, password;
    Button login, signUp, adminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userPhone = findViewById(R.id.tilLoginUserPhone);
        password = findViewById(R.id.tilLoginPwd);
        login = findViewById(R.id.btnLogin);
        signUp = findViewById(R.id.btnAskSignUp);
        adminLogin = findViewById(R.id.rbAdmin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private Boolean validateUserPhoneNumber()
    {
        String val = Objects.requireNonNull(userPhone.getEditText()).getText().toString();

        if(val.isEmpty())
        {
            userPhone.setError("This is a required field");
            return false;
        }
        else
        {
            userPhone.setError(null);
            userPhone.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword()
    {
        String val = Objects.requireNonNull(password.getEditText()).getText().toString();

        if(val.isEmpty())
        {
            password.setError("This is a required field");
            return false;
        }
        else
        {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser()
    {
        // validate login info
        if(validateUserPhoneNumber() && validatePassword())
        {
            isUser();         // This fun checks whether any user exists with the provided username or not in the database and also matches its password
        }
    }

    private void isUser()
    {
        String userEnteredPhoneNumber = Objects.requireNonNull(userPhone.getEditText()).getText().toString().trim();
        String userEnteredPassword = Objects.requireNonNull(password.getEditText()).getText().toString().trim();

        FirebaseDatabase rootnode = FirebaseDatabase.getInstance();                                 // get the instance (i.e, parent or root node)
        DatabaseReference reference = rootnode.getReference(UserClass.class.getSimpleName());

        Query checkUser = reference.orderByChild("phone").equalTo(userEnteredPhoneNumber);                  // orderBy(field_name) means search for the field_name

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)  {
                if(snapshot.exists())                                           // if user exists it means there is some data in snapShot
                {
                    userPhone.setError(null);                             // This executes when for the second time user enters correct phone number
                    userPhone.setErrorEnabled(false);

                    String passFromDB = snapshot.child(userEnteredPhoneNumber).child("password").getValue(String.class);      // String.class converts password into string type

                    if(Objects.equals(passFromDB, userEnteredPassword))                      // if user enters correct password
                    {
                        password.setError(null);
                        password.setErrorEnabled(false);

                        String userNameFromDB = snapshot.child(userEnteredPhoneNumber).child("userName").getValue(String.class);
                        String phoneFromDB = snapshot.child(userEnteredPhoneNumber).child("phone").getValue(String.class);
                        String vehicleFromDB = snapshot.child(userEnteredPhoneNumber).child("vehicle").getValue(String.class);

                        // To ensure if user has already logged in, don't show logIn page again
                        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREF_NAME, 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("hasLoggedIn", true);
                        editor.putString("nameOfUser", userNameFromDB);                   // storing name of user so that it'll be available throughout the running of app
                        editor.putString("vehicleOfUser", vehicleFromDB);
                        editor.apply();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("phone", phoneFromDB);

                        startActivity(intent);

                        Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();

                        finish();
                    }
                    else                                            // if password entered is wrong
                    {
                        password.setError("Wrong password");
                        password.requestFocus();
                    }
                }
                else                                                // if user with entered username doesn't exist
                {
                    userPhone.setError("No such user exists");
                    userPhone.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}