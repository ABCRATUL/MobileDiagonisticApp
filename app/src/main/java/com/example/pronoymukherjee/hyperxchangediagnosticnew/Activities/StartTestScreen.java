package com.example.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

public class StartTestScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test_screen);
        Intent intent=new Intent(StartTestScreen.this,AutoTestScreen.class);
        startActivity(intent);
    }
}
