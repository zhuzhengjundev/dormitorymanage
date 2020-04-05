package cn.dormitorymanage.homeActivity;

import android.content.Intent;
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

import cn.dormitorymanage.R;
import cn.dormitorymanage.adapter.WeixiuItem;
import cn.dormitorymanage.adapter.WeixiuItemAdapter;
import cn.dormitorymanage.function.HttpRequest;
import cn.dormitorymanage.function.User;

public class WeixiuActivity extends AppCompatActivity {

    private ListView listView;
    private WeixiuItem weixiuItem;
    private List<WeixiuItem> weixiuItemList = new ArrayList<>();
    private WeixiuItemAdapter weixiuItemAdapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixiu);
        user = new User(WeixiuActivity.this);
        findViewById(R.id.weixiuActivity_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.weixiuActivity_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WeixiuActivity.this,WeixiuAddActivity.class));
            }
        });
        initView();
    }

    private void initView() {
        listView = findViewById(R.id.weixiuActivity_listView);
        weixiuItemAdapter = new WeixiuItemAdapter(WeixiuActivity.this, R.layout.adapter_layout_weixiuitem, weixiuItemList,user);
        listView.setAdapter(weixiuItemAdapter);
    }

    String tung = "";
    private void getData() {
        weixiuItemList.clear();
        weixiuItemAdapter.notifyDataSetChanged();
        try {
            User user = new User(WeixiuActivity.this);
            JSONObject jsonObject = new JSONObject();
            String url = "";
            if (user.getIsAdmin()){
                url = "queryAllRepair";
                tung = user.getManag_dormitory_num();
            }else {
                url = "queryOneRepair";
                jsonObject.put("repair_dormitoryid", user.getStu_dormitoryid());
                tung = user.getStu_dormitoryid().substring(0, 1);
            }
            HttpRequest.postJSONArray(url, jsonObject, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if(jsonObject.getString("repair_dormitoryid").substring(0, 1).equals(tung)){
                                weixiuItem = new WeixiuItem(jsonObject.getString("repair_id"), jsonObject.getString("repair_stuid"), jsonObject.getString("repair_dormitoryid"), jsonObject.getString("repair_name"), jsonObject.getString("repair_remark"), jsonObject.getString("repair_time"), jsonObject.getString("repair_stat"));
                                weixiuItemList.add(weixiuItem);
                                weixiuItemAdapter.notifyDataSetChanged();
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
        getData();
    }
}
