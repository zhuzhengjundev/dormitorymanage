package cn.dormitorymanage.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.Response;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.dormitorymanage.ImageFdActivity;
import cn.dormitorymanage.R;
import cn.dormitorymanage.function.HttpRequest;
import cn.dormitorymanage.function.User;

public class ShiwuzhaolingItemAdapter extends ArrayAdapter<ShiwuzhaolingItem> {

    private Context mContext;
    private int resourceId;
    private List<ShiwuzhaolingItem> shiwuzhaolingItemList;
    private User user;

    public ShiwuzhaolingItemAdapter(Context context, int resource, List<ShiwuzhaolingItem> shiwuzhaolingItemList, User user) {
        super(context, resource, shiwuzhaolingItemList);
        mContext = context;
        resourceId = resource;
        this.shiwuzhaolingItemList = shiwuzhaolingItemList;
        this.user = user;
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
            viewHolder.textView_lostarticle_del = view.findViewById(R.id.shiwuzhaolingItem_del);
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
        if (user.getIsAdmin()) {
            viewHolder.textView_lostarticle_del.setVisibility(View.GONE);
        }else{
            if (user.getStu_id().equals(shiwuzhaolingItem.getLostarticle_stuid())) {
                viewHolder.textView_lostarticle_del.setVisibility(View.VISIBLE);
            }else{
                viewHolder.textView_lostarticle_del.setVisibility(View.GONE);
            }
        }
        viewHolder.textView_lostarticle_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("提示");
                dialog.setMessage("确认删除该条失物招领信息吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            HttpRequest.postJSONObject("delLostarticle", new JSONObject("{\"lostarticle_id\":\"" + shiwuzhaolingItemList.get(position).getLostarticle_id() + "\"}"), new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        if (jsonObject.getBoolean("isOk")) {
                                            Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                                            remove(getItem(position));
                                        }else{
                                            Toast.makeText(getContext(), "删除失败请稍后重试", Toast.LENGTH_SHORT).show();
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
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show();
            }
        });
        viewHolder.textView_lostarticle_title.setText(shiwuzhaolingItem.getLostarticle_title());
        viewHolder.textView_lostarticle_content.setText(shiwuzhaolingItem.getLostarticle_content());
        viewHolder.textView_lostarticle_phonenumber.setText(shiwuzhaolingItem.getLostarticle_phonenumber());
        viewHolder.textView_lostarticle_stat.setText(shiwuzhaolingItem.getLostarticle_stat());
        viewHolder.textView_lostarticle_time.setText(shiwuzhaolingItem.getLostarticle_time());
        viewHolder.textView_lostarticle_phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + shiwuzhaolingItemList.get(position).getLostarticle_phonenumber().trim());
                intent.setData(data);
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    private class ViewHolder {
        ImageView imageView_img;
        TextView textView_lostarticle_del;
        TextView textView_lostarticle_title;
        TextView textView_lostarticle_content;
        TextView textView_lostarticle_phonenumber;
        TextView textView_lostarticle_stat;
        TextView textView_lostarticle_time;
    }
}