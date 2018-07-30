package com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.Timer;
import java.util.TimerTask;

public class PowerButtonTestActivity extends AppCompatActivity {
    Timer timer;
    private String TAG_CLASS = PowerButtonTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_button_test);
        setTitle("");
        this.setFinishOnTouchOutside(false);
        initializeViews();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                completeActivity(false);
            }
        }, Constants.TEST_TIMER);
    }

    private void initializeViews() {
        timer = new Timer();
    }

    /**
     * Method to finish the activity.
     *
     * @param status: true if Successful else false.
     */
    private void completeActivity(boolean status) {
        if (status) {
            setResult(RESULT_OK);
            timer.cancel();
        } else {
            setResult(RESULT_CANCELED);
        }
        finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            Message.logMessage(TAG_CLASS, hasFocus + "");
            Message.toastMesage(getApplicationContext(),
                    "Press the back button now.", "");
            completeActivity(true);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
