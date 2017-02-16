package com.cameronzemek.workout;

public class ExerciseSet {
    public final static int STATE_TODO = 0;
    public final static int STATE_CURRENT = 1;
    public final static int STATE_FAILED = 2;
    public final static int STATE_SUCCESS = 3;

    private int reps;
    private float weight;
    protected int state = STATE_TODO;
    private boolean amrap = false;

    public ExerciseSet() {
        this.reps = 0;
        this.weight = 0f;
    }

    public ExerciseSet(int reps, float weight) {
        this.reps = reps;
        this.weight = weight;
    }

    public ExerciseSet(int reps, float weight, boolean amrap) {
        this.reps = reps;
        this.weight = weight;
        this.amrap = amrap;
    }

    public ExerciseSet(int reps, float weight, int state) {
        this.reps = reps;
        this.weight = weight;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    public String getDetails() {
        return String.format("%d%s x %.2fkg", reps, amrap ? "+" : "", weight);
    }
}
