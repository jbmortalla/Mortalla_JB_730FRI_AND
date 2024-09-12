package com.eldroid.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private Context context;
    private ArrayList<Model> taskList;
    private LayoutInflater inflater;

    // Constructor
    public Adapter(Context context, ArrayList<Model> taskList) {
        this.context = context;
        this.taskList = taskList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        ImageView taskImage = convertView.findViewById(R.id.taskImage);
        TextView taskText = convertView.findViewById(R.id.textView_task);

        Model task = taskList.get(position);

        checkBox.setChecked(task.isChecked());
        taskImage.setImageResource(task.getImageResource());
        taskText.setText(task.getTaskDescription());

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setChecked(isChecked);
        });

        checkBox.setFocusable(false);

        return convertView;
    }
    public boolean isTaskChecked(int position) {
        return taskList.get(position).isChecked();
    }
}
