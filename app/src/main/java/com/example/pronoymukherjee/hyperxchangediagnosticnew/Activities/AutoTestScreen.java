package com.example.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Adapters.TestItemAdapter;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.TestApi;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Objects.Test;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

public class AutoTestScreen extends AppCompatActivity {
    public String TAG_CLASS=AutoTestScreen.class.getSimpleName();
    RecyclerView testList;
    RecyclerView.LayoutManager layoutManager;
    AppCompatImageView _currentTestImage;
    AppCompatButton _exitApp;
    AppCompatImageButton _stopTest;
    AppCompatImageView _successBucket, _failedBucket;
    Context context;
    int score = 0;
    TestItemAdapter testItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_test_screen);
        initializeViews();
        context = getApplicationContext();
        layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
        testList.setLayoutManager(layoutManager);
        testItemAdapter = new TestItemAdapter(Constants.automatedTestList);
        testList.setAdapter(testItemAdapter);
        _successBucket.setImageResource(R.drawable.ic_sucess_bucket);
        _failedBucket.setImageResource(R.drawable.ic_failed_bucket);
        performTest();
    }

    private void initializeViews() {
        testList = findViewById(R.id.pendingTestList);
        _currentTestImage = findViewById(R.id.currentTestImage);
        _exitApp = findViewById(R.id.exitAppButton);
        _stopTest = findViewById(R.id.stopTestButton);
        _successBucket = findViewById(R.id.successTest);
        _failedBucket = findViewById(R.id.failedTest);
    }

    private void performTest() {
        score=0;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Constants.automatedTestList.size() > 0) {
                    Test currentTest = Constants.automatedTestList.get(0);
                    _currentTestImage.setImageResource(currentTest.getTestIconID());
                    YoYo.with(Techniques.Bounce).duration(2000).playOn(_currentTestImage);
                    switch (currentTest.getTestName()) {
                        case "Ram Test":
                            score = TestApi.testRam(context);
                            break;
                        case "Battery Test":
                            score = TestApi.testBattery(context);
                            break;
                        case "Wifi Test":
                            score = TestApi.testNetwork(context);
                            break;
                        case "Bluetooth Test":
                            score = TestApi.testBluetooth(context);
                            break;
                        case "NFC Test":
                            score = TestApi.testNFC(context);
                            break;
                        case "Flash Test":
                            score = TestApi.testFlashAvailability(context);
                            break;
                        case "Accelerometer Test":
                            score = TestApi.testAcclerometer(context);
                            break;
                        case "Gyroscope Test":
                            score = TestApi.testGyroscope(context);
                            break;
                        case "External Storage Test":
                            score = TestApi.testExternalStorage(context);
                            break;
                    }
                    Message.logMessage(TAG_CLASS,score+"");
                    currentTest.setScore(score);
                    if (score > 0) {
                        Constants.successTestList.add(currentTest);
                        YoYo.with(Techniques.Shake).duration(2000).playOn(_successBucket);
                    } else if (score == 0) {
                        Constants.failedTestList.add(currentTest);
                        YoYo.with(Techniques.Shake).duration(2000).playOn(_failedBucket);
                    }
                    Constants.automatedTestList.remove(currentTest);
                    testItemAdapter.notifyDataSetChanged();
                    performTest();
                }
            }
        },2000);

    }
}
