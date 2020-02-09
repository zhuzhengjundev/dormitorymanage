package cn.dormitorymanage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.dormitorymanage.ImageFdActivity;
import cn.dormitorymanage.R;

public class ShiwuzhaolingItemAdapter extends ArrayAdapter<ShiwuzhaolingItem> {

    private Context mContext;
    private int resourceId;
    private List<ShiwuzhaolingItem> shiwuzhaolingItemList;

    public ShiwuzhaolingItemAdapter(Context context, int resource, List<ShiwuzhaolingItem> shiwuzhaolingItemList) {
        super(context, resource, shiwuzhaolingItemList);
        mContext = context;
        resourceId = resource;
        this.shiwuzhaolingItemList = shiwuzhaolingItemList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ShiwuzhaolingItem shiwuzhaolingItem = getItem(position);
        View view;
        ShiwuzhaolingItemAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ShiwuzhaolingItemAdapter.ViewHolder();
            viewHolder.imageView_img = view.findViewById(R.id.shiwuzhaolingItem_img);
            viewHolder.textView_lostarticle_title = view.findViewById(R.id.shiwuzhaolingItem_title);
            viewHolder.textView_lostarticle_content = view.findViewById(R.id.shiwuzhaolingItem_content);
            viewHolder.textView_lostarticle_phonenumber = view.findViewById(R.id.shiwuzhaolingItem_phoneNum);
            viewHolder.textView_lostarticle_stat = view.findViewById(R.id.shiwuzhaolingItem_start);
            viewHolder.textView_lostarticle_time = view.findViewById(R.id.shiwuzhaolingItem_time);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ShiwuzhaolingItemAdapter.ViewHolder) view.getTag();
        }
        Glide.with(mContext)// 为当前Activity 加载图片
                .load(shiwuzhaolingItem.getLostarticle_img().trim())// 从URL 中加载
                .placeholder(R.drawable.titleimg)
                .error(R.drawable.titleimg)
                .into(viewHolder.imageView_img);
        viewHolder.imageView_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImageFdActivity.class);
                intent.putExtra("imgUrl", shiwuzhaolingItemList.get(position).getLostarticle_img());
                mContext.startActivity(intent);
            }
        });
        viewHolder.textView_lostarticle_title.setText(shiwuzhaolingItem.getLostarticle_title());
        viewHolder.textView_lostarticle_content.setText(shiwuzhaolingItem.getLostarticle_content());
        viewHolder.textView_lostarticle_phonenumber.setText(shiwuzhaolingItem.getLostarticle_phonenumber());
        viewHolder.textView_lostarticle_stat.setText(shiwuzhaolingItem.getLostarticle_stat());
        viewHolder.textView_lostarticle_time.setText(shiwuzhaolingItem.getLostarticle_time());
        return view;
    }

    private class ViewHolder {
        ImageView imageView_img;
        TextView textView_lostarticle_title;
        TextView textView_lostarticle_content;
        TextView textView_lostarticle_phonenumber;
        TextView textView_lostarticle_stat;
        TextView textView_lostarticle_time;
    }
}