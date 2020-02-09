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

public class WeishengjianchaAddActivity extends AppCompatActivity {

    Spinner spinner;
    List<String> stringList = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    String dormitory_id = "";

    Spinner spinner2;
    List<String> stringList2 = new ArrayList<>();
    ArrayAdapter arrayAdapter2;
    String hygiene_grade = "";

    EditText textView_remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weishengjiancha_add);
        findViewById(R.id.weishengjianchaAddActivity_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initView();
    }

    private void initView() {
        spinner = findViewById(R.id.weishengjianchaAddActivity_Spinner);
        arrayAdapter = new ArrayAdapter(WeishengjianchaAddActivity.this, android.R.layout.simple_spinner_item, stringList);
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
        spinner2 = findViewById(R.id.weishengjianchaAddActivity_Spinner2);
        stringList2.add("优");
        stringList2.add("良");
        stringList2.add("差");
        arrayAdapter2 = new ArrayAdapter(WeishengjianchaAddActivity.this, android.R.layout.simple_spinner_item, stringList2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinner2.setAdapter(arrayAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hygiene_grade = stringList2.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        textView_remark = findViewById(R.id.weishengjianchaAddActivity_remark);
        findViewById(R.id.weishengjianchaAddActivity_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String remark = textView_remark.getText().toString().trim();
                try {
                    HttpRequest.postJSONObject("addHygiene", new JSONObject("{\"hygiene_dormitoryid\":\"" + dormitory_id + "\",\"hygiene_grade\":\"" + hygiene_grade + "\",\"hygiene_remark\":\"" + remark + "\",\"hygiene_time\":\"" + new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(System.currentTimeMillis())) + "\"}"), new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            try {
                                if (jsonObject.getString("isOk").equals("true")) {
                                    Toast.makeText(WeishengjianchaAddActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
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
            User user = new User(WeishengjianchaAddActivity.this);
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
