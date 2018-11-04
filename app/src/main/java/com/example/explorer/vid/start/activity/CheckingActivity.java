package com.example.explorer.vid.start.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.explorer.vid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class CheckingActivity extends AppCompatActivity {


    private EditText editTextNID, editTextBirth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ProgressBar progressBar;
    private DatePicker mDisplayDate;
    private  DatePickerDialog.OnDateSetListener mDatesetListener;
    int val = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);

        progressBar = findViewById(R.id.checkingProgressBarId);

        editTextNID = findViewById(R.id.checkingNidId);
        //editTextBirth = findViewById(R.id.checkingBirthId);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Member");

        DatePicker Dialogue;

        mDisplayDate=findViewById(R.id.checkingBirthId);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(CheckingActivity.this
                        ,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,mDatesetListener,year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDatesetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                //Log.d(TAG,"onDateSet; date;" +month "/" +day "/"+year);
                month=month+1;
                String date=day+"/"+month+"/"+year;
                //mDisplayDate.setDate(date);
            }
        };
    }

    public void Verification(View view) {
        final String nid = editTextNID.getText().toString().trim();
        final String date = editTextBirth.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        Query mobileQuery = FirebaseDatabase.getInstance().getReference()
                .child("Member").child(nid).orderByChild("dateOfBirth").equalTo(date);

        mobileQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount()> 0){
                    val = 2;
                    Toast.makeText(getApplicationContext(),"Syncing....",
                            Toast.LENGTH_SHORT).show();

                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Please insert correct data",
                            Toast.LENGTH_LONG).show();
                }
                if (val > 0){
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(CheckingActivity.this,SignupActivity.class);
                    intent.putExtra("u_nid",nid);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
