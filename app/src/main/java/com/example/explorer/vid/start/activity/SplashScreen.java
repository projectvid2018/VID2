package com.example.explorer.vid.start.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.explorer.vid.R;
import com.example.explorer.vid.vid.Activity.HomeActivity;

public class SplashScreen extends Activity implements Runnable
{

    Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        mThread = new Thread(this);

        mThread.start();
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(1500);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            startActivity(new Intent(SplashScreen.this, HomeActivity.class));

            finish();
        }
    }

}
