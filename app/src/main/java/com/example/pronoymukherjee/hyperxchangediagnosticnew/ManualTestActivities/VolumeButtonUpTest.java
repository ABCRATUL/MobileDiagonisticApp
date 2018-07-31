package com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.VoiceSpeak;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.Timer;
import java.util.TimerTask;

public class VolumeButtonUpTest extends AppCompatActivity {
    Timer timer;
    VoiceSpeak voiceSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_button_up_test);
        this.setFinishOnTouchOutside(false);
        setTitle("");
        initializeViews();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                completeActivity(false);
            }
        }, Constants.TEST_TIMER);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                voiceSpeak.speakVoice(getResources().getString(R.string.volume_up_test));
            }
        }, Constants.VOICE_DELAY);
    }

    /**
     * Method to complete the Activity.
     *
     * @param status: true, if successful.
     */
    private void completeActivity(boolean status) {
        if (status)
            setResult(RESULT_OK);
        else
            setResult(RESULT_CANCELED);
        finish();
    }

    /**
     * Method to initialize Views.
     */
    private void initializeViews() {
        voiceSpeak = new VoiceSpeak(getApplicationContext());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            Message.toastMesage(getApplicationContext(), "volume Up Button Pressed.", "");
            completeActivity(true);
            timer.cancel();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
