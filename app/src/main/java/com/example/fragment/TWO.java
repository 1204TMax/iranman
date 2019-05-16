package com.example.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.iranman.MainActivity;
import com.example.stu_manager.query_stu;
import com.example.tools.QRHistory;
import com.example.zxingLibraryQR.AlbumQR;
import com.example.zxingLibraryQR.QRScan;
import com.example.iranman.R;
import com.example.tools.weather;
import com.example.utils.DataGenerator;

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
        Button Weather = view.findViewById(R.id.Weather);
        Button Stumanage = view.findViewById(R.id.studentmanager);
        Button Album = view.findViewById(R.id.Album);
        Button qrhisttory = view.findViewById(R.id.qrhisttory);
        Album.setOnClickListener(new TWO.ButtonListener());
        QRScan.setOnClickListener(new TWO.ButtonListener());
        Weather.setOnClickListener(new TWO.ButtonListener());
        Stumanage.setOnClickListener(new TWO.ButtonListener());
        qrhisttory.setOnClickListener(new TWO.ButtonListener());
        return view;
    }
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.QR:
                    Intent intent = new Intent(getActivity(),QRScan.class);
                    startActivityForResult(intent,2);
                    break;
                case R.id.Weather:
                    Intent intent2 = new Intent(getActivity(), weather.class);
                    startActivityForResult(intent2,2);
                    break;
                case R.id.studentmanager:
                    Intent intent3 = new Intent(getActivity(), query_stu.class);
                    startActivityForResult(intent3,2);
                    break;
                case R.id.Album:
                    Intent intent4 = new Intent(getActivity(), AlbumQR.class);
                    startActivityForResult(intent4,2);
                    break;
                case R.id.qrhisttory:
                    Intent intent5 = new Intent(getActivity(), QRHistory.class);
                    startActivityForResult(intent5,2);
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
