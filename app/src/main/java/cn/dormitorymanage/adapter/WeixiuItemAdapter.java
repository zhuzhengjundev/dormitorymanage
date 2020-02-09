package cn.dormitorymanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cn.dormitorymanage.R;

public class WeixiuItemAdapter extends ArrayAdapter<WeixiuItem> {

    private Context mContext;
    private int resourceId;
    private List<WeixiuItem> weixiuItemList;

    public WeixiuItemAdapter(Context context, int resource, List<WeixiuItem> weixiuItemList) {
        super(context, resource, weixiuItemList);
        mContext = context;
        resourceId = resource;
        this.weixiuItemList = weixiuItemList;
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
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (WeixiuItemAdapter.ViewHolder) view.getTag();
        }
        viewHolder.textView_repair_dormitoryid.setText(weixiuItem.getRepair_dormitoryid());
        viewHolder.textView_repair_name.setText(weixiuItem.getRepair_name());
        viewHolder.textView_repair_remark.setText(weixiuItem.getRepair_remark());
        viewHolder.textView_repair_time.setText(weixiuItem.getRepair_time());
        return view;
    }

    private class ViewHolder {
        TextView textView_repair_dormitoryid;
        TextView textView_repair_name;
        TextView textView_repair_remark;
        TextView textView_repair_time;
    }

}