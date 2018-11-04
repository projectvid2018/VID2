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

public class LawActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;



    private String u_nid ="1234567890";
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law);


        textView1 = findViewById(R.id.lNameId);
        textView2 = findViewById(R.id.lCaseId);
        textView3 = findViewById(R.id.lPendingCasesId);
        textView4 = findViewById(R.id.lCaseFileNoId);
        textView5 = findViewById(R.id.lCaseDetails);

        mRef = FirebaseDatabase.getInstance().getReference().child("Member").child(u_nid).child("Law");


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String string1 = dataSnapshot.child("name").getValue().toString();
                String string2 = dataSnapshot.child("lawCase").getValue().toString();
                String string3 = dataSnapshot.child("pendingCases").getValue().toString();
                String string4 = dataSnapshot.child("caseFileNo").getValue().toString();
                String string5 = dataSnapshot.child("caseDetails").getValue().toString();


                textView1.setText(string1);
                textView2.setText(string2);
                textView3.setText(string3);
                textView4.setText(string4);
                textView5.setText(string5);





            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_LONG).show();

            }
        });

    }
}
