package com.example.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.ArrayList;
import java.util.List;

public class StartTestScreen extends AppCompatActivity {
    String modelName, imeiNumber;
    AppCompatTextView _modelName, _imeiNumber;
    AppCompatButton _marketPlace, _startTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test_screen);
        modelName = Build.MANUFACTURER + " " + Build.PRODUCT;
        initializeViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions();
        }
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
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==Constants.PERMISSION_REQUEST_CODE){
            if (resultCode==RESULT_OK)
                checkPermissions();
        }
    }
}
