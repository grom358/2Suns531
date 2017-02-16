package com.cameronzemek.workout;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise, eg. Squat, Bench, OHP, Deadlift, Row, etc.
 */
public class Exercise {
    private String name;
    private List<ExerciseSet> sets;

    public Exercise(String name) {
        this.name = name;
        sets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<ExerciseSet> getSets() {
        return sets;
    }
}
