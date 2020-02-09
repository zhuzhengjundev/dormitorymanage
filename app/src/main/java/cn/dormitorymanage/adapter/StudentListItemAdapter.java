package cn.dormitorymanage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import cn.dormitorymanage.R;
import cn.dormitorymanage.StudentDataActivity;

public class StudentListItemAdapter extends ArrayAdapter<StudentListItem> {

    private Context mContext;
    private int resourceId;
    private List<StudentListItem> studentListItemList;

    public StudentListItemAdapter(Context context, int resource, List<StudentListItem> studentListItemList) {
        super(context, resource, studentListItemList);
        mContext = context;
        resourceId = resource;
        this.studentListItemList = studentListItemList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        StudentListItem studentListItem = getItem(position);
        View view;
        StudentListItemAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new StudentListItemAdapter.ViewHolder();
            viewHolder.textView_name = view.findViewById(R.id.studentListItem_text);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (StudentListItemAdapter.ViewHolder) view.getTag();
        }
        viewHolder.textView_name.setText(studentListItem.getStu_name() + "(" + studentListItem.getStu_dormitoryid() + ")");
        viewHolder.textView_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StudentDataActivity.class);
                intent.putExtra("stu_id", studentListItemList.get(position).getStu_id());
                intent.putExtra("stu_name", studentListItemList.get(position).getStu_name());
                intent.putExtra("stu_gender", studentListItemList.get(position).getStu_gender());
                intent.putExtra("stu_img", studentListItemList.get(position).getStu_img());
                intent.putExtra("stu_age", studentListItemList.get(position).getStu_age());
                intent.putExtra("stu_nativeplace", studentListItemList.get(position).getStu_nativeplace());
                intent.putExtra("stu_major", studentListItemList.get(position).getStu_major());
                intent.putExtra("stu_hobby", studentListItemList.get(position).getStu_hobby());
                intent.putExtra("stu_dormitoryid", studentListItemList.get(position).getStu_dormitoryid());
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    private class ViewHolder {
        TextView textView_name;
    }
}