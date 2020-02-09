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
import cn.dormitorymanage.adapter.SongshuiItem;
import cn.dormitorymanage.adapter.SongshuiItemAdapter;
import cn.dormitorymanage.function.HttpRequest;
import cn.dormitorymanage.function.User;

public class SongshuiActivity extends AppCompatActivity {
    User user = new User(SongshuiActivity.this);

    private ListView listView;
    private SongshuiItem songshuiItem;
    private List<SongshuiItem> songshuiItemList = new ArrayList<>();
    private SongshuiItemAdapter songshuiItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songshui);
        findViewById(R.id.songshuiActivity_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.songshuiActivity_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SongshuiActivity.this,SongshuiAddActivity.class));
            }
        });
        if (user.getIsAdmin()) {
            findViewById(R.id.songshuiActivity_add).setVisibility(View.GONE);
        }
        initView();
    }

    private void initView() {
        listView = findViewById(R.id.songshuiActivity_listView);
        songshuiItemAdapter = new SongshuiItemAdapter(SongshuiActivity.this, R.layout.adapter_layout_songshuiitem, songshuiItemList);
        listView.setAdapter(songshuiItemAdapter);
    }


    String tung = "";
    private void getData() {
        songshuiItemList.clear();
        songshuiItemAdapter.notifyDataSetChanged();
        try {
            User user = new User(SongshuiActivity.this);
            JSONObject jsonObject = new JSONObject();
            String url = "";
            if (user.getIsAdmin()){
                url = "queryAllWater";
                tung = user.getManag_dormitory_num();
            }else {
                url = "queryOneWater";
                jsonObject.put("water_dormitoryid", user.getStu_dormitoryid());
                tung = user.getStu_dormitoryid().substring(0, 1);
            }
            HttpRequest.postJSONArray(url, jsonObject, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if(jsonObject.getString("water_dormitoryid").substring(0, 1).equals(tung)){
                                songshuiItem = new SongshuiItem(jsonObject.getString("water_dormitoryid"),jsonObject.getString("water_num"),jsonObject.getString("water_time"));
                                songshuiItemList.add(songshuiItem);
                                songshuiItemAdapter.notifyDataSetChanged();
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
