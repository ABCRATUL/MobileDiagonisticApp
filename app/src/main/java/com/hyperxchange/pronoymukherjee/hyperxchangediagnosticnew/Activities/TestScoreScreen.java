package com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.ExcelCreator;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.io.File;

public class TestScoreScreen extends AppCompatActivity {
    AppCompatImageView _sadFace, _okayFace, _happyFace;
    AppCompatTextView _sadText, _okayText, _happyText, _testScore, _priceValue;
    AppCompatButton _submitButton, _exitButton;
    CircularProgressBar _circularProgressBar;
    int basePrice = 12000;
    int physicalStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_score_screen);
        initializeViews();
        _circularProgressBar
                .setProgress(Constants.successManualTestList.size() +
                        Constants.successTestList.size());
        _testScore.setText(String.valueOf(Constants.successManualTestList.size()
                + Constants.successTestList.size()));
        //TODO:Get the Price.
        _priceValue.setText(String.valueOf(basePrice));
        _sadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _sadFace.setImageResource(R.drawable.ic_sad_face);
                _happyFace.setImageResource(R.drawable.ic_happy_black);
                _okayFace.setImageResource(R.drawable.ic_okay_black);
                physicalStatus = Constants.SAD_CODE;
                changePrice(physicalStatus);
            }
        });
        _okayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _sadFace.setImageResource(R.drawable.ic_sad_black);
                _happyFace.setImageResource(R.drawable.ic_happy_black);
                _okayFace.setImageResource(R.drawable.ic_okay_face);
                physicalStatus = Constants.OKAY_CODE;
                changePrice(physicalStatus);
            }
        });
        _happyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _sadFace.setImageResource(R.drawable.ic_sad_black);
                _happyFace.setImageResource(R.drawable.ic_happy_face);
                _okayFace.setImageResource(R.drawable.ic_okay_black);
                physicalStatus = Constants.HAPPY_CODE;
                changePrice(physicalStatus);
            }
        });
        _exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitApp();
            }
        });
        _submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isWrite = ExcelCreator.createExcel(getApplicationContext());
                if (isWrite)
                    Message.toastMesage(getApplicationContext(),
                            "You can find the report at: " +
                                    Environment.getExternalStorageDirectory()
                                    + Constants.HX_FOLDER_NAME + File.separator + Constants.HX_REPORT_FOLDER_NAME,
                            "long");
                Intent intent = new Intent(TestScoreScreen.this, StartTestScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Method to initialize Widgets.
     */
    private void initializeViews() {
        _testScore = findViewById(R.id.testScore);
        _sadFace = findViewById(R.id.sadFace);
        _okayFace = findViewById(R.id.okayFace);
        _happyFace = findViewById(R.id.happyFace);
        _sadText = findViewById(R.id.sadText);
        _okayText = findViewById(R.id.okayText);
        _happyText = findViewById(R.id.happyText);
        _submitButton = findViewById(R.id.submitButtonTestScore);
        _exitButton = findViewById(R.id.exitAppButtonTestScore);
        _circularProgressBar = findViewById(R.id.testProgress);
        _priceValue = findViewById(R.id.priceValue);
    }

    /**
     * Method to change the Price.
     *
     * @param CODE: If OKAY or Bad or Good.
     */
    @SuppressLint("SetTextI18n")
    private void changePrice(int CODE) {
        if (CODE == Constants.SAD_CODE)
            _priceValue.setText("5000/-");
        else if (CODE == Constants.OKAY_CODE)
            _priceValue.setText("10000/-");
        else if (CODE == Constants.HAPPY_CODE)
            _priceValue.setText("12000/-");
    }

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