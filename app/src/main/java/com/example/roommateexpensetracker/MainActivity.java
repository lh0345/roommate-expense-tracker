package com.example.roommateexpensetracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_EXPENSE_REQUEST_CODE = 1;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Button addExpenseButton = findViewById(R.id.buttonAddExpense);
        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
                startActivityForResult(intent, ADD_EXPENSE_REQUEST_CODE);
            }
        });

        Button resetButton = findViewById(R.id.buttonReset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetExpenses();
            }
        });

        if (findViewById(R.id.fragmentContainer) != null) {
            if (savedInstanceState != null) {
                return;
            }

            Log.d("MainActivity", "Adding MainFragment");
            MainFragment mainFragment = new MainFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, mainFragment)
                    .commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_EXPENSE_REQUEST_CODE && resultCode == RESULT_OK) {
            MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
            if (mainFragment != null) {
                Log.d("MainActivity", "Refreshing MainFragment");
                mainFragment.loadExpenses();
            }
        }
    }

    private void resetExpenses() {
        ExpenseDatabaseHelper db = new ExpenseDatabaseHelper(this);
        db.clearExpenses();
        FragmentManager fragmentManager = getSupportFragmentManager();
        MainFragment mainFragment = new MainFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, mainFragment)
                .commit();
    }
}
