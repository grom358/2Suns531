package com.cameronzemek.workout;

public class AssistanceSet extends ExerciseSet {
    protected String name;

    public AssistanceSet(String name) {
        this.name = name;
    }

    @Override
    public String getDetails() {
        return name;
    }
}
