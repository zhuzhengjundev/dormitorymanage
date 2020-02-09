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
import cn.dormitorymanage.adapter.GonggaoMsgItem;
import cn.dormitorymanage.adapter.GonggaoMsgItemAdapter;
import cn.dormitorymanage.function.HttpRequest;
import cn.dormitorymanage.function.User;

public class GonggaoMsgActivity extends AppCompatActivity {

    User user = new User(GonggaoMsgActivity.this);

    private ListView listView;
    private GonggaoMsgItem gonggaoMsgItem;
    private List<GonggaoMsgItem> gonggaoMsgItemList = new ArrayList<>();
    private GonggaoMsgItemAdapter gonggaoMsgItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gonggao_msg);
        findViewById(R.id.gonggaoMsgActivity_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.gonggaoMsgActivity_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GonggaoMsgActivity.this,GonggaoMsgAddActivity.class));
            }
        });
        if (!user.getIsAdmin()) {
            findViewById(R.id.gonggaoMsgActivity_add).setVisibility(View.GONE);
        }

        initView();
    }

    private void initView() {
        listView = findViewById(R.id.gonggaoMsgActivity_listView);
        gonggaoMsgItemAdapter = new GonggaoMsgItemAdapter(GonggaoMsgActivity.this, R.layout.adapter_layout_gonggaomsgitem, gonggaoMsgItemList);
        listView.setAdapter(gonggaoMsgItemAdapter);
    }

    private void getData() {
        gonggaoMsgItemList.clear();
        gonggaoMsgItemAdapter.notifyDataSetChanged();
        try {
            HttpRequest.postJSONArray("queryNotice", new JSONObject("{}"), new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            gonggaoMsgItem = new GonggaoMsgItem(jsonObject.getString("notice_id"), jsonObject.getString("notice_title"), jsonObject.getString("notice_content"), jsonObject.getString("notice_time"));
                            gonggaoMsgItemList.add(gonggaoMsgItem);
                            gonggaoMsgItemAdapter.notifyDataSetChanged();
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
