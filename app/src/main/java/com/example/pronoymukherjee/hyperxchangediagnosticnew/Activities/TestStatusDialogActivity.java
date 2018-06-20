package com.example.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ListView;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Adapters.TestStatusItemAdapter;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

public class TestStatusDialogActivity extends AppCompatActivity {
    AppCompatImageView _testDialogIcon;
    AppCompatImageButton _closeDialogButton;
    ListView testList;
    TestStatusItemAdapter statusItemAdapter;
    int statusIconID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_status_dialog);
        initializeViews();
        Intent intent=getIntent();
        if(intent!=null){
            Bundle bundle=intent.getExtras();
            boolean success=bundle.getBoolean(Constants.TEST_STATUS_DIALOG_KEY);
            if(success){
                statusItemAdapter= new
                        TestStatusItemAdapter(this,
                        Constants.successTestList);
                statusIconID=R.drawable.ic_sucess_bucket;
            }else{
                statusItemAdapter=new TestStatusItemAdapter(this,
                        Constants.failedTestList);
                statusIconID=R.drawable.ic_failed_bucket;
            }
            testList.setAdapter(statusItemAdapter);
            _testDialogIcon.setImageResource(statusIconID);
        }
        _closeDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initializeViews(){
        _testDialogIcon=findViewById(R.id.testStatusDialogIcon);
        _closeDialogButton=findViewById(R.id.testStatusDialogClose);
        testList=findViewById(R.id.testDialogList);
    }
}
