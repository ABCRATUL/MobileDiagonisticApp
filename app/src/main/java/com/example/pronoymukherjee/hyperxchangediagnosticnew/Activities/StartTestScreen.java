package com.example.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.telephony.TelephonyManager;
import android.view.View;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.ArrayList;
import java.util.List;

public class StartTestScreen extends AppCompatActivity {
    String modelName, imeiNumber;
    AppCompatTextView _modelName, _imeiNumber;
    AppCompatButton _marketPlace, _startTest;
    boolean permissionGranted=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test_screen);
        modelName = Build.MANUFACTURER + " " + Build.PRODUCT;
        initializeViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions();
        }
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if(permissionGranted) {
            imeiNumber = telephonyManager.getDeviceId(0);
            _imeiNumber.setText(imeiNumber);
        }
        _startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable()){
                    //TODO: Start the test.
                }
                else{
                    Bundle bundle=new Bundle();
                    bundle.putString(Constants.DIALOG_MSG,"Please connect the device to the internet.");
                    Intent dialogIntent=new Intent(StartTestScreen.this,PermissionExplainDialog.class);
                    dialogIntent.putExtras(bundle);
                    startActivityForResult(dialogIntent,Constants.DIALOG_INTERNT_CODE);
                }
            }
        });
    }

    private void initializeViews() {
        _modelName = findViewById(R.id.modelName);
        _imeiNumber = findViewById(R.id.imeiNumber);
        _startTest = findViewById(R.id.startTestButton);
        _marketPlace = findViewById(R.id.marketPlaceButton);
    }

    private void checkPermissions() {
        List<String> permissionsNeeded = new ArrayList<>();
        int cameraPermission = ContextCompat
                .checkSelfPermission(this, Manifest.permission.CAMERA);
        int externalStoragePermission = ContextCompat
                .checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int locationPermission = ContextCompat
                .checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int phoneStatePermission = ContextCompat
                .checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (cameraPermission != PackageManager.PERMISSION_GRANTED)
            permissionsNeeded.add(Manifest.permission.CAMERA);
        if (externalStoragePermission != PackageManager.PERMISSION_GRANTED)
            permissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (locationPermission != PackageManager.PERMISSION_GRANTED)
            permissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        if (phoneStatePermission != PackageManager.PERMISSION_GRANTED)
            permissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);
        requestPermission(permissionsNeeded.toArray(new String[permissionsNeeded.size()]));
    }

    private void requestPermission(String[] permissionsNeeded) {
        ActivityCompat.requestPermissions(this,
                permissionsNeeded,
                Constants.PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Constants.PERMISSION_REQUEST_CODE) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    Intent explainIntent = new Intent(StartTestScreen.this,
                            PermissionExplainDialog.class);
                    startActivityForResult(explainIntent, Constants.PERMISSION_REQUEST_CODE);
                    break;
                }
            }
            permissionGranted=true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==Constants.PERMISSION_REQUEST_CODE){
            if (resultCode==RESULT_OK)
                checkPermissions();
        }
        else if (requestCode==Constants.DIALOG_INTERNT_CODE){
            //TODO: Start test after checking internet.
        }
    }/**
     * This is the method to check whether the device is connected to internet or not.
     *
     * @return: true if connected else false.
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
