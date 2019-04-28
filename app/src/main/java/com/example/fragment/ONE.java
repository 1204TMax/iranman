package com.example.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.iranman.MainActivity;
import com.example.iranman.R;
import com.example.stu_manager.add_stu;
import com.example.stu_manager.del_stu;
import com.example.stu_manager.modify_stu;
import com.example.stu_manager.query_stu;

public class ONE extends Fragment {
    public static ONE newInstance() {
        ONE fragment = new ONE();
        return fragment;
    }
 @Override
    public  View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstate){
        View view = inflater.inflate(R.layout.fragment_one,container,false);


        return view;
 }
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {

            }
        }
    }
}
