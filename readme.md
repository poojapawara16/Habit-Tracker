# Habit Tracker App

## Overview

Habit Tracker is an Android application designed to help users build positive habits, maintain consistency, and improve productivity. The application allows users to track daily habits, monitor streaks, earn reward points, and analyze their progress through graphical statistics.

---

## Features

### Habit Management

* Add new habits
* Delete existing habits
* View all habits in a list

### Streak Tracking

* Track daily habit completion
* Maintain current streak count
* Store best streak achieved

### Reward System

* Earn points for completing habits
* Lose points when habits are missed
* View total accumulated points

### Statistics Dashboard

* Bar Chart for habit performance analysis
* Pie Chart for completion percentage visualization
* Productivity insights through graphical reports

### Reminder Notifications

* Daily reminders using AlarmManager
* Encourages users to maintain consistency

### User Interface

* Clean and simple design
* Material Design components
* Responsive layout

---

## Technologies Used

| Technology          | Purpose                 |
| ------------------- | ----------------------- |
| Java                | Application Development |
| Android Studio      | Development Environment |
| SQLite              | Local Database Storage  |
| RecyclerView        | Display Habit List      |
| SharedPreferences   | Store User Points       |
| AlarmManager        | Daily Notifications     |
| MPAndroidChart      | Data Visualization      |
| Material Components | User Interface Design   |

---

## Project Structure

```text
com.example.habbit_tracker
│
├── MainActivity.java
├── AddHabitActivity.java
├── StatisticsActivity.java
├── ReminderReceiver.java
│
├── adapter
│   └── HabitAdapter.java
│
├── database
│   └── DatabaseHelper.java
│
├── model
│   └── Habit.java
│
└── res
    ├── layout
    ├── drawable
    └── values
```

---

## Statistics Module

### Bar Chart

The Bar Chart displays:

* Completed Habits
* Missed Habits
* Active Habits

### Pie Chart

The Pie Chart displays:

* Completion Percentage
* Miss Percentage
* Habit Distribution

Charts are implemented using the MPAndroidChart library.

---

## Database

The application uses SQLite database to store:

* Habit Name
* Reward Points
* Penalty Points
* Current Streak
* Best Streak
* Last Completion Date

---

## Reminder System

A daily reminder notification is scheduled using AlarmManager.

Purpose:

* Remind users to complete habits
* Improve consistency
* Maintain streaks

---

## Screenshots

### Home Screen

Displays:

* Total Points
* Habit List
* Add Habit Button
* Statistics Button

### Add Habit Screen

Allows users to:

* Create new habits
* Set reward points
* Set penalty points

### Statistics Screen

Displays:

* Bar Chart
* Pie Chart
* Habit Performance Analysis

---

## Learning Outcomes

This project helped in understanding:

* Android Application Development
* Java Programming
* SQLite Database Operations
* RecyclerView Implementation
* Notification Handling
* UI/UX Design Principles
* Data Visualization Techniques

---

## Future Enhancements

* User Authentication
* Cloud Database Integration
* Habit Categories
* Dark Mode Support
* Weekly and Monthly Reports
* Achievement Badges
* Data Backup and Restore

---

## Developer

Developed by: Pooja Pawara

Project Title: Habit Tracker

---

