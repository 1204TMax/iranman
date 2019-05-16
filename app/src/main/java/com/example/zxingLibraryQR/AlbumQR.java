package com.example.zxingLibraryQR;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.iranman.R;
import com.example.utils.ImageUtil;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlbumQR extends AppCompatActivity {
    private static int REQUEST_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_qr);

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE);
    }
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
                                Toast.makeText(AlbumQR.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
                                Date date = new Date(System.currentTimeMillis());
                                String time = simpleDateFormat.format(date);
                                QRrecord qr = new QRrecord();
                                qr.setDelrecord(0);
                                qr.setRecord(result);
                                qr.setTime(time);
                                qr.save();
                                finish();
                            }

                            @Override
                            public void onAnalyzeFailed() {
                                AlbumQR.this.finish();
                                Toast.makeText(AlbumQR.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
