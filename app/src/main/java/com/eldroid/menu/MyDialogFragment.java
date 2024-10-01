package com.eldroid.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

public class MyDialogFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the dialog layout
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);

        TextView dialogTitle = view.findViewById(R.id.dialogTitle);
        TextView dialogMessage = view.findViewById(R.id.dialogMessage);
        Button positiveButton = view.findViewById(R.id.positiveButton);
        Button negativeButton = view.findViewById(R.id.negativeButton);

        dialogTitle.setText("Dialog Title");
        dialogMessage.setText("This is the message for the dialog.");

        positiveButton.setOnClickListener(v -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new NewFragment()); // Replace with NewFragment
            transaction.addToBackStack(null);
            transaction.commit();
            dismiss(); // Dismiss the dialog after action
        });

        negativeButton.setOnClickListener(v -> {
            requireActivity().finish(); // Close the app
        });

        return view; // Return the inflated view
    }
}
