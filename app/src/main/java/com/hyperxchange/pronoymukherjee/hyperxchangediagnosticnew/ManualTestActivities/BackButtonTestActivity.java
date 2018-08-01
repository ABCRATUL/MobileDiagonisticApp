package com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.VoiceSpeak;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.Timer;
import java.util.TimerTask;

public class BackButtonTestActivity extends AppCompatActivity {
    Timer timer;
    VoiceSpeak voiceSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_button_test);
        setTitle("");
        voiceSpeak = new VoiceSpeak(getApplicationContext());
        this.setFinishOnTouchOutside(false);
        timer = new Timer();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                voiceSpeak.speakVoice(getResources().getString(R.string.back_button_msg));
            }
        }, Constants.VOICE_DELAY);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                completeActivity(false);
            }
        }, Constants.TEST_TIMER);
    }

    /**
     * Method to complete the Activity.
     *
     * @param status: true if Successful.
     */
    private void completeActivity(boolean status) {
        if (status) {
            setResult(RESULT_OK);
            timer.cancel();
        } else
            setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Message.toastMesage(getApplicationContext(), "Back Button Pressed.", "");
            completeActivity(true);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
