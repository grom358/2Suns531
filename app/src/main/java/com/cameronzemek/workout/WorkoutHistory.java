package com.cameronzemek.workout;

public class WorkoutHistory {
    private long id;
    private String when;

    public WorkoutHistory(long id, String when) {
        this.id = id;
        this.when = when;
    }

    public long getId() {
        return id;
    }

    public String getWhen() {
        return when;
    }

    public String toString() {
        return getWhen();
    }
}
