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

public class MarriageActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;

    private DatabaseReference mRef, mDatabase;
    private FirebaseUser current_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marriage);


        textView1 = findViewById(R.id.spouseNameId);
        textView2 = findViewById(R.id.spouseFatherNameId);
        textView3 = findViewById(R.id.spouseMotherNameId);
        textView4 = findViewById(R.id.spouseNid);
        textView5 = findViewById(R.id.marriageDateId);
        textView6 = findViewById(R.id.marriageCertificateNo);

        current_user = FirebaseAuth.getInstance().getCurrentUser();
        String user_id = current_user.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference("Registered_user/"+user_id);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String user_nid = dataSnapshot.child("userNID").getValue().toString();
                mRef = FirebaseDatabase.getInstance().getReference().child("Member").child(user_nid).child("Marriage");

                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        String spouseName = dataSnapshot.child("spouseName").getValue().toString();
                        String spousefather = dataSnapshot.child("spousefather").getValue().toString();
                        String spousemother = dataSnapshot.child("spousemother").getValue().toString();
                        String spouseNid = dataSnapshot.child("spouseNid").getValue().toString();
                        String marriageDate = dataSnapshot.child("marriageDate").getValue().toString();
                        String marriageCertificateNo = dataSnapshot.child("marriageCertificateNo").getValue().toString();


                        textView1.setText(spouseName);
                        textView2.setText(spousefather);
                        textView3.setText(spousemother);
                        textView4.setText(spouseNid);
                        textView5.setText(marriageDate);
                        textView6.setText(marriageCertificateNo);



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
