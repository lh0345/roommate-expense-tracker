package com.example.roommateexpensetracker;

public class Expense {
    private int id;
    private String name;
    private double amount;

    public Expense(int id, String name, double amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public Expense(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }
}
