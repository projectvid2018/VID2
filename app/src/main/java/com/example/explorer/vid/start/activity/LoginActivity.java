package com.example.explorer.vid.start.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.explorer.vid.R;
import com.example.explorer.vid.vid.Activity.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_MAIL = "email";

    private EditText editTextEmail;
    private EditText editTextPassword;
    private CardView cardView;
    private ProgressBar progressBarLogIn;


    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cardView = findViewById(R.id.cardViewIdLogIn);
        editTextEmail = findViewById(R.id.logInEmailId);
        editTextPassword = findViewById(R.id.logInPasswordId);
        progressBarLogIn = findViewById(R.id.loginProgressbarId);

        firebaseAuth = FirebaseAuth.getInstance();

    }


    public void loginActivity(View view) {
        progressBarLogIn.setVisibility(View.VISIBLE);
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("please insert email");
            editTextEmail.requestFocus();
            progressBarLogIn.setVisibility(View.GONE);
            return;

        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("please Insert valid email");
            editTextEmail.requestFocus();
            progressBarLogIn.setVisibility(View.GONE);
            return;
        }
        //password
        else if(password.isEmpty()){
            editTextPassword.setError("please insert password");
            editTextPassword.requestFocus();
            progressBarLogIn.setVisibility(View.GONE);
            return;
        }

        else {
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                progressBarLogIn.setVisibility(View.GONE);
                                intent.putExtra(EXTRA_MAIL,email);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Please enter correct password or email.",Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }
    }

    public void AddNewUser(View view) {
        Intent intent = new Intent(getApplicationContext(),CheckingActivity.class);
        startActivity(intent);
    }


    public void ForgotPassword(View view) {
        Intent intent = new Intent(getApplicationContext(),ForgotpassActivity.class);
        startActivity(intent);
    }
}
