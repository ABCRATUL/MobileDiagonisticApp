package com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Objects.Test;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.ArrayList;

public class Constants {
    private static String TAG_CLASS=Constants.class.getSimpleName();
    public static int PERMISSION_REQUEST_CODE=69;
    public static String DIALOG_MSG="dialogMsg";
    public static int DIALOG_INTERNET_CODE =169;
    public static ArrayList<Test> automatedTestList=new ArrayList<>();
    public static ArrayList<Test> successTestList=new ArrayList<>();
    public static ArrayList<Test> failedTestList=new ArrayList<>();

    public static ArrayList<Test> manualTestList=new ArrayList<>();
    public static ArrayList<Test> successManualTestList=new ArrayList<>();
    public static ArrayList<Test> failedManualTestList=new ArrayList<>();

    public static String TEST_STATUS_DIALOG_KEY ="testStatus";
    public static String TEST_STATUS_KEY="statusTestKey";
    public static int TEST_TIMER=5000;
    public static String SPEAKER_VOLUME_MSG="Please make sure that your media volume is audible.";



    /**
     * This is the method to fill the automated test list.
     */
    public static void fillAutomatedTestList(){
        automatedTestList.add(new Test("Ram Test",0, R.drawable.ic_ram));
        automatedTestList.add(new Test("Battery Test",0,R.drawable.ic_battery));
        automatedTestList.add(new Test("Wifi Test",0,R.drawable.ic_wifi));
        automatedTestList.add(new Test("Bluetooth Test",0,R.drawable.ic_bluetooth));
        automatedTestList.add(new Test("NFC Test",0,R.drawable.ic_nfc));
        automatedTestList.add(new Test("Flash Test",0,R.drawable.flashlight));
        automatedTestList.add(new Test("Accelerometer Test",0,R.drawable.ic_accelerometer));
        automatedTestList.add(new Test("Gyroscope Test",0,R.drawable.ic_gyroscope));
        automatedTestList.add(new Test("External Storage Test",0,R.drawable.ic_external_storage));
        Message.logMessage(TAG_CLASS,automatedTestList.size()+"");
    }
    public static void fillManualTestList(){
        manualTestList.add(new Test("Touch Screen Test",0,R.drawable.ic_touch_screen_test));
        manualTestList.add(new Test("Speaker Test",0,R.drawable.ic_speaker_test));
        manualTestList.add(new Test("Volume Button Up Test",0,R.drawable.ic_volume_button_test));
        manualTestList.add(new Test("Volume Button Down Test",0,R.drawable.ic_volume_button_test));
        manualTestList.add(new Test("Proximity Test",0,R.drawable.ic_proximity_test));
        manualTestList.add(new Test("Rear Camera Test",0,R.drawable.ic_rear_cam_test));
        manualTestList.add(new Test("Front Camera Test",0,R.drawable.ic_front_cam_test));
        manualTestList.add(new Test("Back Button Test",0,R.drawable.ic_back_button_test));
        manualTestList.add(new Test("Home Button Test",0,R.drawable.ic_home_button_test));
        manualTestList.add(new Test("Power Button Test",0,R.drawable.ic_power_button_test));
        manualTestList.add(new Test("Vibration Test",0,R.drawable.ic_vibration_test));
        manualTestList.add(new Test("Charger Test",0,R.drawable.ic_charger_test));
        manualTestList.add(new Test("Headphone Test",0,R.drawable.ic_headphone_jack_test));
    }
    public static int TOUCH_SCREEN_CODE=1;
    public static int SPEAKER_CODE=2;
    public static int VOLUME_BUTTON_UP_CODE=3;
    public static int VOLUME_BUTTON_DOWN_CODE=4;
}

