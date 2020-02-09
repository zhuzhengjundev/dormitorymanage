package cn.dormitorymanage.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.dormitorymanage.R;
import cn.dormitorymanage.function.HttpRequest;
import cn.dormitorymanage.function.User;
import cn.dormitorymanage.homeActivity.FangkedengjiActivity;
import cn.dormitorymanage.homeActivity.GonggaoMsgActivity;
import cn.dormitorymanage.homeActivity.ShiwuzhaolingActivity;
import cn.dormitorymanage.homeActivity.SongshuiActivity;
import cn.dormitorymanage.homeActivity.WeishengjianchaActivity;
import cn.dormitorymanage.homeActivity.WeixiuActivity;

public class HomeFragment extends Fragment implements OnBannerListener, View.OnClickListener {

    private Banner banner;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;

    private TextView textView_time,textView_title,textView_content;

    User user ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        user = new User(getContext());
        initBanner();
        initView();
    }

    private void initView() {
        getActivity().findViewById(R.id.homeFragment_btn_weixiu).setOnClickListener(this);
        getActivity().findViewById(R.id.homeFragment_btn_songshui).setOnClickListener(this);
        getActivity().findViewById(R.id.homeFragment_btn_fangkedengji).setOnClickListener(this);
        getActivity().findViewById(R.id.homeFragment_btn_weishengjiancha).setOnClickListener(this);
        getActivity().findViewById(R.id.homeFragment_btn_shiwuzhaoling).setOnClickListener(this);
        getActivity().findViewById(R.id.homeFragment_btn_gonggaoMsg).setOnClickListener(this);
        textView_time = getActivity().findViewById(R.id.homeFragment_txt_gonggaoMsg_time);
        textView_title = getActivity().findViewById(R.id.homeFragment_txt_gonggaoMsg_title);
        textView_content = getActivity().findViewById(R.id.homeFragment_txt_gonggaoMsg_content);
        getData();
    }

    private void getData() {
        try {
            HttpRequest.postJSONArray("queryNotice", new JSONObject("{}"), new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String time = jsonObject.getString("notice_time");
                        textView_time.setText(time.substring(time.length()-8,time.length()-3));
                        textView_title.setText(jsonObject.getString("notice_title"));
                        textView_content.setText(jsonObject.getString("notice_content"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeFragment_btn_weixiu:
                if(!user.getIsAdmin()){
                    if(user.getStu_dormitoryid().equals("")){
                        Toast.makeText(getContext(),"你的宿舍暂未分配，请联系宿管分配宿舍后查看",Toast.LENGTH_SHORT).show();
                    }else{
                        startActivity(new Intent(getContext(), WeixiuActivity.class));
                    }
                }else{
                    startActivity(new Intent(getContext(), WeixiuActivity.class));
                }
                break;
            case R.id.homeFragment_btn_songshui:
                if(!user.getIsAdmin()){
                    if(user.getStu_dormitoryid().equals("")){
                        Toast.makeText(getContext(),"你的宿舍暂未分配，请联系宿管分配宿舍后查看",Toast.LENGTH_SHORT).show();
                    }else{
                        startActivity(new Intent(getContext(), SongshuiActivity.class));
                    }
                }else{
                    startActivity(new Intent(getContext(), SongshuiActivity.class));
                }
                break;
            case R.id.homeFragment_btn_fangkedengji:
                if(!user.getIsAdmin()){
                    if(user.getStu_dormitoryid().equals("")){
                        Toast.makeText(getContext(),"你的宿舍暂未分配，请联系宿管分配宿舍后查看",Toast.LENGTH_SHORT).show();
                    }else{
                        startActivity(new Intent(getContext(), FangkedengjiActivity.class));
                    }
                }else{
                    startActivity(new Intent(getContext(), FangkedengjiActivity.class));
                }
                break;
            case R.id.homeFragment_btn_weishengjiancha:
                if(!user.getIsAdmin()){
                    if(user.getStu_dormitoryid().equals("")){
                        Toast.makeText(getContext(),"你的宿舍暂未分配，请联系宿管分配宿舍后查看",Toast.LENGTH_SHORT).show();
                    }else{
                        startActivity(new Intent(getContext(), WeishengjianchaActivity.class));
                    }
                }else{
                    startActivity(new Intent(getContext(), WeishengjianchaActivity.class));
                }
                break;
            case R.id.homeFragment_btn_shiwuzhaoling:
                startActivity(new Intent(getContext(), ShiwuzhaolingActivity.class));
                break;
            case R.id.homeFragment_btn_gonggaoMsg:
                startActivity(new Intent(getContext(), GonggaoMsgActivity.class));
                break;
            default:
                break;
        }
    }

    private void initBanner() {
        banner = (Banner) getActivity().findViewById(R.id.homeFragment_banner);
        //放图片地址的集合
        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        list_title.add("好好学习");
        list_title.add("天天向上");
        list_title.add("热爱劳动");
        list_title.add("不搞对象");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();
    }
    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第"+position+"张轮播图");
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
}
