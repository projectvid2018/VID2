package com.example.explorer.vid.nav.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.explorer.vid.R;

public class InstituteActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listViewInstitute;
    private SearchView searchViewInstitute;
    private ArrayAdapter<String> instituteAdapter;

    private  String[] instituteName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute);

        listViewInstitute = findViewById(R.id.instituteListView);
        searchViewInstitute = findViewById(R.id.searchViewInstituteId);
        instituteName = getResources().getStringArray(R.array.institute_name);

        instituteAdapter = new ArrayAdapter<>(this
                , R.layout.institute_view, R.id.instituteTextViewId, instituteName);

        listViewInstitute.setAdapter(instituteAdapter);
        listViewInstitute.setOnItemClickListener(this);

        searchViewInstitute.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                instituteAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String value = instituteAdapter.getItem(position);
        boolean a = false;

        String[] uni_name = getResources().getStringArray(R.array.university_name);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.uni_dialog_layout,null);

        TextView textViewInstituteName = mView.findViewById(R.id.instituteNameDialogID);
        LinearLayout linearLayoutUni = mView.findViewById(R.id.uniDialogLayoutId);
        LinearLayout linearLayoutBank = mView.findViewById(R.id.bankDialogLayoutId);


        for (int i = 0; i < uni_name.length; i++ ){
            if (uni_name[i].equals(value)){
               a = true;
            }
        }
        if (a){
            linearLayoutBank.setVisibility(View.GONE);
        }
        if(!a){
            linearLayoutUni.setVisibility(View.GONE);
        }
        textViewInstituteName.setText(value);

        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Data send",Toast.LENGTH_LONG).show();

            }
        });
        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();

    }
}
