package com.example.tools;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragment.TWO;
import com.example.iranman.R;
import com.example.utils.HisAdapter;
import com.example.utils.MyApplication;
import com.example.utils.StuAdapter;
import com.example.zxingLibraryQR.QRrecord;

import org.litepal.LitePal;

import java.util.List;

public class QRHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrhistiry);
        Button left = findViewById(R.id.txt_left);
        TextView title = findViewById(R.id.txt_title);
        Button right = findViewById(R.id.txt_right);
        left.setOnClickListener(new QRHistory.ButtonListener());
        right.setOnClickListener(new QRHistory.ButtonListener());
        left.setText("返回");
        right.setText("");
        /*获取数据库的数据*/
        List<QRrecord> QRhistorys = LitePal.findAll(QRrecord.class);
        HisAdapter qrAdapter = new HisAdapter(QRhistorys);
        qrAdapter.notifyDataSetChanged();
        RecyclerView recyclerView = findViewById(R.id.qrhistorylist);
        Context mcontext = MyApplication.getContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mcontext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(qrAdapter);
    }
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.txt_left:
                    finish();
            }
        }
    }
}
