package com.example.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iranman.R;
import com.example.stu_manager.modify_stu;
import com.example.stu_manager.query_stu;
import com.example.stu_manager.student;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StuAdapter extends RecyclerView.Adapter<StuAdapter.ViewHolder>{
    private List<student> stuList;
    private MyApplication myApplication;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View studentview;
        TextView  stuid;
        TextView stuname;
        TextView stupwd;
        Button delstu;
        Button modifystu;


        public ViewHolder(View view){
            super(view);
            studentview = view;
            stuid = view.findViewById(R.id.stuid);
            stuname = view.findViewById(R.id.stuname);
            stupwd = view.findViewById(R.id.stupwd);
            modifystu = view.findViewById(R.id.modifystu);
            delstu = view.findViewById(R.id.delstu);
        }
    }
    public StuAdapter(List<student> students){
        stuList = students;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        Context mcontext = MyApplication.getContext();
        holder.modifystu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                student student = stuList.get(position);
                Intent intent=new Intent(mcontext,modify_stu.class);
                intent.putExtra("Name",student.getStuName());
                intent.putExtra("Pwd",student.getStuPwd());
                String id = String.valueOf(student.getStuId());
                intent.putExtra("Id",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
            }
        });
        holder.delstu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                final student student = stuList.get(position);
                new Thread(new Runnable() {//新建线程
                    @Override
                    public void run() {
                        List<student> stuList2 = new ArrayList<>();
                        try {
                            Log.d("进入run","进入run");
                            Class.forName("com.mysql.jdbc.Driver");//加载驱动
                            Connection cn= DriverManager.getConnection("jdbc:mysql://120.79.79.76:3306/hblog","root","1234");//连接
                            String sql="DELETE FROM user WHERE user_id =" + student.getStuId();//准备语句
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
                Toast.makeText(mcontext,"删除成功",Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        student student = stuList.get(position);
        holder.stuname.setText(student.getStuName());
        holder.stupwd.setText(student.getStuPwd());
        String stuid1 =String.valueOf(student.getStuId());
        holder.stuid.setText(stuid1);
    }
    @Override
    public int getItemCount() {
        return stuList.size();
    }

}
