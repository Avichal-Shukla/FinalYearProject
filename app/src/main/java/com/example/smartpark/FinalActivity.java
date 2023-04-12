package com.example.smartpark;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.HashSet;

public class FinalActivity extends AppCompatActivity {

    public static final String FINAL_EXIT = "FinalExit";

    Button btnFinalBack;
    String currentUserContact, slotOfExitingUser;
    TextView finalMessage;
    FirebaseDatabase rootNode;
    DatabaseReference reference, reference2, reference3, reference4;
    HashSet<String> userContacts = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        // Whenever a user enters this activity, the payment status TextView begins to show itself
        SharedPreferences sharedPreferences = getSharedPreferences(HomePageAdmin.ADMIN_PREF, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("clearTextView", false);
        editor.apply();

        finalMessage = findViewById(R.id.tvFinalMessage);

        SharedPreferences receiverSF = getSharedPreferences(SlotDetailsActivity.SLOT_DETAIL_PREF, 0);
        slotOfExitingUser = receiverSF.getString("mySlot", "0");

        Intent receiverIntent = getIntent();
        currentUserContact = receiverIntent.getStringExtra("phoneNumber");

        reference2 = FirebaseDatabase.getInstance().getReference(PaidUsersClass.class.getSimpleName());

        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // Loop through the child nodes of the "PaidUserClass" node
                for (DataSnapshot childSnapshot : snapshot.getChildren())
                {
                    // Get the key (user ID) and data (user object) for each child node of UserClass node
                    String userPhones = childSnapshot.getKey();

                    HashMap<String, Object> userData = (HashMap<String, Object>) childSnapshot.getValue();
//                    String userName = (String) userData.get("userName");

                    userContacts.add(userPhones);

                }

                if(userContacts.contains(currentUserContact))
                {
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("BookedUsersClass");

                    // Deleting the current User data from BookedUsersClass reference node
                    DatabaseReference nodeToBeDeleted = reference.child(currentUserContact);

                    nodeToBeDeleted.removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            if (databaseError != null)
                            {
                                Toast.makeText(FinalActivity.this, "Some error occurred", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                SharedPreferences sharedPreferences1 = getSharedPreferences(FINAL_EXIT, 0);
                                SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                                editor1.putString("slotToDeallocate", slotOfExitingUser);
                                editor1.apply();

                                // Remove that slot from SlotStatusClass which was occupied till now
                                reference3 = FirebaseDatabase.getInstance().getReference("SlotStatusClass");
                                DatabaseReference slotNodeToBeDeleted = reference3.child(slotOfExitingUser);

                                slotNodeToBeDeleted.removeValue(new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        if (error != null)
                                        {
                                            Toast.makeText(FinalActivity.this, "Some error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(FinalActivity.this, "Slot deallocated successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                                // Deleting exitingUser data from PaidUsersClass
                                reference4 = FirebaseDatabase.getInstance().getReference("PaidUsersClass");
                                DatabaseReference paidUserNodeToBeDeleted = reference4.child(currentUserContact);

                                paidUserNodeToBeDeleted.removeValue(new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        if(error != null)
                                        {
                                            Toast.makeText(FinalActivity.this, "Some error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(FinalActivity.this, "User's payment status has been cleared from database", Toast.LENGTH_SHORT).show();
                                            SharedPreferences sharedPreferences = getSharedPreferences(HomePageAdmin.ADMIN_PREF, 0);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putBoolean("paymentCleared", false);
                                            editor.putBoolean("clearTextView", true);   // Whenever a user completes everything, the payment status TextView vanishes
                                            editor.apply();
                                        }
                                    }
                                });

                                Toast.makeText(FinalActivity.this, "Exit successfully", Toast.LENGTH_SHORT).show();
                                SharedPreferences sharedPreferences = getSharedPreferences(HomePageAdmin.ADMIN_PREF, 0);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("paymentCleared", true);
                                editor.apply();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(FinalActivity.this, "Payment pending", Toast.LENGTH_SHORT).show();
                    finalMessage.setText("Please make the payment first!");
                }

                btnFinalBack = findViewById(R.id.btnFinalBack);
                btnFinalBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(FinalActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}