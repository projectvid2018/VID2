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

public class HajjActivity extends AppCompatActivity {


    private TextView tvName ;
    private TextView tvHajjRegNo, tvHajjFlightNo, tvHajjSession  ;
    String u_nid = "1234567890";

    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hajj);


        tvName = findViewById(R.id.hNameId);
        tvHajjRegNo = findViewById(R.id.hajjRegId);
        tvHajjFlightNo = findViewById(R.id.hajjFlightId);
        tvHajjSession = findViewById(R.id.hajjSessionId);


        mRef = FirebaseDatabase.getInstance().getReference().child("Member").child(u_nid).child("Hajj");


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("name").getValue().toString();
                String hajjRegno = dataSnapshot.child("hajjRegno").getValue().toString();
                String hajjFlightno = dataSnapshot.child("hajjFlightno").getValue().toString();
                String hajjSession = dataSnapshot.child("hajjSession").getValue().toString();


                tvName.setText(name);
                tvHajjRegNo.setText(hajjRegno);
                tvHajjFlightNo.setText(hajjFlightno);
                tvHajjSession.setText(hajjSession);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_LONG).show();

            }
        });



    }
}
