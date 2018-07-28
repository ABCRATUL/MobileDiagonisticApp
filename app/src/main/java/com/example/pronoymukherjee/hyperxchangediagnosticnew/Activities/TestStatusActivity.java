package com.example.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

public class TestStatusActivity extends AppCompatActivity {
    AppCompatImageView _statusIcon;
    AppCompatImageButton _successBucket, _failedBucket, _nextButton;
    int statusIconID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_status);
        initializeViews();
        final Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            boolean isCompleted = bundle.getBoolean(Constants.TEST_STATUS_KEY);
            if (isCompleted)
                statusIconID = R.drawable.ic_test_completed;
            else statusIconID = R.drawable.ic_test_failed;
            _statusIcon.setImageResource(statusIconID);
        }

        _successBucket.setBackground(getResources().getDrawable(R.drawable.ic_sucess_bucket));
        _failedBucket.setBackground(getResources().getDrawable(R.drawable.ic_failed_bucket));
        _successBucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openDialogIntent = new Intent(TestStatusActivity.this,
                        TestStatusDialogActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constants.TEST_STATUS_DIALOG_KEY, true);
                bundle.putBoolean(Constants.TEST_IS_MANUAL,false);
                openDialogIntent.putExtras(bundle);
                startActivity(openDialogIntent);
            }
        });
        _failedBucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openDialogIntent = new Intent(TestStatusActivity.this,
                        TestStatusDialogActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constants.TEST_STATUS_DIALOG_KEY, false);
                bundle.putBoolean(Constants.TEST_IS_MANUAL,false);
                openDialogIntent.putExtras(bundle);
                startActivity(openDialogIntent);
            }
        });
        _nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:Go to Manual Test.
                Constants.fillAutomatedTestList();
                Intent manualTestIntent = new Intent(TestStatusActivity.this,
                        ManualTestScreen.class);
                startActivity(manualTestIntent);
            }
        });
    }

    private void initializeViews() {
        _statusIcon = findViewById(R.id.testStatusIcon);
        _successBucket = findViewById(R.id.successTestSet);
        _failedBucket = findViewById(R.id.failedTestSet);
        _nextButton = findViewById(R.id.next);
    }
}
