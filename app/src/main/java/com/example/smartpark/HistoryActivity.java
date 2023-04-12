package com.example.smartpark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class HistoryActivity extends AppCompatActivity {

    DatabaseReference reference;
    ListView lvUserDataHistory;
    TextView adminName;
    ArrayList<String> userNamesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        SharedPreferences receiver = getSharedPreferences(AdminLogin.PREF_NAME_ADMIN, 0);
        String nameOfAdmin = receiver.getString("nameOfAdmin", "Admin");
        String idAdmin = receiver.getString("idAdmin", "ID");

        adminName = findViewById(R.id.NameAdminHistory);
        adminName.setText(nameOfAdmin);

        reference = FirebaseDatabase.getInstance().getReference(UserClass.class.getSimpleName());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Loop through the child nodes of the "UserClass" node
                for (DataSnapshot childSnapshot : snapshot.getChildren())
                {
                    // Get the key (user ID) and data (user object) for each child node of UserClass node
                    String userPhones = childSnapshot.getKey();

                    HashMap<String, Object> userData = (HashMap<String, Object>) childSnapshot.getValue();
                    String userName = (String) userData.get("userName");

                    userNamesList.add(userName);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(HistoryActivity.this, android.R.layout.simple_list_item_1, userNamesList);

                lvUserDataHistory = findViewById(R.id.lvUserDataHistory);

                lvUserDataHistory.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }
}