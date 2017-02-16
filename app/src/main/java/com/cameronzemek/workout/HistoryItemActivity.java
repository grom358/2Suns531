package com.cameronzemek.workout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class HistoryItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_item);

        long workoutId = getIntent().getLongExtra("workoutId", -1);
        if (workoutId == -1) {
            Toast toast = Toast.makeText(this, "Invalid workout!", Toast.LENGTH_LONG);
            toast.show();
            finish();
            return;
        }

        ListView exerciseListView = (ListView) findViewById(R.id.exerciseListView);

        WorkoutHistoryDbHelper dbHelper = new WorkoutHistoryDbHelper(this);
        List<Exercise> workout = dbHelper.getHistory(workoutId);

        ExerciseListAdapter itemAdapter = new ExerciseListAdapter(workout, LayoutInflater.from(this));
        exerciseListView.setAdapter(itemAdapter);
    }
}
