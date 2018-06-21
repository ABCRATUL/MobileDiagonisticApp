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
    public static String TEST_STATUS_DIALOG_KEY ="testStatus";
    public static String TEST_STATUS_KEY="statusTestKey";

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
}

