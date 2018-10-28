package com.example.explorer.vid.vid.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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

public class PassportActivity extends AppCompatActivity {

    private Button backButton;
    private TextView tvSurname, tvGivenName,tvBirthDate ;
    private TextView tvSex, tvIssueDate,tvExpiryDate,tvPersonalNo ;
    private TextView tvName, tvFatherName,tvMotherName ;
    private TextView tvSpouseName, tvPermanentAddress ;
    private TextView tvContactName, tvRelation,tvContactAddress ;
    private TextView tvContactTelephone;

    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passport);

        backButton = findViewById(R.id.backButtonId);

        tvSurname = findViewById(R.id.surnamePassportId);
        tvGivenName = findViewById(R.id.givenNameId);
        tvBirthDate = findViewById(R.id.birthPassportId);
        tvSex = findViewById(R.id.sexPassportId);
        tvIssueDate = findViewById(R.id.issueDatePassportId);
        tvExpiryDate = findViewById(R.id.expiryDatePassportId);
        tvPersonalNo = findViewById(R.id.personalPassportId);
        tvName = findViewById(R.id.namePId);
        tvFatherName = findViewById(R.id.fpID);
        tvMotherName = findViewById(R.id.mpID);
        tvSpouseName = findViewById(R.id.spID);
        tvPermanentAddress = findViewById(R.id.permanetAddresPassportID);
        tvContactName = findViewById(R.id.contactNameId);
        tvRelation = findViewById(R.id.relationshipId);
        tvContactAddress = findViewById(R.id.contactAddressID);
        tvContactTelephone = findViewById(R.id.telephoneNoID);

        mRef = FirebaseDatabase.getInstance().getReference().child("Member").child("1234567890").child("Passport");


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String surname = dataSnapshot.child("sureName").getValue().toString();
                String givenName = dataSnapshot.child("givenName").getValue().toString();
                String dateOfBirth = dataSnapshot.child("dateOfBirth").getValue().toString();
                String sex = dataSnapshot.child("sex").getValue().toString();
                String dateOfIssue = dataSnapshot.child("dateOfIssue").getValue().toString();
                String dateOfExpiry = dataSnapshot.child("dateOfExpiry").getValue().toString();
                String PassportPersonalNumber = dataSnapshot.child("PassportPersonalNumber").getValue().toString();
                String name = dataSnapshot.child("sureName").getValue().toString();
                String fatherName = dataSnapshot.child("fatherName").getValue().toString();
                String motherName = dataSnapshot.child("motherName").getValue().toString();
                String spouseOrHusbendName = dataSnapshot.child("spouseOrHusbendName").getValue().toString();
                String permanentAddress = dataSnapshot.child("permanentAddress").getValue().toString();
                String emergencyContact = dataSnapshot.child("emergencyContact").getValue().toString();
                String relashionship = dataSnapshot.child("relashionship").getValue().toString();
                String contactAddress = dataSnapshot.child("contactAddress").getValue().toString();
                String contactTelephone = dataSnapshot.child("contactTelephone").getValue().toString();

                tvSurname.setText(surname);
                tvGivenName.setText(givenName);
                tvBirthDate.setText(dateOfBirth);
                tvSex.setText(sex);
                tvIssueDate.setText(dateOfIssue);
                tvExpiryDate.setText(dateOfExpiry);
                tvPersonalNo.setText(PassportPersonalNumber);
                tvName.setText(name);
                tvFatherName.setText(fatherName);
                tvMotherName.setText(motherName);
                tvSpouseName.setText(spouseOrHusbendName);
                tvPermanentAddress.setText(permanentAddress);
                tvContactName.setText(emergencyContact);
                tvRelation.setText(relashionship);
                tvContactAddress.setText(contactAddress);
                tvContactTelephone.setText(contactTelephone);
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
