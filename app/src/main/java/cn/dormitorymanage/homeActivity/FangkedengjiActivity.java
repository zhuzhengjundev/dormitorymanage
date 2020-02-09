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
import cn.dormitorymanage.adapter.FangkedengjiItem;
import cn.dormitorymanage.adapter.FangkedengjiItemAdapter;
import cn.dormitorymanage.function.HttpRequest;
import cn.dormitorymanage.function.User;

public class FangkedengjiActivity extends AppCompatActivity {

    User user = new User(FangkedengjiActivity.this);

    private ListView listView;
    private FangkedengjiItem fangkedengjiItem;
    private List<FangkedengjiItem> fangkedengjiItemList = new ArrayList<>();
    private FangkedengjiItemAdapter fangkedengjiItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangkedengji);
        findViewById(R.id.fangkejiluActivity_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.fangkejiluActivity_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FangkedengjiActivity.this,FangkedengjiAddActivity.class));
            }
        });
        if (!user.getIsAdmin()) {
            findViewById(R.id.fangkejiluActivity_add).setVisibility(View.GONE);
        }

        initView();
    }

    private void initView() {
        listView = findViewById(R.id.fangkejiluActivity_listView);
        fangkedengjiItemAdapter = new FangkedengjiItemAdapter(FangkedengjiActivity.this, R.layout.adapter_layout_fangkedengjiitem, fangkedengjiItemList);
        listView.setAdapter(fangkedengjiItemAdapter);
    }

    String tung = "";
    private void getData() {
        fangkedengjiItemList.clear();
        fangkedengjiItemAdapter.notifyDataSetChanged();
        try {
            JSONObject jsonObject = new JSONObject();
            String url = "";
            if (user.getIsAdmin()){
                url = "queryAllVisitor";
                tung = user.getManag_dormitory_num();
            }else {
                url = "queryOneVisitor";
                jsonObject.put("visitor_dormitoryid", user.getStu_dormitoryid());
                tung = user.getStu_dormitoryid().substring(0, 1);
            }
            HttpRequest.postJSONArray(url, jsonObject, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if(jsonObject.getString("visitor_dormitoryid").substring(0, 1).equals(tung)){
                                fangkedengjiItem = new FangkedengjiItem(jsonObject.getString("visitor_dormitoryid"), jsonObject.getString("visitor_name"), jsonObject.getString("visitor_identity"), jsonObject.getString("visitor_remark"),jsonObject.getString("visitor_isagree"), jsonObject.getString("visitor_time"));
                                fangkedengjiItemList.add(fangkedengjiItem);
                                fangkedengjiItemAdapter.notifyDataSetChanged();
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
