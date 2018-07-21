package com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.Timer;
import java.util.TimerTask;

public class RGBTestActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgbtest);
        initializeViews();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                completeActivity(false);
            }
        }, Constants.TEST_TIMER);
        Timer timer1=new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        constraintLayout.setBackgroundColor(getResources().getColor(R.color.red));
                    }
                });
            }
        },500);
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        constraintLayout.setBackgroundColor(getResources().getColor(R.color.green));
                    }
                });
            }
        },1500);
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        constraintLayout.setBackgroundColor(getResources().getColor(R.color.blue));
                    }
                });
                completeActivity(true);
            }
        },2500);
    }

    /**
     * Method to initialize Views.
     */
    private void initializeViews(){
        constraintLayout=findViewById(R.id.rgbLayout);
        timer=new Timer();
    }

    /**
     * Method to complete activity.
     * @param status: true if successful else false.
     */
    private void completeActivity(boolean status){
        if(status){
            timer.cancel();
            setResult(RESULT_OK);
        }
        else setResult(RESULT_CANCELED);
        finish();
    }
}