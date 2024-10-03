package com.eldroid.navigation.task;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.eldroid.navigation.R;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<Model> taskList;

    public Adapter(Context context, ArrayList<Model> taskList) {
        this.context = context;
        this.taskList = taskList;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.checkBox = convertView.findViewById(R.id.checkBox);
            holder.taskImage = convertView.findViewById(R.id.taskImage);
            holder.textViewTask = convertView.findViewById(R.id.textView_task);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Model task = taskList.get(position);
        holder.textViewTask.setText(task.getDescription());
        holder.checkBox.setChecked(task.isCompleted());
        holder.taskImage.setImageResource(task.getImageResource());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setCompleted(isChecked);
            updateTaskStrikethrough(holder.textViewTask, isChecked);
        });

        updateTaskStrikethrough(holder.textViewTask, task.isCompleted());

        return convertView;
    }

    private void updateTaskStrikethrough(TextView textView, boolean isCompleted) {
        if (isCompleted) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }

    static class ViewHolder {
        CheckBox checkBox;
        ImageView taskImage;
        TextView textViewTask;
    }
}
