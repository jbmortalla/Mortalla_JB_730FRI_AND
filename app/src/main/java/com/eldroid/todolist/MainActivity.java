package com.eldroid.todolist;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TaskAdapter taskAdapter;
    private ArrayList<Task> taskList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, taskList);

        listView = findViewById(R.id.listView);
        listView.setAdapter(taskAdapter);

        EditText editTextTask = findViewById(R.id.editText_Task);
        editTextTask.setOnEditorActionListener((v, actionId, event) -> {
            String taskDescription = editTextTask.getText().toString();
            if (!taskDescription.isEmpty()) {
                addTask(taskDescription);
                editTextTask.setText("");
            }
            return true;
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {

        });

        listView.setOnTouchListener(new OnTouchListener());
    }

    private void addTask(String description) {
        Task task = new Task(description, false, R.drawable.placeholder_image);
        taskList.add(task);
        taskAdapter.notifyDataSetChanged();
    }

    private class OnTouchListener implements View.OnTouchListener {
        private long lastClickTime = 0;
        private static final long DOUBLE_CLICK_TIME_DELTA = 300;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                long clickTime = System.currentTimeMillis();
                if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                    int position = listView.pointToPosition((int) event.getX(), (int) event.getY());
                    if (position >= 0) {
                        showEditDeleteDialog(position);
                    }
                }
                lastClickTime = clickTime;
            }
            return false;
        }
    }

    private void showEditDeleteDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit or Delete Task");

        builder.setItems(new CharSequence[]{"Edit", "Delete"}, (dialog, which) -> {
            if (which == 0) { // Edit
                showEditTaskDialog(position);
            } else if (which == 1) { // Delete
                taskList.remove(position);
                taskAdapter.notifyDataSetChanged();
            }
        });

        builder.show();
    }

    private void showEditTaskDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Task");

        EditText input = new EditText(this);
        input.setText(taskList.get(position).getDescription());
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String newDescription = input.getText().toString();
            taskList.get(position).setDescription(newDescription);
            taskAdapter.notifyDataSetChanged();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}