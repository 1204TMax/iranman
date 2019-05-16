package com.example.utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iranman.R;
import com.example.stu_manager.modify_stu;
import com.example.stu_manager.student;
import com.example.zxingLibraryQR.QRrecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HisAdapter extends RecyclerView.Adapter<HisAdapter.ViewHolder>{
    private List<QRrecord> hisList;
    private MyApplication myApplication;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View studentview;
        TextView time;
        TextView num;
        public ViewHolder(View view){
            super(view);
            studentview = view;
            time = view.findViewById(R.id.recordtime);
            num = view.findViewById(R.id.record);
        }
    }
    public HisAdapter(List<QRrecord> historys){
        hisList = historys;
    }
    @NonNull
    @Override
    public HisAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,parent,false);
        final HisAdapter.ViewHolder holder = new HisAdapter.ViewHolder(view);
        Context mcontext = MyApplication.getContext();
        return holder;
    }
    @Override
    public void onBindViewHolder(HisAdapter.ViewHolder holder, int position) {
        QRrecord QRrecord = hisList.get(position);
        holder.time.setText(QRrecord.getTime());
        holder.num.setText(QRrecord.getRecord());
    }
    @Override
    public int getItemCount() {
        return hisList.size();
    }
}
