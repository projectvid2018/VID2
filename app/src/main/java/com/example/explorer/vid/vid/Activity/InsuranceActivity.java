package com.example.explorer.vid.vid.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class InsuranceActivity extends AppCompatActivity {


    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;


    private DatabaseReference mRef, mDatabase;
    private FirebaseUser current_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);

        textView1 = findViewById(R.id.iNameId);
        textView2 = findViewById(R.id.iPresentAddress);
        textView3 = findViewById(R.id.iIssueDate);
        textView4 = findViewById(R.id.iCompanyId);
        textView5 = findViewById(R.id.iPolicyNameId);
        textView6 = findViewById(R.id.iCategoryId);
        textView7 = findViewById(R.id.iSessionId);

        current_user = FirebaseAuth.getInstance().getCurrentUser();
        String user_id = current_user.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference("Registered_user/"+user_id);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String user_nid = dataSnapshot.child("userNID").getValue().toString();
                mRef = FirebaseDatabase.getInstance().getReference().child("Member").child(user_nid).child("Insurance");

                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        String taxNo = dataSnapshot.child("name").getValue().toString();
                        String taxPayStatus = dataSnapshot.child("presentAddress").getValue().toString();
                        String taxPayDate = dataSnapshot.child("insuranceIssueDate").getValue().toString();
                        String taxCategory = dataSnapshot.child("insuranceComName").getValue().toString();
                        String taxAmmount = dataSnapshot.child("insurancePolicyName").getValue().toString();
                        String insuranceCategory = dataSnapshot.child("insuranceCategory").getValue().toString();
                        String insuranceSession = dataSnapshot.child("insuranceSession").getValue().toString();


                        textView1.setText(taxNo);
                        textView2.setText(taxPayStatus);
                        textView3.setText(taxPayDate);
                        textView4.setText(taxCategory);
                        textView5.setText(taxAmmount);
                        textView5.setText(insuranceCategory);
                        textView5.setText(insuranceSession);




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
