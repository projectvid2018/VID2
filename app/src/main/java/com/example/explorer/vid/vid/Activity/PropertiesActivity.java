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

public class PropertiesActivity extends AppCompatActivity {

    private Button backButton;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;




    private String u_nid ="1234567890";
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties);

        backButton = findViewById(R.id.backButtonId);

        textView1 = findViewById(R.id.propertyCategoryId);
        textView2 = findViewById(R.id.propertyDetailsId);
        textView3 = findViewById(R.id.propertyBuyDateId);


        mRef = FirebaseDatabase.getInstance().getReference().child("Member").child(u_nid).child("Property");


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


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
