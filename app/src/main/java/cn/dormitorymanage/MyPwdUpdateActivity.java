package cn.dormitorymanage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import cn.dormitorymanage.function.HttpRequest;
import cn.dormitorymanage.function.User;

public class MyPwdUpdateActivity extends AppCompatActivity {

    private User user;
    private EditText editText_pwd1, editText_pwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pwd_update);
        user = new User(MyPwdUpdateActivity.this);
        findViewById(R.id.mypwdupdateActivity_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    private void initView() {
        editText_pwd1 = findViewById(R.id.mypwdupdateActivity_pwd1);
        editText_pwd2 = findViewById(R.id.mypwdupdateActivity_pwd2);

        findViewById(R.id.mypwdupdateActivity_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd1 = editText_pwd1.getText().toString().trim();
                final String pwd2 = editText_pwd2.getText().toString().trim();
                if (pwd1.trim().equals(user.getStu_pwd().trim())) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("stu_id", user.getStu_id());
                        jsonObject.put("stu_pwd", pwd2);
                        jsonObject.put("stu_name", user.getStu_name());
                        jsonObject.put("stu_gender", user.getStu_gender());
                        jsonObject.put("stu_img", user.getStu_img());
                        jsonObject.put("stu_age", user.getStu_age());
                        jsonObject.put("stu_nativeplace", user.getStu_nativeplace());
                        jsonObject.put("stu_major", user.getStu_major());
                        jsonObject.put("stu_hobby", user.getStu_hobby());
                        jsonObject.put("stu_phone", user.getStu_phone());
                        jsonObject.put("stu_dormitoryid", user.getStu_dormitoryid());
                        HttpRequest.postJSONObject("updateStudent", jsonObject, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                try {
                                    if (jsonObject.getString("isOk").equals("true")) {
                                        user.setStu_pwd(pwd2);
                                        Toast.makeText(MyPwdUpdateActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
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
                }else{
                    Toast.makeText(MyPwdUpdateActivity.this, "原密码输入不正确", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
