package com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

public class VibrationTestActivity extends AppCompatActivity {
    AppCompatEditText _numberVibration;
    AppCompatButton _vibrateButton,_submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibration_test);
        this.setFinishOnTouchOutside(false);
        initializeViews();
    }
    private void initializeViews(){
        _numberVibration=findViewById(R.id.vibrationEnter);
        _vibrateButton=findViewById(R.id.vibrateButton);
        _submitButton=findViewById(R.id.submitVibration);
    }
}
