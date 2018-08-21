package com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;

import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.R;

public class PinCheckActivity extends AppCompatActivity {
    AppCompatEditText _pin1, _pin2, _pin3, _pin4;
    AppCompatButton _loginButton, _exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_check);
        setTitle("");
        this.setFinishOnTouchOutside(false);
        initializeViews();
        _pin1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initializeViews() {
        _pin1 = findViewById(R.id.pin1);
        _pin2 = findViewById(R.id.pin2);
        _pin3 = findViewById(R.id.pin3);
        _pin4 = findViewById(R.id.pin4);
        _loginButton = findViewById(R.id.submitPin);
        _exitButton = findViewById(R.id.exitAppPin);
    }
}
