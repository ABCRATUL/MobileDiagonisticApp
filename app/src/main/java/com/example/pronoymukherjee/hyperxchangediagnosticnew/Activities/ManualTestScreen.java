package com.example.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.widget.GridView;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

public class ManualTestScreen extends AppCompatActivity {
    AppCompatImageView _successBucket,_failedBucket,_exitApp;
    AppCompatImageView _currentTest;
    GridView _testGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_test_screen);
        initializeViews();
    }

    private void initializeViews() {
        _successBucket=findViewById(R.id.successTestManual);
        _failedBucket=findViewById(R.id.failedTestManual);
        _exitApp=findViewById(R.id.exitAppButtonManual);
        _currentTest=findViewById(R.id.currentManualTest);
        _testGrid=findViewById(R.id.manualTestGrid);
    }
}
