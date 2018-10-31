package com.example.explorer.vid.start.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.explorer.vid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CheckingActivity extends AppCompatActivity {


    private EditText editTextNID, editTextBirth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    int val = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);

        editTextNID = findViewById(R.id.checkingBirthId);
        editTextBirth = findViewById(R.id.checkingBirthId);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
    }

    public void Verification(View view) {
        final String nid = editTextNID.getText().toString().trim();
        final String date = editTextBirth.getText().toString().trim();

        Query mobileQuery = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(nid).orderByChild("BirthDate").equalTo(date);

        mobileQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount()> 0){
                    val = 2;
                    Toast.makeText(getApplicationContext(),"Syncing....",
                            Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getApplicationContext(),"Please insert correct data",
                            Toast.LENGTH_LONG).show();
                }
                if (val > 0){
                    Intent intent = new Intent(CheckingActivity.this,SignupActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
