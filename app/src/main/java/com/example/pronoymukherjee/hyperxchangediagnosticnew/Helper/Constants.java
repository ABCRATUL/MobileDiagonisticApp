package com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Objects.Test;

import java.util.ArrayList;

public class Constants {
    public static int PERMISSION_REQUEST_CODE=69;
    public static String DIALOG_MSG="dialogMsg";
    public static int DIALOG_INTERNT_CODE=169;
    public static ArrayList<Test> automatedTestList=new ArrayList<>();

    /**
     * This is the
     */
    public static void fillAutomatedTestList(){
        automatedTestList.add(new Test("Ram Test",0,0));
        automatedTestList.add(new Test("Battery Test",0,0));
        automatedTestList.add(new Test("Wifi Test",0,0));
        automatedTestList.add(new Test("Bluetooth Test",0,0));
        automatedTestList.add(new Test("NFC Test",0,0));
        automatedTestList.add(new Test("Flash Test",0,0));
        automatedTestList.add(new Test("Acclerometer Test",0,0));
        automatedTestList.add(new Test("Gyroscope Test",0,0));
        automatedTestList.add(new Test("External Storage Test",0,0));
    }
}

