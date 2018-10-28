package com.example.explorer.vid.start.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.explorer.vid.R;
import com.example.explorer.vid.vid.Activity.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;

    private static final String TAG= "SignupActivity";
    private TextView mDisplayDate;
    private  DatePickerDialog.OnDateSetListener mDatesetListener;

    private EditText editTextNid;
    private EditText editTextUsername;
    private EditText editTextDate;
    private EditText editTextPhoneNo;
    private EditText editTextEmail;
    private EditText editTextPassword;
    CardView cardView;

    private ProgressBar progressBar;

    private  String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    DatabaseReference databaseReference;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseReference = FirebaseDatabase.getInstance().getReference("people");

        editTextNid = findViewById(R.id.nidId);
        editTextUsername = findViewById(R.id.userNameId);
        editTextDate = findViewById(R.id.day_picker_selected_date_layout);
        editTextPhoneNo = findViewById(R.id.phoneNoId);
        editTextEmail = findViewById(R.id.emailId);
        editTextPassword = findViewById(R.id.passwordId);
        cardView = findViewById(R.id.SignupId);

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressbarId);

        //DatePicker Dialogue/spiner

        mDisplayDate=(TextView) findViewById((R.id.day_picker_selected_date_layout));
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(SignupActivity.this
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
                String date=month+"/"+day+"/"+year;
                mDisplayDate.setText(date);
            }
        };

    }

    public void SignUp(View view)
    {
        addMember();
    }

    public void addMember(){
        String nid = editTextNid.getText().toString().trim();
        String username = editTextUsername.getText().toString().trim();
        String  date= editTextDate.getText().toString().trim();
        String phoneNo = editTextPhoneNo.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // NID
        if(nid.isEmpty()){
            editTextNid.setError("Insert NID");
            editTextNid.requestFocus();
            return;
        }
        else if (nid.length()<10  ){
            editTextNid.setError("Insert valid NID");
            editTextNid.requestFocus();
            return;
        }

        //UserName
        else if(username.isEmpty()){
            editTextUsername.setError("Insert user name");
            editTextUsername.requestFocus();
            return;
        }

        // Date
        else if(date.isEmpty()){
            editTextDate.setError("Insert date of birth");
            editTextDate.requestFocus();
            return;
        }

        //Phone Number
        else if(phoneNo.isEmpty()){
            editTextPhoneNo.setError("Insert phone number");
            editTextPhoneNo.requestFocus();
            return;
        }
        else if(phoneNo.length()<11  ){
            editTextPhoneNo.setError("Insert valid phone number");
            editTextPhoneNo.requestFocus();
            return;
        }




        //Email
        else if(email.isEmpty()){
            editTextEmail.setError("Insert email");
            editTextEmail.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Insert valid email");
            editTextEmail.requestFocus();
            return;
        }




        //password
        else if(password.isEmpty()){
            editTextPassword.setError("Insert password");
            editTextPassword.requestFocus();
            return;
        }
        else if(password.length()<6){
            editTextPassword.setError("Insert valid password");
            editTextPassword.requestFocus();
            return;
        }


        else{
            String id = databaseReference.push().getKey();

            People people = new People(id,nid,username,date,phoneNo,email,password);
            databaseReference.child(nid).setValue(people);



            progressBar.setVisibility(View.VISIBLE);

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"User Registration Successful",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }else {
                        if(task.getException() instanceof FirebaseAuthUserCollisionException){
                            Toast.makeText(getApplicationContext(),"You are already registered",Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }

    }



}
