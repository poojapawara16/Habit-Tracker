package com.example.habbit_tracker.database;


import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;
import com.example.habbit_tracker.model.Habit;
import java.util.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "habit_db";
    private static final int DB_VERSION = 1;
    public static final String TABLE = "habits";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE = "CREATE TABLE " + TABLE + "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT,"
                + "reward INTEGER,"
                + "penalty INTEGER,"
                + "last_date TEXT,"
                + "current_streak INTEGER DEFAULT 0,"
                + "best_streak INTEGER DEFAULT 0)";

        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void insertHabit(String name, int reward, int penalty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("reward", reward);
        values.put("penalty", penalty);
        db.insert(TABLE, null, values);
    }

    public List<Habit> getAllHabits() {

        List<Habit> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(new Habit(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getInt(6)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void updateHabit(int id, String date, int current, int best) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("last_date", date);
        values.put("current_streak", current);
        values.put("best_streak", best);
        db.update(TABLE, values, "id=?", new String[]{String.valueOf(id)});
    }

    public void deleteHabit(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, "id=?", new String[]{String.valueOf(id)});
    }

    public int getHabitCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT COUNT(*) FROM " + TABLE, null);
        c.moveToFirst();
        int count = c.getInt(0);
        c.close();
        return count;
    }

    public int getBestStreakOverall() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT MAX(best_streak) FROM " + TABLE, null);
        c.moveToFirst();
        int best = c.getInt(0);
        c.close();
        return best;
    }
}

