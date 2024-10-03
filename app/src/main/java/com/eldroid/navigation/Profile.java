package com.eldroid.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.eldroid.navigation.info.Adapter;
import com.eldroid.navigation.info.Model;

public class Profile extends Fragment {
    private EditText nameEditText;
    private EditText emailEditText;
    private RadioGroup genderRadioGroup;
    private CheckBox sportsCheckBox;
    private CheckBox musicCheckBox;
    private CheckBox readingCheckBox;
    private Button saveButton;

    private Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        nameEditText = view.findViewById(R.id.editTextName);
        emailEditText = view.findViewById(R.id.editTextEmail);
        genderRadioGroup = view.findViewById(R.id.radioGroupGender);
        sportsCheckBox = view.findViewById(R.id.checkBoxSports);
        musicCheckBox = view.findViewById(R.id.checkBoxMusic);
        readingCheckBox = view.findViewById(R.id.checkBoxReading);
        saveButton = view.findViewById(R.id.buttonSave);

        adapter = new Adapter();

        loadProfileData();

        saveButton.setOnClickListener(v -> saveProfileData());

        return view;
    }

    private void loadProfileData() {
        Model profile = adapter.getProfile();

        nameEditText.setText(profile.getName());
        emailEditText.setText(profile.getEmail());

        String gender = profile.getGender();
        if (gender.equals("Male")) {
            genderRadioGroup.check(R.id.radioMale);
        } else if (gender.equals("Female")) {
            genderRadioGroup.check(R.id.radioFemale);
        } else if (gender.equals("Other")) {
            genderRadioGroup.check(R.id.radioOther);
        } else {
            genderRadioGroup.clearCheck();
        }

        String interest = profile.getInterest();
        sportsCheckBox.setChecked(interest.contains("Sports"));
        musicCheckBox.setChecked(interest.contains("Music"));
        readingCheckBox.setChecked(interest.contains("Reading"));
    }

    private void saveProfileData() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();

        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        String gender = "";
        if (selectedGenderId == R.id.radioMale) {
            gender = "Male";
        } else if (selectedGenderId == R.id.radioFemale) {
            gender = "Female";
        } else if (selectedGenderId == R.id.radioOther) {
            gender = "Other";
        }

        // Get selected interests
        StringBuilder interest = new StringBuilder();
        if (sportsCheckBox.isChecked()) {
            interest.append("Sports ");
        }
        if (musicCheckBox.isChecked()) {
            interest.append("Music ");
        }
        if (readingCheckBox.isChecked()) {
            interest.append("Reading ");
        }

        adapter.updateProfile(name, email, gender, interest.toString().trim());

        Toast.makeText(getActivity(), "Profile information saved successfully!", Toast.LENGTH_SHORT).show();
    }
}
