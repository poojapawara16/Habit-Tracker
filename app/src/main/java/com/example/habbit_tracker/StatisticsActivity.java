package com.example.habbit_tracker;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.habbit_tracker.database.DatabaseHelper;
import com.example.habbit_tracker.model.Habit;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {

    TextView tvStats;
    DatabaseHelper db;
    BarChart barChart;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        tvStats = findViewById(R.id.tvStats);
        barChart = findViewById(R.id.barChart);
        pieChart = findViewById(R.id.pieChart);

        db = new DatabaseHelper(this);

        loadStatistics();
        loadBarChart();
        loadPieChart();
    }

    private void loadStatistics() {

        List<Habit> habitList = db.getAllHabits();

        int totalHabits = habitList.size();
        int totalBestStreak = 0;
        int totalCurrentStreak = 0;

        for (Habit habit : habitList) {
            totalBestStreak += habit.getBestStreak();
            totalCurrentStreak += habit.getCurrentStreak();
        }

        String stats =
                "Total Habits: " + totalHabits + "\n\n" +
                        "Total Current Streak: " + totalCurrentStreak + "\n\n" +
                        "Total Best Streak: " + totalBestStreak;

        tvStats.setTextColor(android.graphics.Color.BLACK);
        tvStats.setText(stats);
    }

    private void loadBarChart() {

        List<Habit> habitList = db.getAllHabits();

        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        for (int i = 0; i < habitList.size(); i++) {
            Habit habit = habitList.get(i);
            entries.add(new BarEntry(i, habit.getBestStreak()));
            labels.add(habit.getName());
        }

        BarDataSet dataSet = new BarDataSet(entries, "Best Streak");
        dataSet.setValueTextSize(12f);
        dataSet.setColor(Color.parseColor("#4FC3F7"));

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        barChart.getAxisRight().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.setFitBars(true);
        barChart.animateY(1000);

        applyDarkModeToBarChart();

        barChart.invalidate();
    }

    private void loadPieChart() {

        List<Habit> habitList = db.getAllHabits();

        int completed = 0;
        int missed = 0;

        for (Habit habit : habitList) {
            completed += habit.getBestStreak();
            if (habit.getCurrentStreak() == 0) {
                missed++;
            }
        }

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(completed, "Completed"));
        entries.add(new PieEntry(missed, "Missed"));

        PieDataSet dataSet = new PieDataSet(entries, "Habit Overview");
        dataSet.setValueTextSize(14f);
        dataSet.setColors(Color.GREEN, Color.RED);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);

        pieChart.getDescription().setEnabled(false);
        pieChart.animateY(1000);

        applyDarkModeToPieChart();

        pieChart.invalidate();

//        pieChart.setHoleRadius(60f);
//        pieChart.setTransparentCircleRadius(65f);
//        pieChart.setDrawEntryLabels(false);
//        pieChart.getLegend().setEnabled(true);

    }

    private void applyDarkModeToBarChart() {
        boolean isDark =
                (getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK)
                        == Configuration.UI_MODE_NIGHT_YES;

        int textColor = isDark ? Color.WHITE : Color.BLACK;

        barChart.getXAxis().setTextColor(textColor);
        barChart.getAxisLeft().setTextColor(textColor);
        barChart.getLegend().setTextColor(textColor);
    }

    private void applyDarkModeToPieChart() {
        boolean isDark =
                (getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK)
                        == Configuration.UI_MODE_NIGHT_YES;

        int textColor = isDark ? Color.WHITE : Color.BLACK;

        pieChart.setEntryLabelColor(textColor);
        pieChart.getLegend().setTextColor(textColor);
    }
}
