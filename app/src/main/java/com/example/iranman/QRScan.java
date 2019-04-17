package com.example.iranman;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class QRScan extends AppCompatActivity {
    public static QRScan mactivity;
    public static Context mContext;
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

    }

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.txt_right:
                    Log.d(TAG, "这是扫码功能");
                    break;
                case R.id.txt_left:
                    Log.d(TAG, "返回");
                    setResult(20);
                    finish();
                    break;
            }
        }
    }

}
