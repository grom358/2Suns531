package com.cameronzemek.workout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class WorkoutActivity extends AppCompatActivity {
    public static final String KEY_DONE = "com.cameronzemek.workout.done";

    private BroadcastReceiver receiver;
    private Stopwatch restStopwatch;
    private StopwatchTimer restTimer;
    private WorkoutNotification restNotification;
    private StopwatchTimer workoutTimer;
    private ListView exerciseListView;
    private ExerciseListAdapter itemAdapter;
    private EditText editReps;
    private EditText editWeight;
    private WorkoutTracker workoutTracker;
    private View layoutSetControls;
    private float smallestWeightIncrement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        float volume = sharedPref.getInt("volume", 100) / 100f;

        boolean isMetric = sharedPref.getString("unit", "kg").equals("kg");
        String roundKey = isMetric ? "kg_round" : "lb_round";
        float defaultRound = isMetric ? 2.5f : 5f;
        smallestWeightIncrement = sharedPref.getFloat(roundKey, defaultRound);

        setContentView(R.layout.activity_workout);
        layoutSetControls = findViewById(R.id.layoutSetControls);

        exerciseListView = (ListView) findViewById(R.id.exerciseListView);
        editReps = (EditText) findViewById(R.id.reps);
        editWeight = (EditText) findViewById(R.id.weight);

        // Keep screen on for restStopwatch.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        restTimer = (StopwatchTimer) findViewById(R.id.restTimer);
        restNotification = WorkoutNotification.build(this, getIntent());
        restStopwatch = new Stopwatch();
        restStopwatch.schedule(new BellPlayer(this, volume), 60000, false);

        workoutTimer = (StopwatchTimer) findViewById(R.id.workoutTimer);
        workoutTimer.start();

        List<Exercise> workout = WorkoutProgram.build(sharedPref);
        workoutTracker = new WorkoutTracker(workout);

        init();
        itemAdapter = new ExerciseListAdapter(workout, LayoutInflater.from(this));
        exerciseListView.setAdapter(itemAdapter);

        // Allow receive done event.
        IntentFilter filter = new IntentFilter();
        filter.addAction(KEY_DONE);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(KEY_DONE)) {
                    onDone(null);
                }
            }
        };
        registerReceiver(receiver, filter);

        restNotification.show();
    }

    private void init() {
        String name = workoutTracker.getExercise().getName();
        ExerciseSet set = workoutTracker.getSet();
        editReps.setText(Integer.toString(set.getReps()));
        editWeight.setText(Util.format(set.getWeight()));
        restNotification.setText(String.format("%s: %s", name, set.getDetails()));
        if (set instanceof AssistanceSet) {
            layoutSetControls.setVisibility(View.GONE);
        } else {
            layoutSetControls.setVisibility(View.VISIBLE);
        }
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            view.clearFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void onMinusRep(View view) {
        int reps = Integer.valueOf(editReps.getText().toString());
        reps--;
        if (reps < 0) {
            reps = 0;
        }
        editReps.setText(Integer.toString(reps));
        hideKeyboard();
    }

    public void onPlusRep(View view) {
        int reps = Integer.valueOf(editReps.getText().toString());
        reps++;
        if (reps >= 100) {
            reps = 99;
        }
        editReps.setText(Integer.toString(reps));
        hideKeyboard();
    }

    public void onMinusWeight(View view) {
        float w = Float.valueOf(editWeight.getText().toString());
        float weight = Util.roundUp(w - smallestWeightIncrement, smallestWeightIncrement);
        if (weight < 0f) {
            weight = 0f;
        }
        editWeight.setText(Util.format(weight));
        hideKeyboard();
    }

    public void onPlusWeight(View view) {
        float w = Float.valueOf(editWeight.getText().toString());
        float weight = Util.roundDown(w + smallestWeightIncrement, smallestWeightIncrement);
        if (weight >= 1000f) {
            weight = 999.99f;
        }
        editWeight.setText(Util.format(weight));
        hideKeyboard();
    }

    public void onDone(View view) {
        workoutTracker.completeSet(
                Integer.valueOf(editReps.getText().toString()),
                Float.valueOf(editWeight.getText().toString()));
        itemAdapter.notifyDataSetChanged();

        if (!workoutTracker.isDone()) {
            ensureVisible(exerciseListView, workoutTracker.getPosition() + (workoutTracker.isLastSet() ? 1 : 0));
            init();

            // Start rest timer.
            restTimer.reset();
            restStopwatch.reset();
            restTimer.start();
            restStopwatch.start();
            restNotification.setWhen(restTimer.getStartTime());
            restNotification.show();

        } else {
            SharedPreferences sharedPref = getSharedPreferences(
                    getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            WorkoutProgram.complete(this, sharedPref, workoutTracker.getWorkout());
            finish();
        }
    }

    public void onUndo(View view) {
        workoutTracker.undo();
        itemAdapter.notifyDataSetChanged();
        init();
    }

    public static void ensureVisible(ListView listView, int pos) {
        if (listView == null) {
            return;
        }

        if (pos < 0) {
            return;
        }

        if (pos >= listView.getCount()) {
            // Scroll to last item.
            pos = listView.getCount() - 1;
        }

        int first = listView.getFirstVisiblePosition();
        int last = listView.getLastVisiblePosition();

        if (pos < first || pos >= last) {
            int scrollTo = 1 + pos - (last - first);
            if (scrollTo < 0) {
                scrollTo = 0;
            }
            listView.setSelection(scrollTo);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        restNotification.cancel();
        unregisterReceiver(receiver);
    }
}
