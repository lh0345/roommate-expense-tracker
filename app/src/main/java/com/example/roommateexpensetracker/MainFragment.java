package com.example.roommateexpensetracker;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    private ExpenseDatabaseHelper dbHelper;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: MainFragment view created");

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        dbHelper = new ExpenseDatabaseHelper(getContext());
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator()); // Adding default item animator

        loadExpenses();

        return view;
    }

    public void loadExpenses() {
        List<Expense> expenseList = new ArrayList<>();
        try (Cursor cursor = dbHelper.getReadableDatabase().query(ExpenseDatabaseHelper.TABLE_EXPENSES, null, null, null, null, null, null)) {
            Log.d(TAG, "loadExpenses: Cursor count = " + cursor.getCount());
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_NAME));
                double amount = cursor.getDouble(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_AMOUNT));
                expenseList.add(new Expense(id, name, amount));
            }
        } catch (Exception e) {
            Log.e(TAG, "Error loading expenses", e);
        }

        Log.d(TAG, "loadExpenses: Expense list size = " + expenseList.size());
        ExpenseAdapter adapter = new ExpenseAdapter(expenseList);
        recyclerView.setAdapter(adapter);
    }
}
