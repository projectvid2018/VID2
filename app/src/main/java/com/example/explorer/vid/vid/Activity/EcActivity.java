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

public class EcActivity extends AppCompatActivity {

    private TextView tvName,tvNid, tvBirthDate ;
    private TextView tvEcZone, tvEcSerialNo, tvEcVotingAddress  ;
    private DatabaseReference mRef, mDatabase;
    private FirebaseUser current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ec);


        tvName = findViewById(R.id.eNameId);
        tvNid = findViewById(R.id.eNidId);
        tvBirthDate = findViewById(R.id.eBirthDate);
        tvEcZone = findViewById(R.id.eEcZoneId);
        tvEcSerialNo = findViewById(R.id.ecSerialNoId);
        tvEcVotingAddress = findViewById(R.id.ecVotingAddressId);

        current_user = FirebaseAuth.getInstance().getCurrentUser();
        String user_id = current_user.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference("Registered_user/"+user_id);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String user_nid = dataSnapshot.child("userNID").getValue().toString();
                mRef = FirebaseDatabase.getInstance().getReference().child("Member").child(user_nid).child("EC");
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        String name = dataSnapshot.child("name").getValue().toString();
                        String nid = dataSnapshot.child("nid").getValue().toString();
                        String dateOfBirth = dataSnapshot.child("dateOfBirth").getValue().toString();

                        String ec_Zone = dataSnapshot.child("ec_Zone").getValue().toString();
                        String ec_SerialNo = dataSnapshot.child("ec_SerialNo").getValue().toString();
                        String ec_VotingAddress = dataSnapshot.child("ec_VotingAddress").getValue().toString();


                        tvName.setText(name);
                        tvBirthDate.setText(dateOfBirth);
                        tvNid.setText(nid);
                        tvEcZone.setText(ec_Zone);
                        tvEcSerialNo.setText(ec_SerialNo);
                        tvEcVotingAddress.setText(ec_VotingAddress);


                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_LONG).show();

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
