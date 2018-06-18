package com.example.pronoymukherjee.hyperxchangediagnosticnew.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Objects.TestItem;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.util.ArrayList;

public class TestItemAdapter extends RecyclerView.Adapter<TestItemAdapter.ViewHolder> {
    private ArrayList<TestItem> dataset;

    public TestItemAdapter(ArrayList<TestItem> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public TestItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(dataset.get(position).getTestName());
        holder.imageView.setImageResource(dataset.get(position).getImageID());
    }


    @Override
    public int getItemCount() {
        return 0;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        AppCompatTextView textView;
        AppCompatImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.testName);
            imageView=itemView.findViewById(R.id.imageTest);
        }

    }
}
