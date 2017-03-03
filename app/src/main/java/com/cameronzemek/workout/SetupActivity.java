package com.cameronzemek.workout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class SetupActivity extends AppCompatActivity {
    private EditText editRound;
    private EditText editPressWeight;
    private EditText editBenchWeight;
    private EditText editSquatWeight;
    private EditText editDeadliftWeight;
    private EditText editWeek;
    private Spinner spinnerDay;
    private Spinner spinnerUnit;
    private Spinner spinnerVariation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        final SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        editRound = (EditText) findViewById(R.id.editRound);
        editPressWeight = (EditText) findViewById(R.id.editPressWeight);
        editBenchWeight = (EditText) findViewById(R.id.editBenchWeight);
        editSquatWeight = (EditText) findViewById(R.id.editSquatWeight);
        editDeadliftWeight = (EditText) findViewById(R.id.editDeadliftWeight);
        editWeek = (EditText) findViewById(R.id.editWeek);

        spinnerDay = (Spinner) findViewById(R.id.spinnerDay);
        spinnerDay.setAdapter(ArrayAdapter.createFromResource(this, R.array.day, R.layout.spinner_item));

        spinnerVariation = (Spinner) findViewById(R.id.spinnerVariation);
        spinnerVariation.setAdapter(ArrayAdapter.createFromResource(this, R.array.variations, R.layout.spinner_item));

        spinnerUnit = (Spinner) findViewById(R.id.spinnerUnit);
        spinnerUnit.setAdapter(ArrayAdapter.createFromResource(this, R.array.units, R.layout.spinner_item));
        spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                boolean isMetric = position != 1;
                String roundKey = isMetric ? "kg_round" : "lb_round";
                float defaultRound = isMetric ? 2.5f : 5f;
                editRound.setText(Util.format(sharedPref.getFloat(roundKey, defaultRound)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        boolean isMetric = sharedPref.getString("unit", "kg").equals("kg");
        spinnerUnit.setSelection(isMetric ? 0 : 1);
        String roundKey = isMetric ? "kg_round" : "lb_round";
        float defaultRound = isMetric ? 2.5f : 5f;
        editRound.setText(Util.format(sharedPref.getFloat(roundKey, defaultRound)));
        editPressWeight.setText(Util.format(sharedPref.getFloat("press_weight", 100)));
        editBenchWeight.setText(Util.format(sharedPref.getFloat("bench_weight", 100)));
        editSquatWeight.setText(Util.format(sharedPref.getFloat("squat_weight", 100)));
        editDeadliftWeight.setText(Util.format(sharedPref.getFloat("deadlift_weight", 100)));
        spinnerVariation.setSelection(sharedPref.getInt("variation", WorkoutProgram.VARIATION_6DAY_SQUAT));
        editWeek.setText(Integer.toString(sharedPref.getInt("week", 1)));
        spinnerDay.setSelection(sharedPref.getInt("day_no", 0));
    }

    private void saveSettings() {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        boolean isMetric = spinnerUnit.getSelectedItemPosition() != 1;
        editor.putString("unit", isMetric ? "kg" : "lb");
        String roundKey = isMetric ? "kg_round" : "lb_round";
        editor.putFloat(roundKey, Float.valueOf(editRound.getText().toString()));
        editor.putFloat("press_weight", Float.valueOf(editPressWeight.getText().toString()));
        editor.putFloat("bench_weight", Float.valueOf(editBenchWeight.getText().toString()));
        editor.putFloat("squat_weight", Float.valueOf(editSquatWeight.getText().toString()));
        editor.putFloat("deadlift_weight", Float.valueOf(editDeadliftWeight.getText().toString()));
        editor.putInt("week", Integer.valueOf(editWeek.getText().toString()));

        int variation = spinnerVariation.getSelectedItemPosition();
        editor.putInt("variation", variation);

        int dayNo = spinnerDay.getSelectedItemPosition();
        // Sanitize day.
        switch (variation) {
            case WorkoutProgram.VARIATION_4DAY:
                if (dayNo == WorkoutProgram.WEDNESDAY) {
                    // 4 day program does not have saturday.
                    dayNo = WorkoutProgram.THURSDAY;
                }
            case WorkoutProgram.VARIATION_5DAY:
                if (dayNo > WorkoutProgram.FRIDAY) {
                    dayNo = WorkoutProgram.MONDAY;
                }
        }
        editor.putInt("day_no", dayNo);
        editor.commit();
    }

    public void onSave(View view) {
        saveSettings();
        finish();
    }
}
