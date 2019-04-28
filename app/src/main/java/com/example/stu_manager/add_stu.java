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

import com.example.iranman.MainActivity;
import com.example.iranman.R;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class add_stu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stu);
        Button submodify = findViewById(R.id.subadd);
        EditText name1 = findViewById(R.id.addname);
        EditText pwd1 = findViewById(R.id.addpwd);
        Button left = findViewById(R.id.txt_left);
        TextView right = findViewById(R.id.txt_right);
        TextView title = findViewById(R.id.txt_title);
        title.setText("添加用户");
        right.setText("");
        left.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                add_stu.this.finish();
               }
          });

        submodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = name1.getText().toString();
                String pwd = pwd1.getText().toString();
                if(name!=null||pwd!=null){
                    new Thread(new Runnable() {//新建线程
                        @Override
                        public void run() {
                            List<student> stuList2 = new ArrayList<>();
                            try {

                                Log.d("进入run","进入run");
                                Class.forName("com.mysql.jdbc.Driver");//加载驱动
                                Connection cn= DriverManager.getConnection("jdbc:mysql://120.79.79.76:3306/hblog","root","1234");//连接
                                PreparedStatement ps=null;
                                Log.d("name",name);
                                Log.d("pwd",pwd);
                                String sql = "INSERT INTO user(user_name,user_pwd) VALUES('"+name+"','"+pwd+"')";
                                ps = cn.prepareStatement(sql);
                                Log.d("sql语句",sql);
                                int ret = ps.executeUpdate();
                                cn.close();//记得关闭 不然内存泄漏
                                ps.close();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                                //text1.setText("驱动初始化失败"+e);
                            } catch (SQLException e) {
                                e.printStackTrace();
                                //text1.setText("数据库链接失败"+e);
                            }
                        }
                    }).start();//执行线程
                    Toast.makeText(add_stu.this,"上传成功",Toast.LENGTH_SHORT);
                    add_stu.this.finish();
                    finish();
                }else{
                    Toast.makeText(add_stu.this,"用户名和密码不允许为空",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
