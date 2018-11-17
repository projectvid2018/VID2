package com.example.explorer.vid.start.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;

import android.widget.Toast;

import com.example.explorer.vid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import at.markushi.ui.CircleButton;

public class CheckingActivity extends AppCompatActivity {

    public static final String EXTRA_NID = "nid";
    public static final String EXTRA_DATE = "date";


    private EditText editTextNID, editTextBirth;
    private CardView checkButton, nextButton;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ProgressBar progressBar;
    private CircleButton circleButtonCheck ;

    private  DatePickerDialog.OnDateSetListener mDateSetListener;
    int val = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);

        progressBar = findViewById(R.id.checkingProgressBarId);
        editTextNID = findViewById(R.id.checkingNidId);
        editTextBirth = findViewById(R.id.checkingBirthDateId);
        circleButtonCheck = findViewById(R.id.circleCheckId);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Member");

        //DatePicker Dialogue;
        editTextBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(
                        CheckingActivity.this,
                        android.R.style.Theme_Holo_Dialog
                        ,mDateSetListener,year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                //Log.d(TAG,"onDateSet; date;" +month "/" +day "/"+year);
                month = month+1;

                if(day< 10 && month>10){
                    String date = year+"-"+month+"-0"+day;
                    editTextBirth.setText(date);
                }
                else if(month < 10 && day >10){
                    String date = year+"-0"+month+"-"+day;
                    editTextBirth.setText(date);
                }
                else if(day < 10 && month< 10){
                    String date = year+"-0"+month+"-0"+day;
                    editTextBirth.setText(date);
                }
                else {
                    String date = year+"-"+month+"-"+day;
                    editTextBirth.setText(date);

                }
                //mDisplayDate.setDate(date);
            }
        };
        circleButtonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Verification();
            }
        });

    }





    private void Verification() {
        final String nid = editTextNID.getText().toString().trim();
        final String date = editTextBirth.getText().toString().trim();

        if (nid.isEmpty()){
            editTextNID.setError("Please enter NID");
            editTextNID.requestFocus();
            return;
        }
        if (date.isEmpty()){
            editTextBirth.setError("Please enter date of birth");
            editTextBirth.requestFocus();
            return;
        }
        if (nid.length() > 11) {
            editTextNID.setError("Please enter correct NID");
            editTextNID.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        final Query BirthDateQuery = FirebaseDatabase.getInstance().getReference()
                .child("Member/"+nid).orderByChild("dateOfBirth").equalTo(date);


        Query userQuery = FirebaseDatabase.getInstance().getReference()
                .child("Registered_user").orderByChild("userNID").equalTo(nid);

        userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount()> 0){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Your nid already registered",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    BirthDateQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getChildrenCount()> 0){
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),"You can registered....",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                                intent.putExtra(EXTRA_NID,nid);
                                intent.putExtra(EXTRA_DATE,date);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),"Please insert correct data",
                                        Toast.LENGTH_LONG).show();
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }


}
