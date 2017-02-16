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

    public static List<Exercise> build(SharedPreferences sharedPref) {
        List<Exercise> workout = new ArrayList<>();
        int week = sharedPref.getInt("week", 1) - 1;
        int day = sharedPref.getInt("day_no", MONDAY);

        float squatTm = sharedPref.getFloat("squat_weight", 100);
        float benchTm = sharedPref.getFloat("bench_weight", 100);
        float deadliftTm = sharedPref.getFloat("deadlift_weight", 100);
        float pressTm = sharedPref.getFloat("press_weight", 100);

        Exercise bench, ohp, squat, sumoDead, inclineBench, deadlift, frontSquat, cgBench, assistance;

        final float PLATE_ROUND = 2.5f;

        switch (day) {
            case MONDAY:
                bench = new Exercise("Bench");
                bench.getSets().add(new ExerciseSet(8, Util.round(benchTm * 0.65f, PLATE_ROUND)));
                bench.getSets().add(new ExerciseSet(6, Util.round(benchTm * 0.75f, PLATE_ROUND)));
                bench.getSets().add(new ExerciseSet(4, Util.round(benchTm * 0.85f, PLATE_ROUND)));
                bench.getSets().add(new ExerciseSet(4, Util.round(benchTm * 0.85f, PLATE_ROUND)));
                bench.getSets().add(new ExerciseSet(4, Util.round(benchTm * 0.85f, PLATE_ROUND)));
                bench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.8f, PLATE_ROUND)));
                bench.getSets().add(new ExerciseSet(6, Util.round(benchTm * 0.75f, PLATE_ROUND)));
                bench.getSets().add(new ExerciseSet(7, Util.round(benchTm * 0.7f, PLATE_ROUND)));
                bench.getSets().add(new ExerciseSet(8, Util.round(benchTm * 0.65f, PLATE_ROUND), true));
                workout.add(bench);
                ohp = new Exercise("OHP");
                ohp.getSets().add(new ExerciseSet(6, Util.round(pressTm * 0.5f, PLATE_ROUND)));
                ohp.getSets().add(new ExerciseSet(5, Util.round(pressTm * 0.6f, PLATE_ROUND)));
                ohp.getSets().add(new ExerciseSet(3, Util.round(pressTm * 0.7f, PLATE_ROUND)));
                ohp.getSets().add(new ExerciseSet(5, Util.round(pressTm * 0.7f, PLATE_ROUND)));
                ohp.getSets().add(new ExerciseSet(7, Util.round(pressTm * 0.7f, PLATE_ROUND)));
                ohp.getSets().add(new ExerciseSet(4, Util.round(pressTm * 0.7f, PLATE_ROUND)));
                ohp.getSets().add(new ExerciseSet(6, Util.round(pressTm * 0.7f, PLATE_ROUND)));
                ohp.getSets().add(new ExerciseSet(8, Util.round(pressTm * 0.7f, PLATE_ROUND)));
                workout.add(ohp);
                assistance = new Exercise("Assistance");
                assistance.getSets().add(new AssistanceSet("Chest, Arms, Back"));
                workout.add(assistance);
                break;
            case TUESDAY:
                squat = new Exercise("Squat");
                squat.getSets().add(new ExerciseSet(5, Util.round(squatTm * 0.75f, PLATE_ROUND)));
                squat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.85f, PLATE_ROUND)));
                squat.getSets().add(new ExerciseSet(1, Util.round(squatTm * 0.95f, PLATE_ROUND), true));
                squat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.9f, PLATE_ROUND)));
                squat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.85f, PLATE_ROUND)));
                squat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.8f, PLATE_ROUND)));
                squat.getSets().add(new ExerciseSet(5, Util.round(squatTm * 0.75f, PLATE_ROUND)));
                squat.getSets().add(new ExerciseSet(5, Util.round(squatTm * 0.7f, PLATE_ROUND)));
                squat.getSets().add(new ExerciseSet(5, Util.round(squatTm * 0.65f, PLATE_ROUND), true));
                workout.add(squat);
                sumoDead = new Exercise("Sumo Dead");
                sumoDead.getSets().add(new ExerciseSet(5, Util.round(deadliftTm * 0.5f, PLATE_ROUND)));
                sumoDead.getSets().add(new ExerciseSet(5, Util.round(deadliftTm * 0.6f, PLATE_ROUND)));
                sumoDead.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.7f, PLATE_ROUND)));
                sumoDead.getSets().add(new ExerciseSet(5, Util.round(deadliftTm * 0.7f, PLATE_ROUND)));
                sumoDead.getSets().add(new ExerciseSet(7, Util.round(deadliftTm * 0.7f, PLATE_ROUND)));
                sumoDead.getSets().add(new ExerciseSet(4, Util.round(deadliftTm * 0.7f, PLATE_ROUND)));
                sumoDead.getSets().add(new ExerciseSet(6, Util.round(deadliftTm * 0.7f, PLATE_ROUND)));
                sumoDead.getSets().add(new ExerciseSet(8, Util.round(deadliftTm * 0.7f, PLATE_ROUND)));
                workout.add(sumoDead);
                assistance = new Exercise("Assistance");
                assistance.getSets().add(new AssistanceSet("Legs, Abs"));
                workout.add(assistance);
                break;
            case WEDNESDAY:
                ohp = new Exercise("OHP");
                ohp.getSets().add(new ExerciseSet(5, Util.round(pressTm * 0.75f, PLATE_ROUND)));
                ohp.getSets().add(new ExerciseSet(3, Util.round(pressTm * 0.85f, PLATE_ROUND)));
                ohp.getSets().add(new ExerciseSet(1, Util.round(pressTm * 0.95f, PLATE_ROUND), true));
                ohp.getSets().add(new ExerciseSet(3, Util.round(pressTm * 0.9f, PLATE_ROUND)));
                ohp.getSets().add(new ExerciseSet(3, Util.round(pressTm * 0.85f, PLATE_ROUND)));
                ohp.getSets().add(new ExerciseSet(5, Util.round(pressTm * 0.8f, PLATE_ROUND)));
                ohp.getSets().add(new ExerciseSet(5, Util.round(pressTm * 0.75f, PLATE_ROUND)));
                ohp.getSets().add(new ExerciseSet(5, Util.round(pressTm * 0.7f, PLATE_ROUND)));
                ohp.getSets().add(new ExerciseSet(5, Util.round(pressTm * 0.65f, PLATE_ROUND), true));
                workout.add(ohp);
                inclineBench = new Exercise("Incline Bench");
                inclineBench.getSets().add(new ExerciseSet(6, Util.round(benchTm * 0.4f, PLATE_ROUND)));
                inclineBench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.5f, PLATE_ROUND)));
                inclineBench.getSets().add(new ExerciseSet(3, Util.round(benchTm * 0.6f, PLATE_ROUND)));
                inclineBench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.6f, PLATE_ROUND)));
                inclineBench.getSets().add(new ExerciseSet(7, Util.round(benchTm * 0.6f, PLATE_ROUND)));
                inclineBench.getSets().add(new ExerciseSet(4, Util.round(benchTm * 0.6f, PLATE_ROUND)));
                inclineBench.getSets().add(new ExerciseSet(6, Util.round(benchTm * 0.6f, PLATE_ROUND)));
                inclineBench.getSets().add(new ExerciseSet(8, Util.round(benchTm * 0.6f, PLATE_ROUND)));
                workout.add(inclineBench);
                assistance = new Exercise("Assistance");
                assistance.getSets().add(new AssistanceSet("Shoulders, Chest"));
                workout.add(assistance);
                break;
            case THURSDAY:
                deadlift = new Exercise("Deadlift");
                deadlift.getSets().add(new ExerciseSet(5, Util.round(deadliftTm * 0.75f, PLATE_ROUND)));
                deadlift.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.85f, PLATE_ROUND)));
                deadlift.getSets().add(new ExerciseSet(1, Util.round(deadliftTm * 0.95f, PLATE_ROUND), true));
                deadlift.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.9f, PLATE_ROUND)));
                deadlift.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.85f, PLATE_ROUND)));
                deadlift.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.8f, PLATE_ROUND)));
                deadlift.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.75f, PLATE_ROUND)));
                deadlift.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.7f, PLATE_ROUND)));
                deadlift.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.65f, PLATE_ROUND), true));
                workout.add(deadlift);
                frontSquat = new Exercise("Front Squat");
                frontSquat.getSets().add(new ExerciseSet(5, Util.round(squatTm * 0.35f, PLATE_ROUND)));
                frontSquat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.45f, PLATE_ROUND)));
                frontSquat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.55f, PLATE_ROUND)));
                frontSquat.getSets().add(new ExerciseSet(5, Util.round(squatTm * 0.55f, PLATE_ROUND)));
                frontSquat.getSets().add(new ExerciseSet(7, Util.round(squatTm * 0.55f, PLATE_ROUND)));
                frontSquat.getSets().add(new ExerciseSet(4, Util.round(squatTm * 0.55f, PLATE_ROUND)));
                frontSquat.getSets().add(new ExerciseSet(6, Util.round(squatTm * 0.55f, PLATE_ROUND)));
                frontSquat.getSets().add(new ExerciseSet(8, Util.round(squatTm * 0.55f, PLATE_ROUND)));
                workout.add(frontSquat);
                assistance = new Exercise("Assistance");
                assistance.getSets().add(new AssistanceSet("Back, Abs"));
                workout.add(assistance);
                break;
            case FRIDAY:
                bench = new Exercise("Bench");
                bench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.75f, PLATE_ROUND)));
                bench.getSets().add(new ExerciseSet(3, Util.round(benchTm * 0.85f, PLATE_ROUND)));
                bench.getSets().add(new ExerciseSet(1, Util.round(benchTm * 0.95f, PLATE_ROUND), true));
                bench.getSets().add(new ExerciseSet(3, Util.round(benchTm * 0.9f, PLATE_ROUND)));
                bench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.85f, PLATE_ROUND)));
                bench.getSets().add(new ExerciseSet(3, Util.round(benchTm * 0.8f, PLATE_ROUND)));
                bench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.75f, PLATE_ROUND)));
                bench.getSets().add(new ExerciseSet(3, Util.round(benchTm * 0.7f, PLATE_ROUND)));
                bench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.65f, PLATE_ROUND), true));
                workout.add(bench);
                cgBench = new Exercise("C.G. Bench");
                cgBench.getSets().add(new ExerciseSet(6, Util.round(benchTm * 0.4f, PLATE_ROUND)));
                cgBench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.5f, PLATE_ROUND)));
                cgBench.getSets().add(new ExerciseSet(3, Util.round(benchTm * 0.6f, PLATE_ROUND)));
                cgBench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.6f, PLATE_ROUND)));
                cgBench.getSets().add(new ExerciseSet(7, Util.round(benchTm * 0.6f, PLATE_ROUND)));
                cgBench.getSets().add(new ExerciseSet(4, Util.round(benchTm * 0.6f, PLATE_ROUND)));
                cgBench.getSets().add(new ExerciseSet(6, Util.round(benchTm * 0.6f, PLATE_ROUND)));
                cgBench.getSets().add(new ExerciseSet(8, Util.round(benchTm * 0.6f, PLATE_ROUND)));
                workout.add(cgBench);
                assistance = new Exercise("Assistance");
                assistance.getSets().add(new AssistanceSet("Arms, Other"));
                workout.add(assistance);
                break;
            case SATURDAY:
                squat = new Exercise("Squat");
                for (int i = 0; i < 8; i++) {
                    squat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.725f, PLATE_ROUND)));
                }
                workout.add(squat);
                sumoDead = new Exercise("Sumo Dead");
                for (int i = 0; i < 6; i++) {
                    sumoDead.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.75f * 0.75f, PLATE_ROUND)));
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
