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

public class EduActivity extends AppCompatActivity {

    private TextView tvName,tvBirthDate, tvSchoolName,tvSscRoll ;
    private TextView tvSscRegistration, tvSscGroup,tvSscResult ;
    private TextView tvSscBoard, tvSscSession, tvSscPassingYear ;

    private TextView tvCollageName, tvHscRoll,tvHscReg ;
    private TextView tvHscGroup,tvHscResult ;
    private TextView tvHscBoard, tvHscSession, tvHscPassingYear ;

    private TextView tvUniName, tvUniID, tvUniDepartment ;
    private TextView tvUniSession,tvUniPassYear;

    private DatabaseReference mRef;
    private String u_nid ="1234567890";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu);


        tvName = findViewById(R.id.nameEduId);
        tvBirthDate = findViewById(R.id.dateEduId);
        tvSchoolName = findViewById(R.id.schoolNameId);
        tvSscRoll = findViewById(R.id.sscRollId);
        tvSscRegistration = findViewById(R.id.sscRegId);
        tvSscGroup = findViewById(R.id.sscGroupId);
        tvSscResult = findViewById(R.id.sscResultId);
        tvSscBoard = findViewById(R.id.sscBoardId);
        tvSscSession = findViewById(R.id.sscSessionId);
        tvSscPassingYear = findViewById(R.id.sscPassingYearId);

        tvCollageName = findViewById(R.id.collageNameId);
        tvHscRoll = findViewById(R.id.hscRollId);
        tvHscReg = findViewById(R.id.hscRegId);
        tvHscGroup = findViewById(R.id.hscGroupId);
        tvHscResult = findViewById(R.id.hscResultId);
        tvHscBoard = findViewById(R.id.hscBoardId);
        tvHscSession = findViewById(R.id.hscSessionId);
        tvHscPassingYear = findViewById(R.id.hscPassingYearId);

        tvUniName = findViewById(R.id.uniNameId);
        tvUniID = findViewById(R.id.uniID);
        tvUniDepartment = findViewById(R.id.uniDepartmentId);
        tvUniSession = findViewById(R.id.uniSessionId);
        tvUniPassYear = findViewById(R.id.uniPassingYearId);

        mRef = FirebaseDatabase.getInstance().getReference().child("Member").child(u_nid).child("Education");


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("name").getValue().toString();
                String dateOfBirth = dataSnapshot.child("dateOfBirth").getValue().toString();
                String schoolName = dataSnapshot.child("schoolName").getValue().toString();
                String sscRoll = dataSnapshot.child("sscRoll").getValue().toString();
                String sscRegistration = dataSnapshot.child("sscRegistration").getValue().toString();
                String sscGroup = dataSnapshot.child("sscGroup").getValue().toString();
                String sscResult = dataSnapshot.child("sscResult").getValue().toString();
                String sscBoard = dataSnapshot.child("sscBoard").getValue().toString();
                String sscSession = dataSnapshot.child("sscSession").getValue().toString();
                String sscPassingYear = dataSnapshot.child("sscPassingYear").getValue().toString();

                String collageName = dataSnapshot.child("collageName").getValue().toString();
                String hscRoll = dataSnapshot.child("hscRoll").getValue().toString();
                String hscReg = dataSnapshot.child("hscReg").getValue().toString();
                String hscGroup = dataSnapshot.child("hscGroup").getValue().toString();
                String hscResult = dataSnapshot.child("hscResult").getValue().toString();
                String hscBoard = dataSnapshot.child("hscBoard").getValue().toString();
                String hscSession = dataSnapshot.child("hscSession").getValue().toString();
                String hscPassingYear = dataSnapshot.child("hscPassingYear").getValue().toString();

                String uniName = dataSnapshot.child("uniName").getValue().toString();
                String uniID = dataSnapshot.child("uniID").getValue().toString();
                String uniSession = dataSnapshot.child("uniSession").getValue().toString();
                String uniDepartment = dataSnapshot.child("uniDepartment").getValue().toString();
                String uniPassYear = dataSnapshot.child("uniPassYear").getValue().toString();

                tvName.setText(name);
                tvBirthDate.setText(dateOfBirth);
                tvSchoolName.setText(schoolName);
                tvSscRoll.setText(sscRoll);
                tvSscRegistration.setText(sscRegistration);
                tvSscGroup.setText(sscGroup);
                tvSscResult.setText(sscResult);
                tvSscBoard.setText(sscBoard);
                tvSscSession.setText(sscSession);
                tvSscPassingYear.setText(sscPassingYear);

                tvCollageName.setText(collageName);
                tvHscRoll.setText(hscRoll);
                tvHscReg.setText(hscReg);
                tvHscGroup.setText(hscGroup);
                tvHscResult.setText(hscResult);
                tvHscBoard.setText(hscBoard);
                tvHscSession.setText(hscSession);
                tvHscPassingYear.setText(hscPassingYear);

                tvUniName.setText(uniName);
                tvUniID.setText(uniID);
                tvUniDepartment.setText(uniDepartment);
                tvUniSession.setText(uniSession);
                tvUniPassYear.setText(uniPassYear);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_LONG).show();

            }
        });

    }
}
