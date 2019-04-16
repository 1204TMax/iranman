package com.example.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iranman.R;

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
}
