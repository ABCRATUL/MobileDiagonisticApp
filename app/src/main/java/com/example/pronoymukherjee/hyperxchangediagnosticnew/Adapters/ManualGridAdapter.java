package com.example.pronoymukherjee.hyperxchangediagnosticnew.Adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Objects.Test;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.ArrayList;

public class ManualGridAdapter extends BaseAdapter {
    ArrayList<Test> dataset;
    Context context;

    public ManualGridAdapter(ArrayList<Test> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataset.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view==null){
            view= layoutInflater.inflate(R.layout.test_item,null);
            AppCompatTextView textView=view.findViewById(R.id.testName);
            AppCompatImageView imageView=view.findViewById(R.id.imageTest);
            textView.setText(dataset.get(position).getTestName());
            imageView.setImageResource(dataset.get(position).getTestIconID());
        }
        return view;
    }
}