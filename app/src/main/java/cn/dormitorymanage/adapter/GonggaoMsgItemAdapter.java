package cn.dormitorymanage.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

public class GonggaoMsgItemAdapter extends ArrayAdapter<GonggaoMsgItem> {

    private Context mContext;
    private int resourceId;
    private List<GonggaoMsgItem> gonggaoMsgItemList;
    User user = new User(getContext());

    public GonggaoMsgItemAdapter(Context context, int resource, List<GonggaoMsgItem> gonggaoMsgItemList) {
        super(context, resource, gonggaoMsgItemList);
        mContext = context;
        resourceId = resource;
        this.gonggaoMsgItemList = gonggaoMsgItemList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        GonggaoMsgItem gonggaoMsgItem = getItem(position);
        View view;
        GonggaoMsgItemAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new GonggaoMsgItemAdapter.ViewHolder();
            viewHolder.imageView_dalete = view.findViewById(R.id.gonggaoMsgItem_dalete);
            viewHolder.textView_notice_title = view.findViewById(R.id.gonggaoMsgItem_title);
            viewHolder.textView_notice_content = view.findViewById(R.id.gonggaoMsgItem_content);
            viewHolder.textView_notice_time = view.findViewById(R.id.gonggaoMsgItem_time);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (GonggaoMsgItemAdapter.ViewHolder) view.getTag();
        }
        viewHolder.textView_notice_title.setText(gonggaoMsgItem.getNotice_title());
        viewHolder.textView_notice_content.setText(gonggaoMsgItem.getNotice_content());
        viewHolder.textView_notice_time.setText(gonggaoMsgItem.getNotice_time());
        if(!user.getIsAdmin()){
            viewHolder.imageView_dalete.setVisibility(View.GONE);
        }
        viewHolder.imageView_dalete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                dialog.setTitle("提示");
                dialog.setMessage("确认删除该条公告信息吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            HttpRequest.postJSONObject("delNotice", new JSONObject("{\"notice_id\":\"" + gonggaoMsgItemList.get(position).getNotice_id() + "\"}"), new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        if(jsonObject.getString("isOk").equals("true")){
                                            Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                                            remove(getItem(position));
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
        ImageView imageView_dalete;
        TextView textView_notice_title;
        TextView textView_notice_content;
        TextView textView_notice_time;
    }

}