package cn.dormitorymanage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import cn.dormitorymanage.function.HttpRequest;
import cn.dormitorymanage.function.User;

public class LoginActivity extends AppCompatActivity {

    EditText editText_name, editText_pwd;
    User user = new User(LoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.loginActivity_zhuce).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ZhuceActivity.class));
            }
        });
        if(!(user.getStu_name().equals("") && user.getManag_name().equals(""))){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
        initView();
    }

    private void initView() {
        editText_name = findViewById(R.id.loginActivity_name);
        editText_pwd = findViewById(R.id.loginActivity_pwd);
        findViewById(R.id.loginActivity_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText_name.getText().toString().trim();
                String pwd = editText_pwd.getText().toString().trim();
                if (name.length() >= 6) {
                    if(name.substring(0,5).equals("admin")){
                        try {
                            HttpRequest.postJSONObject("adminLogin", new JSONObject("{\"admin_id\":\"" + name + "\",\"admin_pwd\":\"" + pwd + "\"}"), new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        user.setIsAdmin(true);
                                        user.setManag_id(jsonObject.getString("manag_id"));
                                        user.setManag_name(jsonObject.getString("manag_name"));
                                        user.setManag_dormitory_num(jsonObject.getString("manag_dormitory_num"));
                                        user.setManag_manag_xb(jsonObject.getString("manag_xb"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(LoginActivity.this, "账号或密码不存在", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        try {
                            HttpRequest.postJSONObject("login", new JSONObject("{\"stu_id\":\"" + name + "\",\"stu_pwd\":\"" + pwd + "\"}"), new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        user.setIsAdmin(false);
                                        user.setStu_id(jsonObject.getString("stu_id"));
                                        user.setStu_pwd(jsonObject.getString("stu_pwd"));
                                        user.setStu_name(jsonObject.getString("stu_name"));
                                        user.setStu_gender(jsonObject.getString("stu_gender"));
                                        user.setStu_img(jsonObject.getString("stu_img"));
                                        user.setStu_age(jsonObject.getString("stu_age"));
                                        user.setStu_nativeplace(jsonObject.getString("stu_nativeplace"));
                                        user.setStu_major(jsonObject.getString("stu_major"));
                                        user.setStu_hobby(jsonObject.getString("stu_hobby"));
                                        user.setStu_dormitoryid(jsonObject.getString("stu_dormitoryid"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(LoginActivity.this, "账号或密码不存在", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
