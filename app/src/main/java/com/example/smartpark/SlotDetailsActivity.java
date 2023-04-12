package com.example.smartpark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SlotDetailsActivity extends AppCompatActivity {

    public static final String SLOT_DETAIL_PREF = "MySlot";

    TextView detailPageName, detailPagePhone, detailPageVehicle, detailPageSlot;
    DatabaseReference reference;
    Button bookSlot;
    String currentUser, currentUserVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_details);

        detailPageName = findViewById(R.id.tvSlotName);
        detailPagePhone = findViewById(R.id.tvSlotContact);
        detailPageVehicle = findViewById(R.id.tvSlotVehicle);
        detailPageSlot = findViewById(R.id.tvSlotSlotNo);

        Intent receiverIntent = getIntent();
        String currentUserPhoneNumber = receiverIntent.getStringExtra("phoneNumber");

        Intent receiverIntent2 = getIntent();
        String currentSlotNumber = receiverIntent2.getStringExtra("slotNo");

        reference = FirebaseDatabase.getInstance().getReference("UserClass");
        reference.child(currentUserPhoneNumber).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful())
                {
                    if(task.getResult().exists())
                    {
                        DataSnapshot snapshot = task.getResult();
                        currentUser = String.valueOf(snapshot.child("userName").getValue());
                        currentUserVehicle = String.valueOf(snapshot.child("vehicle").getValue());

                        detailPageName.setText(currentUser);
                        detailPagePhone.setText(currentUserPhoneNumber);
                        detailPageVehicle.setText(currentUserVehicle);
                        detailPageSlot.setText(currentSlotNumber);
                    }
                }
            }
        });

        bookSlot = findViewById(R.id.btnBookSlot);
        bookSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences(SLOT_DETAIL_PREF, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("mySlot", currentSlotNumber);
                editor.apply();

                Intent intent = new Intent(SlotDetailsActivity.this, NavigationActivity.class);
                intent.putExtra("currentUserName", currentUser);
                intent.putExtra("currentUserPhone", currentUserPhoneNumber);
                intent.putExtra("currentUserVehicle", currentUserVehicle);
                intent.putExtra("currentSlotNumber", currentSlotNumber);
                startActivity(intent);
                finish();
            }
        });
    }
}