package com.example.roommateexpensetracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        EditText nameEditText = findViewById(R.id.editTextName);
        EditText amountEditText = findViewById(R.id.editTextAmount);
        Button saveButton = findViewById(R.id.buttonSave);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                double amount = Double.parseDouble(amountEditText.getText().toString());

                ExpenseDatabaseHelper dbHelper = new ExpenseDatabaseHelper(AddExpenseActivity.this);
                dbHelper.addExpense(new Expense(name, amount));

                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
