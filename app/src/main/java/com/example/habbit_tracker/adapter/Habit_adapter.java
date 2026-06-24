package com.example.habbit_tracker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.habbit_tracker.model.Habit;
import com.example.habbit_tracker.R;



import java.util.List;

public class Habit_adapter extends RecyclerView.Adapter<Habit_adapter.HabitViewHolder> {

    private List<Habit> habitList;

    public Habit_adapter(List<Habit> habitList) {
        this.habitList = habitList;
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habit, parent, false);
        return new HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        Habit habit = habitList.get(position);
        holder.habitText.setText(
                habit.getName() + " | Reward: " +
                        habit.getReward() +
                        " | Penalty: " +
                        habit.getPenalty()
        );
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    public static class HabitViewHolder extends RecyclerView.ViewHolder {

        TextView habitText;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            habitText = itemView.findViewById(R.id.habitText);
        }
    }
}

