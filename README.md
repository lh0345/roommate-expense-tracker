# Roommate Expense Tracker

An Android application for recording and storing expenses on a device.

The current version lets a user add an expense with a name and amount, view saved expenses in a list, and clear the stored records.

## What I Implemented

* Created separate Android activities for viewing and adding expenses
* Stored expense data locally with SQLite
* Created the expense table with `SQLiteOpenHelper`
* Inserted records using `ContentValues`
* Queried saved expenses with a `Cursor`
* Displayed expense records with `RecyclerView` and a custom adapter
* Used an Android `Intent` to open the add-expense screen
* Refreshed the expense list after a new record was saved
* Added a reset action that clears stored expenses

## What I Learned

This project helped me understand the basic structure of a native Android application.

I learned how Android activities and fragments work together and how data can move between screens using intents and activity results.

I also learned how to work directly with SQLite on Android. I created a table, inserted records, queried them with a `Cursor`, converted database rows into Java objects, and displayed those objects through a `RecyclerView`.

The project also taught me why UI code, data objects, database code, and list adapters should have separate responsibilities.

## What This Project Demonstrates

* Android application structure
* Java
* Activities and fragments
* Local SQLite persistence
* Basic database operations
* `RecyclerView` and adapter patterns
* Passing control between Android screens
* Separating UI and data code

## Tech Used

* Java
* Android SDK
* SQLite
* RecyclerView
* Android Fragments
* XML layouts
* Gradle

## Current Scope

This version works as an expense log. It stores expense names and amounts but does not calculate roommate balances or split payments.

## Running the Project

Open the project in Android Studio, sync the Gradle files, and run it on an Android emulator or physical device.
