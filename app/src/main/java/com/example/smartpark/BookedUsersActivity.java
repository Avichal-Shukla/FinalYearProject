package com.example.smartpark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BookedUsersActivity extends AppCompatActivity {

    TextView adminName, exitingUserName;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    HashSet<String> userContacts = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_users);

        SharedPreferences receiver = getSharedPreferences(AdminLogin.PREF_NAME_ADMIN, 0);
        String nameOfAdmin = receiver.getString("nameOfAdmin", "Admin");
        String idAdmin = receiver.getString("idAdmin", "ID");

        adminName = findViewById(R.id.NameAdminBU);
        adminName.setText(nameOfAdmin);

        SharedPreferences receiverSF = getSharedPreferences(HomePageAdmin.ADMIN_PREF, 0);
        String nameOfExitingUser = receiverSF.getString("exitingUserName", "Exiting User");
        String phoneOfExitingUser = receiverSF.getString("exitingUserPhone", "Exiting UserPhone");

        SharedPreferences receiverSF2 = getSharedPreferences(MainActivity.FOR_USER_PAYMENT_STATUS, 0);
        boolean userIsAboutToGo = receiverSF2.getBoolean("showUserPaymentStatus", false);

        exitingUserName = findViewById(R.id.tvExitingUser);

        if (userIsAboutToGo) {
            exitingUserName.setText(nameOfExitingUser);

            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference(PaidUsersClass.class.getSimpleName());

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    // Loop through the child nodes of the "PaidUserClass" node
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        // Get the key (user ID) and data (user object) for each child node of UserClass node
                        String userPhones = childSnapshot.getKey();

                        HashMap<String, Object> userData = (HashMap<String, Object>) childSnapshot.getValue();
//                    String userName = (String) userData.get("userName");

                        userContacts.add(userPhones);

                    }

                    exitingUserName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            SharedPreferences statusReceiver = getSharedPreferences(HomePageAdmin.ADMIN_PREF, 0);
                            boolean status = statusReceiver.getBoolean("paymentCleared", false);

                            if (userContacts.contains(phoneOfExitingUser) || status)
                            {
                                Toast.makeText(BookedUsersActivity.this, "Payment completed", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                SharedPreferences sharedPreferences = getSharedPreferences(HomePageAdmin.ADMIN_PREF, 0);
                                boolean clearTextView = sharedPreferences.getBoolean("clearTextView", false);

                                if(clearTextView)
                                {
                                    exitingUserName.setText("");
                                }
                                else
                                {
                                    Toast.makeText(BookedUsersActivity.this, "Payment pending", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }
}