package com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;
import com.multidots.fingerprintauth.FingerPrintAuthCallback;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;


@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintTestActivity extends AppCompatActivity implements FingerPrintAuthCallback {
    private String TAG_CLASS = FingerprintManager.class.getSimpleName();
    FingerPrintAuthHelper fingerPrintAuthHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_test);
        fingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(this, this);
        fingerPrintAuthHelper.startAuth();
    }

    @Override
    public void onNoFingerPrintHardwareFound() {
        Message.logMessage(TAG_CLASS, "NO finger print hardware ");
    }

    @Override
    public void onNoFingerPrintRegistered() {
        Message.logMessage(TAG_CLASS, "Please register some finger print.");
    }

    @Override
    public void onBelowMarshmallow() {
        Message.logMessage(TAG_CLASS, "API Level doesn't support Finger print.");
    }

    @Override
    public void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject) {
        Message.toastMesage(getApplicationContext(), "Success", "");
    }

    @Override
    public void onAuthFailed(int errorCode, String errorMessage) {
        Message.toastMesage(getApplicationContext(),
                "Finger Print Detected", "");
    }
}
