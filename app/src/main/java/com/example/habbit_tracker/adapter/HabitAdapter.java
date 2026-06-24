package com.example.habbit_tracker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habbit_tracker.R;
import com.example.habbit_tracker.model.Habit;

import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder> {

    private final List<Habit> habitList;
    private final OnHabitActionListener listener;

    // Action Listener Interface
    public interface OnHabitActionListener {
        void onComplete(Habit habit);
        void onMiss(Habit habit);
        void onDelete(Habit habit);
    }

    public HabitAdapter(List<Habit> habitList, OnHabitActionListener listener) {
        this.habitList = habitList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.habit_item, parent, false);
        return new HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {

        Habit habit = habitList.get(position);

        holder.tvHabitName.setText(habit.getName());
        holder.tvStreak.setText("🔥 Streak: " + habit.getCurrentStreak());

        holder.btnComplete.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION && listener != null) {
                listener.onComplete(habitList.get(adapterPosition));
            }
        });

        holder.btnMiss.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION && listener != null) {
                listener.onMiss(habitList.get(adapterPosition));
            }
        });

        holder.btnDelete.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION && listener != null) {
                listener.onDelete(habitList.get(adapterPosition));
            }
        });
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    // 🔥 REQUIRED for updated MainActivity
    public void updateData(List<Habit> newList) {
        habitList.clear();
        habitList.addAll(newList);
        notifyDataSetChanged();
    }

    static class HabitViewHolder extends RecyclerView.ViewHolder {

        TextView tvHabitName, tvStreak;
        Button btnComplete, btnMiss, btnDelete;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHabitName = itemView.findViewById(R.id.tvHabitName);
            tvStreak = itemView.findViewById(R.id.tvStreak);
            btnComplete = itemView.findViewById(R.id.btnComplete);
            btnMiss = itemView.findViewById(R.id.btnMiss);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
