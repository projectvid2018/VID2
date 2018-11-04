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

public class BankActivity extends AppCompatActivity {



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
        setContentView(R.layout.activity_bank);



        textView1 = findViewById(R.id.baNameId);
        textView2 = findViewById(R.id.baPresentAddressId);
        textView3 = findViewById(R.id.baNidId);
        textView4 = findViewById(R.id.baMobileId);

        textView5 = findViewById(R.id.bankNameId);
        textView6 = findViewById(R.id.bankAccountNoId);
        textView7 = findViewById(R.id.bankAccCategoryId);
        textView8 = findViewById(R.id.incomeSourceId);


        mRef = FirebaseDatabase.getInstance().getReference().child("Member").child(u_nid).child("Bank");


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String name = dataSnapshot.child("name").getValue().toString();
                String presentAddress = dataSnapshot.child("presentAddress").getValue().toString();
                String nid = dataSnapshot.child("nid").getValue().toString();
                String mobile = dataSnapshot.child("mobile").getValue().toString();

                String bankName = dataSnapshot.child("bankName").getValue().toString();
                String bankAccno = dataSnapshot.child("bankAccno").getValue().toString();
                String bankAccCategory = dataSnapshot.child("bankAccCategory").getValue().toString();
                String incomeSource = dataSnapshot.child("incomeSource").getValue().toString();


                textView1.setText(name);
                textView2.setText(presentAddress);
                textView3.setText(nid);
                textView4.setText(mobile);

                textView5.setText(bankName);
                textView6.setText(bankAccno);
                textView7.setText(bankAccCategory);
                textView8.setText(incomeSource);




            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_LONG).show();

            }
        });

    }
}
