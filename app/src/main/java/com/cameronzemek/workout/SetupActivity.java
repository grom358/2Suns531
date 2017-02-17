package com.cameronzemek.workout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class SetupActivity extends AppCompatActivity {
    private EditText editPressWeight;
    private EditText editBenchWeight;
    private EditText editSquatWeight;
    private EditText editDeadliftWeight;
    private EditText editWeek;
    private Spinner spinnerDay;
    private Spinner spinnerUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        editPressWeight = (EditText) findViewById(R.id.editPressWeight);
        editBenchWeight = (EditText) findViewById(R.id.editBenchWeight);
        editSquatWeight = (EditText) findViewById(R.id.editSquatWeight);
        editDeadliftWeight = (EditText) findViewById(R.id.editDeadliftWeight);
        editWeek = (EditText) findViewById(R.id.editWeek);

        spinnerDay = (Spinner) findViewById(R.id.spinnerDay);
        spinnerDay.setAdapter(ArrayAdapter.createFromResource(this, R.array.day, R.layout.spinner_item));

        spinnerUnit = (Spinner) findViewById(R.id.spinnerUnit);
        spinnerUnit.setAdapter(ArrayAdapter.createFromResource(this, R.array.units, R.layout.spinner_item));

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        spinnerUnit.setSelection(sharedPref.getString("unit", "kg").equals("kg") ? 0 : 1);
        editPressWeight.setText(String.format("%.2f", sharedPref.getFloat("press_weight", 100)));
        editBenchWeight.setText(String.format("%.2f", sharedPref.getFloat("bench_weight", 100)));
        editSquatWeight.setText(String.format("%.2f", sharedPref.getFloat("squat_weight", 100)));
        editDeadliftWeight.setText(String.format("%.2f", sharedPref.getFloat("deadlift_weight", 100)));
        editWeek.setText(Integer.toString(sharedPref.getInt("week", 1)));
        spinnerDay.setSelection(sharedPref.getInt("day_no", 0));
    }

    private void saveSettings() {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("unit", spinnerUnit.getSelectedItemPosition() == 1 ? "lb" : "kg");
        editor.putFloat("press_weight", Float.valueOf(editPressWeight.getText().toString()));
        editor.putFloat("bench_weight", Float.valueOf(editBenchWeight.getText().toString()));
        editor.putFloat("squat_weight", Float.valueOf(editSquatWeight.getText().toString()));
        editor.putFloat("deadlift_weight", Float.valueOf(editDeadliftWeight.getText().toString()));
        editor.putInt("week", Integer.valueOf(editWeek.getText().toString()));
        editor.putInt("day_no", spinnerDay.getSelectedItemPosition());
        editor.commit();
    }

    public void onSave(View view) {
        saveSettings();
        finish();
    }
}
