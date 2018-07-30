package com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;
import com.multidots.fingerprintauth.FingerPrintAuthCallback;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;

import java.util.Timer;
import java.util.TimerTask;


@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintTestActivity extends AppCompatActivity implements FingerPrintAuthCallback {
    private String TAG_CLASS = FingerprintManager.class.getSimpleName();
    FingerPrintAuthHelper fingerPrintAuthHelper;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_test);
        setTitle("");
        fingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(this, this);
        fingerPrintAuthHelper.startAuth();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                completeActivity(false);
            }
        }, Constants.TEST_TIMER);
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
        Message.logMessage(TAG_CLASS, "API Level doesn't support finger_print.");
    }

    @Override
    public void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject) {
        Message.toastMesage(getApplicationContext(), "finger_print Checked.", "");
        completeActivity(true);
    }

    @Override
    public void onAuthFailed(int errorCode, String errorMessage) {
        Message.toastMesage(getApplicationContext(),
                "Finger Print Detected", "");
        completeActivity(true);
    }

    /**
     * Method to complete the Activity.
     *
     * @param status: true if successful, else false.
     */
    private void completeActivity(boolean status) {
        fingerPrintAuthHelper.stopAuth();
        if (status) {
            timer.cancel();
            setResult(RESULT_OK);
        } else {
            setResult(RESULT_CANCELED);
        }
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
