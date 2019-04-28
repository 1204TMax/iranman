package com.example.stu_manager;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iranman.R;
import com.example.utils.MyApplication;
import com.example.utils.StuAdapter;
import com.example.utils.jdbc_factory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class query_stu extends AppCompatActivity {
    public static List<student> stuList = new ArrayList<>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String username;
    String userpassword;
    boolean flag = false;
    ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_stu);
        pg = (ProgressBar) findViewById(R.id.qu_pd);
        TextView Ttitle = findViewById(R.id.txt_title);
        TextView Tright = findViewById(R.id.txt_right);
        Ttitle.setText("学生信息");
        Tright.setText("十");
        Button add = findViewById(R.id.txt_right);
        Button left = findViewById(R.id.txt_left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(20);
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(query_stu.this,add_stu.class);
                startActivity(intent);
            }
        });
        /*query mt=new query();
        mt.execute();*/
        Refresh();
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.F5);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                //重新获取数据
                //获取完成
                swipeRefreshLayout.setRefreshing(false);
                query mt=new query();
                mt.execute();
            }
        });
    }

    class query extends AsyncTask<Void,Integer,ArrayList<student>> {
        private MyApplication myApplication;
        List<student> stuList2 = new ArrayList<>();

        @Override
        protected void onPreExecute(){ ;
        }
        @Override
        protected ArrayList<student> doInBackground(Void... integers) {
            try{
                try {
                    Class.forName("com.mysql.jdbc.Driver");//加载驱动
                    Connection cn= DriverManager.getConnection("jdbc:mysql://120.79.79.76:3306/hblog","root","1234");//连接
                    String sql="select * from user";//准备语句
                    Statement st=(Statement)cn.createStatement();
                    ResultSet rs=st.executeQuery(sql);//执行
                    rs.last();
                    double count = rs.getRow();
                    rs.first();
                    String asd = String.valueOf(count);
                    while(rs.next()){//遍历结果
                        final String id=rs.getString("user_id");//查找字段
                        final String name=rs.getString("user_name");//查找字段
                        final String pwd=rs.getString("user_pwd");//查找字段
                        int stuid  = Integer.parseInt(id);
                        student student = new student();
                        student.setStuId(stuid);
                        student.setStuName(name);
                        student.setStuPwd(pwd);
                        stuList2.add(student);

                        double nowsize = stuList2.size();
                        double pga = nowsize/count;
                        double progress1 =pga*100;
                        int progress = (int) progress1;
                        publishProgress(progress);
                    }
                    cn.close();//记得关闭 不然内存泄漏
                    st.close();
                    rs.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }catch(Exception e){
                e.printStackTrace();
                Toast.makeText(query_stu.this,"获取网络数据错误",Toast.LENGTH_SHORT).show();
            }
            return (ArrayList<student>) stuList2;
        }
        @Override
        protected void onProgressUpdate(Integer...values){
            super.onProgressUpdate(values);
//            pg.getBackground().setAlpha(0);
            pg.setProgress(values[0]);
            if(values[0] == 95){
                pg.setVisibility(View.GONE);
            }
        }
        @Override
        protected void onPostExecute(ArrayList<student> result){
            StuAdapter stuAdapter = new StuAdapter(result);
            stuAdapter.notifyDataSetChanged();
            RecyclerView recyclerView = findViewById(R.id.stu_list);
            Context mcontext = MyApplication.getContext();
            LinearLayoutManager layoutManager = new LinearLayoutManager(mcontext);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(stuAdapter);
        }
    }
    public void Refresh(){
        query mt=new query();
        mt.execute();
    }
}
