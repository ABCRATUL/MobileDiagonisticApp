package com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities;

import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.VoiceSpeak;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class HomeButtonTestActivity extends AppCompatActivity {
    Timer timer;
    VoiceSpeak voiceSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_button_test);
        setTitle("");
        initializeViews();
        this.setFinishOnTouchOutside(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                voiceSpeak.speakVoice(getResources().getString(R.string.home_button_test_msg));
            }
        },Constants.VOICE_DELAY);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                completeActivity(false);
            }
        }, Constants.TEST_TIMER);
    }

    private void initializeViews() {
        timer = new Timer();
        voiceSpeak=new VoiceSpeak(getApplicationContext());
    }

    /**
     * Method to complete the activity.
     *
     * @param status: True if successful, else false.
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
    protected void onUserLeaveHint() {
        Message.toastMesage(getApplicationContext(),
                "Home Button Pressed, now restore the app.",
                "");
        completeActivity(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
