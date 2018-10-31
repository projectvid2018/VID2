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

public class DrivingActivity extends AppCompatActivity {
    private Button backButton;

    private TextView tvName, tvBirthDate,tvBloodGroup,tvVehicleId ;
    private TextView tvFatherName,tvPresentAddress,tvDrivingLicenseNo  ;
    private TextView tvDrivingLicenseCategory,tvDrivingLicenseIssue,tvDrivingLicenseEnd  ;

    private DatabaseReference mRef;
    String u_nid = "1234567890";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving);

        backButton = findViewById(R.id.backButtonId);

        tvName = findViewById(R.id.dNameId);
        tvBirthDate = findViewById(R.id.dBirthId);
        tvBloodGroup = findViewById(R.id.dBloodGroupId);
        tvFatherName = findViewById(R.id.dFatherHusbandNameId);

        tvPresentAddress = findViewById(R.id.dPresentAddress);
        tvDrivingLicenseNo = findViewById(R.id.drivingLicenseNoId);
        tvDrivingLicenseCategory = findViewById(R.id.drivingLicenseCategoryId);
        tvDrivingLicenseIssue = findViewById(R.id.drivingLicenseIssueId);
        tvDrivingLicenseEnd = findViewById(R.id.drivingLicenseEndId);
        tvVehicleId = findViewById(R.id.dVehicleId);

        mRef = FirebaseDatabase.getInstance().getReference().child("Member").child(u_nid).child("Driving");


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String name = dataSnapshot.child("name").getValue().toString();
                String dateOfBirth = dataSnapshot.child("dateOfBirth").getValue().toString();
                String bloodGroup = dataSnapshot.child("bloodGroup").getValue().toString();
                String fatherOrHusbend = dataSnapshot.child("fatherOrHusbend").getValue().toString();

                String presentAddress = dataSnapshot.child("presentAddress").getValue().toString();
                String drivingLicenseNo = dataSnapshot.child("drivingLicenseNo").getValue().toString();
                String drivinglicensecategory = dataSnapshot.child("drivinglicensecategory").getValue().toString();
                String drivingLicenseIssue = dataSnapshot.child("drivingLicenseIssue").getValue().toString();
                String drivinglicenseEnd = dataSnapshot.child("drivinglicenseEnd").getValue().toString();
                String vehicleId = dataSnapshot.child("vehicleId").getValue().toString();


                tvName.setText(name);
                tvBirthDate.setText(dateOfBirth);
                tvBloodGroup.setText(bloodGroup);
                tvFatherName.setText(fatherOrHusbend);
                tvPresentAddress.setText(presentAddress);

                tvDrivingLicenseNo.setText(drivingLicenseNo);
                tvDrivingLicenseCategory.setText(drivinglicensecategory);
                tvDrivingLicenseIssue.setText(drivingLicenseIssue);
                tvDrivingLicenseEnd.setText(drivinglicenseEnd);
                tvVehicleId.setText(vehicleId);

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
