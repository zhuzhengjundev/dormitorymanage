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

public class WeixiuItemAdapter extends ArrayAdapter<WeixiuItem> {

    private Context mContext;
    private int resourceId;
    private List<WeixiuItem> weixiuItemList;
    private User user;

    public WeixiuItemAdapter(Context context, int resource, List<WeixiuItem> weixiuItemList, User user) {
        super(context, resource, weixiuItemList);
        mContext = context;
        resourceId = resource;
        this.weixiuItemList = weixiuItemList;
        this.user = user;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        WeixiuItem weixiuItem = getItem(position);
        View view;
        WeixiuItemAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new WeixiuItemAdapter.ViewHolder();
            viewHolder.textView_repair_dormitoryid = view.findViewById(R.id.weixiuItem_dormitoryid);
            viewHolder.textView_repair_name = view.findViewById(R.id.weixiuItem_name);
            viewHolder.textView_repair_remark = view.findViewById(R.id.weixiuItem_remark);
            viewHolder.textView_repair_time = view.findViewById(R.id.weixiuItem_time);
            viewHolder.textView_repair_stat = view.findViewById(R.id.weixiuItem_stat);
            viewHolder.linearLayout = view.findViewById(R.id.weixiuItem_btn_LinearLayout);
            viewHolder.textView_btn_upStat = view.findViewById(R.id.weixiuItem_upStat);
            viewHolder.textView_btn_del = view.findViewById(R.id.weixiuItem_del);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (WeixiuItemAdapter.ViewHolder) view.getTag();
        }
        viewHolder.textView_repair_dormitoryid.setText(weixiuItem.getRepair_dormitoryid());
        viewHolder.textView_repair_name.setText(weixiuItem.getRepair_name());
        viewHolder.textView_repair_remark.setText(weixiuItem.getRepair_remark());
        viewHolder.textView_repair_time.setText(weixiuItem.getRepair_time());
        viewHolder.textView_repair_stat.setText(weixiuItem.getRepair_stat());
        if (user.getIsAdmin()) {
            viewHolder.textView_btn_del.setVisibility(View.GONE);
            if (weixiuItem.getRepair_stat().equals("已维修")) {
                viewHolder.textView_btn_upStat.setVisibility(View.GONE);
            }else{
                viewHolder.textView_btn_upStat.setVisibility(View.VISIBLE);
            }
        }else{
            viewHolder.textView_btn_upStat.setVisibility(View.GONE);
            if (user.getStu_id().equals(weixiuItem.getRepair_stuid())) {
                viewHolder.textView_btn_del.setVisibility(View.VISIBLE);
            }else{
                viewHolder.textView_btn_del.setVisibility(View.GONE);
            }
        }
        viewHolder.textView_btn_upStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("提示");
                dialog.setMessage("确认将该条维修信息状态改为已维修吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            HttpRequest.postJSONObject("updateRepair", new JSONObject("{\"visitor_stuid\":\"" + weixiuItemList.get(position).getRepair_id() + "\",\"visitor_stat\":\"已维修\"}"), new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        if (jsonObject.getBoolean("isOk")) {
                                            Toast.makeText(getContext(), "更改成功", Toast.LENGTH_SHORT).show();
                                            weixiuItemList.get(position).setRepair_stat("已维修");
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
                dialog.setMessage("确认删除该条维修信息吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            HttpRequest.postJSONObject("delRepair", new JSONObject("{\"visitor_stuid\":\"" + weixiuItemList.get(position).getRepair_id() + "\"}"), new Response.Listener<JSONObject>() {
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
        TextView textView_repair_dormitoryid;
        TextView textView_repair_name;
        TextView textView_repair_remark;
        TextView textView_repair_time;
        TextView textView_repair_stat;
        LinearLayout linearLayout;
        TextView textView_btn_upStat;
        TextView textView_btn_del;
    }

}