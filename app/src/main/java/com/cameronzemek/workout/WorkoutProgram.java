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
    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;
    public static final int SATURDAY = 5;

    public static final int VARIATION_4DAY = 0;
    public static final int VARIATION_5DAY = 1;
    public static final int VARIATION_6DAY_SQUAT = 2;
    public static final int VARIATION_6DAY_DEADLIFT = 3;

    private static final float KG_PLATE_ROUND = 2.5f;
    private static final float POUND_PLATE_ROUND = 5.0f;

    private static List<Exercise> buildBenchPress(SharedPreferences sharedPref, float plateRound, String unit) {
        List<Exercise> workout = new ArrayList<>();

        float benchTm = sharedPref.getFloat("bench_weight", 100);
        float pressTm = sharedPref.getFloat("press_weight", 100);

        Exercise bench = new Exercise("Bench");
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
        Exercise ohp = new Exercise("OHP");
        ohp.getSets().add(new ExerciseSet(6, Util.round(pressTm * 0.5f, plateRound), unit));
        ohp.getSets().add(new ExerciseSet(5, Util.round(pressTm * 0.6f, plateRound), unit));
        ohp.getSets().add(new ExerciseSet(3, Util.round(pressTm * 0.7f, plateRound), unit));
        ohp.getSets().add(new ExerciseSet(5, Util.round(pressTm * 0.7f, plateRound), unit));
        ohp.getSets().add(new ExerciseSet(7, Util.round(pressTm * 0.7f, plateRound), unit));
        ohp.getSets().add(new ExerciseSet(4, Util.round(pressTm * 0.7f, plateRound), unit));
        ohp.getSets().add(new ExerciseSet(6, Util.round(pressTm * 0.7f, plateRound), unit));
        ohp.getSets().add(new ExerciseSet(8, Util.round(pressTm * 0.7f, plateRound), unit));
        workout.add(ohp);
        Exercise assistance = new Exercise("Assistance");
        assistance.getSets().add(new AssistanceSet("Chest, Arms, Back"));
        workout.add(assistance);

        return workout;
    }

    private static List<Exercise> buildSquat(SharedPreferences sharedPref, float plateRound, String unit) {
        List<Exercise> workout = new ArrayList<>();

        float squatTm = sharedPref.getFloat("squat_weight", 100);
        float deadliftTm = sharedPref.getFloat("deadlift_weight", 100);

        Exercise squat = new Exercise("Squat");
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
        Exercise sumoDead = new Exercise("Sumo Dead");
        sumoDead.getSets().add(new ExerciseSet(5, Util.round(deadliftTm * 0.5f, plateRound), unit));
        sumoDead.getSets().add(new ExerciseSet(5, Util.round(deadliftTm * 0.6f, plateRound), unit));
        sumoDead.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.7f, plateRound), unit));
        sumoDead.getSets().add(new ExerciseSet(5, Util.round(deadliftTm * 0.7f, plateRound), unit));
        sumoDead.getSets().add(new ExerciseSet(7, Util.round(deadliftTm * 0.7f, plateRound), unit));
        sumoDead.getSets().add(new ExerciseSet(4, Util.round(deadliftTm * 0.7f, plateRound), unit));
        sumoDead.getSets().add(new ExerciseSet(6, Util.round(deadliftTm * 0.7f, plateRound), unit));
        sumoDead.getSets().add(new ExerciseSet(8, Util.round(deadliftTm * 0.7f, plateRound), unit));
        workout.add(sumoDead);
        Exercise assistance = new Exercise("Assistance");
        assistance.getSets().add(new AssistanceSet("Legs, Abs"));
        workout.add(assistance);

        return workout;
    }

    private static List<Exercise> buildPress(SharedPreferences sharedPref, float plateRound, String unit) {
        List<Exercise> workout = new ArrayList<>();

        float benchTm = sharedPref.getFloat("bench_weight", 100);
        float pressTm = sharedPref.getFloat("press_weight", 100);

        Exercise ohp = new Exercise("OHP");
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
        Exercise inclineBench = new Exercise("Incline Bench");
        inclineBench.getSets().add(new ExerciseSet(6, Util.round(benchTm * 0.4f, plateRound), unit));
        inclineBench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.5f, plateRound), unit));
        inclineBench.getSets().add(new ExerciseSet(3, Util.round(benchTm * 0.6f, plateRound), unit));
        inclineBench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.6f, plateRound), unit));
        inclineBench.getSets().add(new ExerciseSet(7, Util.round(benchTm * 0.6f, plateRound), unit));
        inclineBench.getSets().add(new ExerciseSet(4, Util.round(benchTm * 0.6f, plateRound), unit));
        inclineBench.getSets().add(new ExerciseSet(6, Util.round(benchTm * 0.6f, plateRound), unit));
        inclineBench.getSets().add(new ExerciseSet(8, Util.round(benchTm * 0.6f, plateRound), unit));
        workout.add(inclineBench);
        Exercise assistance = new Exercise("Assistance");
        assistance.getSets().add(new AssistanceSet("Shoulders, Chest"));
        workout.add(assistance);

        return workout;
    }

    private static List<Exercise> buildDeadlift(SharedPreferences sharedPref, float plateRound, String unit) {
        List<Exercise> workout = new ArrayList<>();

        float squatTm = sharedPref.getFloat("squat_weight", 100);
        float deadliftTm = sharedPref.getFloat("deadlift_weight", 100);

        Exercise deadlift = new Exercise("Deadlift");
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
        Exercise frontSquat = new Exercise("Front Squat");
        frontSquat.getSets().add(new ExerciseSet(5, Util.round(squatTm * 0.35f, plateRound), unit));
        frontSquat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.45f, plateRound), unit));
        frontSquat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.55f, plateRound), unit));
        frontSquat.getSets().add(new ExerciseSet(5, Util.round(squatTm * 0.55f, plateRound), unit));
        frontSquat.getSets().add(new ExerciseSet(7, Util.round(squatTm * 0.55f, plateRound), unit));
        frontSquat.getSets().add(new ExerciseSet(4, Util.round(squatTm * 0.55f, plateRound), unit));
        frontSquat.getSets().add(new ExerciseSet(6, Util.round(squatTm * 0.55f, plateRound), unit));
        frontSquat.getSets().add(new ExerciseSet(8, Util.round(squatTm * 0.55f, plateRound), unit));
        workout.add(frontSquat);
        Exercise assistance = new Exercise("Assistance");
        assistance.getSets().add(new AssistanceSet("Back, Abs"));
        workout.add(assistance);

        return workout;
    }

    private static List<Exercise> buildBench(SharedPreferences sharedPref, float plateRound, String unit) {
        List<Exercise> workout = new ArrayList<>();

        float benchTm = sharedPref.getFloat("bench_weight", 100);

        Exercise bench = new Exercise("Bench");
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
        Exercise cgBench = new Exercise("C.G. Bench");
        cgBench.getSets().add(new ExerciseSet(6, Util.round(benchTm * 0.4f, plateRound), unit));
        cgBench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.5f, plateRound), unit));
        cgBench.getSets().add(new ExerciseSet(3, Util.round(benchTm * 0.6f, plateRound), unit));
        cgBench.getSets().add(new ExerciseSet(5, Util.round(benchTm * 0.6f, plateRound), unit));
        cgBench.getSets().add(new ExerciseSet(7, Util.round(benchTm * 0.6f, plateRound), unit));
        cgBench.getSets().add(new ExerciseSet(4, Util.round(benchTm * 0.6f, plateRound), unit));
        cgBench.getSets().add(new ExerciseSet(6, Util.round(benchTm * 0.6f, plateRound), unit));
        cgBench.getSets().add(new ExerciseSet(8, Util.round(benchTm * 0.6f, plateRound), unit));
        workout.add(cgBench);
        Exercise assistance = new Exercise("Assistance");
        assistance.getSets().add(new AssistanceSet("Arms, Other"));
        workout.add(assistance);

        return workout;
    }

    private static List<Exercise> buildExtraSquat(SharedPreferences sharedPref, float plateRound, String unit) {
        List<Exercise> workout = new ArrayList<>();

        float squatTm = sharedPref.getFloat("squat_weight", 100);
        float deadliftTm = sharedPref.getFloat("deadlift_weight", 100);

        Exercise squat = new Exercise("Squat");
        for (int i = 0; i < 8; i++) {
            squat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.725f, plateRound), unit));
        }
        workout.add(squat);
        Exercise sumoDead = new Exercise("Sumo Dead");
        for (int i = 0; i < 6; i++) {
            sumoDead.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.75f * 0.75f, plateRound), unit));
        }
        workout.add(sumoDead);
        Exercise assistance = new Exercise("Assistance");
        assistance.getSets().add(new AssistanceSet("Upper Back, Legs"));
        workout.add(assistance);

        return workout;
    }

    private static List<Exercise> buildExtraDeadlift(SharedPreferences sharedPref, float plateRound, String unit) {
        List<Exercise> workout = new ArrayList<>();

        float squatTm = sharedPref.getFloat("squat_weight", 100);
        float deadliftTm = sharedPref.getFloat("deadlift_weight", 100);

        Exercise deadlift = new Exercise("Deadlift");
        for (int i = 0; i < 6; i++) {
            deadlift.getSets().add(new ExerciseSet(3, Util.round(deadliftTm * 0.725f, plateRound), unit));
        }
        workout.add(deadlift);
        Exercise frontSquat = new Exercise("Front Squat");
        for (int i = 0; i < 8; i++) {
            frontSquat.getSets().add(new ExerciseSet(3, Util.round(squatTm * 0.75f * 0.75f, plateRound), unit));
        }
        workout.add(frontSquat);
        Exercise assistance = new Exercise("Assistance");
        assistance.getSets().add(new AssistanceSet("Upper Back, Legs"));
        workout.add(assistance);

        return workout;
    }

    public static List<Exercise> build(SharedPreferences sharedPref) {
        int day = sharedPref.getInt("day_no", MONDAY);
        int variation = sharedPref.getInt("variation", VARIATION_6DAY_SQUAT);

        String unit = sharedPref.getString("unit", "kg");
        boolean isMetric = sharedPref.getString("unit", "kg").equals("kg");
        String roundKey = isMetric ? "kg_round" : "lb_round";
        float defaultRound = isMetric ? KG_PLATE_ROUND : POUND_PLATE_ROUND;
        float plateRound = sharedPref.getFloat(roundKey, defaultRound);

        switch (variation) {
            case VARIATION_4DAY:
                switch (day) {
                    case MONDAY:
                        return buildBenchPress(sharedPref, plateRound, unit);
                    case TUESDAY:
                        return buildSquat(sharedPref, plateRound, unit);
                    case THURSDAY:
                        return buildBench(sharedPref, plateRound, unit);
                    case FRIDAY:
                        return buildDeadlift(sharedPref, plateRound, unit);
                }
                break;
            case VARIATION_5DAY:
            case VARIATION_6DAY_SQUAT:
            case VARIATION_6DAY_DEADLIFT:
                switch (day) {
                    case MONDAY:
                        return buildBenchPress(sharedPref, plateRound, unit);
                    case TUESDAY:
                        if (variation == VARIATION_6DAY_DEADLIFT) {
                            return buildDeadlift(sharedPref, plateRound, unit);
                        } else {
                            return buildSquat(sharedPref, plateRound, unit);
                        }
                    case WEDNESDAY:
                        return buildPress(sharedPref, plateRound, unit);
                    case THURSDAY:
                        if (variation == VARIATION_6DAY_DEADLIFT) {
                            return buildSquat(sharedPref, plateRound, unit);
                        } else {
                            return buildDeadlift(sharedPref, plateRound, unit);
                        }
                    case FRIDAY:
                        return buildBench(sharedPref, plateRound, unit);
                    case SATURDAY:
                        if (variation == VARIATION_6DAY_DEADLIFT) {
                            return buildExtraDeadlift(sharedPref, plateRound, unit);
                        } else if (variation == VARIATION_6DAY_SQUAT) {
                            return buildExtraSquat(sharedPref, plateRound, unit);
                        }
                }
                break;
        }
        throw new RuntimeException("Invalid settings!");
    }

    public static void complete(Context ctx, SharedPreferences sharedPref, List<Exercise> workout) {
        int weekIndex = sharedPref.getInt("week", 1);
        int dayNo = sharedPref.getInt("day_no", MONDAY);
        int variation = sharedPref.getInt("variation", VARIATION_6DAY_SQUAT);
        // Increment to next workout.
        dayNo++;
        if (variation == VARIATION_4DAY && dayNo == WEDNESDAY) {
            // There is no wednesday workout in 4 day variation.
            dayNo = THURSDAY;
        }
        if ((variation < VARIATION_6DAY_SQUAT && dayNo > FRIDAY) || dayNo > SATURDAY) {
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
