package cn.dormitorymanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cn.dormitorymanage.R;

public class WeishengjianchaItemAdapter extends ArrayAdapter<WeishengjianchaItem> {

    private Context mContext;
    private int resourceId;
    private List<WeishengjianchaItem> weishengjianchaItemList;

    public WeishengjianchaItemAdapter(Context context, int resource, List<WeishengjianchaItem> weishengjianchaItemList) {
        super(context, resource, weishengjianchaItemList);
        mContext = context;
        resourceId = resource;
        this.weishengjianchaItemList = weishengjianchaItemList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        WeishengjianchaItem weishengjianchaItem = getItem(position);
        View view;
        WeishengjianchaItemAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new WeishengjianchaItemAdapter.ViewHolder();
            viewHolder.textView_hygiene_dormitoryid = view.findViewById(R.id.weishengjianchaItem_dormitoryid);
            viewHolder.textView_hygiene_grade = view.findViewById(R.id.weishengjianchaItem_grade);
            viewHolder.textView_hygiene_remark = view.findViewById(R.id.weishengjianchaItem_remark);
            viewHolder.textView_hygiene_time = view.findViewById(R.id.weishengjianchaItem_time);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (WeishengjianchaItemAdapter.ViewHolder) view.getTag();
        }
        viewHolder.textView_hygiene_dormitoryid.setText(weishengjianchaItem.getHygiene_dormitoryid());
        viewHolder.textView_hygiene_grade.setText(weishengjianchaItem.getHygiene_grade());
        viewHolder.textView_hygiene_remark.setText(weishengjianchaItem.getHygiene_remark());
        viewHolder.textView_hygiene_time.setText(weishengjianchaItem.getHygiene_time());
        return view;
    }

    private class ViewHolder {
        TextView textView_hygiene_dormitoryid;
        TextView textView_hygiene_grade;
        TextView textView_hygiene_remark;
        TextView textView_hygiene_time;
    }
}