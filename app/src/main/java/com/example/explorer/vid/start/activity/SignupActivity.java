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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import static com.example.explorer.vid.start.activity.CheckingActivity.EXTRA_DATE;
import static com.example.explorer.vid.start.activity.CheckingActivity.EXTRA_NID;

public class SignupActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;

    private static final String TAG= "SignupActivity";
/*    private TextView mDisplayDate;
    private  DatePickerDialog.OnDateSetListener mDatesetListener;*/


    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConPassword;
    CardView cardView;

    private ProgressBar progressBar;

    private  String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    DatabaseReference userDatabase;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);




        editTextEmail = findViewById(R.id.emailId);
        editTextPassword = findViewById(R.id.passwordId);
        editTextConPassword = findViewById(R.id.confirmPasswordId);
        cardView = findViewById(R.id.SignupId);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbarId);




    }

    public void SignUp(View view)
    {
        addMember();
    }

    public void addMember(){

        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConPassword.getText().toString().trim();

        Intent intent = getIntent();
        final String nid = intent.getStringExtra(EXTRA_NID);
        final String birthDate = intent.getStringExtra(EXTRA_DATE);

        //Email
        if(email.isEmpty()){
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

        else if(confirmPassword.isEmpty()){
            editTextConPassword.setError("Insert confirm password");
            editTextConPassword.requestFocus();
            return;
        }
        else if(password.length()<6){
            editTextPassword.setError("Insert valid password");
            editTextPassword.requestFocus();
            return;
        }
        else if (!password.equals(confirmPassword)){
            editTextConPassword.setError("Password doesn't match");
        }

        else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                        String id = current_user.getUid();
                        userDatabase = FirebaseDatabase.getInstance()
                                .getReference("Registered_user/"+id);

                        User newUser = new User(id,nid,birthDate,email,password);

                        userDatabase.setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(),"User Registration Successful",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });

                    }else {
                        progressBar.setVisibility(View.GONE);
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
