package cn.dormitorymanage;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.dormitorymanage.adapter.StudentListItem;
import cn.dormitorymanage.adapter.StudentListItem2Adapter;
import cn.dormitorymanage.function.HttpRequest;
import cn.dormitorymanage.function.User;

public class SelectStudentActivity extends AppCompatActivity {
    private User user;

    private List<String> stringList = new ArrayList<>();

    private ListView listView;
    private StudentListItem studentListItem;
    private List<StudentListItem> studentListItemList = new ArrayList<>();
    private StudentListItem2Adapter studentListItem2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_student);
        user = new User(SelectStudentActivity.this);
        findViewById(R.id.selectStudentActivity_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    private void initView() {
        listView = findViewById(R.id.selectStudentActivity_ListView);
        studentListItem2Adapter = new StudentListItem2Adapter(SelectStudentActivity.this, R.layout.adapter_layout_selectstudentitem, studentListItemList, stringList);
        listView.setAdapter(studentListItem2Adapter);
    }

    String tung = "";
    private void getData() {
        tung = user.getManag_dormitory_num();
        try {
            HttpRequest.postJSONArray("queryAllStudent", new JSONObject("{}"), new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if(jsonObject.getString("stu_gender").trim().equals(user.getManag_manag_xb().trim())){
                                studentListItem = new StudentListItem(jsonObject.getString("stu_id"), jsonObject.getString("stu_name"), jsonObject.getString("stu_gender"), jsonObject.getString("stu_img"), jsonObject.getString("stu_age"), jsonObject.getString("stu_nativeplace"), jsonObject.getString("stu_major"), jsonObject.getString("stu_hobby"), jsonObject.getString("stu_dormitoryid"));
                                studentListItemList.add(studentListItem);
                                studentListItem2Adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            },null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDatadormitoryid();
    }


    private void getDatadormitoryid(){
        try {
            HttpRequest.postJSONArray("queryDormitory", new JSONObject("{\"tung\":\"" + user.getManag_dormitory_num() + "\"}"), new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            stringList.add(jsonObject.getString("dormitory_room"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            },null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getData();
    }
}
