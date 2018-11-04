package com.example.explorer.vid.start.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.explorer.vid.R;

import java.util.Calendar;

public class ForgotpassActivity extends AppCompatActivity {


    private static final String TAG= "ForgotpassActivity";
    private TextView mDisplayDate;
    private  DatePickerDialog.OnDateSetListener mDatesetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);


        mDisplayDate=(TextView) findViewById((R.id.day_picker_selected_date_layout));

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(ForgotpassActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDatesetListener,year,month,day);

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
    public void Register(View view) {
    }

}
