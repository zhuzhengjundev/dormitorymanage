package cn.dormitorymanage.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.dormitorymanage.R;
import cn.dormitorymanage.function.HttpRequest;
import cn.dormitorymanage.function.User;

public class SongshuiItemAdapter extends ArrayAdapter<SongshuiItem> {

    private Context mContext;
    private int resourceId;
    private List<SongshuiItem> songshuiItemList;
    private User user;

    public SongshuiItemAdapter(Context context, int resource, List<SongshuiItem> songshuiItemList, User user) {
        super(context, resource, songshuiItemList);
        mContext = context;
        resourceId = resource;
        this.songshuiItemList = songshuiItemList;
        this.user = user;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        SongshuiItem songshuiItem = getItem(position);
        View view;
        SongshuiItemAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new SongshuiItemAdapter.ViewHolder();
            viewHolder.textView_water_dormitoryid = view.findViewById(R.id.songshuiItem_dormitoryid);
            viewHolder.textView_water_num = view.findViewById(R.id.songshuiItem_name);
            viewHolder.textView_water_time = view.findViewById(R.id.songshuiItem_time);
            viewHolder.textView_water_stat = view.findViewById(R.id.songshuiItem_stat);
            viewHolder.linearLayout = view.findViewById(R.id.songshuiItem_btn_LinearLayout);
            viewHolder.textView_btn_upStat = view.findViewById(R.id.songshuiItem_upStat);
            viewHolder.textView_btn_del = view.findViewById(R.id.songshuiItem_del);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (SongshuiItemAdapter.ViewHolder) view.getTag();
        }
        if (user.getIsAdmin()) {
            viewHolder.linearLayout.setVisibility(View.GONE);
        }else{
            if (user.getStu_id().equals(songshuiItem.getWater_stuid())) {
                viewHolder.linearLayout.setVisibility(View.VISIBLE);
                if (songshuiItem.getWater_stat().equals("已送达")) {
                    viewHolder.textView_btn_upStat.setVisibility(View.GONE);
                }
            }else{
                viewHolder.linearLayout.setVisibility(View.GONE);
            }
        }
        viewHolder.textView_water_dormitoryid.setText(songshuiItem.getWater_dormitoryid());
        viewHolder.textView_water_num.setText(songshuiItem.getWater_num());
        viewHolder.textView_water_time.setText(songshuiItem.getWater_time());
        viewHolder.textView_water_stat.setText(songshuiItem.getWater_stat());
        viewHolder.textView_btn_upStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("提示");
                dialog.setMessage("确认将该条送水信息改为已送达吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            HttpRequest.postJSONObject("updateWater", new JSONObject("{\"water_id\":\"" + songshuiItemList.get(position).getWater_id() + "\",\"water_stat\":\"已送达\"}"), new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        if (jsonObject.getBoolean("isOk")) {
                                            Toast.makeText(getContext(), "更改成功", Toast.LENGTH_SHORT).show();
                                            songshuiItemList.get(position).setWater_stat("已送达");
                                            notifyDataSetChanged();
                                        }else{
                                            Toast.makeText(getContext(), "更改失败请稍后重试", Toast.LENGTH_SHORT).show();
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
        viewHolder.textView_btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("提示");
                dialog.setMessage("确认删除该条送水信息吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            HttpRequest.postJSONObject("delWater", new JSONObject("{\"water_id\":\"" + songshuiItemList.get(position).getWater_id() + "\"}"), new Response.Listener<JSONObject>() {
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
        return view;
    }

    private class ViewHolder {
        TextView textView_water_dormitoryid;
        TextView textView_water_num;
        TextView textView_water_time;
        TextView textView_water_stat;
        LinearLayout linearLayout;
        TextView textView_btn_upStat;
        TextView textView_btn_del;
    }
}