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

public class PropertiesActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;

    private DatabaseReference mRef, mDatabase;
    private FirebaseUser current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties);



        textView1 = findViewById(R.id.propertyCategoryId);
        textView2 = findViewById(R.id.propertyDetailsId);
        textView3 = findViewById(R.id.propertyBuyDateId);


        current_user = FirebaseAuth.getInstance().getCurrentUser();
        String user_id = current_user.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference("Registered_user/"+user_id);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String user_nid = dataSnapshot.child("userNID").getValue().toString();
                mRef = FirebaseDatabase.getInstance().getReference().child("Member").child(user_nid).child("Property");
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String string1 = dataSnapshot.child("propertyDetails").getValue().toString();
                        String string2 = dataSnapshot.child("propertyDetails").getValue().toString();
                        String string3 = dataSnapshot.child("propertyBuyDate").getValue().toString();

                        textView1.setText(string1);
                        textView2.setText(string2);
                        textView3.setText(string3);

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
