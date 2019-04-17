package com.example.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.iranman.MainActivity;
import com.example.iranman.QRScan;
import com.example.iranman.R;
import com.example.tool.DataGenerator;

public class TWO extends Fragment{
    private Fragment[]mFragments;
    public static TWO newInstance() {
        TWO fragment = new TWO();
        return fragment;
    }
    @Override
    public  View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstate){
        View view = inflater.inflate(R.layout.fragment_two,container,false);
        Button QRScan = view.findViewById(R.id.QR);
        QRScan.setOnClickListener(new TWO.ButtonListener());
        return view;
    }
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.QR:
                    Intent intent = new Intent(getActivity(),QRScan.class);
                    startActivityForResult(intent,2);
                    break;
            }
        }
    }
    @Override
    public void onActivityResult(int RequestCode,int resultCode,Intent data){
        super.onActivityResult(RequestCode , resultCode , data);
        if(RequestCode == 2&&resultCode==20){
            MainActivity mainActivity = (MainActivity)getActivity();
            FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mFragments = DataGenerator.getFragments();
            Fragment mTWO = mFragments[1];
            fragmentTransaction.replace(R.id.home_container, mTWO);
        }
    }
}
