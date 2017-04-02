package com.cameronzemek.workout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

public class SetupActivity extends AppCompatActivity {
    private EditText editRound;
    private EditText editBarWeight;
    private EditText editPressWeight;
    private EditText editBenchWeight;
    private EditText editSquatWeight;
    private EditText editDeadliftWeight;
    private EditText editWeek;
    private EditText[] editPlateWeights = new EditText[PlateCalculator.PLATE_LENGTH];
    private EditText[] editPlateCounts = new EditText[PlateCalculator.PLATE_LENGTH];
    private Spinner spinnerDay;
    private Spinner spinnerUnit;
    private Spinner spinnerVariation;
    private SeekBar seekBarVolume;
    private Switch switchCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        final SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        editRound = (EditText) findViewById(R.id.editRound);
        editBarWeight = (EditText) findViewById(R.id.editBarWeight);
        editPressWeight = (EditText) findViewById(R.id.editPressWeight);
        editBenchWeight = (EditText) findViewById(R.id.editBenchWeight);
        editSquatWeight = (EditText) findViewById(R.id.editSquatWeight);
        editDeadliftWeight = (EditText) findViewById(R.id.editDeadliftWeight);
        editWeek = (EditText) findViewById(R.id.editWeek);
        switchCalculator = (Switch) findViewById(R.id.switchCalculator);

        // TODO: programmatically setup plate calculator inputs.
        editPlateWeights[0] = (EditText) findViewById(R.id.editPlate1Weight);
        editPlateWeights[1] = (EditText) findViewById(R.id.editPlate2Weight);
        editPlateWeights[2] = (EditText) findViewById(R.id.editPlate3Weight);
        editPlateWeights[3] = (EditText) findViewById(R.id.editPlate4Weight);
        editPlateWeights[4] = (EditText) findViewById(R.id.editPlate5Weight);
        editPlateWeights[5] = (EditText) findViewById(R.id.editPlate6Weight);
        editPlateWeights[6] = (EditText) findViewById(R.id.editPlate7Weight);
        editPlateWeights[7] = (EditText) findViewById(R.id.editPlate8Weight);
        editPlateWeights[8] = (EditText) findViewById(R.id.editPlate9Weight);
        editPlateWeights[9] = (EditText) findViewById(R.id.editPlate10Weight);
        editPlateWeights[10] = (EditText) findViewById(R.id.editPlate11Weight);
        editPlateWeights[11] = (EditText) findViewById(R.id.editPlate12Weight);
        editPlateWeights[12] = (EditText) findViewById(R.id.editPlate13Weight);
        editPlateWeights[13] = (EditText) findViewById(R.id.editPlate14Weight);
        editPlateWeights[14] = (EditText) findViewById(R.id.editPlate15Weight);
        editPlateWeights[15] = (EditText) findViewById(R.id.editPlate16Weight);
        editPlateWeights[16] = (EditText) findViewById(R.id.editPlate17Weight);
        editPlateWeights[17] = (EditText) findViewById(R.id.editPlate18Weight);

        editPlateCounts[0] = (EditText) findViewById(R.id.editPlate1Count);
        editPlateCounts[1] = (EditText) findViewById(R.id.editPlate2Count);
        editPlateCounts[2] = (EditText) findViewById(R.id.editPlate3Count);
        editPlateCounts[3] = (EditText) findViewById(R.id.editPlate4Count);
        editPlateCounts[4] = (EditText) findViewById(R.id.editPlate5Count);
        editPlateCounts[5] = (EditText) findViewById(R.id.editPlate6Count);
        editPlateCounts[6] = (EditText) findViewById(R.id.editPlate7Count);
        editPlateCounts[7] = (EditText) findViewById(R.id.editPlate8Count);
        editPlateCounts[8] = (EditText) findViewById(R.id.editPlate9Count);
        editPlateCounts[9] = (EditText) findViewById(R.id.editPlate10Count);
        editPlateCounts[10] = (EditText) findViewById(R.id.editPlate11Count);
        editPlateCounts[11] = (EditText) findViewById(R.id.editPlate12Count);
        editPlateCounts[12] = (EditText) findViewById(R.id.editPlate13Count);
        editPlateCounts[13] = (EditText) findViewById(R.id.editPlate14Count);
        editPlateCounts[14] = (EditText) findViewById(R.id.editPlate15Count);
        editPlateCounts[15] = (EditText) findViewById(R.id.editPlate16Count);
        editPlateCounts[16] = (EditText) findViewById(R.id.editPlate17Count);
        editPlateCounts[17] = (EditText) findViewById(R.id.editPlate18Count);

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
                init(sharedPref, isMetric);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        seekBarVolume = (SeekBar) findViewById(R.id.seekBarVolume);

        String unit = sharedPref.getString("unit", "kg");
        boolean isMetric = unit.equals("kg");
        init(sharedPref, isMetric);
        spinnerUnit.setSelection(isMetric ? 0 : 1);
        editPressWeight.setText(Util.format(sharedPref.getFloat("press_weight", 100)));
        editBenchWeight.setText(Util.format(sharedPref.getFloat("bench_weight", 100)));
        editSquatWeight.setText(Util.format(sharedPref.getFloat("squat_weight", 100)));
        editDeadliftWeight.setText(Util.format(sharedPref.getFloat("deadlift_weight", 100)));
        spinnerVariation.setSelection(sharedPref.getInt("variation", WorkoutProgram.VARIATION_6DAY_SQUAT));
        editWeek.setText(Integer.toString(sharedPref.getInt("week", 1)));
        spinnerDay.setSelection(sharedPref.getInt("day_no", 0));
        seekBarVolume.setProgress(sharedPref.getInt("volume", 100));
        switchCalculator.setChecked(sharedPref.getBoolean("plate_calculator", true));
    }

    private void init(final SharedPreferences sharedPref, boolean isMetric) {
        String unit = isMetric ? "kg" : "lb";
        float defaultRound = isMetric ? 2.5f : 5f;
        float defaultBarWeight = isMetric ? 20f : 45f;
        editRound.setText(Util.format(sharedPref.getFloat(unit + "_round", defaultRound)));
        editBarWeight.setText(Util.format(sharedPref.getFloat(unit + "_bar_weight", defaultBarWeight)));

        int[] defaultPlateCounts = isMetric ? PlateCalculator.DEFAULT_KG_PLATE_COUNTS : PlateCalculator.DEFAULT_LB_PLATE_COUNTS;
        for (int i = 0; i < PlateCalculator.PLATE_LENGTH; i++) {
            EditText editPlateWeight = editPlateWeights[i];
            editPlateWeight.setText(Util.format(sharedPref.getFloat(unit + "_plate_weight_" + i, PlateCalculator.DEFAULT_PLATE_WEIGHTS[i])));

            EditText editPlateCount = editPlateCounts[i];
            editPlateCount.setText(String.valueOf(sharedPref.getInt(unit + "_plate_count_" + i, defaultPlateCounts[i])));
        }
    }

    private void saveSettings() {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        boolean isMetric = spinnerUnit.getSelectedItemPosition() != 1;
        String unit = isMetric ? "kg" : "lb";
        editor.putString("unit", unit);
        editor.putFloat(unit + "_round", Util.parseFloat(editRound.getText().toString()));
        editor.putFloat(unit + "_bar_weight", Util.parseFloat(editBarWeight.getText().toString()));
        editor.putFloat("press_weight", Util.parseFloat(editPressWeight.getText().toString()));
        editor.putFloat("bench_weight", Util.parseFloat(editBenchWeight.getText().toString()));
        editor.putFloat("squat_weight", Util.parseFloat(editSquatWeight.getText().toString()));
        editor.putFloat("deadlift_weight", Util.parseFloat(editDeadliftWeight.getText().toString()));
        editor.putInt("week", Integer.valueOf(editWeek.getText().toString()));
        editor.putInt("volume", seekBarVolume.getProgress());
        editor.putBoolean("plate_calculator", switchCalculator.isChecked());

        for (int i = 0; i < PlateCalculator.PLATE_LENGTH; i++) {
            EditText editPlateWeight = editPlateWeights[i];
            editor.putFloat(unit + "_plate_weight_" + i, Util.parseFloat(editPlateWeight.getText().toString()));

            EditText editPlateCount = editPlateCounts[i];
            editor.putInt(unit + "_plate_count_" + i, Util.parseInt(editPlateCount.getText().toString()));
        }

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
