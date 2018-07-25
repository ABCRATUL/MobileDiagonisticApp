package com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.Timer;
import java.util.TimerTask;

public class HomeButtonTestActivity extends AppCompatActivity {
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_button_test);
        setTitle("");
        initializeViews();
        this.setFinishOnTouchOutside(false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                completeActivity(false);
            }
        }, Constants.TEST_TIMER);
    }
    private void initializeViews(){
        timer=new Timer();
    }

    /**
     * Method to complete the activity.
     * @param status: True if successful, else false.
     */
    private void completeActivity(boolean status){
        if(status){
            setResult(RESULT_OK);
            timer.cancel();
        }
        else{
            setResult(RESULT_CANCELED);
        }
        finish();
    }

    @Override
    protected void onUserLeaveHint() {
        Message.toastMesage(getApplicationContext(),
                "Home Button Pressed, now restore the app.",
                "");
        completeActivity(true);
    }
}