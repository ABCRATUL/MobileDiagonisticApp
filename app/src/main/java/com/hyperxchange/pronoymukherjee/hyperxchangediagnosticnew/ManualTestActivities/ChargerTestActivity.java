package com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.KeyEvent;
import android.view.View;

import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.VoiceSpeak;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.Timer;
import java.util.TimerTask;

public class ChargerTestActivity extends AppCompatActivity {
    AppCompatButton _checkButton;
    Timer timer;
    BroadcastReceiver receiver;
    IntentFilter intentFilter;
    VoiceSpeak voiceSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chager_test);
        this.setFinishOnTouchOutside(false);
        setTitle("");
        initializeViews();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                completeActivity(false);
            }
        }, Constants.TEST_TIMER);
        _checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCharger();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                voiceSpeak.speakVoice(getResources().getString(R.string.charger_msg));
            }
        },Constants.VOICE_DELAY);
    }

    /**
     * method to initialize Views.
     */
    private void initializeViews() {
        _checkButton = findViewById(R.id.checkChargerButton);
        timer = new Timer();
        intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        voiceSpeak=new VoiceSpeak(getApplicationContext());
    }

    /**
     * Method to complete the Activity.
     *
     * @param status: true if Successful, else false.
     */
    private void completeActivity(boolean status) {
        if (status) {
            setResult(RESULT_OK);
            timer.cancel();
        } else {
            setResult(RESULT_CANCELED);
        }
        //unregisterReceiver(receiver);
        finish();
    }

    /**
     * Method to check the charger.
     */
    private void checkCharger() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int status = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                boolean isSuccess = false;
                unregisterReceiver(receiver);
                switch (status) {
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        Message.toastMesage(getApplicationContext(),
                                "On AC Power.", "");
                        isSuccess = true;
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        Message.toastMesage(getApplicationContext(),
                                "On USB Power", "");
                        isSuccess = true;
                        break;
                    case 0:
                        Message.toastMesage(getApplicationContext(),
                                "On Battery Power", "");
                        isSuccess = false;
                        break;
                }
                completeActivity(isSuccess);
            }
        };
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
