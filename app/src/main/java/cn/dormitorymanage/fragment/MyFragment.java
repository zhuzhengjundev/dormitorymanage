package cn.dormitorymanage.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import cn.dormitorymanage.LoginActivity;
import cn.dormitorymanage.MyDataUpdateActivity;
import cn.dormitorymanage.MyPwdUpdateActivity;
import cn.dormitorymanage.R;
import cn.dormitorymanage.SelectWeixiuShifuActivity;
import cn.dormitorymanage.function.User;

public class MyFragment extends Fragment {

    private ImageView imageView;
    private TextView textView;
    User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        user = new User(getContext());
        initView();
    }

    private void initView() {
        imageView = getActivity().findViewById(R.id.myFragment_img);
        textView = getActivity().findViewById(R.id.myFragment_name);

        getActivity().findViewById(R.id.myFragment_ziliaoxiugai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MyDataUpdateActivity.class));
            }
        });

        getActivity().findViewById(R.id.myFragment_pwdUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MyPwdUpdateActivity.class));
            }
        });

        getActivity().findViewById(R.id.myFragment_selectWeixiushifu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SelectWeixiuShifuActivity.class));
            }
        });

        if(user.getIsAdmin()){
            imageView.setImageResource(R.drawable.admin);
            textView.setText("管理员："+user.getManag_name());
            getActivity().findViewById(R.id.myFragment_ziliaoxiugai).setVisibility(View.GONE);
            getActivity().findViewById(R.id.myFragment_pwdUpdate).setVisibility(View.GONE);
        }else{
            imageView.setImageResource(R.drawable.student);
            textView.setText("学生："+user.getStu_name());
            getActivity().findViewById(R.id.myFragment_selectWeixiushifu).setVisibility(View.GONE);
        }

        getActivity().findViewById(R.id.myFragment_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("提示");
                dialog.setMessage("确认退出当前账号吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        user.setIsAdmin(false);
                        user.setManag_id("");
                        user.setManag_name("");
                        user.setManag_dormitory_num("");
                        user.setManag_manag_xb("");
                        user.setStu_id("");
                        user.setStu_name("");
                        user.setStu_gender("");
                        user.setStu_major("");
                        user.setStu_dormitoryid("");
                        user.setStu_hobby("");
                        user.setStu_phone("");
                        user.setStu_age("");
                        user.setStu_nativeplace("");
                        user.setStu_img("");
                        startActivity(new Intent(getContext(), LoginActivity.class));
                        getActivity().finish();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show();
            }
        });
    }
}
