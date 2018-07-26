package com.example.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class TestScoreScreen extends AppCompatActivity {
    AppCompatImageView _sadFace, _okayFace, _happyFace;
    AppCompatTextView _sadText, _okayText, _happyText, _testScore, _priceValue;
    AppCompatButton _submitButton, _cancelButton;
    CircularProgressBar _circularProgressBar;
    int basePrice = 12000;
    int physicalStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_score_screen);
        initializeViews();
        _circularProgressBar
                .setProgress(Constants.successManualTestList.size()+
                        Constants.successTestList.size());
        _testScore.setText(String.valueOf(Constants.successManualTestList.size()
                +Constants.successTestList.size()));
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
        _cancelButton = findViewById(R.id.exitAppButtonTestScore);
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
}
