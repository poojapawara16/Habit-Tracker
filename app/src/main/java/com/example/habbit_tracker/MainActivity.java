package com.example.habbit_tracker;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.*;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.*;
import androidx.recyclerview.widget.*;
import com.example.habbit_tracker.database.DatabaseHelper;
import com.example.habbit_tracker.model.Habit;
import com.example.habbit_tracker.adapter.HabitAdapter;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tvTotalPoints;
    private DatabaseHelper db;
    private HabitAdapter adapter;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private int totalPoints;

    private static final String PREF_NAME = "habit_pref";
    private static final String KEY_POINTS = "total_points";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        tvTotalPoints = findViewById(R.id.tvTotalPoints);

        db = new DatabaseHelper(this);

        preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = preferences.edit();

        totalPoints = preferences.getInt(KEY_POINTS, 0);
        updatePointsUI();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        loadHabits();

        findViewById(R.id.btnAddHabit).setOnClickListener(v ->
                startActivity(new Intent(this, AddHabitActivity.class)));

        findViewById(R.id.btnStats).setOnClickListener(v ->
                startActivity(new Intent(this, StatisticsActivity.class)));

        // Schedule Daily Reminder
        scheduleDailyReminder();
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to exit?")
//                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Yes", (dialog, which) -> {
                    finishAffinity();   // closes the app completely
                })
                .setNegativeButton("No", null)
                .setCancelable(true)
                .show();
    }


    // DAILY REMINDER METHOD
    private void scheduleDailyReminder() {

        Intent intent = new Intent(this, ReminderReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        AlarmManager alarmManager =
                (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 20); // 8 PM
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // If 8PM already passed today → schedule for tomorrow
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        if (alarmManager != null) {
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
            );
        }
    }

    private void loadHabits() {

        List<Habit> habitList = db.getAllHabits();

        adapter = new HabitAdapter(habitList, new HabitAdapter.OnHabitActionListener() {

            @Override
            public void onComplete(Habit habit) {

                String today = getTodayDate();
                String yesterday = getYesterdayDate();

                if (today.equals(habit.getLastDate())) {
                    showToast("Already marked today!");
                    return;
                }

                int newStreak;

                if (yesterday.equals(habit.getLastDate())) {
                    newStreak = habit.getCurrentStreak() + 1;
                } else {
                    newStreak = 1;
                }

                int best = Math.max(newStreak, habit.getBestStreak());

                totalPoints += habit.getReward();

                habit.setCurrentStreak(newStreak);
                habit.setBestStreak(best);
                habit.setLastDate(today);

                db.updateHabit(habit.getId(), today, newStreak, best);

                savePoints();
                updatePointsUI();
                adapter.notifyItemChanged(habitList.indexOf(habit));

                checkBadges(newStreak);
            }

            @Override
            public void onMiss(Habit habit) {

                String today = getTodayDate();

                if (today.equals(habit.getLastDate())) {
                    showToast("Already marked today!");
                    return;
                }

                totalPoints -= habit.getPenalty();
                if (totalPoints < 0) totalPoints = 0;

                habit.setCurrentStreak(0);
                habit.setLastDate(today);

                db.updateHabit(habit.getId(), today, 0, habit.getBestStreak());

                savePoints();
                updatePointsUI();
                adapter.notifyItemChanged(habitList.indexOf(habit));
            }

            @Override
            public void onDelete(Habit habit) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete Habit")
                        .setMessage("Are you sure you want to delete this habit?")
                        .setPositiveButton("Yes", (d, w) -> {
                            db.deleteHabit(habit.getId());
                            loadHabits();
                            showToast("Habit Deleted");
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void checkBadges(int streak) {
        if (streak == 7) showToast("🔥 7 Day Warrior Badge!");
        if (streak == 30) showToast("👑 Habit Master Badge!");
    }

    private void savePoints() {
        editor.putInt(KEY_POINTS, totalPoints);
        editor.apply();
    }

    private void updatePointsUI() {
        tvTotalPoints.setText("✨ Total Points: " + totalPoints);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private String getTodayDate() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(new Date());
    }

    private String getYesterdayDate() {
        long yesterday = System.currentTimeMillis() - (24 * 60 * 60 * 1000);
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(new Date(yesterday));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadHabits();
    }
}
