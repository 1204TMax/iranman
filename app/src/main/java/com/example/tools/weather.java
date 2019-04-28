package com.example.tools;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.iranman.R;
import com.example.stu_manager.modify_stu;
import com.example.stu_manager.query_stu;
import com.example.utils.MyApplication;

import java.util.ArrayList;
import java.util.List;
import static android.content.ContentValues.TAG;

public class weather extends AppCompatActivity {

    public LocationClient mLocationClient;
    private TextView positionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        mLocationClient = new LocationClient((getApplicationContext()));
        mLocationClient.registerLocationListener(new MyLocationListener());
        setContentView(R.layout.activity_weather);
        positionText = findViewById(R.id.city);
        Button left = findViewById(R.id.txt_left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(20);
                finish();
            }
        });
        TextView right = findViewById(R.id.txt_right);
        TextView title = findViewById(R.id.txt_title);
        right.setText("");
        title.setText("你的位置");
        List<String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(weather.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(weather.this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(weather.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(weather.this,permissions,1);
        }else{
            requestLocation();
        }
    }

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.txt_left:
                    Log.d(TAG, "返回");
                    setResult(20);
                    finish();
                    break;
            }
            }
        }
        private void requestLocation(){
        initLocation();
        mLocationClient.start();
        }
    public void initLocation(){
            LocationClientOption option = new LocationClientOption();
            option.setIsNeedAddress(true);
            mLocationClient.setLocOption(option);
        }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mLocationClient.stop();
        }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
           switch(requestCode){
               case 1:
                   if(grantResults.length>0){
                       for(int result:grantResults){
                           if(result != PackageManager.PERMISSION_GRANTED){
                               Toast.makeText(this,"必须同意所有权限才能使用本功能",Toast.LENGTH_SHORT).show();
                               finish();
                               return;
                           }
                       }
                       requestLocation();
                   }else{
                       Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                       finish();
                   }
                   break;
                   default:
           }
        }
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
           StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("纬度:").append(location.getLatitude()).append("\n");
            currentPosition.append("经线:").append(location.getLongitude()).append("\n");
            currentPosition.append("国家:").append(location.getCountry()).append("\n");;
            currentPosition.append("省:").append(location.getProvince()).append("\n");;
            currentPosition.append("市:").append(location.getCity()).append("\n");
            currentPosition.append("区:").append(location.getDistrict()).append("\n");;
            currentPosition.append("街道:").append(location.getStreet()).append("\n");;
            currentPosition.append("定位方式:");
            if(location.getLocType() == BDLocation.TypeGpsLocation){
                currentPosition.append("GPS");
            }else if(location.getLocType() == BDLocation.TypeNetWorkLocation){
                currentPosition.append("网络");
            }
            positionText.setText(currentPosition);
        }

    }
}


