package com.example.explorer.vid.vid.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.explorer.vid.R;

public class FeedbackActivity extends AppCompatActivity {
    Button ButtonSendFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        final EditText nameField = (EditText) findViewById(R.id.EditTextName);
        String name = nameField.getText().toString();

        final EditText emailField = (EditText) findViewById(R.id.EditTextEmail);
        String email = emailField.getText().toString();

        final EditText feedbackField = (EditText) findViewById(R.id.EditTextFeedbackBody);
        String feedback = feedbackField.getText().toString();
        final Spinner feedbackSpinner = (Spinner) findViewById(R.id.SpinnerFeedbackType);
        String feedbackType = feedbackSpinner.getSelectedItem().toString();
        final CheckBox responseCheckbox = (CheckBox) findViewById(R.id.CheckBoxResponse);
        boolean bRequiresResponse = responseCheckbox.isChecked();
    }

    public void sendFeedback(View ButtonSendFeedback) {
        // Do click handling here
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        startActivity(emailIntent);

    }
}


