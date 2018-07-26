package com.example.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.nfc.Tag;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.GridView;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Adapters.ManualGridAdapter;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.BackButtonTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.CameraFrontTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.CameraRearTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.ChargerTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.FingerprintTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.HeadPhoneTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.HomeButtonTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.MicroPhoneTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.PowerButtonTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.ProximityTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.RGBTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.ScreenBrightnessTest;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.SpeakerTest;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.TouchScreenTest;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.VibrationTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.VolumeButtonDownTest;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.VolumeButtonUpTest;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Objects.Test;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

public class ManualTestScreen extends AppCompatActivity {
    AppCompatImageButton _exitApp;
    AppCompatImageView _currentTest, _successBucket, _failedBucket;
    GridView _testGrid;
    ManualGridAdapter gridAdapter;
    Test currentTest;
    private String TAG_CLASS = ManualTestScreen.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_test_screen);
        initializeViews();
        gridAdapter = new ManualGridAdapter(Constants.manualTestList,
                getApplicationContext());
        _testGrid.setAdapter(gridAdapter);
        gridAdapter.notifyDataSetChanged();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                performTest();
            }
        }, 1000);
        _exitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitApp();
            }
        });
    }

    /**
     * Method to initialize the widgets.
     */
    private void initializeViews() {
        _successBucket = findViewById(R.id.successTestManual);
        _failedBucket = findViewById(R.id.failedTestManual);
        _exitApp = findViewById(R.id.exitAppButtonManual);
        _currentTest = findViewById(R.id.currentManualTest);
        _testGrid = findViewById(R.id.manualTestGrid);
    }

    /**
     * Method to invoke individual test activities.
     * This method is called from Activity Result after completing one test.
     */
    private void performTest() {
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                Intent intent = null;
                if (Constants.manualTestList.size() > 0) {
                    currentTest = Constants.manualTestList.get(0);
                    _currentTest.setImageResource(currentTest.getTestIconID());
                    switch (currentTest.getTestName()) {
                        case "Touch Screen":
                            intent = new Intent(ManualTestScreen.this,
                                    TouchScreenTest.class);
                            startActivityForResult(intent, Constants.TOUCH_SCREEN_CODE);
                            break;
                        case "Speaker":
                            intent = new Intent(ManualTestScreen.this,
                                    SpeakerTest.class);
                            startActivityForResult(intent, Constants.SPEAKER_CODE);
                            break;
                        case "Volume Button Up":
                            intent = new Intent(ManualTestScreen.this,
                                    VolumeButtonUpTest.class);
                            startActivityForResult(intent, Constants.VOLUME_BUTTON_UP_CODE);
                            break;
                        case "Volume Button Down":
                            intent = new Intent(ManualTestScreen.this,
                                    VolumeButtonDownTest.class);
                            startActivityForResult(intent, Constants.VOLUME_BUTTON_DOWN_CODE);
                            break;
                        case "Proximity":
                            intent = new Intent(ManualTestScreen.this,
                                    ProximityTestActivity.class);
                            startActivityForResult(intent, Constants.PROXIMITY_CODE);
                            break;
                        case "Rear Camera":
                            intent = new Intent(ManualTestScreen.this,
                                    CameraRearTestActivity.class);
                            startActivityForResult(intent, Constants.REAR_CAMERA_CODE);
                            break;
                        case "Front Camera":
                            intent = new Intent(ManualTestScreen.this,
                                    CameraFrontTestActivity.class);
                            startActivityForResult(intent, Constants.FRONT_CAMERA_CODE);
                            break;
                        case "Back Button":
                            intent = new Intent(ManualTestScreen.this,
                                    BackButtonTestActivity.class);
                            startActivityForResult(intent, Constants.BACK_BUTTON_CODE);
                            break;
                        case "Home Button":
                            intent = new Intent(ManualTestScreen.this,
                                    HomeButtonTestActivity.class);
                            startActivityForResult(intent, Constants.HOME_BUTTON_CODE);
                            break;
                        case "Power Button":
                            intent = new Intent(ManualTestScreen.this,
                                    PowerButtonTestActivity.class);
                            startActivityForResult(intent, Constants.POWER_BUTTON_CODE);
                            break;
                        case "Vibration":
                            intent = new Intent(ManualTestScreen.this,
                                    VibrationTestActivity.class);
                            startActivityForResult(intent, Constants.VIBRATION_CODE);
                            break;
                        case "Charger":
                            intent = new Intent(ManualTestScreen.this,
                                    ChargerTestActivity.class);
                            startActivityForResult(intent, Constants.CHARGER_CODE);
                            break;
                        case "Headphone":
                            intent = new Intent(ManualTestScreen.this,
                                    HeadPhoneTestActivity.class);
                            startActivityForResult(intent, Constants.HEADPHONE_CODE);
                            break;
                        case "RGB":
                            intent = new Intent(ManualTestScreen.this,
                                    RGBTestActivity.class);
                            startActivityForResult(intent, Constants.RGB_CODE);
                            break;
                        case "MicroPhone":
                            intent = new Intent(ManualTestScreen.this,
                                    MicroPhoneTestActivity.class);
                            startActivityForResult(intent, Constants.MICROPHONE_CODE);
                            break;
                        case "Screen Brightness":
                            intent = new Intent(ManualTestScreen.this,
                                    ScreenBrightnessTest.class);
                            startActivityForResult(intent, Constants.SCREEN_BRIGHTNESS_CODE);
                            break;
                        case "Fingerprint":
                            FingerprintManager fingerprintManager= (FingerprintManager)
                                    getSystemService(FINGERPRINT_SERVICE);
                            if(fingerprintManager.isHardwareDetected()){
                                if(fingerprintManager.hasEnrolledFingerprints()){
                                    intent=new Intent(ManualTestScreen.this,
                                            FingerprintTestActivity.class);
                                    startActivityForResult(intent,Constants.FINGER_PRINT_CODE);
                                }else {
                                    Message.toastMesage(getApplicationContext(),
                                            "Please enroll some finger prints. ","");
                                }
                            }else {
                                Message.toastMesage(getApplicationContext(),
                                        "Your device doesn't support finger print.","");
                            }
                            break;
                    }
                    //Constants.manualTestList.remove(currentTest);
                    gridAdapter.notifyDataSetChanged();
                } else {
                    Intent statusIntent = new Intent(ManualTestScreen.this,
                            ManualTestStatusActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(Constants.TEST_STATUS_KEY, true);
                    statusIntent.putExtras(bundle);
                    startActivity(statusIntent);
                    finish();
                }
            }
        }, 2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Constants.successManualTestList.add(currentTest);
        } else if (resultCode == RESULT_CANCELED) {
            Constants.failedManualTestList.add(currentTest);
        }
        Constants.manualTestList.remove(currentTest);
        gridAdapter.notifyDataSetChanged();
        if (Constants.manualTestList.size() >= 0) {

            Message.logMessage(TAG_CLASS, "FAILED:" + Constants.failedManualTestList.size() + "");
            Message.logMessage(TAG_CLASS, "SUCCESS:" + Constants.successManualTestList.size() + "");

            performTest();
        } else {
            Intent statusIntent = new Intent(ManualTestScreen.this,
                    ManualTestStatusActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean(Constants.TEST_STATUS_KEY, true);
            statusIntent.putExtras(bundle);
            startActivity(statusIntent);
            finish();
        }
    }

    /**
     * This is the method to exit the app.
     */
    @SuppressLint("ObsoleteSdkInt")
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