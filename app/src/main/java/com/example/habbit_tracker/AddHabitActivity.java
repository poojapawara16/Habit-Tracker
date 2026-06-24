package com.example.habbit_tracker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.habbit_tracker.database.DatabaseHelper;

public class AddHabitActivity extends AppCompatActivity {

    EditText etHabitName, etReward, etPenalty;
    Button btnSave;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        etHabitName = findViewById(R.id.etHabitName);
        etReward = findViewById(R.id.etReward);
        etPenalty = findViewById(R.id.etPenalty);
        btnSave = findViewById(R.id.btnSave);

        db = new DatabaseHelper(this);

        btnSave.setOnClickListener(v -> {

            String name = etHabitName.getText().toString().trim();
            String rewardStr = etReward.getText().toString().trim();
            String penaltyStr = etPenalty.getText().toString().trim();

            if (name.isEmpty() || rewardStr.isEmpty() || penaltyStr.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int reward = Integer.parseInt(rewardStr);
            int penalty = Integer.parseInt(penaltyStr);

            db.insertHabit(name, reward, penalty);

            Toast.makeText(this, "Habit Added Successfully!", Toast.LENGTH_SHORT).show();

            finish(); // Go back to MainActivity
        });
    }
}
