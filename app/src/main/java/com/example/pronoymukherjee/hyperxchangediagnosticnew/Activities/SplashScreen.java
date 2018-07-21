package com.example.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.content.Intent;
import android.os.BatteryManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.MicroPhoneTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.RGBTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.SpeakerTest;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Constants.fillAutomatedTestList();
        Constants.fillManualTestList();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent changeActivityIntent=new Intent(SplashScreen.this,
                        StartTestScreen.class);
                startActivity(changeActivityIntent);
            }
        },100);
    }
}
