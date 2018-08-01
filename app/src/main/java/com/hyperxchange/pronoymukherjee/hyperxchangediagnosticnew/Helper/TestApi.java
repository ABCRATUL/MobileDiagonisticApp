package com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
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
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import java.util.Objects;

public class TestApi {
    static int score = 0;

    public static int testRam(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        assert activityManager != null;
        activityManager.getMemoryInfo(memoryInfo);
        long availMemory = memoryInfo.availMem;
        long totalMemory = memoryInfo.totalMem;
        activityManager = null;
        memoryInfo = null;
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
                switch (status) {
                    case BatteryManager.BATTERY_HEALTH_GOOD:
                        score = 10;
                        break;
                    case BatteryManager.BATTERY_HEALTH_COLD:
                        score = 10;
                        break;
                    case BatteryManager.BATTERY_HEALTH_DEAD:
                        score = 1;
                        break;
                    default:
                        score = 1;
                        break;
                }
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
        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        final boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        WifiManager wifiManager = (WifiManager)
                context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        boolean hasWifi = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_WIFI);
        if (hasWifi) {
            assert wifiManager != null;
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
        wifiManager = null;
        cm = null;
        activeNetwork = null;
        return score;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static int testAcclerometer(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        assert sensorManager != null;
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometerSensor != null) {
            accelerometerSensor = null;
            sensorManager = null;
            return 10;
        }
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static int testGyroscope(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        assert sensorManager != null;
        Sensor gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyroscopeSensor != null)
            return 10;
        return 0;
    }

    public static int testExternalStorage(Context context) {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED))
            return 10;
        return 0;
    }
}
