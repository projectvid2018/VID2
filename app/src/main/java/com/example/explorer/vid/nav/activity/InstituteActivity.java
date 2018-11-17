package com.example.explorer.vid.nav.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.explorer.vid.R;

public class InstituteActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listViewInstitute;
    private SearchView searchViewInstitute;
    private ArrayAdapter<String> instituteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute);

        listViewInstitute = findViewById(R.id.instituteListView);
        searchViewInstitute = findViewById(R.id.searchViewInstituteId);
        String[] instituteName = getResources().getStringArray(R.array.institute_name);

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
        Toast.makeText(InstituteActivity.this,value,Toast.LENGTH_LONG).show();

    }
}