package com.example.fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.iranman.MainActivity;
import com.example.stu_manager.query_stu;
import com.example.tools.QRScan;
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
        if (Build.VERSION.SDK_INT>22){
            if (ContextCompat.checkSelfPermission(getActivity(),
                    android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                //先判断有没有权限 ，没有就在这里进行权限的申请
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.CAMERA},1);

            }else {
                //说明已经获取到摄像头权限了 想干嘛干嘛
            }
        }else {
//这个说明系统版本在6.0之下，不需要动态获取权限。

        }
        Button QRScan = view.findViewById(R.id.QR);
        Button Weather = view.findViewById(R.id.Weather);
        Button Stumanage = view.findViewById(R.id.studentmanager);
        QRScan.setOnClickListener(new TWO.ButtonListener());
        Weather.setOnClickListener(new TWO.ButtonListener());
        Stumanage.setOnClickListener(new TWO.ButtonListener());
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
