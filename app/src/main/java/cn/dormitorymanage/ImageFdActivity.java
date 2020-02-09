package cn.dormitorymanage;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ImageFdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_fd);
        ImageView imageView = findViewById(R.id.imageFd_img);
        Glide.with(ImageFdActivity.this)// 为当前Activity 加载图片
                .load(getIntent().getStringExtra("imgUrl").trim())// 从URL 中加载
                .placeholder(R.drawable.hei)
                .error(R.drawable.hei)
                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
