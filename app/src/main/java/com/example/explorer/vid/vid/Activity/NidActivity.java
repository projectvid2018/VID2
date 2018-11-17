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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NidActivity extends AppCompatActivity {
    private TextView tvName, tvFatherName,tvMotherName;
    private TextView tvBirthDate,tvNid, tvPermanentAddress,tvBloodGroup  ;
    private DatabaseReference mRef, mDatabase;
    private FirebaseUser current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nid);


        tvName = findViewById(R.id.nNameId);
        tvFatherName = findViewById(R.id.nFatherNameId);
        tvMotherName = findViewById(R.id.nMotherNameId);
        tvBirthDate = findViewById(R.id.nBirthDateId);
        tvNid = findViewById(R.id.nIdNo);
        tvPermanentAddress = findViewById(R.id.nPermanentId);
        tvBloodGroup = findViewById(R.id.nBloodId);

        current_user = FirebaseAuth.getInstance().getCurrentUser();
        String user_id = current_user.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference("Registered_user/"+user_id);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String user_nid = dataSnapshot.child("userNID").getValue().toString();
                mRef = FirebaseDatabase.getInstance().getReference().child("Member").child(user_nid).child("Personal");
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String name = dataSnapshot.child("name").getValue().toString();
                        String fatherName = dataSnapshot.child("fatherName").getValue().toString();
                        String motherName = dataSnapshot.child("motherName").getValue().toString();
                        String dateOfBirth = dataSnapshot.child("dateOfBirth").getValue().toString();
                        String nid = dataSnapshot.child("nid").getValue().toString();

                        String permanentAddress = dataSnapshot.child("permanentAddress").getValue().toString();
                        String bloodGroup = dataSnapshot.child("bloodGroup").getValue().toString();


                        tvName.setText(name);
                        tvFatherName.setText(fatherName);
                        tvMotherName.setText(motherName);
                        tvBirthDate.setText(dateOfBirth);
                        tvNid.setText(nid);
                        tvPermanentAddress.setText(permanentAddress);
                        tvBloodGroup.setText(bloodGroup);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_LONG).show();

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_LONG).show();

            }
        });





    }
}
