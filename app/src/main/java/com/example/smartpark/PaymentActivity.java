package com.example.smartpark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    Button payment;
    String nameOfUser, contactNumberOfUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        payment = findViewById(R.id.btnPayHere);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
    }

    public void startPayment() {
        // Get user's data
        Intent receiverIntent = getIntent();
        nameOfUser = receiverIntent.getStringExtra("nameOfUser");
        contactNumberOfUser = receiverIntent.getStringExtra("contactNumberOfUser");

        SharedPreferences receiverSF = getSharedPreferences(AdminSignup.PARKING_FEE, 0);
        String amount = receiverSF.getString("ParkingLotFee", "1");

        int parkingLotFee = Integer.parseInt(amount)*100;

        // initialize Razorpay checkout
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_JEFYir3ApkZjZu");
        checkout.setImage(R.drawable.app_logo);

        // initialize JSON object
        JSONObject object = new JSONObject();

        try {
            object.put("name", nameOfUser);
            object.put("contact", contactNumberOfUser);
            object.put("description", "Test Payment");
            object.put("theme.color", "#528FF0");
            object.put("currency", "INR");
            object.put("amount", parkingLotFee);
//            object.put("send_sms_hash", true);

            checkout.open(PaymentActivity.this, object);
        } catch (JSONException e) {
            Toast.makeText(PaymentActivity.this, "Error in payment", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {

        SharedPreferences sharedPreferences = getSharedPreferences(HomePageAdmin.ADMIN_PREF, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("paymentCleared", true);
        editor.putString("exitingUserPhone", contactNumberOfUser);
        editor.apply();

        SharedPreferences sharedPreferences1 = getSharedPreferences(MainActivity.FOR_USER_PAYMENT_STATUS, 0);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        editor1.putBoolean("showUserPaymentStatus", true);
        editor1.apply();

        Toast.makeText(PaymentActivity.this, "Payment Successful", Toast.LENGTH_SHORT).show();

        reference = FirebaseDatabase.getInstance().getReference(PaidUsersClass.class.getSimpleName());

        PaidUsersClass paidUsersClass = new PaidUsersClass(nameOfUser, contactNumberOfUser, true);
        reference.child(contactNumberOfUser).setValue(paidUsersClass);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(PaymentActivity.this, "Error:" + s, Toast.LENGTH_SHORT).show();
    }
}