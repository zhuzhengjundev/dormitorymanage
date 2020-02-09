package cn.dormitorymanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.dormitorymanage.R;
import cn.dormitorymanage.function.HttpRequest;

public class StudentListItem2Adapter extends ArrayAdapter<StudentListItem> {

    private Context mContext;
    private int resourceId;
    private List<StudentListItem> studentListItemList;
    private List<String> stringList;
    private ArrayAdapter arrayAdapter;

    public StudentListItem2Adapter(Context context, int resource, List<StudentListItem> studentListItemList,List<String> stringList) {
        super(context, resource, studentListItemList);
        mContext = context;
        resourceId = resource;
        this.studentListItemList = studentListItemList;
        this.stringList = stringList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        StudentListItem studentListItem = getItem(position);
        View view;
        StudentListItem2Adapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new StudentListItem2Adapter.ViewHolder();
            viewHolder.textView_name = view.findViewById(R.id.selectStudentItem_name);
            viewHolder.textView_age = view.findViewById(R.id.selectStudentItem_age);
            viewHolder.spinner = view.findViewById(R.id.selectStudentItem_dormitoryid);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (StudentListItem2Adapter.ViewHolder) view.getTag();
        }
        viewHolder.textView_name.setText(studentListItem.getStu_name() + "(" + studentListItem.getStu_id() + ")");
        viewHolder.textView_age.setText(studentListItem.getStu_age() + "岁");
        arrayAdapter = new ArrayAdapter(mContext, android.R.layout.simple_spinner_item, stringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        viewHolder.spinner.setAdapter(arrayAdapter);
        for (int i = 0; i < stringList.size(); i++) {
            if (stringList.get(i).trim().equals(studentListItemList.get(position).getStu_dormitoryid().trim())) {
                viewHolder.spinner.setSelection(i);
            }
        }
        viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int p, long id) {
                try {
                    HttpRequest.postJSONObject("updateStudentDormitory", new JSONObject("{\"stu_id\":\"" + studentListItemList.get(position).getStu_id() + "\",\"stu_dormitoryid\":\"" + stringList.get(p) + "\"}"), new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            try {
                                if(jsonObject.getString("isOk").equals("true")){
                                    Toast.makeText(getContext(), "更新成功", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getContext(), "更新失败", Toast.LENGTH_SHORT).show();
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

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    private class ViewHolder {
        TextView textView_name;
        TextView textView_age;
        Spinner spinner;
    }
}