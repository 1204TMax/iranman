package com.example.tools;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iranman.R;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import static android.content.ContentValues.TAG;

public class QRScan extends AppCompatActivity {
    public static QRScan mactivity;
    public static Context mContext;
    private final int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);
        mactivity=this;
        mContext = this.getBaseContext();
        Button Rtitle = findViewById(R.id.txt_right);
        Button Ltitle = findViewById(R.id.txt_left);
        Rtitle.setText("重新扫描");
        Ltitle.setText("返回");
        Rtitle.setOnClickListener(new ButtonListener());
        Ltitle.setOnClickListener(new ButtonListener());

        ZXingLibrary.initDisplayOpinion(this);
        Intent intent = new Intent(QRScan.this, MyQR.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.txt_right:
                    Log.d(TAG, "这是扫码功能");
                    Intent intent = new Intent(QRScan.this, MyQR.class);
                    startActivityForResult(intent, REQUEST_CODE);
                    break;
                case R.id.txt_left:
                    Log.d(TAG, "返回");
                    setResult(20);
                    finish();
                    break;
            }
        }
    }
    @Override
    public void  onActivityResult(int RequestCode,int resultCode,Intent data){
        TextView t2 = findViewById(R.id.QR_T2);
        if (RequestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    t2.setText(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    t2.setText("解析失败，请重试");
                    t2.setTextColor(Color.parseColor("#ef342a"));
                }
            }
            if(resultCode==303){
                //处理扫描结果（在界面上显示）
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    String three = data.getStringExtra("albumqr");
                    t2.setText(three);
                }
            } else if(resultCode==505){
                String three = "解析失败，请重试";
                t2.setText(three);
                t2.setTextColor(Color.parseColor("#ef342a"));
            }
        }
        }

    }
