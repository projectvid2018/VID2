package com.example.explorer.vid.vid.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.explorer.vid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoActivity extends AppCompatActivity {

    private String u_nid ="1234567890";
    private DatabaseReference mRef;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;
    private TextView textView10;
    private TextView textView11;
    private TextView textView12;
    private TextView textView13;
    private TextView textView14;
    private TextView textView15;
    private TextView textView16;
    private TextView textView17;
    private TextView textView18;
    private TextView textView19;
    private TextView textView20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        textView1 = findViewById(R.id.perNameId);
        textView2 = findViewById(R.id.perSurNameId);
        textView3 = findViewById(R.id.perNidId);
        textView4 = findViewById(R.id.perFatherNameId);
        textView5 = findViewById(R.id.perMotherNameId);
        textView6 = findViewById(R.id.perBirthId);
        textView7 = findViewById(R.id.perPresentAddId);
        textView8 = findViewById(R.id.perPermanentAddId);
        textView9 = findViewById(R.id.nationalityId);
        textView10 = findViewById(R.id.perSexId);
        textView11 = findViewById(R.id.perHeightId);
        textView12 = findViewById(R.id.perWeightId);
        textView13 = findViewById(R.id.perReligionId);
        textView14 = findViewById(R.id.perBloodGroupId);
        textView15 = findViewById(R.id.disabilitiesId);
        textView16 = findViewById(R.id.disabilityTypeId);
        textView17 = findViewById(R.id.perBirthPlaceId);
        textView18 = findViewById(R.id.personalEmailId);
        textView19 = findViewById(R.id.businessEmailId);
        textView20 = findViewById(R.id.contactNoId);


        mRef = FirebaseDatabase.getInstance().getReference().child("Member").child(u_nid).child("Personal");


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String string1 = dataSnapshot.child("name").getValue().toString();
                String string2 = dataSnapshot.child("surName").getValue().toString();
                String string3 = dataSnapshot.child("nid").getValue().toString();
                String string4 = dataSnapshot.child("fatherName").getValue().toString();
                String string5 = dataSnapshot.child("motherName").getValue().toString();
                String string6 = dataSnapshot.child("dateOfBirth").getValue().toString();
                String string7 = dataSnapshot.child("presentAddress").getValue().toString();
                String string8 = dataSnapshot.child("permanentAddress").getValue().toString();
                String string9 = dataSnapshot.child("nationality").getValue().toString();
                String string10 = dataSnapshot.child("sex").getValue().toString();
                String string11 = dataSnapshot.child("height").getValue().toString();
                String string12 = dataSnapshot.child("weight").getValue().toString();
                String string13 = dataSnapshot.child("religion").getValue().toString();
                String string14 = dataSnapshot.child("bloodGroup").getValue().toString();
                String string15 = dataSnapshot.child("disabilities").getValue().toString();
                String string16 = dataSnapshot.child("disabilityType").getValue().toString();
                String string17 = dataSnapshot.child("birthplace").getValue().toString();
                String string18 = dataSnapshot.child("personalEmail").getValue().toString();
                String string19 = dataSnapshot.child("businessEmail").getValue().toString();
                String string20 = dataSnapshot.child("contactNo").getValue().toString();

                textView1.setText(string1);
                textView2.setText(string2);
                textView3.setText(string3);
                textView4.setText(string4);
                textView5.setText(string5);
                textView6.setText(string6);
                textView7.setText(string7);
                textView8.setText(string8);
                textView9.setText(string9);
                textView10.setText(string10);
                textView11.setText(string11);
                textView12.setText(string12);
                textView13.setText(string13);
                textView14.setText(string14);
                textView15.setText(string15);
                textView16.setText(string16);
                textView17.setText(string17);
                textView18.setText(string18);
                textView19.setText(string19);
                textView20.setText(string20);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_LONG).show();

            }
        });


    }
}
