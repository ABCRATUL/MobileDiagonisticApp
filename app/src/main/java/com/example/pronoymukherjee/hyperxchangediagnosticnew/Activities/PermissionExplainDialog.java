package com.example.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

public class PermissionExplainDialog extends AppCompatActivity {
    AppCompatButton _okayButton, _exitButton;
    AppCompatTextView _textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_explain_dialog);
        initializeViews();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String msg=bundle.getString(Constants.DIALOG_MSG);
        if(msg.length()>0){
            _textView.setText(msg);
        }
        _okayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
        _exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                exitApp();
                finish();
            }
        });
    }

    private void initializeViews() {
        _okayButton = findViewById(R.id.permissionDialogButton);
        _textView=findViewById(R.id.permissionDialogTextView);
        _exitButton = findViewById(R.id.permissionDialogButtonExit);
    }

    /**
     * This is the method to exit the app.
     */
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
