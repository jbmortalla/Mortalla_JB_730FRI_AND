package com.eldroid.todolist;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Model> tasks;
    private Adapter adapter;
    private EditText editTextTask;
    private ListView listView;

    private Handler handler = new Handler();
    private long lastClickTime = 0;
    private final int DOUBLE_CLICK_DELAY = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        editTextTask = findViewById(R.id.editText_Task);

        tasks = new ArrayList<>();
        adapter = new Adapter(this, tasks);
        listView.setAdapter(adapter);

        editTextTask.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                String taskDescription = editTextTask.getText().toString().trim();
                if (!taskDescription.isEmpty()) {
                    tasks.add(new Model(taskDescription, false, R.drawable.placeholder_image));
                    adapter.notifyDataSetChanged();
                    editTextTask.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a task", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            return false;
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastClickTime < DOUBLE_CLICK_DELAY) {
                if (adapter.isTaskChecked(position)) {
                    handleDoubleClick(position);
                } else {
                    Toast.makeText(MainActivity.this, "Double-click is disabled for unchecked tasks", Toast.LENGTH_SHORT).show();
                }
            }
            lastClickTime = currentTime;
        });
    }

    private void handleDoubleClick(int position) {
        Model task = (Model) adapter.getItem(position);

        showTaskOptions(position);
    }

    private void showTaskOptions(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Task Options");

        String[] options = {"Edit", "Delete"};
        builder.setItems(options, (dialog, which) -> {
            if (which == 0) {
                editTask(position);
            } else if (which == 1) {
                deleteTask(position);
            }
        });

        builder.show();
    }

    private void editTask(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Task");

        final EditText input = new EditText(this);
        input.setText(tasks.get(position).getTaskDescription());
        builder.setView(input);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newTaskDescription = input.getText().toString().trim();
            if (!newTaskDescription.isEmpty()) {
                tasks.get(position).setTaskDescription(newTaskDescription);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(MainActivity.this, "Task description cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void deleteTask(int position) {
        tasks.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show();
    }
}
