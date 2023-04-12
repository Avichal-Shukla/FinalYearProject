package com.example.smartpark;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smartpark.databinding.ActivityMapBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashSet;

public class MapActivity extends AppCompatActivity {

    public static final String PREF_NAME_2 = "ParkSpotsStatus";

    ActivityMapBinding binding;
    AlertDialog dialog;
    String phoneNumber;
    boolean open = true, closed = false, flag;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    HashSet<String> bookedUsers = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the status bar
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_map);

        // Creating parkingLots Status class
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference(SlotStatusClass.class.getSimpleName());

        // binding is used to avoid writing findViewById multiple times for each spot
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences receiverSF = getSharedPreferences(FinalActivity.FINAL_EXIT, 0);
        String slotToDeallocate = receiverSF.getString("slotToDeallocate", "0");

        if(!slotToDeallocate.equals("0"))
        {
            SharedPreferences changeStatusSF = getSharedPreferences(PREF_NAME_2, 0);
            SharedPreferences.Editor editor = changeStatusSF.edit();

            switch (slotToDeallocate)
            {
                case "1":
                    ImageView imageView = findViewById(R.id.spot1);
                    imageView.setImageResource(R.drawable.parkspot1);
                    editor.putString("isP1Reserved", "no");
                    imageView.setOnClickListener(listener);
                    break;

                case "2":
                    ImageView imageView2 = findViewById(R.id.spot2);
                    imageView2.setImageResource(R.drawable.parkspot2);
                    editor.putString("isP2Reserved", "no");
                    imageView2.setOnClickListener(listener);
                    break;

                case "3":
                    ImageView imageView3 = findViewById(R.id.spot3);
                    imageView3.setImageResource(R.drawable.parkspot3);
                    editor.putString("isP3Reserved", "no");
                    imageView3.setOnClickListener(listener);
                    break;

                case "4":
                    ImageView imageView4 = findViewById(R.id.spot4);
                    imageView4.setImageResource(R.drawable.parkspot4);
                    editor.putString("isP4Reserved", "no");
                    imageView4.setOnClickListener(listener);
                    break;

                case "5":
                    ImageView imageView5 = findViewById(R.id.spot5);
                    imageView5.setImageResource(R.drawable.parkspot5);
                    editor.putString("isP5Reserved", "no");
                    imageView5.setOnClickListener(listener);
                    break;

                case "6":
                    ImageView imageView6 = findViewById(R.id.spot6);
                    imageView6.setImageResource(R.drawable.parkspot6);
                    editor.putString("isP6Reserved", "no");
                    imageView6.setOnClickListener(listener);
                    break;

                case "7":
                    ImageView imageView7 = findViewById(R.id.spot7);
                    imageView7.setImageResource(R.drawable.parkspot7);
                    editor.putString("isP7Reserved", "no");
                    imageView7.setOnClickListener(listener);
                    break;

                case "8":
                    ImageView imageView8 = findViewById(R.id.spot8);
                    imageView8.setImageResource(R.drawable.parkspot8);
                    editor.putString("isP8Reserved", "no");
                    imageView8.setOnClickListener(listener);
                    break;

                case "9":
                    ImageView imageView9 = findViewById(R.id.spot9);
                    imageView9.setImageResource(R.drawable.parkspot9);
                    editor.putString("isP9Reserved", "no");
                    imageView9.setOnClickListener(listener);
                    break;

                case "10":
                    ImageView imageView10 = findViewById(R.id.spot10);
                    imageView10.setImageResource(R.drawable.parkspot10);
                    editor.putString("isP10Reserved", "no");
                    imageView10.setOnClickListener(listener);
                    break;

                case "11":
                    ImageView imageView11 = findViewById(R.id.spot11);
                    imageView11.setImageResource(R.drawable.parkspot11);
                    editor.putString("is11Reserved", "no");
                    imageView11.setOnClickListener(listener);
                    break;

                case "12":
                    ImageView imageView12 = findViewById(R.id.spot12);
                    imageView12.setImageResource(R.drawable.parkspot12);
                    editor.putString("isP12Reserved", "no");
                    imageView12.setOnClickListener(listener);
                    break;

                case "13":
                    ImageView imageView13 = findViewById(R.id.spot13);
                    imageView13.setImageResource(R.drawable.parkspot13);
                    editor.putString("isP13Reserved", "no");
                    imageView13.setOnClickListener(listener);
                    break;

                case "14":
                    ImageView imageView14 = findViewById(R.id.spot14);
                    imageView14.setImageResource(R.drawable.parkspot14);
                    editor.putString("isP14Reserved", "no");
                    imageView14.setOnClickListener(listener);
                    break;

                case "15":
                    ImageView imageView15 = findViewById(R.id.spot15);
                    imageView15.setImageResource(R.drawable.parkspot15);
                    editor.putString("isP15Reserved", "no");
                    imageView15.setOnClickListener(listener);
                    break;

                case "16":
                    ImageView imageView16 = findViewById(R.id.spot16);
                    imageView16.setImageResource(R.drawable.parkspot16);
                    editor.putString("isP16Reserved", "no");
                    imageView16.setOnClickListener(listener);
                    break;

                case "17":
                    ImageView imageView17 = findViewById(R.id.spot17);
                    imageView17.setImageResource(R.drawable.parkspot17);
                    editor.putString("isP17Reserved", "no");
                    imageView17.setOnClickListener(listener);
                    break;

                case "18":
                    ImageView imageView18 = findViewById(R.id.spot18);
                    imageView18.setImageResource(R.drawable.parkspot18);
                    editor.putString("isP18Reserved", "no");
                    imageView18.setOnClickListener(listener);
                    break;

                case "19":
                    ImageView imageView19 = findViewById(R.id.spot19);
                    imageView19.setImageResource(R.drawable.parkspot19);
                    editor.putString("isP19Reserved", "no");
                    imageView19.setOnClickListener(listener);
                    break;

                case "20":
                    ImageView imageView20 = findViewById(R.id.spot20);
                    imageView20.setImageResource(R.drawable.parkspot20);
                    editor.putString("isP20Reserved", "no");
                    imageView20.setOnClickListener(listener);
                    break;

                case "21":
                    ImageView imageView21 = findViewById(R.id.spot21);
                    imageView21.setImageResource(R.drawable.parkspot21);
                    editor.putString("isP21Reserved", "no");
                    imageView21.setOnClickListener(listener);
                    break;

                case "22":
                    ImageView imageView22 = findViewById(R.id.spot22);
                    imageView22.setImageResource(R.drawable.parkspot22);
                    editor.putString("isP22Reserved", "no");
                    imageView22.setOnClickListener(listener);
                    break;

                case "23":
                    ImageView imageView23 = findViewById(R.id.spot23);
                    imageView23.setImageResource(R.drawable.parkspot23);
                    editor.putString("isP23Reserved", "no");
                    imageView23.setOnClickListener(listener);
                    break;

                case "24":
                    ImageView imageView24 = findViewById(R.id.spot24);
                    imageView24.setImageResource(R.drawable.parkspot24);
                    editor.putString("isP24Reserved", "no");
                    imageView24.setOnClickListener(listener);
                    break;

                case "25":
                    ImageView imageView25 = findViewById(R.id.spot25);
                    imageView25.setImageResource(R.drawable.parkspot25);
                    editor.putString("isP25Reserved", "no");
                    imageView25.setOnClickListener(listener);
                    break;

                case "26":
                    ImageView imageView26 = findViewById(R.id.spot26);
                    imageView26.setImageResource(R.drawable.parkspot26);
                    editor.putString("isP26Reserved", "no");
                    imageView26.setOnClickListener(listener);
                    break;

                case "27":
                    ImageView imageView27 = findViewById(R.id.spot27);
                    imageView27.setImageResource(R.drawable.parkspot27);
                    editor.putString("isP27Reserved", "no");
                    imageView27.setOnClickListener(listener);
                    break;

                case "28":
                    ImageView imageView28 = findViewById(R.id.spot28);
                    imageView28.setImageResource(R.drawable.parkspot28);
                    editor.putString("isP28Reserved", "no");
                    imageView28.setOnClickListener(listener);
                    break;

                case "29":
                    ImageView imageView29 = findViewById(R.id.spot29);
                    imageView29.setImageResource(R.drawable.parkspot29);
                    editor.putString("isP29Reserved", "no");
                    imageView29.setOnClickListener(listener);
                    break;

                case "30":
                    ImageView imageView30 = findViewById(R.id.spot30);
                    imageView30.setImageResource(R.drawable.parkspot30);
                    editor.putString("isP30Reserved", "no");
                    imageView30.setOnClickListener(listener);
                    break;

                case "31":
                    ImageView imageView31 = findViewById(R.id.spot31);
                    imageView31.setImageResource(R.drawable.parkspot31);
                    editor.putString("isP31Reserved", "no");
                    imageView31.setOnClickListener(listener);
                    break;

                case "32":
                    ImageView imageView32 = findViewById(R.id.spot32);
                    imageView32.setImageResource(R.drawable.parkspot32);
                    editor.putString("isP32Reserved", "no");
                    imageView32.setOnClickListener(listener);
                    break;

                case "33":
                    ImageView imageView33 = findViewById(R.id.spot33);
                    imageView33.setImageResource(R.drawable.parkspot33);
                    editor.putString("isP33Reserved", "no");
                    imageView33.setOnClickListener(listener);
                    break;

                case "34":
                    ImageView imageView34 = findViewById(R.id.spot34);
                    imageView34.setImageResource(R.drawable.parkspot34);
                    editor.putString("isP34Reserved", "no");
                    imageView34.setOnClickListener(listener);
                    break;

                case "35":
                    ImageView imageView35 = findViewById(R.id.spot35);
                    imageView35.setImageResource(R.drawable.parkspot35);
                    editor.putString("isP35Reserved", "no");
                    imageView35.setOnClickListener(listener);
                    break;

                case "36":
                    ImageView imageView36 = findViewById(R.id.spot36);
                    imageView36.setImageResource(R.drawable.parkspot36);
                    editor.putString("isP36Reserved", "no");
                    imageView36.setOnClickListener(listener);
                    break;
            }

            editor.apply();
        }

        Intent receiver = getIntent();
        phoneNumber = receiver.getStringExtra("phoneNumber");

        if (bookedUsers.contains(phoneNumber))                                       // if same user tries to book again without exit
        {
            Toast.makeText(MapActivity.this, "You have already booked a slot", Toast.LENGTH_SHORT).show();
            flag = closed;
        }
        else                                                                        // if there is a new user
        {
            flag = open;

            // Set status of each parking lot
            SharedPreferences checkStatus = getSharedPreferences(PREF_NAME_2, 0);

            String statusP1 = checkStatus.getString("isP1Reserved", "no");
            String statusP2 = checkStatus.getString("isP2Reserved", "no");
            String statusP3 = checkStatus.getString("isP3Reserved", "no");
            String statusP4 = checkStatus.getString("isP4Reserved", "no");
            String statusP5 = checkStatus.getString("isP5Reserved", "no");
            String statusP6 = checkStatus.getString("isP6Reserved", "no");
            String statusP7 = checkStatus.getString("isP7Reserved", "no");
            String statusP8 = checkStatus.getString("isP8Reserved", "no");
            String statusP9 = checkStatus.getString("isP9Reserved", "no");
            String statusP10 = checkStatus.getString("isP10Reserved", "no");
            String statusP11 = checkStatus.getString("isP11Reserved", "no");
            String statusP12 = checkStatus.getString("isP12Reserved", "no");
            String statusP13 = checkStatus.getString("isP13Reserved", "no");
            String statusP14 = checkStatus.getString("isP14Reserved", "no");
            String statusP15 = checkStatus.getString("isP15Reserved", "no");
            String statusP16 = checkStatus.getString("isP16Reserved", "no");
            String statusP17 = checkStatus.getString("isP17Reserved", "no");
            String statusP18 = checkStatus.getString("isP18Reserved", "no");
            String statusP19 = checkStatus.getString("isP19Reserved", "no");
            String statusP20 = checkStatus.getString("isP20Reserved", "no");
            String statusP21 = checkStatus.getString("isP21Reserved", "no");
            String statusP22 = checkStatus.getString("isP22Reserved", "no");
            String statusP23 = checkStatus.getString("isP23Reserved", "no");
            String statusP24 = checkStatus.getString("isP24Reserved", "no");
            String statusP25 = checkStatus.getString("isP25Reserved", "no");
            String statusP26 = checkStatus.getString("isP26Reserved", "no");
            String statusP27 = checkStatus.getString("isP27Reserved", "no");
            String statusP28 = checkStatus.getString("isP28Reserved", "no");
            String statusP29 = checkStatus.getString("isP29Reserved", "no");
            String statusP30 = checkStatus.getString("isP30Reserved", "no");
            String statusP31 = checkStatus.getString("isP31Reserved", "no");
            String statusP32 = checkStatus.getString("isP32Reserved", "no");
            String statusP33 = checkStatus.getString("isP33Reserved", "no");
            String statusP34 = checkStatus.getString("isP34Reserved", "no");
            String statusP35 = checkStatus.getString("isP35Reserved", "no");
            String statusP36 = checkStatus.getString("isP36Reserved", "no");

            if (statusP1.equals("yes")) {                                                           // if this slot is booked
                binding.spot1.setBackgroundResource(R.drawable.parkspotred1);
            }
            else{
                binding.spot1.setOnClickListener(listener);
            }
            if (statusP2.equals("yes")) {
                binding.spot2.setBackgroundResource(R.drawable.parkspotred2);
            }
            else{
                binding.spot2.setOnClickListener(listener);
            }
            if (statusP3.equals("yes")) {
                binding.spot3.setBackgroundResource(R.drawable.parkspotred3);
            }
            else{
                binding.spot3.setOnClickListener(listener);
            }
            if (statusP4.equals("yes")) {
                binding.spot4.setBackgroundResource(R.drawable.parkspotred4);
            }
            else{
                binding.spot4.setOnClickListener(listener);
            }
            if (statusP5.equals("yes")) {
                binding.spot5.setBackgroundResource(R.drawable.parkspotred5);
            }
            else{
                binding.spot5.setOnClickListener(listener);
            }
            if (statusP6.equals("yes")) {
                binding.spot6.setBackgroundResource(R.drawable.parkspotred6);
            }
            else{
                binding.spot6.setOnClickListener(listener);
            }
            if (statusP7.equals("yes")) {
                binding.spot7.setBackgroundResource(R.drawable.parkspotred7);
            }
            else{
                binding.spot7.setOnClickListener(listener);
            }
            if (statusP8.equals("yes")) {
                binding.spot8.setBackgroundResource(R.drawable.parkspotred8);
            }
            else{
                binding.spot8.setOnClickListener(listener);
            }
            if (statusP9.equals("yes")) {
                binding.spot9.setBackgroundResource(R.drawable.parkspotred9);
            }
            else{
                binding.spot9.setOnClickListener(listener);
            }
            if (statusP10.equals("yes")) {
                binding.spot10.setBackgroundResource(R.drawable.parkspotred10);
            }
            else{
                binding.spot10.setOnClickListener(listener);
            }
            if (statusP11.equals("yes")) {
                binding.spot11.setBackgroundResource(R.drawable.parkspotred11);
            }
            else{
                binding.spot11.setOnClickListener(listener);
            }
            if (statusP12.equals("yes")) {
                binding.spot12.setBackgroundResource(R.drawable.parkspotred12);
            }
            else{
                binding.spot12.setOnClickListener(listener);
            }
            if (statusP13.equals("yes")) {
                binding.spot13.setBackgroundResource(R.drawable.parkspotred13);
            }
            else{
                binding.spot13.setOnClickListener(listener);
            }
            if (statusP14.equals("yes")) {
                binding.spot14.setBackgroundResource(R.drawable.parkspotred14);
            }
            else{
                binding.spot14.setOnClickListener(listener);
            }
            if (statusP15.equals("yes")) {
                binding.spot15.setBackgroundResource(R.drawable.parkspotred15);
            }
            else{
                binding.spot15.setOnClickListener(listener);
            }
            if (statusP16.equals("yes")) {
                binding.spot16.setBackgroundResource(R.drawable.parkspotred16);
            }
            else{
                binding.spot16.setOnClickListener(listener);
            }
            if (statusP17.equals("yes")) {
                binding.spot17.setBackgroundResource(R.drawable.parkspotred17);
            }
            else{
                binding.spot17.setOnClickListener(listener);
            }
            if (statusP18.equals("yes")) {
                binding.spot18.setBackgroundResource(R.drawable.parkspotred18);
            }
            else{
                binding.spot18.setOnClickListener(listener);
            }
            if (statusP19.equals("yes")) {
                binding.spot19.setBackgroundResource(R.drawable.parkspotred19);
            }
            else{
                binding.spot19.setOnClickListener(listener);
            }
            if (statusP20.equals("yes")) {
                binding.spot20.setBackgroundResource(R.drawable.parkspotred20);
            }
            else{
                binding.spot20.setOnClickListener(listener);
            }
            if (statusP21.equals("yes")) {
                binding.spot21.setBackgroundResource(R.drawable.parkspotred21);
            }
            else{
                binding.spot21.setOnClickListener(listener);
            }
            if (statusP22.equals("yes")) {
                binding.spot22.setBackgroundResource(R.drawable.parkspotred22);
            }
            else{
                binding.spot22.setOnClickListener(listener);
            }
            if (statusP23.equals("yes")) {
                binding.spot23.setBackgroundResource(R.drawable.parkspotred23);
            }
            else{
                binding.spot23.setOnClickListener(listener);
            }
            if (statusP24.equals("yes")) {
                binding.spot24.setBackgroundResource(R.drawable.parkspotred24);
            }
            else{
                binding.spot24.setOnClickListener(listener);
            }
            if (statusP25.equals("yes")) {
                binding.spot25.setBackgroundResource(R.drawable.parkspotred25);
            }
            else{
                binding.spot25.setOnClickListener(listener);
            }
            if (statusP26.equals("yes")) {
                binding.spot26.setBackgroundResource(R.drawable.parkspotred26);
            }
            else{
                binding.spot26.setOnClickListener(listener);
            }
            if (statusP27.equals("yes")) {
                binding.spot27.setBackgroundResource(R.drawable.parkspotred27);
            }
            else{
                binding.spot27.setOnClickListener(listener);
            }
            if (statusP28.equals("yes")) {
                binding.spot28.setBackgroundResource(R.drawable.parkspotred28);
            }
            else{
                binding.spot28.setOnClickListener(listener);
            }
            if (statusP29.equals("yes")) {
                binding.spot29.setBackgroundResource(R.drawable.parkspotred29);
            }
            else{
                binding.spot29.setOnClickListener(listener);
            }
            if (statusP30.equals("yes")) {
                binding.spot30.setBackgroundResource(R.drawable.parkspotred30);
            }
            else{
                binding.spot30.setOnClickListener(listener);
            }
            if (statusP31.equals("yes")) {
                binding.spot31.setBackgroundResource(R.drawable.parkspotred31);
            }
            else{
                binding.spot31.setOnClickListener(listener);
            }
            if (statusP32.equals("yes")) {
                binding.spot32.setBackgroundResource(R.drawable.parkspotred32);
            }
            else{
                binding.spot32.setOnClickListener(listener);
            }
            if (statusP33.equals("yes")) {
                binding.spot33.setBackgroundResource(R.drawable.parkspotred33);
            }
            else{
                binding.spot33.setOnClickListener(listener);
            }
            if (statusP34.equals("yes")) {
                binding.spot34.setBackgroundResource(R.drawable.parkspotred34);
            }
            else{
                binding.spot34.setOnClickListener(listener);
            }
            if (statusP35.equals("yes")) {
                binding.spot35.setBackgroundResource(R.drawable.parkspotred35);
            }
            else{
                binding.spot35.setOnClickListener(listener);
            }
            if (statusP36.equals("yes")) {
                binding.spot36.setBackgroundResource(R.drawable.parkspotred36);
            }
            else{
                binding.spot36.setOnClickListener(listener);
            }
        }
    }

    // Here we define the function common for clicking all the parking spots
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {                                  // On clicking the particular Parking Lot

            // To save the state of the map
            SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME_2, 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.apply();

            SharedPreferences receiverSP = getSharedPreferences(PREF_NAME_2, 0);

            if(flag == open)                                                                // means user is a fresh user
            {
                // Vibrate the device when clicked on the spot
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(60, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    vibrator.vibrate(60);
                }

                // Confirmation box
                AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
                builder.setTitle("Confirm Slot");
                builder.setMessage("Do you wish to select this slot?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {               // On clicking the Dialog Box "Yes"
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Determine which spot has been selected and make changes corresponding to that particular spot
                        ImageView imageView = (ImageView) v;
                        imageView.setBackgroundResource(android.R.color.transparent);

                        int spot = imageView.getId();
                        String slotNo="";

                        switch (spot)
                        {
                            case R.id.spot1:
                                String isP1Booked = receiverSP.getString("isP1Reserved", "null");
                                if(!isP1Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred1);
                                    slotNo = "1";
                                    editor.putString("isP1Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("1", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot2:
                                String isP2Booked = receiverSP.getString("isP2Reserved", "null");
                                if(!isP2Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred2);
                                    slotNo = "2";
                                    editor.putString("isP2Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("2", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot3:
                                String isP3Booked = receiverSP.getString("isP3Reserved", "null");
                                if(!isP3Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred3);
                                    slotNo = "3";
                                    editor.putString("isP3Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("3", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot4:
                                String isP4Booked = receiverSP.getString("isP4Reserved", "null");
                                if(!isP4Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred4);
                                    slotNo = "4";
                                    editor.putString("isP4Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("4", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot5:
                                String isP5Booked = receiverSP.getString("isP5Reserved", "null");
                                if(!isP5Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred5);
                                    slotNo = "5";
                                    editor.putString("isP5Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("5", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot6:
                                String isP6Booked = receiverSP.getString("isP6Reserved", "null");
                                if(!isP6Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred6);
                                    slotNo = "6";
                                    editor.putString("isP6Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("6", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot7:
                                String isP7Booked = receiverSP.getString("isP7Reserved", "null");
                                if(!isP7Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred7);
                                    slotNo = "7";
                                    editor.putString("isP7Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("7", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot8:
                                String isP8Booked = receiverSP.getString("isP8Reserved", "null");
                                if(!isP8Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred8);
                                    slotNo = "8";
                                    editor.putString("isP8Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("8", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot9:
                                String isP9Booked = receiverSP.getString("isP9Reserved", "null");
                                if(!isP9Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred9);
                                    slotNo = "9";
                                    editor.putString("isP9Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("9", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot10:
                                String isP10Booked = receiverSP.getString("isP10Reserved", "null");
                                if(!isP10Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred10);
                                    slotNo = "10";
                                    editor.putString("isP10Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("10", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot11:
                                String isP11Booked = receiverSP.getString("isP11Reserved", "null");
                                if(!isP11Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred11);
                                    slotNo = "11";
                                    editor.putString("isP11Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("11", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot12:
                                String isP12Booked = receiverSP.getString("isP12Reserved", "null");
                                if(!isP12Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred12);
                                    slotNo = "12";
                                    editor.putString("isP12Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("12", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot13:
                                String isP13Booked = receiverSP.getString("isP13Reserved", "null");
                                if(!isP13Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred13);
                                    slotNo = "13";
                                    editor.putString("isP13Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("13", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot14:
                                String isP14Booked = receiverSP.getString("isP14Reserved", "null");
                                if(!isP14Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred14);
                                    slotNo = "14";
                                    editor.putString("isP14Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("14", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot15:
                                String isP15Booked = receiverSP.getString("isP15Reserved", "null");
                                if(!isP15Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred15);
                                    slotNo = "15";
                                    editor.putString("isP15Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("15", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot16:
                                String isP16Booked = receiverSP.getString("isP16Reserved", "null");
                                if(!isP16Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred16);
                                    slotNo = "16";
                                    editor.putString("isP16Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("16", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot17:
                                String isP17Booked = receiverSP.getString("isP17Reserved", "null");
                                if(!isP17Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred17);
                                    slotNo = "17";
                                    editor.putString("isP17Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("17", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot18:
                                String isP18Booked = receiverSP.getString("isP18Reserved", "null");
                                if(!isP18Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred18);
                                    slotNo = "18";
                                    editor.putString("isP18Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("18", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot19:
                                String isP19Booked = receiverSP.getString("isP19Reserved", "null");
                                if(!isP19Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred19);
                                    slotNo = "19";
                                    editor.putString("isP19Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("19", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot20:
                                String isP20Booked = receiverSP.getString("isP20Reserved", "null");
                                if(!isP20Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred20);
                                    slotNo = "20";
                                    editor.putString("isP20Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("20", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot21:
                                String isP21Booked = receiverSP.getString("isP21Reserved", "null");
                                if(!isP21Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred21);
                                    slotNo = "21";
                                    editor.putString("isP21Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("21", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot22:
                                String isP22Booked = receiverSP.getString("isP22Reserved", "null");
                                if(!isP22Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred22);
                                    slotNo = "22";
                                    editor.putString("isP22Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("22", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot23:
                                String isP23Booked = receiverSP.getString("isP23Reserved", "null");
                                if(!isP23Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred23);
                                    slotNo = "23";
                                    editor.putString("isP23Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("23", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot24:
                                String isP24Booked = receiverSP.getString("isP24Reserved", "null");
                                if(!isP24Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred24);
                                    slotNo = "24";
                                    editor.putString("isP24Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("24", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot25:
                                String isP25Booked = receiverSP.getString("isP25Reserved", "null");
                                if(!isP25Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred25);
                                    slotNo = "25";
                                    editor.putString("isP25Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("25", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot26:
                                String isP26Booked = receiverSP.getString("isP26Reserved", "null");
                                if(!isP26Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred26);
                                    slotNo = "26";
                                    editor.putString("isP26Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("26", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot27:
                                String isP27Booked = receiverSP.getString("isP27Reserved", "null");
                                if(!isP27Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred27);
                                    slotNo = "27";
                                    editor.putString("isP27Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("27", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot28:
                                String isP28Booked = receiverSP.getString("isP28Reserved", "null");
                                if(!isP28Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred28);
                                    slotNo = "28";
                                    editor.putString("isP28Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("28", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot29:
                                String isP29Booked = receiverSP.getString("isP29Reserved", "null");
                                if(!isP29Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred29);
                                    slotNo = "29";
                                    editor.putString("isP29Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("29", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot30:
                                String isP30Booked = receiverSP.getString("isP30Reserved", "null");
                                if(!isP30Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred30);
                                    slotNo = "30";
                                    editor.putString("isP30Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("30", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot31:
                                String isP31Booked = receiverSP.getString("isP31Reserved", "null");
                                if(!isP31Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred31);
                                    slotNo = "31";
                                    editor.putString("isP31Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("31", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot32:
                                String isP32Booked = receiverSP.getString("isP32Reserved", "null");
                                if(!isP32Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred32);
                                    slotNo = "32";
                                    editor.putString("isP32Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("32", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot33:
                                String isP33Booked = receiverSP.getString("isP33Reserved", "null");
                                if(!isP33Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred33);
                                    slotNo = "33";
                                    editor.putString("isP33Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("33", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot34:
                                String isP34Booked = receiverSP.getString("isP34Reserved", "null");
                                if(!isP34Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred34);
                                    slotNo = "34";
                                    editor.putString("isP34Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("34", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot35:
                                String isP35Booked = receiverSP.getString("isP35Reserved", "null");
                                if(!isP35Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred35);
                                    slotNo = "35";
                                    editor.putString("isP35Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("35", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.spot36:
                                String isP36Booked = receiverSP.getString("isP36Reserved", "null");
                                if(!isP36Booked.equals("yes"))
                                {
                                    imageView.setImageResource(R.drawable.parkspotred36);
                                    slotNo = "36";
                                    editor.putString("isP36Reserved", "yes");

                                    SlotStatusClass slotStatusClass = new SlotStatusClass("36", "booked");
                                    reference.child(slotNo).setValue(slotStatusClass);
                                }
                                else
                                {
                                    Toast.makeText(MapActivity.this, "Sorry but this slot is already booked", Toast.LENGTH_SHORT).show();
                                }
                                break;
                        }

                        editor.putString("slotNumber", slotNo);
                        editor.apply();

                        Intent receiverIntent = getIntent();
                        phoneNumber = receiverIntent.getStringExtra("phoneNumber");

                        bookedUsers.add(phoneNumber);

                        Intent intent2 = new Intent(MapActivity.this, SlotDetailsActivity.class);
                        intent2.putExtra("slotNo", slotNo);
                        intent2.putExtra("phoneNumber", phoneNumber);

                        startActivity(intent2);
                        finish();
                    }
                });
                builder.setNegativeButton("NO", null);
                builder.setNeutralButton("Cancel", null);

                builder.setCancelable(false);                   // so that user cannot cancel it without answering its question

                dialog = builder.create();
                dialog.show();

            }
        }
    };
}