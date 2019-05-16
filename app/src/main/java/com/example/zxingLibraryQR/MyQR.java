package com.example.zxingLibraryQR;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.iranman.R;
import com.example.utils.ImageUtil;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

public class MyQR extends AppCompatActivity {
    private int i = 1;
    private static int REQUEST_IMAGE = 101;
    private static int TYPE_XC_RETURN_CODE = 202;
    private static int ABC = 303;
    private static int ABCD = 505;
    public LocationClient mLocationClient;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static int REQUEST_PERMISSION_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mLocationClient = new LocationClient((getApplicationContext()));
        /*拍照权限和存储权限动态获取*/
        if (Build.VERSION.SDK_INT>22){
        List<String> permissionList = new ArrayList<>();
            if (ContextCompat.checkSelfPermission(MyQR.this,android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                permissionList.add(Manifest.permission.CAMERA);
            }
            if (ActivityCompat.checkSelfPermission(MyQR.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if(!permissionList.isEmpty()){
                String [] permissions = permissionList.toArray(new String[permissionList.size()]);
                ActivityCompat.requestPermissions(MyQR.this,permissions,1);
            }else{
                requestLocation();
            }
        }else {
                //这个说明系统版本在6.0之下，不需要动态获取权限。
        }
        /**
         * 执行扫面Fragment的初始化操作
         */
        CaptureFragment captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);

        captureFragment.setAnalyzeCallback(analyzeCallback);
        /**
         * 替换我们的扫描控件
         */ getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
        Button B1 = findViewById(R.id.second_button1);
        Button B2 = findViewById(R.id.AlbumQR);
        Button B3 = findViewById(R.id.second_button3);
        B1.setOnClickListener(new MyQR.ButtonListener());
        B2.setOnClickListener(new MyQR.ButtonListener());
        B3.setOnClickListener(new MyQR.ButtonListener());
    }
    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            MyQR.this.setResult(RESULT_OK, resultIntent);
            MyQR.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            MyQR.this.setResult(RESULT_OK, resultIntent);
            MyQR.this.finish();
        }
    };

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
                switch (v.getId()){
                    case R.id.second_button1:
                        CodeUtils.isLightEnable(false);
                        finish();
                        break;
                    case R.id.second_button3:
                        i=i+1;
                        if(i % 2 == 0){
                            CodeUtils.isLightEnable(true);
                        }else{
                            CodeUtils.isLightEnable(false);
                        }
                        break;
                    case R.id.AlbumQR:
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("image/*");
                        startActivityForResult(intent, REQUEST_IMAGE);
                        break;
                }
            }
        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    try {
                        CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
                            @Override
                            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                                //手机震动
                                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                                vibrator.vibrate(200);
                                Intent intent = new Intent();
                                intent.putExtra("albumqr", result);
                                setResult(ABC,intent);
                                MyQR.this.finish();
                            }
                            @Override
                            public void onAnalyzeFailed() {
                                setResult(ABCD);
                                MyQR.this.finish();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
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
    protected void onDestroy() {
        super.onDestroy();
        CodeUtils.isLightEnable(false);
    }

}
