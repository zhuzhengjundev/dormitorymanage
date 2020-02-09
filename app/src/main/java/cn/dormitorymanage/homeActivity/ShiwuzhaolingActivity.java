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
import cn.dormitorymanage.adapter.ShiwuzhaolingItem;
import cn.dormitorymanage.adapter.ShiwuzhaolingItemAdapter;
import cn.dormitorymanage.function.HttpRequest;

public class ShiwuzhaolingActivity extends AppCompatActivity {

    private ListView listView;
    private ShiwuzhaolingItem shiwuzhaolingItem;
    private List<ShiwuzhaolingItem> shiwuzhaolingItemList = new ArrayList<>();
    private ShiwuzhaolingItemAdapter shiwuzhaolingItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiwuzhaoling);
        findViewById(R.id.shiwuzhaolingActivity_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.shiwuzhaolingActivity_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShiwuzhaolingActivity.this,ShiwuzhaolingAddActivity.class));
            }
        });
        initView();
    }

    private void initView() {
        listView = findViewById(R.id.shiwuzhaolingActivity_listView);
        shiwuzhaolingItemAdapter = new ShiwuzhaolingItemAdapter(ShiwuzhaolingActivity.this, R.layout.adapter_layout_shiwuzhaolingitem, shiwuzhaolingItemList);
        listView.setAdapter(shiwuzhaolingItemAdapter);
    }

    String tung = "";
    private void getData() {
        shiwuzhaolingItemList.clear();
        shiwuzhaolingItemAdapter.notifyDataSetChanged();
        try {
            HttpRequest.postJSONArray("queryLostarticle", new JSONObject("{}"), new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            shiwuzhaolingItem = new ShiwuzhaolingItem(jsonObject.getString("lostarticle_title"), jsonObject.getString("lostarticle_content"), jsonObject.getString("lostarticle_img"), jsonObject.getString("lostarticle_phonenumber"), jsonObject.getString("lostarticle_stat"), jsonObject.getString("lostarticle_time"));
                            shiwuzhaolingItemList.add(shiwuzhaolingItem);
                            shiwuzhaolingItemAdapter.notifyDataSetChanged();
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
