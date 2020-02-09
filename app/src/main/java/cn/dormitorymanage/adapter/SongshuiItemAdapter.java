package cn.dormitorymanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cn.dormitorymanage.R;

public class SongshuiItemAdapter extends ArrayAdapter<SongshuiItem> {

    private Context mContext;
    private int resourceId;
    private List<SongshuiItem> songshuiItemList;

    public SongshuiItemAdapter(Context context, int resource, List<SongshuiItem> songshuiItemList) {
        super(context, resource, songshuiItemList);
        mContext = context;
        resourceId = resource;
        this.songshuiItemList = songshuiItemList;
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
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (SongshuiItemAdapter.ViewHolder) view.getTag();
        }
        viewHolder.textView_water_dormitoryid.setText(songshuiItem.getWater_dormitoryid());
        viewHolder.textView_water_num.setText(songshuiItem.getWater_num());
        viewHolder.textView_water_time.setText(songshuiItem.getWater_time());
        return view;
    }

    private class ViewHolder {
        TextView textView_water_dormitoryid;
        TextView textView_water_num;
        TextView textView_water_time;
    }
}