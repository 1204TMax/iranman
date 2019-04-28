package com.example.utils;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.iranman.R;
import com.example.stu_manager.query_stu;
import com.example.stu_manager.student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.stu_manager.query_stu.stuList;

@SuppressWarnings("ALL")
public class jdbc_factory {
   /* private MyApplication myApplication;
    List<student> stuList2 = new ArrayList<>();
    @Override
    protected void onPreExecute(){

    }
    @Override
    protected ArrayList<student> doInBackground(Integer... integers) {
        try{
            try {
                Class.forName("com.mysql.jdbc.Driver");//加载驱动
                Connection cn= DriverManager.getConnection("jdbc:mysql://120.79.79.76:3306/hblog","root","1234");//连接
                String sql="select * from user";//准备语句
                Statement st=(Statement)cn.createStatement();
                ResultSet rs=st.executeQuery(sql);//执行
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
                }
                cn.close();//记得关闭 不然内存泄漏
                st.close();
                rs.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                //text1.setText("驱动初始化失败"+e);
            } catch (SQLException e) {
                e.printStackTrace();
                //text1.setText("数据库链接失败"+e);
            }
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(,"获取网络数据错误",Toast.LENGTH_SHORT).show();
        }
        return (ArrayList<student>) stuList2;
    }
    @Override
    protected void onProgressUpdate(Integer...values){

    }
    @Override
    protected void onPostExecute(ArrayList<student> result){
        StuAdapter stuAdapter = new StuAdapter(stuList);
        stuAdapter.notifyDataSetChanged();
        RecyclerView recyclerView = findViewById(R.id.stu_list);
        Context mcontext = MyApplication.getContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mcontext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(stuAdapter);*/
}
