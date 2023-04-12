package com.example.smartpark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class HomePageAdmin extends AppCompatActivity {

    public static final String ADMIN_PREF = "HomePageAdminFile";

    Button backToUser, history, paidUsers, parkingLot;
    DatabaseReference reference;
    ListView lvUserDataBU;
    TextView adminName;
    ArrayList<String> userNamesList = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_admin);

        SharedPreferences receiver = getSharedPreferences(AdminLogin.PREF_NAME_ADMIN, 0);
        String nameOfAdmin = receiver.getString("nameOfAdmin", "Admin");

        adminName = findViewById(R.id.NameAdmin);
        adminName.setText(nameOfAdmin);

        history = findViewById(R.id.btnHistory);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageAdmin.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        backToUser = findViewById(R.id.btnBackToUser);
        backToUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageAdmin.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        paidUsers = findViewById(R.id.btnBookedUsers);
        paidUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageAdmin.this, BookedUsersActivity.class);
                startActivity(intent);
            }
        });

        parkingLot = findViewById(R.id.btnMap);
        parkingLot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageAdmin.this, MapActivity.class);
                startActivity(intent);
            }
        });

        reference = FirebaseDatabase.getInstance().getReference(BookedUsersClass.class.getSimpleName());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Loop through the child nodes of the "BookedUsersClass" node
                for (DataSnapshot childSnapshot : snapshot.getChildren())
                {
                    // Get the key (user ID) and data (user object) for each child node of UserClass node
                    String userPhones = childSnapshot.getKey();

                    HashMap<String, Object> userData = (HashMap<String, Object>) childSnapshot.getValue();
                    String userName = (String) userData.get("name");

                    userNamesList.add(userName);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(HomePageAdmin.this, android.R.layout.simple_list_item_1, userNamesList);

                lvUserDataBU = findViewById(R.id.lvUserDataHistory);

                lvUserDataBU.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}