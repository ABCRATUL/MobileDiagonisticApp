package com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper;

import android.hardware.fingerprint.FingerprintManager;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Objects.Test;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.ArrayList;

public class Constants {
    private static String TAG_CLASS = Constants.class.getSimpleName();
    public static int PERMISSION_REQUEST_CODE = 69;
    public static String DIALOG_MSG = "dialogMsg";
    public static int DIALOG_INTERNET_CODE = 169;
    public static ArrayList<Test> automatedTestList = new ArrayList<>();
    public static ArrayList<Test> successTestList = new ArrayList<>();
    public static ArrayList<Test> failedTestList = new ArrayList<>();

    public static ArrayList<Test> manualTestList = new ArrayList<>();
    public static ArrayList<Test> successManualTestList = new ArrayList<>();
    public static ArrayList<Test> failedManualTestList = new ArrayList<>();

    public static String TEST_STATUS_DIALOG_KEY = "testStatus";
    public static String TEST_IS_MANUAL = "isManual";
    public static String TEST_STATUS_KEY = "statusTestKey";
    public static int TEST_TIMER = 15000;
    public static int MICROPHONE_SPEAKER_CODE = 566;
    public static String SPEAKER_VOLUME_MSG = "Please make sure that your media volume is audible.";


    /**
     * This is the method to fill the automated test list.
     */
    public static void fillAutomatedTestList() {
        automatedTestList.add(new Test("Ram", 0, R.drawable.ic_ram));
        automatedTestList.add(new Test("Battery", 0, R.drawable.ic_battery));
        automatedTestList.add(new Test("Wifi", 0, R.drawable.ic_wifi));
        automatedTestList.add(new Test("Bluetooth", 0, R.drawable.ic_bluetooth));
        automatedTestList.add(new Test("NFC", 0, R.drawable.ic_nfc));
        automatedTestList.add(new Test("Flash", 0, R.drawable.flashlight));
        automatedTestList.add(new Test("Accelerometer", 0, R.drawable.ic_accelerometer));
        automatedTestList.add(new Test("Gyroscope", 0, R.drawable.ic_gyroscope));
        automatedTestList.add(new Test("External Storage", 0, R.drawable.ic_external_storage));
        Message.logMessage(TAG_CLASS, automatedTestList.size() + "");
    }

    /**
     * This is the method to fill the manual test list.
     */
    public static void fillManualTestList() {
        manualTestList.add(new Test("Touch Screen", 0, R.drawable.ic_touch_screen_test));
        manualTestList.add(new Test("Speaker", 0, R.drawable.ic_speaker));
        manualTestList.add(new Test("Volume Button Up", 0, R.drawable.ic_volume_button_test));
        manualTestList.add(new Test("Volume Button Down", 0, R.drawable.ic_volume_button_test));
        manualTestList.add(new Test("Proximity", 0, R.drawable.ic_proximity_test));
        manualTestList.add(new Test("Rear Camera", 0, R.drawable.ic_rear_cam_test));
        manualTestList.add(new Test("Front Camera", 0, R.drawable.ic_front_cam_test));
        manualTestList.add(new Test("Back Button", 0, R.drawable.ic_back_button_test));
        manualTestList.add(new Test("Home Button", 0, R.drawable.ic_home_button_test));
        manualTestList.add(new Test("Power Button", 0, R.drawable.ic_power_button_test));
        manualTestList.add(new Test("Vibration", 0, R.drawable.ic_vibration_test));
        manualTestList.add(new Test("Charger", 0, R.drawable.ic_charger_test));
        manualTestList.add(new Test("Headphone", 0, R.drawable.ic_headphone_jack_test));
        manualTestList.add(new Test("RGB", 0, R.drawable.ic_rgb));
        manualTestList.add(new Test("MicroPhone", 0, R.drawable.ic_timer_busy));
        manualTestList.add(new Test("Screen Brightness", 0, R.drawable.ic_gesture_test));
        manualTestList.add(new Test("Fingerprint", 0, R.drawable.ic_fingerprint));
    }

    public static int WRITE_SETTINGS_CODE = 99;
    public static int SAD_CODE = 3001;
    public static int HAPPY_CODE = 3002;
    public static int OKAY_CODE = 3003;
    public static String FINGER_PRINT_KEY = "digKey";

    public static int TOUCH_SCREEN_CODE = 1;
    public static int SPEAKER_CODE = 2;
    public static int VOLUME_BUTTON_UP_CODE = 3;
    public static int VOLUME_BUTTON_DOWN_CODE = 4;
    public static int PROXIMITY_CODE = 5;
    public static int REAR_CAMERA_CODE = 6;
    public static int FRONT_CAMERA_CODE = 7;
    public static int BACK_BUTTON_CODE = 8;
    public static int HOME_BUTTON_CODE = 9;
    public static int POWER_BUTTON_CODE = 10;
    public static int VIBRATION_CODE = 11;
    public static int CHARGER_CODE = 12;
    public static int HEADPHONE_CODE = 13;
    public static int RGB_CODE = 14;
    public static int MICROPHONE_CODE = 15;
    public static int SCREEN_BRIGHTNESS_CODE = 16;
    public static int FINGER_PRINT_CODE=17;
}

