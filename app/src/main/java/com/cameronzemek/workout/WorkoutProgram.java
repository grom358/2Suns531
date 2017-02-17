package com.cameronzemek.workout;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds the workout.
 */
public class WorkoutProgram {
    private static final int MONDAY = 0;
    private static final int TUESDAY = 1;
    private static final int WEDNESDAY = 2;
    private static final int THURSDAY = 3;
    private static final int FRIDAY = 4;
    private static final int SATURDAY = 5;

    private static final float KG_PLATE_ROUND = 2.5f;
    private static final float POUND_PLATE_ROUND = 5.0f;

    public static List<Exercise> build(SharedPreferences sharedPref) {
        List<Exercise> workout = new ArrayList<>();
        int week = sharedPref.getInt("week", 1) - 1;
        int day = sharedPref.getInt("day_no", MONDAY);

        float squatTm = sharedPref.getFloat("squat_weight", 100);
        float benchTm = sharedPref.getFloat("bench_weight", 100);
        float deadliftTm = sharedPref.getFloat("deadlift_weight", 100);
        float pressTm = sharedPref.getFloat("press_weight", 100);
        String unit = sharedPref.getString("unit", "kg");
        
        float plateRound = unit.equals("kg") ? KG_PLATE_ROUND : POUND_PLATE_ROUND;

        Exercise bench, ohp, squat, sumoDead, inclineBench, deadlift, frontSquat, cgBench, assistance;

        switch (day) {
            case MONDAY:
                bench = new Exercise("Bench");
                bench.getSets().add(new ExerciseSet(8, Util.round(benchTm * 0.65f, plateRound), unit));
                bench.getSets().add(new ExerciseSet(6, Util.round(benchTm * 0.75f, plateRound), unit));
                bench.getSets().add(new ExerciseSet(4, Util.round(benchTm * 0.85f, plateRound), unit));
                bench.getSets().add(new ExerciseSet(4, Util.round(benchTm * 0.85f, plateRound), unit));
                bench.getSets().add(new ExerciseSet(4, Util.round(benchTm * 0.85f, plateRound), unit));
                bench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.8f, plateRound), unit));
                bench.getSets().add(new ExerciseSet(6, Util.round(benchTm * 0.75f, plateRound), unit));
                bench.getSets().add(new ExerciseSet(7, Util.round(benchTm * 0.7f, plateRound), unit));
                bench.getSets().add(new ExerciseSet(8, Util.round(benchTm * 0.65f, plateRound), unit, true));
                workout.add(bench);
                ohp = new Exercise("OHP");
                ohp.getSets().add(new ExerciseSet(6, Util.round(pressTm * 0.5f, plateRound), unit));
                ohp.getSets().add(new ExerciseSet(5, Util.round(pressTm * 0.6f, plateRound), unit));
                ohp.getSets().add(new ExerciseSet(3, Util.round(pressTm * 0.7f, plateRound), unit));
                ohp.getSets().add(new ExerciseSet(5, Util.round(pressTm * 0.7f, plateRound), unit));
                ohp.getSets().add(new ExerciseSet(7, Util.round(pressTm * 0.7f, plateRound), unit));
                ohp.getSets().add(new ExerciseSet(4, Util.round(pressTm * 0.7f, plateRound), unit));
                ohp.getSets().add(new ExerciseSet(6, Util.round(pressTm * 0.7f, plateRound), unit));
                ohp.getSets().add(new ExerciseSet(8, Util.round(pressTm * 0.7f, plateRound), unit));
                workout.add(ohp);
                assistance = new Exercise("Assistance");
                assistance.getSets().add(new AssistanceSet("Chest, Arms, Back"));
                workout.add(assistance);
                break;
            case TUESDAY:
                squat = new Exercise("Squat");
                squat.getSets().add(new ExerciseSet(5, Util.round(squatTm * 0.75f, plateRound), unit));
                squat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.85f, plateRound), unit));
                squat.getSets().add(new ExerciseSet(1, Util.round(squatTm * 0.95f, plateRound), unit, true));
                squat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.9f, plateRound), unit));
                squat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.85f, plateRound), unit));
                squat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.8f, plateRound), unit));
                squat.getSets().add(new ExerciseSet(5, Util.round(squatTm * 0.75f, plateRound), unit));
                squat.getSets().add(new ExerciseSet(5, Util.round(squatTm * 0.7f, plateRound), unit));
                squat.getSets().add(new ExerciseSet(5, Util.round(squatTm * 0.65f, plateRound), unit, true));
                workout.add(squat);
                sumoDead = new Exercise("Sumo Dead");
                sumoDead.getSets().add(new ExerciseSet(5, Util.round(deadliftTm * 0.5f, plateRound), unit));
                sumoDead.getSets().add(new ExerciseSet(5, Util.round(deadliftTm * 0.6f, plateRound), unit));
                sumoDead.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.7f, plateRound), unit));
                sumoDead.getSets().add(new ExerciseSet(5, Util.round(deadliftTm * 0.7f, plateRound), unit));
                sumoDead.getSets().add(new ExerciseSet(7, Util.round(deadliftTm * 0.7f, plateRound), unit));
                sumoDead.getSets().add(new ExerciseSet(4, Util.round(deadliftTm * 0.7f, plateRound), unit));
                sumoDead.getSets().add(new ExerciseSet(6, Util.round(deadliftTm * 0.7f, plateRound), unit));
                sumoDead.getSets().add(new ExerciseSet(8, Util.round(deadliftTm * 0.7f, plateRound), unit));
                workout.add(sumoDead);
                assistance = new Exercise("Assistance");
                assistance.getSets().add(new AssistanceSet("Legs, Abs"));
                workout.add(assistance);
                break;
            case WEDNESDAY:
                ohp = new Exercise("OHP");
                ohp.getSets().add(new ExerciseSet(5, Util.round(pressTm * 0.75f, plateRound), unit));
                ohp.getSets().add(new ExerciseSet(3, Util.round(pressTm * 0.85f, plateRound), unit));
                ohp.getSets().add(new ExerciseSet(1, Util.round(pressTm * 0.95f, plateRound), unit, true));
                ohp.getSets().add(new ExerciseSet(3, Util.round(pressTm * 0.9f, plateRound), unit));
                ohp.getSets().add(new ExerciseSet(3, Util.round(pressTm * 0.85f, plateRound), unit));
                ohp.getSets().add(new ExerciseSet(5, Util.round(pressTm * 0.8f, plateRound), unit));
                ohp.getSets().add(new ExerciseSet(5, Util.round(pressTm * 0.75f, plateRound), unit));
                ohp.getSets().add(new ExerciseSet(5, Util.round(pressTm * 0.7f, plateRound), unit));
                ohp.getSets().add(new ExerciseSet(5, Util.round(pressTm * 0.65f, plateRound), unit, true));
                workout.add(ohp);
                inclineBench = new Exercise("Incline Bench");
                inclineBench.getSets().add(new ExerciseSet(6, Util.round(benchTm * 0.4f, plateRound), unit));
                inclineBench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.5f, plateRound), unit));
                inclineBench.getSets().add(new ExerciseSet(3, Util.round(benchTm * 0.6f, plateRound), unit));
                inclineBench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.6f, plateRound), unit));
                inclineBench.getSets().add(new ExerciseSet(7, Util.round(benchTm * 0.6f, plateRound), unit));
                inclineBench.getSets().add(new ExerciseSet(4, Util.round(benchTm * 0.6f, plateRound), unit));
                inclineBench.getSets().add(new ExerciseSet(6, Util.round(benchTm * 0.6f, plateRound), unit));
                inclineBench.getSets().add(new ExerciseSet(8, Util.round(benchTm * 0.6f, plateRound), unit));
                workout.add(inclineBench);
                assistance = new Exercise("Assistance");
                assistance.getSets().add(new AssistanceSet("Shoulders, Chest"));
                workout.add(assistance);
                break;
            case THURSDAY:
                deadlift = new Exercise("Deadlift");
                deadlift.getSets().add(new ExerciseSet(5, Util.round(deadliftTm * 0.75f, plateRound), unit));
                deadlift.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.85f, plateRound), unit));
                deadlift.getSets().add(new ExerciseSet(1, Util.round(deadliftTm * 0.95f, plateRound), unit, true));
                deadlift.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.9f, plateRound), unit));
                deadlift.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.85f, plateRound), unit));
                deadlift.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.8f, plateRound), unit));
                deadlift.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.75f, plateRound), unit));
                deadlift.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.7f, plateRound), unit));
                deadlift.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.65f, plateRound), unit, true));
                workout.add(deadlift);
                frontSquat = new Exercise("Front Squat");
                frontSquat.getSets().add(new ExerciseSet(5, Util.round(squatTm * 0.35f, plateRound), unit));
                frontSquat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.45f, plateRound), unit));
                frontSquat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.55f, plateRound), unit));
                frontSquat.getSets().add(new ExerciseSet(5, Util.round(squatTm * 0.55f, plateRound), unit));
                frontSquat.getSets().add(new ExerciseSet(7, Util.round(squatTm * 0.55f, plateRound), unit));
                frontSquat.getSets().add(new ExerciseSet(4, Util.round(squatTm * 0.55f, plateRound), unit));
                frontSquat.getSets().add(new ExerciseSet(6, Util.round(squatTm * 0.55f, plateRound), unit));
                frontSquat.getSets().add(new ExerciseSet(8, Util.round(squatTm * 0.55f, plateRound), unit));
                workout.add(frontSquat);
                assistance = new Exercise("Assistance");
                assistance.getSets().add(new AssistanceSet("Back, Abs"));
                workout.add(assistance);
                break;
            case FRIDAY:
                bench = new Exercise("Bench");
                bench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.75f, plateRound), unit));
                bench.getSets().add(new ExerciseSet(3, Util.round(benchTm * 0.85f, plateRound), unit));
                bench.getSets().add(new ExerciseSet(1, Util.round(benchTm * 0.95f, plateRound), unit, true));
                bench.getSets().add(new ExerciseSet(3, Util.round(benchTm * 0.9f, plateRound), unit));
                bench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.85f, plateRound), unit));
                bench.getSets().add(new ExerciseSet(3, Util.round(benchTm * 0.8f, plateRound), unit));
                bench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.75f, plateRound), unit));
                bench.getSets().add(new ExerciseSet(3, Util.round(benchTm * 0.7f, plateRound), unit));
                bench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.65f, plateRound), unit, true));
                workout.add(bench);
                cgBench = new Exercise("C.G. Bench");
                cgBench.getSets().add(new ExerciseSet(6, Util.round(benchTm * 0.4f, plateRound), unit));
                cgBench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.5f, plateRound), unit));
                cgBench.getSets().add(new ExerciseSet(3, Util.round(benchTm * 0.6f, plateRound), unit));
                cgBench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.6f, plateRound), unit));
                cgBench.getSets().add(new ExerciseSet(7, Util.round(benchTm * 0.6f, plateRound), unit));
                cgBench.getSets().add(new ExerciseSet(4, Util.round(benchTm * 0.6f, plateRound), unit));
                cgBench.getSets().add(new ExerciseSet(6, Util.round(benchTm * 0.6f, plateRound), unit));
                cgBench.getSets().add(new ExerciseSet(8, Util.round(benchTm * 0.6f, plateRound), unit));
                workout.add(cgBench);
                assistance = new Exercise("Assistance");
                assistance.getSets().add(new AssistanceSet("Arms, Other"));
                workout.add(assistance);
                break;
            case SATURDAY:
                squat = new Exercise("Squat");
                for (int i = 0; i < 8; i++) {
                    squat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.725f, plateRound), unit));
                }
                workout.add(squat);
                sumoDead = new Exercise("Sumo Dead");
                for (int i = 0; i < 6; i++) {
                    sumoDead.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.75f * 0.75f, plateRound), unit));
                }
                workout.add(sumoDead);
                assistance = new Exercise("Assistance");
                assistance.getSets().add(new AssistanceSet("Upper Back, Legs"));
                workout.add(assistance);
                break;
        }
        return workout;
    }

    public static void complete(Context ctx, SharedPreferences sharedPref, List<Exercise> workout) {
        int weekIndex = sharedPref.getInt("week", 1);
        int dayNo = sharedPref.getInt("day_no", MONDAY);
        // Increment to next workout.
        dayNo++;
        if (dayNo > SATURDAY) {
            dayNo = MONDAY;
            weekIndex++;
        }
        // Update settings.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("week", weekIndex);
        editor.putInt("day_no", dayNo);
        editor.commit();

        // Log to database.
        WorkoutHistoryDbHelper dbHelper = new WorkoutHistoryDbHelper(ctx);
        dbHelper.insert(workout);

        Toast toast = Toast.makeText(ctx, "Workout complete!", Toast.LENGTH_LONG);
        toast.show();
    }
}
