package cn.dormitorymanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cn.dormitorymanage.R;

public class FangkedengjiItemAdapter extends ArrayAdapter<FangkedengjiItem> {

    private Context mContext;
    private int resourceId;
    private List<FangkedengjiItem> fangkedengjiItemList;

    public FangkedengjiItemAdapter(Context context, int resource, List<FangkedengjiItem> fangkedengjiItemList) {
        super(context, resource, fangkedengjiItemList);
        mContext = context;
        resourceId = resource;
        this.fangkedengjiItemList = fangkedengjiItemList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        FangkedengjiItem fangkedengjiItem = getItem(position);
        View view;
        FangkedengjiItemAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new FangkedengjiItemAdapter.ViewHolder();
            viewHolder.textView_visitor_dormitoryid = view.findViewById(R.id.fangkedengjiItem_dormitoryid);
            viewHolder.textView_visitor_name = view.findViewById(R.id.fangkedengjiItem_name);
            viewHolder.textView_visitor_identity = view.findViewById(R.id.fangkedengjiItem_identity);
            viewHolder.textView_visitor_remark = view.findViewById(R.id.fangkedengjiItem_remark);
            viewHolder.textView_visitor_isagree = view.findViewById(R.id.fangkedengjiItem_isagree);
            viewHolder.visitor_time = view.findViewById(R.id.fangkedengjiItem_time);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (FangkedengjiItemAdapter.ViewHolder) view.getTag();
        }
        viewHolder.textView_visitor_dormitoryid.setText(fangkedengjiItem.getVisitor_dormitoryid());
        viewHolder.textView_visitor_name.setText(fangkedengjiItem.getVisitor_name());
        viewHolder.textView_visitor_identity.setText(fangkedengjiItem.getVisitor_identity());
        viewHolder.textView_visitor_remark.setText(fangkedengjiItem.getVisitor_remark());
        viewHolder.textView_visitor_isagree.setText(fangkedengjiItem.getVisitor_isagree());
        viewHolder.visitor_time.setText(fangkedengjiItem.getVisitor_time());
        return view;
    }

    private class ViewHolder {
        TextView textView_visitor_dormitoryid;
        TextView textView_visitor_name;
        TextView textView_visitor_identity;
        TextView textView_visitor_remark;
        TextView textView_visitor_isagree;
        TextView visitor_time;
    }
}
