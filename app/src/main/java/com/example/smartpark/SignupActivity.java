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

public class SignupActivity extends AppCompatActivity {

    TextInputLayout regUserName, regUserPhn, regVehicle, regPwd, regConfirmPwd;

    Button btnSignUp;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        regUserName = findViewById(R.id.tilSignupUser);
        regUserPhn = findViewById(R.id.tilSignupPhone);
        regVehicle = findViewById(R.id.tilSignupVehicle);
        regPwd = findViewById(R.id.tilSignupPwd);
        regConfirmPwd = findViewById(R.id.tilSignupRePwd);
        btnSignUp = findViewById(R.id.btnSignup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateUsername() && validatePhone() && validatePassword())
                {
                    rootNode = FirebaseDatabase.getInstance();          // get the root

                    reference = rootNode.getReference(UserClass.class.getSimpleName());         // UserClass will be used as a name for our reference

                    // Get all the values
                    String username = Objects.requireNonNull(regUserName.getEditText()).getText().toString();
                    String userPhone = Objects.requireNonNull(regUserPhn.getEditText()).getText().toString();
                    String userVehicle = Objects.requireNonNull(regVehicle.getEditText()).getText().toString();
                    String pwd = Objects.requireNonNull(regPwd.getEditText()).getText().toString();

                    UserClass userClass = new UserClass(username, userPhone, userVehicle, pwd);                  // whatever is passed here will be stored in our database

                    reference.child(userPhone).setValue(userClass);           // on basis of this field users will be distinguished from each other (here, on basis of username)

                    // To ensure if user has once signed up & Home page is reached, don't show logIn page until he himself signs out
                    SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREF_NAME, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("hasLoggedIn", true);
                    editor.putString("nameOfUser", username);                       // storing name of user so that it'll be available throughout the running of app
                    editor.putString("vehicleOfUser", userVehicle);              // storing vehicle id of user so that it'll be available throughout the running of app
                    editor.apply();

                    Toast.makeText(SignupActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);                // now redirecting to home page
                    intent.putExtra("phone", userPhone);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private Boolean validateUsername()
    {
        String val = Objects.requireNonNull(regUserName.getEditText()).getText().toString();
        if(val.isEmpty())
        {
            regUserName.setError("Username cannot be empty");
            regUserName.requestFocus();                                 // start pointing to this particular field
            return false;
        }
        else
        {
            regUserName.setError(null);                         // remove the error if it is corrected
            regUserName.setErrorEnabled(false);                       // to remove extra space used by error msg as we are using material design
            return true;
        }
    }
    private Boolean validatePhone()
    {
        String num = Objects.requireNonNull(regUserPhn.getEditText()).getText().toString();
        if(num.isEmpty())
        {
            regUserPhn.setError("This field cannot be empty");
            regUserPhn.requestFocus();
            return false;
        }
        else if(num.length() != 10)
        {
            regUserPhn.setError("Invalid contact number");
            regUserPhn.requestFocus();
            return false;
        }
        else
        {
            regUserPhn.setError(null);
            regUserPhn.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword()
    {
        String pass = Objects.requireNonNull(regPwd.getEditText()).getText().toString();
        String rePass = Objects.requireNonNull(regConfirmPwd.getEditText()).getText().toString();

        if(!pass.equals(rePass))
        {
            regConfirmPwd.setError("Password did not match");
            regConfirmPwd.requestFocus();
            return false;
        }
        else
        {
            regConfirmPwd.setError(null);
            regConfirmPwd.setErrorEnabled(false);
            return true;
        }
    }
}