package com.example.explorer.vid.vid.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.explorer.vid.R;
import com.example.explorer.vid.SettingsActivity;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
                Intent intent = new Intent(HomeActivity.this,SettingsActivity.class);
                startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notification) {

        } else if (id == R.id.nav_saved_files) {

        }
        //-------------------------------------------------------------------------------------------logout
        else if (id == R.id.nav_logout) {
            AlertDialog.Builder builder=new AlertDialog.Builder(HomeActivity.this); //-------Home is name of the activity
            builder.setMessage("Do you want to exit?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {

                    finish();
                    Intent i=new Intent();
                    i.putExtra("finish", true);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //----------------------------------- To clean up all activities
                    //startActivity(i);
                    finish();

                }
            });

            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });

            AlertDialog alert=builder.create();
            alert.show();


        }
        //-------------------------------------------------------------------------------------------feedback
        else if (id == R.id.nav_feedback) {
            Intent intent = new Intent(HomeActivity.this,FeedbackActivity.class);
            startActivity(intent);
        }
        //-------------------------------------------------------------------------------------------website link
        else if (id == R.id.nav_web) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://www.vid.com"));
            startActivity(intent);

        }

        //-------------------------------------------------------------------------------------------About
        else if (id == R.id.nav_about) {
            Intent intent = new Intent(HomeActivity.this,AboutActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    LinearLayout nid,info,edu,driving,passport,visa,hajj,properties,law,ec,police,marriage,tax,bank,insurance,birth,social,others;

    public void nid(View view) {
        Intent intent = new Intent(HomeActivity.this,NidActivity.class);
        startActivity(intent);
    }

    public void info(View view) {
        Intent intent = new Intent(HomeActivity.this,InfoActivity.class);
        startActivity(intent);
    }

    public void edu(View view) {
        Intent intent = new Intent(HomeActivity.this,EduActivity.class);
        startActivity(intent);
    }

    public void driving(View view) {
        Intent intent = new Intent(HomeActivity.this,DrivingActivity.class);
        startActivity(intent);
    }

    public void passport(View view) {
        Intent intent = new Intent(HomeActivity.this,PassportActivity.class);
        startActivity(intent);
    }

    public void visa(View view) {
        Intent intent = new Intent(HomeActivity.this,VisaActivity.class);
        startActivity(intent);
    }

    public void hajj(View view) {
        Intent intent = new Intent(HomeActivity.this,HajjActivity.class);
        startActivity(intent);
    }

    public void properties(View view) {
        Intent intent = new Intent(HomeActivity.this,PropertiesActivity.class);
        startActivity(intent);
    }

    public void law(View view) {
        Intent intent = new Intent(HomeActivity.this,LawActivity.class);
        startActivity(intent);
    }

    public void ec(View view) {
        Intent intent = new Intent(HomeActivity.this,EcActivity.class);
        startActivity(intent);
    }

    public void police(View view) {
        Intent intent = new Intent(HomeActivity.this,PoliceActivity.class);
        startActivity(intent);
    }

    public void marriage(View view) {
        Intent intent = new Intent(HomeActivity.this,MarriageActivity.class);
        startActivity(intent);
    }

    public void tax(View view) {
        Intent intent = new Intent(HomeActivity.this,TaxActivity.class);
        startActivity(intent);
    }

    public void bank(View view) {
        Intent intent = new Intent(HomeActivity.this,BankActivity.class);
        startActivity(intent);
    }

    public void insurance(View view) {
        Intent intent = new Intent(HomeActivity.this,InsuranceActivity.class);
        startActivity(intent);
    }

    public void birth(View view) {
        Intent intent = new Intent(HomeActivity.this,BirthActivity.class);
        startActivity(intent);
    }

    public void social(View view) {
        Intent intent = new Intent(HomeActivity.this,SocialActivity.class);
        startActivity(intent);
    }

    public void others(View view) {
        Intent intent = new Intent(HomeActivity.this,OthersActivity.class);
        startActivity(intent);
    }


}


