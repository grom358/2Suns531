package com.cameronzemek.workout;

import java.util.List;

/**
 * Iterate through a workout.
 */
public class WorkoutTracker {
    private List<Exercise> workout;
    private int position = 1;
    private int exerciseNo = 0;
    private int setNo = 0;

    public WorkoutTracker(List<Exercise> workout) {
        this.workout = workout;

        // Set first set as the current set.
        Exercise exercise = workout.get(0);
        exercise.getSets().get(0).setState(ExerciseSet.STATE_CURRENT);
    }

    public boolean isLastSet() {
        Exercise exercise = workout.get(exerciseNo);
        int setCount = exercise.getSets().size();
        return setNo + 1 < setCount;
    }

    public boolean isDone() {
        return exerciseNo == -1;
    }

    public void completeSet(int reps, float weight) {
        Exercise exercise = workout.get(exerciseNo);
        ExerciseSet set = exercise.getSets().get(setNo);
        int targetReps = set.getReps();
        set.setReps(reps);
        set.setWeight(weight);
        if (reps >= targetReps) {
            set.setState(ExerciseSet.STATE_SUCCESS);
        } else {
            set.setState(ExerciseSet.STATE_FAILED);
        }
        position++;
        setNo++;
        if (setNo >= exercise.getSets().size()) {
            exerciseNo++;
            position++;
            setNo = 0;
            if (exerciseNo >= workout.size()) {
                exerciseNo = -1;
                return;
            }
        }
        exercise = workout.get(exerciseNo);
        set = exercise.getSets().get(setNo);
        set.setState(ExerciseSet.STATE_CURRENT);
    }

    public void undo() {
        Exercise exercise = workout.get(exerciseNo);
        ExerciseSet set = exercise.getSets().get(setNo);
        set.setState(ExerciseSet.STATE_TODO);
        position--;
        setNo--;
        if (setNo < 0) {
            exerciseNo--;
            position--;
            if (exerciseNo < 0) {
                exerciseNo = 0;
                exercise = workout.get(exerciseNo);
                setNo = 0;
            } else {
                exercise = workout.get(exerciseNo);
                setNo = exercise.getSets().size() - 1;
            }
        }
        set = exercise.getSets().get(setNo);
        set.setState(ExerciseSet.STATE_CURRENT);
    }

    public int getPosition() {
        return position;
    }

    public Exercise getExercise() {
        return workout.get(exerciseNo);
    }

    public ExerciseSet getSet() {
        return workout.get(exerciseNo).getSets().get(setNo);
    }

    public List<Exercise> getWorkout() {
        return workout;
    }
}
