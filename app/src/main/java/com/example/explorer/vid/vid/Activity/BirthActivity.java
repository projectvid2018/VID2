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

public class BirthActivity extends AppCompatActivity {



    private TextView tvName, tvBirthDate,tvSex ,tvBirthPlace;
    private TextView tvFatherName,tvMotherName,tvPermanentAddress  ;

    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth);


        tvName = findViewById(R.id.nameBirthId);
        tvBirthDate = findViewById(R.id.dateBirthId);
        tvSex = findViewById(R.id.sexBirthId);
        tvBirthPlace = findViewById(R.id.birthPlaceID);
        tvFatherName = findViewById(R.id.fatherNameBirthID);
        tvMotherName = findViewById(R.id.bMotherNameID);
        tvPermanentAddress = findViewById(R.id.bPermanentAddressID);

        mRef = FirebaseDatabase.getInstance().getReference().child("Member").child("1234567890").child("Personal");


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String name = dataSnapshot.child("name").getValue().toString();
                String dateOfBirth = dataSnapshot.child("dateOfBirth").getValue().toString();
                String sex = dataSnapshot.child("sex").getValue().toString();
                String birthPlace = dataSnapshot.child("birthplace").getValue().toString();

                String fatherName = dataSnapshot.child("fatherName").getValue().toString();
                String motherName = dataSnapshot.child("motherName").getValue().toString();
                String permanentAddress = dataSnapshot.child("permanentAddress").getValue().toString();


                tvName.setText(name);
                tvBirthDate.setText(dateOfBirth);
                tvSex.setText(sex);
                tvBirthPlace.setText(birthPlace);
                tvFatherName.setText(fatherName);
                tvMotherName.setText(motherName);
                tvPermanentAddress.setText(permanentAddress);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_LONG).show();

            }
        });



    }
}
