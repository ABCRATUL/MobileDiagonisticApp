package com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.Timer;
import java.util.TimerTask;

public class HeadPhoneTestActivity extends AppCompatActivity {
    AppCompatButton _checkButton;
    private String TAG_CLASS = HeadPhoneTestActivity.class.getSimpleName();
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_phone_test);
        initializeViews();
        setTitle("");
        final AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        _checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (audioManager.isWiredHeadsetOn()) {
                    Message.toastMesage(getApplicationContext(),
                            "Headset is connected.", "");
                    completeActivity(true);
                } else {
                    completeActivity(false);
                }
            }
        });
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                completeActivity(false);
            }
        }, Constants.TEST_TIMER);
    }

    /**
     * Method to initialize the Views.
     */
    private void initializeViews() {
        _checkButton = findViewById(R.id.headPhoneCheckButton);
        timer = new Timer();
    }

    /**
     * Method to complete the Activity.
     *
     * @param status: true if successful, else false.
     */
    private void completeActivity(boolean status) {
        Message.logMessage(TAG_CLASS,status+"");
        if (status) {
            timer.cancel();
            setResult(RESULT_OK);
        } else {
            setResult(RESULT_CANCELED);
            timer.cancel();
        }
        finish();
    }
}