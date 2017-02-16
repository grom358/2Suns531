package com.cameronzemek.workout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String[] days = getResources().getStringArray(R.array.day);
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String week = Integer.toString(sharedPref.getInt("week", 1));
        int dayNo = sharedPref.getInt("day_no", 0);
        String day = days[dayNo];
        ((TextView) findViewById(R.id.txtWeek)).setText(week);
        ((TextView) findViewById(R.id.txtDay)).setText(day);
    }

    public void onSetup(View view) {
        Intent intent = new Intent(this, SetupActivity.class);
        startActivity(intent);
    }

    public void onWorkout(View view) {
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }

    public void onStopwatch(View view) {
        Intent intent = new Intent(this, StopwatchActivity.class);
        startActivity(intent);
    }

    public void onHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}
