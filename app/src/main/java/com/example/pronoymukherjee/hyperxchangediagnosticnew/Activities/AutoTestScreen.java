package com.example.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Adapters.TestItemAdapter;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.TestApi;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Objects.Test;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class AutoTestScreen extends AppCompatActivity {
    public String TAG_CLASS=AutoTestScreen.class.getSimpleName();
    RecyclerView testList;
    RecyclerView.LayoutManager layoutManager;
    ImageView _currentTestImage;
    AppCompatImageButton _exitApp;
    AppCompatImageView _successBucket, _failedBucket;
    CircularProgressBar _progressBar;
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
        _progressBar.setProgress(0);
        _progressBar.setProgressWithAnimation(1,1500);
        _exitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitApp();
            }
        });
        performTest();
    }

    /**
     * Method to initialize all the views of the widgets.
     */
    private void initializeViews() {
        testList = findViewById(R.id.pendingTestList);
        _currentTestImage = findViewById(R.id.currentTestImage);
        _exitApp = findViewById(R.id.exitAppButton);
        _successBucket = findViewById(R.id.successTest);
        _failedBucket = findViewById(R.id.failedTest);
        _progressBar=findViewById(R.id.circularProgressBar);
    }

    /**
     * This is the method which is used to perform the automated test.
     * This is a recursive method to perform all the automated tests.
     */
    private void performTest() {
        score=0;
        _progressBar.setProgressWithAnimation(20,1500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Constants.automatedTestList.size() > 0) {
                    Test currentTest = Constants.automatedTestList.get(0);
                    _currentTestImage.setImageResource(currentTest.getTestIconID());
                    //YoYo.with(Techniques.Shake).duration(1500).playOn(_currentTestImage);
                    switch (currentTest.getTestName()) {
                        case "Ram":
                            score = TestApi.testRam(context);
                            break;
                        case "Battery":
                            score = TestApi.testBattery(context);
                            break;
                        case "Wifi":
                            score = TestApi.testNetwork(context);
                            break;
                        case "Bluetooth":
                            score = TestApi.testBluetooth(context);
                            break;
                        case "NFC":
                            score = TestApi.testNFC(context);
                            break;
                        case "Flash":
                            score = TestApi.testFlashAvailability(context);
                            break;
                        case "Accelerometer":
                            score = TestApi.testAcclerometer(context);
                            break;
                        case "Gyroscope":
                            score = TestApi.testGyroscope(context);
                            break;
                        case "External Storage":
                            score = TestApi.testExternalStorage(context);
                            break;
                    }
                    Message.logMessage(TAG_CLASS,score+"");
                    _progressBar.setProgressWithAnimation(90,1500);
                    currentTest.setScore(score);
                    Constants.automatedTestList.remove(currentTest);
                    testItemAdapter.notifyDataSetChanged();
                    _progressBar.setProgressWithAnimation(100,1000);
                    if (score > 0) {
                        Constants.successTestList.add(currentTest);
                        YoYo.with(Techniques.Shake).duration(1500).playOn(_successBucket);
                    } else if (score == 0) {
                        Constants.failedTestList.add(currentTest);
                        YoYo.with(Techniques.Shake).duration(1500).playOn(_failedBucket);
                    }
                    performTest();
                }
                else{
                    Intent getResultIntent=new Intent(AutoTestScreen.this,
                            TestStatusActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putBoolean(Constants.TEST_STATUS_KEY,true);
                    getResultIntent.putExtras(bundle);
                    startActivity(getResultIntent);
                    finish();
                }
                //_progressBar.setProgressWithAnimation(100,1000);
            }
        },3000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                _progressBar.setProgressWithAnimation(100,1000);
            }
        },2000);
    }
    /**
     * This is the method to exit the app.
     */
    @SuppressLint("ObsoleteSdkInt")
    private void exitApp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.finishAndRemoveTask();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
