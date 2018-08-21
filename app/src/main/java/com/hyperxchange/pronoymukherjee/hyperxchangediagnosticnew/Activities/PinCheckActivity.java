package com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;

import com.android.volley.VolleyError;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.HTTPConnector;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.R;

import org.json.JSONObject;

public class PinCheckActivity extends AppCompatActivity implements HTTPConnector.ResponseListener {
    AppCompatEditText _pin1, _pin2, _pin3, _pin4;
    AppCompatButton _loginButton, _exitButton;
    StringBuilder pinNumber = new StringBuilder();
    ProgressDialog _progressDialog;

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
                if (s.length() == 1) {
                    _pin1.clearFocus();
                    _pin2.requestFocus();
                    pinNumber.append(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        _pin2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    _pin2.clearFocus();
                    _pin3.requestFocus();
                    pinNumber.append(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        _pin3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    _pin3.clearFocus();
                    _pin4.requestFocus();
                    pinNumber.append(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        _pin4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    _pin4.clearFocus();
                    pinNumber.append(s);
                    checkPin();
                }
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
        _progressDialog=new ProgressDialog(this);
        _progressDialog.setCancelable(false);
        _progressDialog.setMessage(getResources().getString(R.string.loading_message));
    }
    private void checkPin(){

    }

    @Override
    public void onResponse(JSONObject response) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
