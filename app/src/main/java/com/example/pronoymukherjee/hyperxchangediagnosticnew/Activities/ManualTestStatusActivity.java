package com.example.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ImageView;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

public class ManualTestStatusActivity extends AppCompatActivity {
    AppCompatImageButton _successButton, _failedButton, _nextButton;
    ImageView _imageView;
    private String TAG_CLASS = ManualTestStatusActivity.class.getSimpleName();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_test_status);
        initializeViews();
        intent = getIntent();
        if (intent != null) {
            Bundle bundle=intent.getExtras();
            boolean isSuccess=bundle.getBoolean(Constants.TEST_STATUS_KEY);
            if(isSuccess){
                _imageView.setImageResource(R.drawable.ic_test_complete);
            }else
                _imageView.setImageResource(R.drawable.ic_test_failed);
        }
        _successButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openDialogIntent = new Intent(ManualTestStatusActivity.this,
                        TestStatusDialogActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constants.TEST_STATUS_DIALOG_KEY, true);
                bundle.putBoolean(Constants.TEST_IS_MANUAL,true);
                openDialogIntent.putExtras(bundle);
                startActivity(openDialogIntent);
            }
        });
        _failedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openDialogIntent = new Intent(ManualTestStatusActivity.this,
                        TestStatusDialogActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constants.TEST_STATUS_DIALOG_KEY, false);
                bundle.putBoolean(Constants.TEST_IS_MANUAL,true);
                openDialogIntent.putExtras(bundle);
                startActivity(openDialogIntent);
            }
        });
        _nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ManualTestStatusActivity.this,
                        TestScoreScreen.class);
                startActivity(intent);
            }
        });
    }

    private void initializeViews() {
        _successButton = findViewById(R.id.successManualTestButton);
        _failedButton = findViewById(R.id.failedManualTestButton);
        _nextButton = findViewById(R.id.manualTestNextButton);
        _imageView = findViewById(R.id.manualTestStatusImage);
    }
}
