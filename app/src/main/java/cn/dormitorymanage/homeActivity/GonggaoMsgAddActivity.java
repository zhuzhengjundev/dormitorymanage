package cn.dormitorymanage.homeActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.dormitorymanage.R;
import cn.dormitorymanage.function.HttpRequest;

public class GonggaoMsgAddActivity extends AppCompatActivity {

    EditText editText_title,editText_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gonggao_msg_add);
        findViewById(R.id.gonggaoMsgAddActivity_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    private void initView() {
        editText_title = findViewById(R.id.gonggaoMsgAddActivity_title);
        editText_content = findViewById(R.id.gonggaoMsgAddActivity_content);
        findViewById(R.id.gonggaoMsgAddActivity_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editText_title.getText().toString().trim();
                String content = editText_content.getText().toString().trim();
                try {
                    HttpRequest.postJSONObject("addNotice", new JSONObject("{\"notice_title\":\"" + title + "\",\"notice_content\":\"" + content + "\",\"notice_time\":\"" + new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(System.currentTimeMillis())) + "\"}"), new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            try {
                                if (jsonObject.getString("isOk").equals("true")) {
                                    Toast.makeText(GonggaoMsgAddActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
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
    }
}
