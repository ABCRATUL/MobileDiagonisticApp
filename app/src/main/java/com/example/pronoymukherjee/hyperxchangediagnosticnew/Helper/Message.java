package com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Message {
    public static void toastMesage(Context context,String msg,String length){
        if(length.equalsIgnoreCase("long")) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    public static void logMessage(String tag,String msg){
        Log.d(tag,msg);
    }
}
