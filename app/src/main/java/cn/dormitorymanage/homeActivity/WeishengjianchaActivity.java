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
import cn.dormitorymanage.adapter.WeishengjianchaItem;
import cn.dormitorymanage.adapter.WeishengjianchaItemAdapter;
import cn.dormitorymanage.function.HttpRequest;
import cn.dormitorymanage.function.User;

public class WeishengjianchaActivity extends AppCompatActivity {

    User user = new User(WeishengjianchaActivity.this);
    private ListView listView;
    private WeishengjianchaItem weishengjianchaItem;
    private List<WeishengjianchaItem> weishengjianchaItemList = new ArrayList<>();
    private WeishengjianchaItemAdapter weishengjianchaItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weishengjiancha);
        findViewById(R.id.weishengjianchaActivity_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.weishengjianchaActivity_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WeishengjianchaActivity.this,WeishengjianchaAddActivity.class));
            }
        });
        if (!user.getIsAdmin()) {
            findViewById(R.id.weishengjianchaActivity_add).setVisibility(View.GONE);
        }

        initView();
    }

    private void initView() {
        listView = findViewById(R.id.weishengjianchaActivity_listView);
        weishengjianchaItemAdapter = new WeishengjianchaItemAdapter(WeishengjianchaActivity.this, R.layout.adapter_layout_weishengjianchaitem, weishengjianchaItemList);
        listView.setAdapter(weishengjianchaItemAdapter);
    }

    String tung = "";
    private void getData() {
        weishengjianchaItemList.clear();
        weishengjianchaItemAdapter.notifyDataSetChanged();
        try {
            JSONObject jsonObject = new JSONObject();
            String url = "";
            if (user.getIsAdmin()){
                url = "queryAllHygiene";
                tung = user.getManag_dormitory_num();
            }else {
                url = "queryOneHygiene";
                jsonObject.put("hygiene_dormitoryid", user.getStu_dormitoryid());
                tung = user.getStu_dormitoryid().substring(0, 1);
            }
            HttpRequest.postJSONArray(url, jsonObject, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if(jsonObject.getString("hygiene_dormitoryid").substring(0, 1).equals(tung)){
                                weishengjianchaItem = new WeishengjianchaItem(jsonObject.getString("hygiene_dormitoryid"), jsonObject.getString("hygiene_grade"), jsonObject.getString("hygiene_remark"), jsonObject.getString("hygiene_time"));
                                weishengjianchaItemList.add(weishengjianchaItem);
                                weishengjianchaItemAdapter.notifyDataSetChanged();
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
