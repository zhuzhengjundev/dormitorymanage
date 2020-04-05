package cn.dormitorymanage;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.dormitorymanage.function.HttpRequest;

public class ZhuceActivity extends AppCompatActivity {

    EditText editText_id,editText_pwd,editText_name;
    Spinner spinner;
    List<String> stringList = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    String xingbie = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        findViewById(R.id.zhuceActivity_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    private void initView() {
        editText_id = findViewById(R.id.zhuceActivity_id);
        editText_pwd = findViewById(R.id.zhuceActivity_pwd);
        editText_name = findViewById(R.id.zhuceActivity_name);
        spinner = findViewById(R.id.zhuceActivity_Spinner);
        stringList.add("男");
        stringList.add("女");
        arrayAdapter = new ArrayAdapter(ZhuceActivity.this, android.R.layout.simple_spinner_item, stringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                xingbie = stringList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        findViewById(R.id.zhuceActivity_zhuce).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String id = editText_id.getText().toString().trim();
                String pwd = editText_pwd.getText().toString().trim();
                String name = editText_name.getText().toString().trim();

                if (id.equals("") || pwd.equals("") || name.equals("")) {
                    Toast.makeText(ZhuceActivity.this, "请将信息填写完整", Toast.LENGTH_SHORT).show();
                }else{
                    if (id.length() >= 6) {
                        try {
                            HttpRequest.postJSONObject("register", new JSONObject("{\"stu_id\":\"" + id + "\",\"stu_pwd\":\"" + pwd + "\",\"stu_name\":\"" + name + "\",\"stu_gender\":\"" + xingbie + "\"}"), new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        if (jsonObject.getString("isOk").equals("true")) {
                                            Toast.makeText(ZhuceActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Log.d("查看", volleyError.toString());
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(ZhuceActivity.this, "账户名长度应大于等于6", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
