package com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.android.volley.VolleyError;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.HTTPConnector;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.ParamsCreator;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PinCheckActivity extends AppCompatActivity implements HTTPConnector.ResponseListener {
    AppCompatEditText _pin1, _pin2, _pin3, _pin4;
    AppCompatButton _loginButton, _exitButton;
    StringBuilder pinNumber = new StringBuilder();
    ProgressDialog _progressDialog;
    InputMethodManager inputMethodManager;
    private String TAG_CLASS = PinCheckActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_check);
        setTitle("");
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        this.setFinishOnTouchOutside(false);
        initializeViews();
        _pin1.requestFocus();
        inputMethodManager.showSoftInput(_pin1, InputMethodManager.SHOW_IMPLICIT);
        _pin1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    _pin1.clearFocus();
                    _pin2.requestFocus();
                    inputMethodManager.showSoftInput(_pin2, InputMethodManager.SHOW_IMPLICIT);
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
                    inputMethodManager.showSoftInput(_pin3, InputMethodManager.SHOW_IMPLICIT);
                    pinNumber.append(s);
                } else if (s.length() == 0) {
                    _pin2.clearFocus();
                    _pin1.requestFocus();
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
                    inputMethodManager.showSoftInput(_pin4, InputMethodManager.SHOW_IMPLICIT);
                    pinNumber.append(s);
                } else if (s.length() == 0) {
                    _pin2.requestFocus();
                    _pin3.clearFocus();
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
                    pinNumber.append(s);
                    checkPin();
                } else if (s.length() == 0) {
                    _pin4.clearFocus();
                    _pin3.requestFocus();
                } else if (s.length() > 1) {
                    String string = s.toString().substring(0, 1);
                    _pin4.setText(string);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pinNumber.length() == 4)
                    checkPin();
                else {
                    Message.toastMessage(getApplicationContext(),
                            "Please enter a valid pin.", "");
                    clearTexts();
                }
            }
        });
        _exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitApp();
            }
        });
    }

    /**
     * Method to initialize the views.
     */
    private void initializeViews() {
        _pin1 = findViewById(R.id.pin1);
        _pin2 = findViewById(R.id.pin2);
        _pin3 = findViewById(R.id.pin3);
        _pin4 = findViewById(R.id.pin4);
        _loginButton = findViewById(R.id.submitPin);
        _exitButton = findViewById(R.id.exitAppPin);
        _progressDialog = new ProgressDialog(this);
        _progressDialog.setCancelable(false);
        _progressDialog.setMessage(getResources().getString(R.string.loading_message));
    }

    /**
     * Method to make the network call and validate the pin.
     */
    private void checkPin() {
        HTTPConnector connector = new HTTPConnector(TAG_CLASS,
                getApplicationContext(), Constants.QUERY_URL, ParamsCreator
                .createParamsForLoginPin(pinNumber.toString()),
                this);
        connector.makeQuery(TAG_CLASS);
        _progressDialog.show();
    }

    @Override
    public void onResponse(JSONObject response) {
        _progressDialog.dismiss();
        Message.logMessage(TAG_CLASS, response.toString());
        try {
            JSONArray array = response.getJSONArray(Constants.JSON_RESULT);
            JSONObject oneObject = array.getJSONObject(0);
            String passcode = oneObject.getString(Constants.LOGIN_PASSCODE);
            if (passcode.equals(pinNumber.toString())) {
                Constants.ENGINEER_EMAIL = oneObject.getString(Constants.LOGIN_EMAIL);
                Intent intent = new Intent(PinCheckActivity.this, StartTestScreen.class);
                startActivity(intent);
                finish();
            } else {
                Message.toastMessage(getApplicationContext(),
                        "Please enter a valid passcode.", "");
                clearTexts();
            }
        } catch (JSONException e) {
            Message.logMessage(TAG_CLASS, e.toString());
            Message.toastMessage(getApplicationContext(),
                    "Please enter a valid passcode.", "");
            clearTexts();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        _progressDialog.dismiss();
        Message.toastMessage(getApplicationContext(),
                "Please enter a valid Pin.", "");
        clearTexts();
    }

    /**
     * Method to clear the Text Fields.
     */
    private void clearTexts() {
        _pin1.getText().clear();
        _pin2.getText().clear();
        _pin3.getText().clear();
        _pin4.getText().clear();
        _pin1.requestFocus();
    }

    /**
     * This is the method to exit the app.
     */
    private void exitApp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.finishAndRemoveTask();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}