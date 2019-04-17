package com.example.iranman;

import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tool.DataGenerator;

public class MainActivity extends AppCompatActivity {
private RadioGroup mRadioGroup;
private Fragment[]mFragments;
private RadioButton mRadioButtonHome;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeImageSize();
        mFragments = DataGenerator.getFragments();
        initView();
    }
    public void initView(){
    mRadioGroup = findViewById(R.id.radio_group_button);
    mRadioButtonHome = findViewById(R.id.radio_button_main);
    mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
        Fragment mFragment = null;
        @Override
        public void onCheckedChanged(RadioGroup group,@IdRes int checkedId) {
            switch (checkedId){
                case R.id.radio_button_main:
                    mFragment = mFragments[0];
                    break;
                case R.id.radio_button_tools:
                    mFragment = mFragments[1];
                    break;
                case R.id.radio_button_mine:
                    mFragment = mFragments[2];
                    break;
            }
            if(mFragments!=null){
                getSupportFragmentManager().beginTransaction().replace(R.id.home_container,mFragment).commit();
            }
        }
    });
    mRadioButtonHome.setChecked(true);
    }

    private void changeImageSize() {
        RadioButton Rmain = findViewById(R.id.radio_button_main);
        RadioButton Rtools = findViewById(R.id.radio_button_tools);
        RadioButton Rmine = findViewById(R.id.radio_button_mine);
        //定义底部标签图片大小
        Drawable main = getResources().getDrawable(R.drawable.main);
        main.setBounds(0, 0, 69, 69);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        Rmain.setCompoundDrawables(null,main,null,null);

        Drawable tools = getResources().getDrawable(R.drawable.tools);
        tools.setBounds(0, 0, 69, 69);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        Rtools.setCompoundDrawables(null,tools,null,null);

        Drawable mine = getResources().getDrawable(R.drawable.mine);
        mine.setBounds(0, 0, 69, 69);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        Rmine.setCompoundDrawables(null,mine,null,null);
    }
}
