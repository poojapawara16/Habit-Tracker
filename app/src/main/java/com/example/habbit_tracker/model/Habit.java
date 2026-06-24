package com.example.habbit_tracker.model;

public class Habit {

    int id;
    String name;
    int reward;
    int penalty;
    String lastDate;
    int currentStreak;
    int bestStreak;

    public Habit(int id, String name, int reward, int penalty,
                 String lastDate, int currentStreak, int bestStreak) {
        this.id = id;
        this.name = name;
        this.reward = reward;
        this.penalty = penalty;
        this.lastDate = lastDate;
        this.currentStreak = currentStreak;
        this.bestStreak = bestStreak;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getReward() { return reward; }
    public int getPenalty() { return penalty; }
    public String getLastDate() { return lastDate; }
    public int getCurrentStreak() { return currentStreak; }
    public int getBestStreak() { return bestStreak; }

    public void setLastDate(String lastDate) { this.lastDate = lastDate; }
    public void setCurrentStreak(int currentStreak) { this.currentStreak = currentStreak; }
    public void setBestStreak(int bestStreak) { this.bestStreak = bestStreak; }
}

