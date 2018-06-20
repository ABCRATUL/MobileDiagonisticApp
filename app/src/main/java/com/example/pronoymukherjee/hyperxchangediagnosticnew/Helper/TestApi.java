package com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper;

import android.app.Activity;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.os.BatteryManager;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;

public class TestApi {
    static int score = 0;

    public static int testRam(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        long availMemory = memoryInfo.availMem;
        long totalMemory = memoryInfo.totalMem;
        if ((availMemory / totalMemory) * 100 <= 60)
            return 5;
        return 10;
    }

    public static int testBattery(Context context) {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryIntent = context.registerReceiver(null, intentFilter);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int status = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
                if (status == BatteryManager.BATTERY_HEALTH_GOOD)
                    score = 10;
                else if (status == BatteryManager.BATTERY_HEALTH_COLD)
                    score = 10;
                else if (status == BatteryManager.BATTERY_HEALTH_DEAD)
                    score = 1;
                else score = 1;
            }
        };
        broadcastReceiver.onReceive(context, batteryIntent);
        return score;
    }

    public static int testBluetooth(Context context) {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null)
            return 0;
        return 10;
    }

    public static int testNFC(Context context) {
        NfcAdapter adapter = NfcAdapter.getDefaultAdapter(context);
        if (adapter == null)
            return 0;
        return 10;
    }

    public static int testFlashAvailability(Context context) {
        score = 0;
        Boolean flashAvailable = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        final Camera cam = Camera.open();
        android.hardware.Camera.Parameters p = cam.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        cam.setParameters(p);
        cam.startPreview();

        if (flashAvailable) {
            score = 6;
        } else {
            score = 0;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cam.stopPreview();
                cam.release();
            }
        }, 3000);
        /*cam.stopPreview();
        cam.release();*/
        return score;
    }

    public static int testNetwork(Context context) {
        score = 0;
        boolean wasWifiOff = false;
        //Check if Connected to Internet
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        final boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        WifiManager wifiManager = (WifiManager)
                context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        boolean hasWifi = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_WIFI);
        if (hasWifi) {
            boolean isWifiEnabled = wifiManager.isWifiEnabled();
            if (!isWifiEnabled) {
                wasWifiOff = true;
                wifiManager.setWifiEnabled(true);

                Toast.makeText(context, "Wifi Enabled.", Toast.LENGTH_SHORT).show();
            }
        } else return score;
        score = 8;
        if (wasWifiOff) {
            wifiManager.setWifiEnabled(false);
            Toast.makeText(context, "Wifi Disabled", Toast.LENGTH_SHORT).show();
        }
        return score;
    }
    public static int testAcclerometer(Context context){
        SensorManager sensorManager= (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometerSensor!=null)
            return 10;
        return 0;
    }
    public static int testGyroscope(Context context){
        SensorManager sensorManager= (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor gyroscopeSensor=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(gyroscopeSensor!=null)
            return 10;
        return 0;
    }
    public static int testExternalStorage(Context context){
        String state= Environment.getExternalStorageState();
        if(state==Environment.MEDIA_MOUNTED)
            return 10;
        return 0;
    }
}
