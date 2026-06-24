# Habit Tracker

## Overview

Habit Tracker is an Android application developed using Java and Android Studio. The application helps users build positive habits by tracking daily activities, maintaining streaks, earning reward points, and viewing progress through graphical statistics.

The application stores habit information locally using SQLite and provides daily reminder notifications to encourage consistency.

---

## Features

### Habit Management

* Add new habits
* Delete habits
* View all habits in a RecyclerView list

### Streak Tracking

* Track daily habit completion
* Maintain current streak
* Store best streak achieved

### Reward System

* Earn points for completed habits
* Deduct points for missed habits
* Display total accumulated points

### Statistics

* Bar Chart for habit performance analysis
* Pie Chart for completion percentage visualization
* Progress monitoring through graphical reports

### Reminder Notifications

* Daily habit reminders using AlarmManager
* Notification support through BroadcastReceiver

---

## Technologies Used

* Java
* Android Studio
* SQLite Database
* RecyclerView
* SharedPreferences
* AlarmManager
* BroadcastReceiver
* MPAndroidChart
* Material Design Components

---

## Project Structure

```text
com.example.habbit_tracker
│
├── AddHabitActivity.java
├── MainActivity.java
├── ReminderReceiver.java
├── Splash.java
├── StatisticsActivity.java
│
├── adapter
│   ├── HabitAdapter.java
│   └── Habit_adapter.java
│
├── database
│   └── DatabaseHelper.java
│
├── model
│   └── Habit.java
│
└── resources
    ├── layouts
    ├── drawables
    └── values
```

---

## Modules

### Splash Module

Displays the splash screen when the application starts.

### Habit Management Module

Allows users to create and manage habits.

### Tracking Module

Tracks daily completion status and streak information.

### Statistics Module

Displays Bar Chart and Pie Chart representations of user progress.

### Reminder Module

Schedules daily notifications to remind users to complete habits.

---

## Database Information

The application uses SQLite database to store:

* Habit Name
* Reward Points
* Penalty Points
* Current Streak
* Best Streak
* Last Completion Date

---

## Application Workflow

1. User launches the application.
2. Splash screen is displayed.
3. User views existing habits on the home screen.
4. User can add new habits.
5. User marks habits as Complete or Miss.
6. Streaks and points are automatically updated.
7. Statistics screen displays progress charts.
8. Daily reminders help maintain consistency.

---

## Learning Outcomes

This project helped in understanding:

* Android Application Development
* Java Programming
* SQLite Database Management
* RecyclerView Implementation
* Activity Navigation
* Notification Scheduling
* Data Visualization using Charts
* User Interface Design

---

## Future Enhancements

* User Authentication
* Cloud Database Integration
* Habit Categories
* Dark Mode Support
* Weekly Reports
* Monthly Reports
* Achievement System
* Data Backup and Restore

---

## Developer

Developed as an Internship Project

Project Title:
Habit Tracker and Productivity Management Application

---

## License

This project is intended for educational and internship purposes.
