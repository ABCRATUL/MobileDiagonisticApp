package com.example.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.widget.GridView;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Adapters.ManualGridAdapter;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.BackButtonTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.CameraFrontTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.CameraRearTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.ChargerTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.HomeButtonTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.ProximityTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.RGBTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.SpeakerTest;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.TouchScreenTest;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.VibrationTestActivity;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.VolumeButtonDownTest;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities.VolumeButtonUpTest;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Objects.Test;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

public class ManualTestScreen extends AppCompatActivity {
    AppCompatImageButton _exitApp;
    AppCompatImageView _currentTest,_successBucket,_failedBucket;
    GridView _testGrid;
    ManualGridAdapter gridAdapter;
    Test currentTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_test_screen);
        initializeViews();
        gridAdapter =new ManualGridAdapter(Constants.manualTestList,
                getApplicationContext());
        _testGrid.setAdapter(gridAdapter);
        gridAdapter.notifyDataSetChanged();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                performTest();
            }
        },1000);

    }
    private void initializeViews() {
        _successBucket=findViewById(R.id.successTestManual);
        _failedBucket=findViewById(R.id.failedTestManual);
        _exitApp=findViewById(R.id.exitAppButtonManual);
        _currentTest=findViewById(R.id.currentManualTest);
        _testGrid=findViewById(R.id.manualTestGrid);
    }
    private void performTest(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                currentTest=Constants.manualTestList.get(0);
                _currentTest.setImageResource(currentTest.getTestIconID());
                switch (currentTest.getTestName()){
                    case "Touch Screen":
                        intent=new Intent(ManualTestScreen.this,
                                TouchScreenTest.class);
                        startActivityForResult(intent,Constants.TOUCH_SCREEN_CODE);
                        break;
                    case "Speaker":
                        intent=new Intent(ManualTestScreen.this,
                                SpeakerTest.class);
                        startActivityForResult(intent,Constants.SPEAKER_CODE);
                        break;
                    case "Volume Button Up":
                        intent=new Intent(ManualTestScreen.this,
                                VolumeButtonUpTest.class);
                        startActivityForResult(intent,Constants.VOLUME_BUTTON_UP_CODE);
                        break;
                    case "Volume Button Down":
                        intent=new Intent(ManualTestScreen.this,
                                VolumeButtonDownTest.class);
                        startActivityForResult(intent,Constants.VOLUME_BUTTON_DOWN_CODE);
                        break;
                    case "Proximity":
                        intent=new Intent(ManualTestScreen.this,
                                ProximityTestActivity.class);
                        startActivityForResult(intent,Constants.PROXIMITY_CODE);
                        break;
                    case "Rear Camera":
                        intent=new Intent(ManualTestScreen.this,
                                CameraRearTestActivity.class);
                        startActivityForResult(intent,Constants.REAR_CAMERA_CODE);
                        break;
                    case "Front Camera":
                        intent=new Intent(ManualTestScreen.this,
                                CameraFrontTestActivity.class);
                        startActivityForResult(intent,Constants.FRONT_CAMERA_CODE);
                        break;
                    case "Back Button":
                        intent=new Intent(ManualTestScreen.this,
                                BackButtonTestActivity.class);
                        startActivityForResult(intent,Constants.BACK_BUTTON_CODE);
                        break;
                    case "Home Button":
                        intent=new Intent(ManualTestScreen.this,
                                HomeButtonTestActivity.class);
                        startActivityForResult(intent,Constants.HOME_BUTTON_CODE);
                        break;
                    case "Power Button":break;//TODO: Add the Power Button.
                    case "Vibration":
                        intent=new Intent(ManualTestScreen.this,
                                VibrationTestActivity.class);
                        startActivityForResult(intent,Constants.VIBRATION_CODE);
                        break;
                    case "Charger":
                        intent=new Intent(ManualTestScreen.this,
                                ChargerTestActivity.class);
                        startActivityForResult(intent,Constants.CHARGER_CODE);
                        break;
                    case "Headphone":break; //TODO: Add the Headphone test.
                    case "RGB":
                        intent=new Intent(ManualTestScreen.this,
                            RGBTestActivity.class);
                        startActivityForResult(intent,Constants.RGB_CODE);
                        break;
                }
                //Constants.manualTestList.remove(currentTest);
                gridAdapter.notifyDataSetChanged();
            }
        },2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            Constants.successManualTestList.add(currentTest);
        }
        else if(resultCode==RESULT_CANCELED){
            Constants.failedManualTestList.add(currentTest);
        }
        Constants.manualTestList.remove(currentTest);
        gridAdapter.notifyDataSetChanged();
        performTest();
    }
}