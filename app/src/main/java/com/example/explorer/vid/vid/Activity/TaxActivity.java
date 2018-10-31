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

public class TaxActivity extends AppCompatActivity {

    private Button backButton;

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
        setContentView(R.layout.activity_tax);

        backButton = findViewById(R.id.backButtonId);

        textView1 = findViewById(R.id.taxNoId);
        textView2 = findViewById(R.id.taxPayStatusId);
        textView3 = findViewById(R.id.taxPayDate);
        textView4 = findViewById(R.id.taxCategoryId);
        textView5 = findViewById(R.id.taxAmountId);


        mRef = FirebaseDatabase.getInstance().getReference().child("Member").child(u_nid).child("Tax");


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String taxNo = dataSnapshot.child("taxNo").getValue().toString();
                String taxPayStatus = dataSnapshot.child("taxPayStatus").getValue().toString();
                String taxPayDate = dataSnapshot.child("taxPayDate").getValue().toString();
                String taxCategory = dataSnapshot.child("taxCategory").getValue().toString();
                String taxAmmount = dataSnapshot.child("taxAmmount").getValue().toString();


                textView1.setText(taxNo);
                textView2.setText(taxPayStatus);
                textView3.setText(taxPayDate);
                textView4.setText(taxCategory);
                textView5.setText(taxAmmount);




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
