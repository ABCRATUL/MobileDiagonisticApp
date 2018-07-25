package com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.Timer;
import java.util.TimerTask;

public class ChargerTestActivity extends AppCompatActivity {
    AppCompatButton _checkButton;
    Timer timer;
    BroadcastReceiver receiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chager_test);
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
        //registerReceiver(receiver,intentFilter);
    }

    /**
     * method to initialize Views.
     */
    private void initializeViews() {
        _checkButton = findViewById(R.id.checkChargerButton);
        timer = new Timer();
        intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
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
                boolean isSuccess=false;
                unregisterReceiver(receiver);
                switch (status) {
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        Message.toastMesage(getApplicationContext(),
                                "On AC Power.", "");
                        isSuccess=true;
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        Message.toastMesage(getApplicationContext(),
                                "On USB Power", "");
                        isSuccess=true;
                        break;
                    case 0:
                        Message.toastMesage(getApplicationContext(),
                                "On Battery Power", "");
                        isSuccess=false;
                        break;
                }
                completeActivity(isSuccess);
            }
        };
        registerReceiver(receiver,intentFilter);
    }
}
