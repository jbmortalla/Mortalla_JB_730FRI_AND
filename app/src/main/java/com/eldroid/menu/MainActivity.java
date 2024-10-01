package com.eldroid.menu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_fragment) {
            // Replace the current fragment with NewFragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new NewFragment());
            transaction.addToBackStack(null);
            transaction.commit();
            return true;

        } else if (itemId == R.id.action_dialog) {
            // Show the DialogFragment
            showMyDialogFragment();
            return true;

        } else if (itemId == R.id.action_exit) {
            // Exit the app
            finish();
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showMyDialogFragment() {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "dialog");
    }
}
