package com.cameronzemek.workout;

public class ExerciseSet {
    public final static int STATE_TODO = 0;
    public final static int STATE_CURRENT = 1;
    public final static int STATE_FAILED = 2;
    public final static int STATE_SUCCESS = 3;

    private int targetReps;
    private int reps;
    private float weight;
    private String unit;
    protected int state = STATE_TODO;
    private boolean amrap = false;

    public ExerciseSet() {
        this.reps = 0;
        this.weight = 0f;
        this.unit = "";
    }

    public ExerciseSet(int reps, float weight, String unit) {
        this.reps = this.targetReps = reps;
        this.weight = weight;
        this.unit = unit;
    }

    public ExerciseSet(int reps, float weight, String unit, boolean amrap) {
        this.reps = this.targetReps = reps;
        this.weight = weight;
        this.amrap = amrap;
        this.unit = unit;
    }

    public ExerciseSet(int reps, float weight, String unit, int state) {
        this.reps = reps;
        this.weight = weight;
        this.unit = unit;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getTargetReps() {
        return targetReps;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getUnit() {
        return unit;
    }

    public String getDetails() {
        return String.format("%d%s x %s%s %s", reps, amrap ? "+" : "", Util.format(weight), unit, Util.plateCalculate(weight, unit));
    }
}
