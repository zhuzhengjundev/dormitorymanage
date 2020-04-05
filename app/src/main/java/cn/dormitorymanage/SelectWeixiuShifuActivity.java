package cn.dormitorymanage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectWeixiuShifuActivity extends AppCompatActivity {

    TextView textView_1, textView_2, textView_3, textView_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_weixiu_shifu);
        findViewById(R.id.selectWeixiushifuActivity_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView_1 = findViewById(R.id.selectWeixiushifuActivity_1);
        textView_2 = findViewById(R.id.selectWeixiushifuActivity_2);
        textView_3 = findViewById(R.id.selectWeixiushifuActivity_3);
        textView_4 = findViewById(R.id.selectWeixiushifuActivity_4);
        textView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(textView_1.getText().toString().trim());
            }
        });
        textView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(textView_2.getText().toString().trim());
            }
        });
        textView_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(textView_3.getText().toString().trim());
            }
        });
        textView_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(textView_4.getText().toString().trim());
            }
        });
    }

    private void call(String phone){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        startActivity(intent);
    }
}
