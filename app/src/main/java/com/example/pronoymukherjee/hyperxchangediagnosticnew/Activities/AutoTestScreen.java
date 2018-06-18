package com.example.pronoymukherjee.hyperxchangediagnosticnew.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Adapters.TestItemAdapter;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Objects.TestItem;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.ArrayList;

public class AutoTestScreen extends AppCompatActivity {
    RecyclerView testList;
    RecyclerView.LayoutManager layoutManager;

    public static String categoryItem[]={
            "Smart Phones",
            "Laptops",
            "Tablets",
            "Cameras"
    };

    public static int imageIDs[]={
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_test_screen);
        initializeViews();
        layoutManager=new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
        ArrayList<TestItem> items=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            TestItem testItem=new TestItem(imageIDs[i],categoryItem[i]);
            items.add(testItem);
        }
        testList.setLayoutManager(layoutManager);
        TestItemAdapter itemAdapter=new TestItemAdapter(items);
        testList.setAdapter(itemAdapter);

    }
    private void initializeViews(){
        testList=findViewById(R.id.pendingTestList);
    }
}
