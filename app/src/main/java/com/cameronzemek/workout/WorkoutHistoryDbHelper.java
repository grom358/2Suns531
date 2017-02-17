package com.cameronzemek.workout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkoutHistoryDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "WorkoutHistory.db";

    public WorkoutHistoryDbHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE workout_history(" +
                        "workout_id INTEGER PRIMARY KEY, " +
                        "workout_date TEXT, " +
                        "elapsed_time TEXT);"
        );
        db.execSQL(
                "CREATE TABLE workout_exercise(" +
                        "workout_exercise_id INTEGER PRIMARY KEY, " +
                        "workout_id INTEGER, " +
                        "exercise_name TEXT, " +
                        "FOREIGN KEY(workout_id) REFERENCES workout_history(workout_id));"
        );
        db.execSQL(
                "CREATE TABLE workout_exercise_set(" +
                        "workout_exercise_set_id INTEGER PRIMARY KEY, " +
                        "workout_exercise_id INTEGER, " +
                        "reps INTEGER, " +
                        "weight NUMERIC, " +
                        "unit STRING, " +
                        "state INTEGER, " +
                        "FOREIGN KEY(workout_exercise_id) REFERENCES workout_exercise(workout_exercise_id));"
        );
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        //db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2) {
            db.execSQL("ALTER TABLE workout_exercise_set ADD COLUMN unit TEXT DEFAULT 'kg'");
        }
    }

    public void insert(List<Exercise> workout) {
        SQLiteDatabase db = getReadableDatabase();
        db.beginTransaction();
        try {
            insert(db, workout);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    private void insert(SQLiteDatabase db, List<Exercise> workout) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String now = df.format(new Date());
        ContentValues values = new ContentValues(1);
        values.put("workout_date", now);
        long workoutId = db.insert("workout_history", null, values);
        System.out.println("INSERT: " + workoutId);
        for (Exercise exercise : workout) {
            if (exercise.getName().equals("Assistance")) {
                // Skip storing assistance.
                continue;
            }
            long exerciseId = insert(db, workoutId, exercise);
            for (ExerciseSet set : exercise.getSets()) {
                insert(db, exerciseId, set);
            }
        }
    }

    private long insert(SQLiteDatabase db, long workoutId, Exercise exercise) {
        ContentValues values = new ContentValues(2);
        values.put("workout_id", workoutId);
        values.put("exercise_name", exercise.getName());
        return db.insert("workout_exercise", null, values);
    }

    private long insert(SQLiteDatabase db, long exerciseId, ExerciseSet set) {
        ContentValues values = new ContentValues(3);
        values.put("workout_exercise_id", exerciseId);
        values.put("reps", set.getReps());
        values.put("weight", set.getWeight());
        values.put("unit", set.getUnit());
        values.put("state", set.getState());
        return db.insert("workout_exercise_set", null, values);
    }

    public List<WorkoutHistory> getHistoryList() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT workout_id, workout_date FROM workout_history ORDER BY workout_date DESC", null);
        int numRows = c.getCount();
        List<WorkoutHistory> historyList = new ArrayList<>(numRows);
        while (c.moveToNext()) {
            historyList.add(new WorkoutHistory(c.getLong(0), c.getString(1)));
        }
        return historyList;
    }

    public List<Exercise> getHistory(long workoutId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] args = {Long.toString(workoutId)};
        Cursor c = db.rawQuery("SELECT workout_exercise_id, exercise_name FROM workout_exercise WHERE workout_id = ?", args);
        int exerciseCount = c.getCount();
        List<Exercise> workout = new ArrayList<>(exerciseCount);
        long[] exerciseIds = new long[exerciseCount];
        int i = 0;
        while (c.moveToNext()) {
            exerciseIds[i++] = c.getLong(0);
            workout.add(new Exercise(c.getString(1)));
        }
        for (i = 0; i < exerciseIds.length; ++i) {
            addSets(db, exerciseIds[i], workout.get(i).getSets());
        }
        return workout;
    }

    private void addSets(SQLiteDatabase db, long exerciseId, List<ExerciseSet> sets) {
        String[] args = {Long.toString(exerciseId)};
        Cursor c = db.rawQuery("SELECT reps, weight, unit, state FROM workout_exercise_set WHERE workout_exercise_id = ?", args);
        while (c.moveToNext()) {
            sets.add(new ExerciseSet(c.getInt(0), c.getFloat(1), c.getString(2), c.getInt(3)));
        }
    }
}
