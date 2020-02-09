package cn.dormitorymanage.homeActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.dormitorymanage.R;
import cn.dormitorymanage.function.HttpRequest;
import cn.dormitorymanage.function.User;

public class FangkedengjiAddActivity extends AppCompatActivity {

    Spinner spinner;
    List<String> stringList = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    String dormitory_id = "";

    Spinner spinner2;
    List<String> stringList2 = new ArrayList<>();
    ArrayAdapter arrayAdapter2;
    String visitor_isagree = "";

    EditText textView_name, textView_identity, textView_remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangkedengji_add);
        findViewById(R.id.fangkejiluaddActivity_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initView();
    }

    private void initView() {
        spinner = findViewById(R.id.fangkejiluaddActivity_Spinner);
        arrayAdapter = new ArrayAdapter(FangkedengjiAddActivity.this, android.R.layout.simple_spinner_item, stringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dormitory_id = stringList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2 = findViewById(R.id.fangkejiluaddActivity_Spinner2);
        stringList2.add("是");
        stringList2.add("否");
        arrayAdapter2 = new ArrayAdapter(FangkedengjiAddActivity.this, android.R.layout.simple_spinner_item, stringList2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinner2.setAdapter(arrayAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                visitor_isagree = stringList2.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        textView_name = findViewById(R.id.fangkejiluaddActivity_name);
        textView_identity = findViewById(R.id.fangkejiluaddActivity_identity);
        textView_remark = findViewById(R.id.fangkejiluaddActivity_remark);
        findViewById(R.id.fangkejiluaddActivity_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = textView_name.getText().toString().trim();
                String identity = textView_identity.getText().toString().trim();
                String remark = textView_remark.getText().toString().trim();
                try {
                    HttpRequest.postJSONObject("addVisitor", new JSONObject("{\"visitor_dormitoryid\":\"" + dormitory_id + "\",\"visitor_name\":\"" + name + "\",\"visitor_identity\":\"" + identity + "\",\"visitor_remark\":\"" + remark + "\",\"visitor_isagree\":\"" + visitor_isagree + "\",\"visitor_time\":\"" + new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(System.currentTimeMillis())) + "\"}"), new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            try {
                                if (jsonObject.getString("isOk").equals("true")) {
                                    Toast.makeText(FangkedengjiAddActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        getData();
    }

    private void getData(){
        try {
            User user = new User(FangkedengjiAddActivity.this);
            String tung = "";
            if (user.getIsAdmin()) {
                tung = user.getManag_dormitory_num();
            }else {
                tung = user.getStu_dormitoryid().substring(0, 1);
            }
            HttpRequest.postJSONArray("queryDormitory", new JSONObject("{\"tung\":\"" + tung + "\"}"), new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            stringList.add(jsonObject.getString("dormitory_room"));
                            arrayAdapter.notifyDataSetChanged();
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
}
