package com.eldroid.navigation;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.eldroid.navigation.task.Adapter;
import com.eldroid.navigation.task.Model;

import java.util.ArrayList;

public class List extends Fragment {

    private Adapter adapter;
    private ArrayList<Model> list;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        list = new ArrayList<>();
        adapter = new Adapter(requireContext(), list);

        listView = view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        EditText editTextTask = view.findViewById(R.id.editText_Task);
        editTextTask.setOnEditorActionListener((v, actionId, event) -> {
            String taskDescription = editTextTask.getText().toString();
            if (!taskDescription.isEmpty()) {
                addTask(taskDescription);
                editTextTask.setText("");
            }
            return true;
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            // Handle item click events if needed
        });

        listView.setOnTouchListener(new OnTouchListener());

        return view;
    }

    private void addTask(String description) {
        Model task = new Model(description); // Adjust this to your Model constructor
        list.add(task);
        adapter.notifyDataSetChanged();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Edit or Delete Task");

        builder.setItems(new CharSequence[]{"Edit", "Delete"}, (dialog, which) -> {
            if (which == 0) { // Edit
                showEditTaskDialog(position);
            } else if (which == 1) { // Delete
                list.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        builder.show();
    }

    private void showEditTaskDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Edit Task");

        EditText input = new EditText(requireContext());
        input.setText(list.get(position).getDescription());
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String newDescription = input.getText().toString();
            list.get(position).setDescription(newDescription);
            adapter.notifyDataSetChanged();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}
