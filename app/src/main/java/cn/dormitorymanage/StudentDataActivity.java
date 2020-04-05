package cn.dormitorymanage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class StudentDataActivity extends AppCompatActivity {

    private ImageView imageView_img;
    private TextView textView_id,textView_name,textView_gender,textView_age,textView_nativeplace,textView_major,textView_hobby, textView_dormitoryid, textView_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data);
        findViewById(R.id.studentDataActivity_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    private void initView() {
        final Intent intent = getIntent();
        imageView_img = findViewById(R.id.studentDataActivity_img);
        Glide.with(StudentDataActivity.this)// 为当前Activity 加载图片
                .load(intent.getStringExtra("stu_img").trim())// 从URL 中加载
                .placeholder(R.drawable.bai)
                .error(R.drawable.bai)
                .into(imageView_img);
        imageView_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(StudentDataActivity.this, ImageFdActivity.class);
                intent1.putExtra("imgUrl", intent.getStringExtra("stu_img").trim());
                startActivity(intent1);
            }
        });
        textView_id = findViewById(R.id.studentDataActivity_id);
        textView_name = findViewById(R.id.studentDataActivity_name);
        textView_gender = findViewById(R.id.studentDataActivity_gender);
        textView_age = findViewById(R.id.studentDataActivity_age);
        textView_nativeplace = findViewById(R.id.studentDataActivity_nativeplace);
        textView_major = findViewById(R.id.studentDataActivity_major);
        textView_hobby = findViewById(R.id.studentDataActivity_hobby);
        textView_dormitoryid = findViewById(R.id.studentDataActivity_dormitoryid);
        textView_phone = findViewById(R.id.studentDataActivity_phone);
        textView_id.setText(intent.getStringExtra("stu_id"));
        textView_name.setText(intent.getStringExtra("stu_name"));
        textView_gender.setText(intent.getStringExtra("stu_gender"));

        textView_age.setText(intent.getStringExtra("stu_age"));
        textView_nativeplace.setText(intent.getStringExtra("stu_nativeplace"));
        textView_major.setText(intent.getStringExtra("stu_major"));
        textView_hobby.setText(intent.getStringExtra("stu_hobby"));
        textView_phone.setText(intent.getStringExtra("stu_phone"));
        textView_dormitoryid.setText(intent.getStringExtra("stu_dormitoryid"));
        textView_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + textView_phone.getText().toString().trim());
                intent.setData(data);
                startActivity(intent);
            }
        });
    }
}
