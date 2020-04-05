package cn.dormitorymanage.homeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
import cn.dormitorymanage.function.User;

public class ShiwuzhaolingActivity extends AppCompatActivity {

    private ListView listView;
    private ShiwuzhaolingItem shiwuzhaolingItem;
    private List<ShiwuzhaolingItem> shiwuzhaolingItemList = new ArrayList<>();
    private ShiwuzhaolingItemAdapter shiwuzhaolingItemAdapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiwuzhaoling);
        user = new User(ShiwuzhaolingActivity.this);
        findViewById(R.id.shiwuzhaolingActivity_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageView imageView = findViewById(R.id.shiwuzhaolingActivity_add);
        if (user.getIsAdmin()) {
            imageView.setVisibility(View.GONE);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShiwuzhaolingActivity.this,ShiwuzhaolingAddActivity.class));
            }
        });
        initView();
    }

    private void initView() {
        listView = findViewById(R.id.shiwuzhaolingActivity_listView);
        shiwuzhaolingItemAdapter = new ShiwuzhaolingItemAdapter(ShiwuzhaolingActivity.this, R.layout.adapter_layout_shiwuzhaolingitem, shiwuzhaolingItemList,user);
        listView.setAdapter(shiwuzhaolingItemAdapter);
        findViewById(R.id.shiwuzhaolingActivity_tab1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(false);
            }
        });
        TextView textView = findViewById(R.id.shiwuzhaolingActivity_tab2);
        if (user.getIsAdmin()) {
            textView.setVisibility(View.GONE);
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(true);
            }
        });
    }

    String tung = "";
    private void getData(boolean isThis) {
        shiwuzhaolingItemList.clear();
        shiwuzhaolingItemAdapter.notifyDataSetChanged();
        JSONObject jsonObject = new JSONObject();
        String url = "queryLostarticle";
        if (isThis) {
            try {
                jsonObject.put("lostarticle_stuid", user.getStu_id());
                url = "queryThisLostarticle";
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        HttpRequest.postJSONArray(url,jsonObject, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        shiwuzhaolingItem = new ShiwuzhaolingItem(jsonObject.getString("lostarticle_id"),jsonObject.getString("lostarticle_stuid"),jsonObject.getString("lostarticle_title"), jsonObject.getString("lostarticle_content"), jsonObject.getString("lostarticle_img"), jsonObject.getString("lostarticle_phonenumber"), jsonObject.getString("lostarticle_stat"), jsonObject.getString("lostarticle_time"));
                        shiwuzhaolingItemList.add(shiwuzhaolingItem);
                        shiwuzhaolingItemAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData(false);
    }
}
