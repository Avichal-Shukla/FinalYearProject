package com.example.smartpark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NavigationActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30,
            p31, p32, p33, p34, p35, p36;
    PagerAdapter pagerAdapter;
    Button parked;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        ViewPager viewPager = findViewById(R.id.fragmentContainer);
        tabLayout = findViewById(R.id.parkingLots);
        p1 = findViewById(R.id.fragP1);
        p2 = findViewById(R.id.fragP2);
        p3 = findViewById(R.id.fragP3);
        p4 = findViewById(R.id.fragP4);
        p5 = findViewById(R.id.fragP5);
        p6 = findViewById(R.id.fragP6);
        p7 = findViewById(R.id.fragP7);
        p8 = findViewById(R.id.fragP8);
        p9 = findViewById(R.id.fragP9);
        p10 = findViewById(R.id.fragP10);
        p11 = findViewById(R.id.fragP11);
        p12 = findViewById(R.id.fragP12);
        p13 = findViewById(R.id.fragP13);
        p14 = findViewById(R.id.fragP14);
        p15 = findViewById(R.id.fragP15);
        p16 = findViewById(R.id.fragP16);
        p17 = findViewById(R.id.fragP17);
        p18 = findViewById(R.id.fragP18);
        p19 = findViewById(R.id.fragP19);
        p20 = findViewById(R.id.fragP20);
        p21 = findViewById(R.id.fragP21);
        p22 = findViewById(R.id.fragP22);
        p23 = findViewById(R.id.fragP23);
        p24 = findViewById(R.id.fragP24);
        p25 = findViewById(R.id.fragP25);
        p26 = findViewById(R.id.fragP26);
        p27 = findViewById(R.id.fragP27);
        p28 = findViewById(R.id.fragP28);
        p29 = findViewById(R.id.fragP29);
        p30 = findViewById(R.id.fragP30);
        p31 = findViewById(R.id.fragP31);
        p32 = findViewById(R.id.fragP32);
        p33 = findViewById(R.id.fragP33);
        p34 = findViewById(R.id.fragP34);
        p35 = findViewById(R.id.fragP35);
        p36 = findViewById(R.id.fragP36);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 36);
        viewPager.setAdapter(pagerAdapter);

        // We use try-catch here to ensure that user clicks on this page only after selecting a slot
        try
        {
            // This is done to ensure that by default the same parking lot number appears to user that has been booked as soon as he clicks on navigation option
            Intent receiverIntent = getIntent();
            int slotNumber = Integer.parseInt(receiverIntent.getStringExtra("currentSlotNumber"))-1;                   // -1 coz here we check from 0 onwards
            viewPager.setCurrentItem(slotNumber);

            if(slotNumber == 0 || slotNumber == 1 || slotNumber == 2 || slotNumber == 3 || slotNumber == 4 || slotNumber == 5 || slotNumber == 6 ||
                    slotNumber == 7 || slotNumber == 8 || slotNumber == 9 || slotNumber == 10 || slotNumber == 11 || slotNumber == 12 || slotNumber == 13 ||
                    slotNumber == 14 || slotNumber == 15 || slotNumber == 16 || slotNumber == 17 || slotNumber == 18 || slotNumber == 19 || slotNumber == 20 ||
                    slotNumber == 21 || slotNumber == 22 || slotNumber == 23 || slotNumber == 24 || slotNumber == 25 || slotNumber == 26 || slotNumber == 27 ||
                    slotNumber == 28 || slotNumber == 29 || slotNumber == 30 || slotNumber == 31 || slotNumber == 32 || slotNumber == 33 || slotNumber == 34 || slotNumber == 35)
            {
                pagerAdapter.notifyDataSetChanged();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Sorry but it seems you haven't selected any slot", Toast.LENGTH_LONG).show();
        }


        // If user directly clicks on the selected slot number or any other slot number
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2 || tab.getPosition() == 3 || tab.getPosition() == 4 || tab.getPosition() == 5 ||
                tab.getPosition() == 6 || tab.getPosition() == 7 || tab.getPosition() == 8 || tab.getPosition() == 9 || tab.getPosition() == 10 || tab.getPosition() == 11 ||
                tab.getPosition() == 12 || tab.getPosition() == 13 || tab.getPosition() == 14 || tab.getPosition() == 15 || tab.getPosition() == 16 ||
                tab.getPosition() == 17 || tab.getPosition() == 18 || tab.getPosition() == 19 || tab.getPosition() == 20 || tab.getPosition() == 21 ||
                tab.getPosition() == 22 || tab.getPosition() == 23 || tab.getPosition() == 24 || tab.getPosition() == 25 || tab.getPosition() == 26 ||
                tab.getPosition() == 27 || tab.getPosition() == 28 || tab.getPosition() == 29 || tab.getPosition() == 30 || tab.getPosition() == 31 ||
                tab.getPosition() == 32 || tab.getPosition() == 33 || tab.getPosition() == 34 || tab.getPosition() == 35)
                {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        parked = findViewById(R.id.btnParked);
        parked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent receiver = getIntent();
                String currentUserName = receiver.getStringExtra("currentUserName");
                String currentUserPhone = receiver.getStringExtra("currentUserPhone");
                String currentUserVehicle = receiver.getStringExtra("currentUserVehicle");
                String currentUserSlotNumber = receiver.getStringExtra("currentSlotNumber");

//                SharedPreferences sharedPreferences1 = getSharedPreferences(MainActivity.FOR_USER_PAYMENT_STATUS, 0);
//                SharedPreferences.Editor editor1 = sharedPreferences1.edit();
//                editor1.putBoolean("showUserPaymentStatus", true);
//                editor1.apply();

                SharedPreferences sharedPreferences = getSharedPreferences(AdminLogin.PREF_NAME_ADMIN, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("currentUserName", currentUserName);
                editor.putString("currentUserPhone", currentUserPhone);
                editor.putString("currentUserVehicle", currentUserVehicle);
                editor.putString("currentUserSlotNumber", currentUserSlotNumber);
                editor.apply();

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference(BookedUsersClass.class.getSimpleName());

                BookedUsersClass bookedUsersClass = new BookedUsersClass(currentUserName, currentUserPhone, currentUserVehicle, currentUserSlotNumber);
                reference.child(currentUserPhone).setValue(bookedUsersClass);

                Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                intent.putExtra("phone", currentUserPhone);
                intent.putExtra("slotNumber", currentUserSlotNumber);
                startActivity(intent);
                finish();
            }
        });

        // If user moves to the desired slot number or any other slot number by swiping
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}