package com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;

import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.R;

public class PinCheckActivity extends AppCompatActivity {
    AppCompatEditText _pinNumber;
    AppCompatButton _loginButton, _exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_check);
        setTitle("");
        this.setFinishOnTouchOutside(false);
        initializeViews();
    }

    private void initializeViews() {
        _pinNumber = findViewById(R.id.pinEnter);
        _loginButton = findViewById(R.id.submitPin);
        _exitButton = findViewById(R.id.exitAppPin);
    }
}
