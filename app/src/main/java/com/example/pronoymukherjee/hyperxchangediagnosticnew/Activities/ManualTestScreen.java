package com.example.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.widget.GridView;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Adapters.ManualGridAdapter;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.CameraRearTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.ProximityTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.SpeakerTest;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.TouchScreenTest;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.VolumeButtonDownTest;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

public class ManualTestScreen extends AppCompatActivity {
    AppCompatImageButton _exitApp;
    AppCompatImageView _currentTest,_successBucket,_failedBucket;
    GridView _testGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_test_screen);
        initializeViews();
        ManualGridAdapter adapter=new ManualGridAdapter(Constants.manualTestList,
                getApplicationContext());
        _testGrid.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Intent intent=new Intent(ManualTestScreen.this, CameraRearTestActivity.class);
        startActivity(intent);
    }
    private void initializeViews() {
        _successBucket=findViewById(R.id.successTestManual);
        _failedBucket=findViewById(R.id.failedTestManual);
        _exitApp=findViewById(R.id.exitAppButtonManual);
        _currentTest=findViewById(R.id.currentManualTest);
        _testGrid=findViewById(R.id.manualTestGrid);
    }
}
