package com.example.stu_manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iranman.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class modify_stu extends AppCompatActivity {
    int ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_stu);
        Button submodify = findViewById(R.id.submodify);
        EditText newpwd = findViewById(R.id.modifypwd);
        String ID1 =this.getIntent().getStringExtra("Id");
        TextView title = findViewById(R.id.txt_title);
        TextView right = findViewById(R.id.txt_right);
        title.setText("更改密码");
        right.setText("");
        int ID = Integer.parseInt(ID1);
        Log.d("id",ID1);

        Button left = findViewById(R.id.txt_left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modify_stu.this.finish();
            }
        });
        submodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("提交开始", "这里开始提交");
                final String val = newpwd.getText().toString();
                if(val==null){
                    Toast.makeText(modify_stu.this,"请输入内容",Toast.LENGTH_SHORT).show();
                }else{
                    new Thread(new Runnable() {//新建线程
                        @Override
                        public void run() {
                            List<student> stuList2 = new ArrayList<>();
                            try {
                                Log.d("进入run","进入run");
                                Class.forName("com.mysql.jdbc.Driver");//加载驱动
                                Connection cn= DriverManager.getConnection("jdbc:mysql://120.79.79.76:3306/hblog","root","1234");//连接
                                String sql="UPDATE user SET user_pwd = '"+val+"'   WHERE user_id = '"+ID+"'";
                                Log.d("sql语句",sql);
                                Statement st=(Statement)cn.createStatement();
                                PreparedStatement ps = cn.prepareStatement(sql);
                                int ret = ps.executeUpdate();
                                cn.close();//记得关闭 不然内存泄漏
                                st.close();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                                //text1.setText("驱动初始化失败"+e);
                            } catch (SQLException e) {
                                e.printStackTrace();
                                //text1.setText("数据库链接失败"+e);
                            }
                        }
                    }).start();//执行线程
                    Toast.makeText(modify_stu.this,"更新成功",Toast.LENGTH_SHORT);
                    modify_stu.this.finish();
                }
            }
        });
    }
}
