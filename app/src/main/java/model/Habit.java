package model;

public class Habit {

    private String name;
    private int reward;
    private int penalty;

    public Habit(String name, int reward, int penalty) {
        this.name = name;
        this.reward = reward;
        this.penalty = penalty;
    }

    public String getName() {
        return name;
    }

    public int getReward() {
        return reward;
    }

    public int getPenalty() {
        return penalty;
    }
}
