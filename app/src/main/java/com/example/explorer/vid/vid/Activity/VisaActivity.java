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

public class VisaActivity extends AppCompatActivity {
    private Button backButton;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;


    private String u_nid ="1234567890";
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa);

        backButton = findViewById(R.id.backButtonId);


        textView1 = findViewById(R.id.vNameId);
        textView2 = findViewById(R.id.visaNidId);
        textView3 = findViewById(R.id.visaIssuedCountryId);
        textView4 = findViewById(R.id.reasonOfGoingId);
        textView5 = findViewById(R.id.visaIssueSessionId);
        textView6 = findViewById(R.id.departureDateId);
        textView7 = findViewById(R.id.arrivalDateId);
        textView8 = findViewById(R.id.visaFlightNoId);


        mRef = FirebaseDatabase.getInstance().getReference().child("Member").child(u_nid).child("Visa");


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String string1 = dataSnapshot.child("name").getValue().toString();
                String string2 = dataSnapshot.child("nid").getValue().toString();
                String string3 = dataSnapshot.child("visaIssuedCountry").getValue().toString();
                String string4 = dataSnapshot.child("reasonOfGoing").getValue().toString();
                String string5 = dataSnapshot.child("visaIssueSession").getValue().toString();
                String string6 = dataSnapshot.child("departureDate").getValue().toString();
                String string7 = dataSnapshot.child("arrivalDate").getValue().toString();
                String string8 = dataSnapshot.child("flightNo").getValue().toString();

                textView1.setText(string1);
                textView2.setText(string2);
                textView3.setText(string3);
                textView4.setText(string4);
                textView5.setText(string5);
                textView6.setText(string6);
                textView7.setText(string7);
                textView8.setText(string8);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_LONG).show();

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
