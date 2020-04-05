package cn.dormitorymanage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.dormitorymanage.R;
import cn.dormitorymanage.SelectStudentActivity;
import cn.dormitorymanage.adapter.StudentListItem;
import cn.dormitorymanage.adapter.StudentListItemAdapter;
import cn.dormitorymanage.function.HttpRequest;
import cn.dormitorymanage.function.User;

public class TxlFragment extends Fragment {

    private User user;

    private ListView listView;
    private StudentListItem studentListItem;
    private List<StudentListItem> studentListItemList = new ArrayList<>();
    private StudentListItemAdapter studentListItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_txl, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        user = new User(getContext());
        ImageView imageView_add = getActivity().findViewById(R.id.txlFragment_add);
        imageView_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SelectStudentActivity.class));
            }
        });
        TextView textView = getActivity().findViewById(R.id.txlFragment_title);
        if (user.getIsAdmin()) {
            textView.setText("我管理的学生");
        }else{
            textView.setText("我的室友");
            imageView_add.setVisibility(View.GONE);
        }
        initView();
    }

    private void initView() {
        listView = getActivity().findViewById(R.id.txlFragment_ListView);
        studentListItemAdapter = new StudentListItemAdapter(getContext(), R.layout.adapter_layout_studentlistitem, studentListItemList);
        listView.setAdapter(studentListItemAdapter);
    }

    String tung = "";
    private void getData() {
        studentListItemList.clear();
        studentListItemAdapter.notifyDataSetChanged();
        try {
            JSONObject jsonObject = new JSONObject();
            String url = "";
            if (user.getIsAdmin()){
                url = "queryAllStudent";
                tung = user.getManag_dormitory_num();
            }else {
                url = "queryThisDormitoryStudent";
                jsonObject.put("stu_dormitoryid", user.getStu_dormitoryid());
                if (user.getStu_dormitoryid().length() > 2) {
                    tung = user.getStu_dormitoryid().substring(0, 1);
                }else{
                    tung = "0";
                }
            }
            HttpRequest.postJSONArray(url, jsonObject, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (jsonObject.getString("stu_dormitoryid").length() > 2) {
                                if(jsonObject.getString("stu_dormitoryid").substring(0, 1).equals(tung)){
                                    studentListItem = new StudentListItem(jsonObject.getString("stu_id"), jsonObject.getString("stu_name"), jsonObject.getString("stu_gender"), jsonObject.getString("stu_img"), jsonObject.getString("stu_age"), jsonObject.getString("stu_nativeplace"), jsonObject.getString("stu_major"), jsonObject.getString("stu_hobby"), jsonObject.getString("stu_phone"), jsonObject.getString("stu_dormitoryid"));
                                    studentListItemList.add(studentListItem);
                                    studentListItemAdapter.notifyDataSetChanged();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            },null);
        } catch (JSONException e) {
            Log.d("错误", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

}
