package com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.BatteryManager;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MicroPhoneTestActivity extends AppCompatActivity {
    AppCompatImageButton _speakButton;
    AppCompatTextView _numberShow;
    private String TAG_CLASS = MicroPhoneTestActivity.class.getSimpleName();
    int generatedNumber;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micro_phone_test);
        setTitle("");
        initializeViews();
        this.setFinishOnTouchOutside(false);
        generatedNumber = generateRandomNumber();
        _numberShow.setText(String.valueOf(generatedNumber));
        _speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                completeActivity(false);
            }
        }, Constants.TEST_TIMER);
    }

    private void initializeViews() {
        _speakButton = findViewById(R.id.voiceButton);
        _numberShow = findViewById(R.id.numberShowMicro);
        timer = new Timer();
    }

    /**
     * Method to generate the random Number.
     *
     * @return randomNumber: between 1000-9999;
     */
    private int generateRandomNumber() {
        Random random = new Random();
        int high = 1000;
        int low = 100;
        return random.nextInt(high + low) + low;
    }

    /**
     * Method to get set the intent for the microphone.
     */
    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, new Locale("en", "In"));
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, Please say the number in words.");
        try {
            startActivityForResult(intent, Constants.MICROPHONE_SPEAKER_CODE);
        } catch (ActivityNotFoundException e) {
            Message.logMessage(TAG_CLASS, e.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.MICROPHONE_SPEAKER_CODE) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> resultSet=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                try {
                    int spokenNumber = Integer.parseInt(resultSet.get(0));
                    if (spokenNumber == generatedNumber) {
                        completeActivity(true);
                    }
                }
                catch (NumberFormatException e){
                    Message.toastMesage(getApplicationContext(),
                            "Please speak Correctly","");
                    generatedNumber=generateRandomNumber();
                    _numberShow.setText(String.valueOf(generatedNumber));
                }
            }
        }
    }

    /**
     * Methdo to close the activity.
     *
     * @param status: True if completed successfully, else false.
     */
    private void completeActivity(boolean status) {
        if (status) {
            timer.cancel();
            Message.logMessage(TAG_CLASS,"Success");
            setResult(RESULT_OK);
        } else
            setResult(RESULT_CANCELED);
        finish();
    }
}
