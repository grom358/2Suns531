package com.cameronzemek.workout;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A stopwatch.
 */
public class Stopwatch {
    /**
     * State when stopwatch is stopped.
     */
    public static final int STATE_STOPPED = 0;

    /**
     * State when stopwatch is running and accumulating time.
     */
    public static final int STATE_RUNNING = 1;

    /**
     * State when stopwatch is paused.
     */
    public static final int STATE_PAUSED = 2;

    /**
     * State when elapsed time of stopwatch is reset after stopping. The reset may be delayed.
     */
    public static final int STATE_RESET = 3;

    /**
     * Property name for state change.
     */
    public static final String STATE_CHANGED = "state_changed";

    /**
     * Current state of the stopwatch.
     */
    private int currentState;

    /**
     * The system time when the stopwatch was started.
     */
    private long startTime;

    /**
     * The amount of time that has elapsed in milliseconds.
     */
    private long elapsedTime;

    /**
     * Stopwatch property observers.
     */
    private PropertyChangeSupport observers;

    public Stopwatch() {
        currentState = STATE_STOPPED;
        startTime = 0;
        elapsedTime = 0;
        observers = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener listener) {
        observers.addPropertyChangeListener(listener);
    }

    public void removeObserver(PropertyChangeListener listener) {
        observers.removePropertyChangeListener(listener);
    }

    /**
     * Change state and notify observers.
     *
     * @param newState The new state of the stopwatch.
     */
    private void fireStateChange(int newState) {
        int oldState = currentState;
        currentState = newState;
        if (currentState != oldState) {
            observers.firePropertyChange(STATE_CHANGED, oldState, currentState);
        }
    }

    /**
     * Get the state of the stopwatch.
     *
     * @return State of stopwatch.
     */
    public int getCurrentState() {
        return currentState;
    }

    /**
     * Get the elapsed time.
     *
     * @return Elapsed time in milliseconds.
     */
    public long getElapsedTime() {
        if (currentState == STATE_RUNNING) {
            return elapsedTime + (System.currentTimeMillis() - startTime);
        } else {
            return elapsedTime;
        }
    }

    /**
     * Start the stopwatch.
     */
    public void start() {
        // after stop() is called, starting the stopwatch resets it.
        if (currentState == STATE_STOPPED && elapsedTime > 0) {
            elapsedTime = 0;
            fireStateChange(STATE_RESET);
        }
        if (currentState != STATE_RUNNING) {
            startTime = System.currentTimeMillis();
            fireStateChange(STATE_RUNNING);
        }
    }

    /**
     * Pause the stopwatch.
     */
    public void pause() {
        if (currentState != STATE_PAUSED) {
            elapsedTime += (System.currentTimeMillis() - startTime);
            fireStateChange(STATE_PAUSED);
        }
    }

    /**
     * Stop the stopwatch.
     */
    public void stop() {
        if (currentState != STATE_STOPPED) {
            elapsedTime += (System.currentTimeMillis() - startTime);
            fireStateChange(STATE_STOPPED);
        }
    }

    /**
     * Stop and reset the stopwatch.
     */
    public void reset() {
        fireStateChange(STATE_STOPPED);
        elapsedTime = 0;
        fireStateChange(STATE_RESET);
    }

    /**
     * Schedule a task to run at fixed-rate of elapsed time.
     *
     * @param runnable task to execute
     * @param period   elapsed time in milliseconds between successive task executions.
     * @param onReset  when true run the task when stopwatch is reset.
     */
    public void schedule(Runnable runnable, long period, boolean onReset) {
        StopwatchTask task = new StopwatchTask(runnable, period, onReset);
        addObserver(task);
    }

    /**
     * Helper class for executing tasks according to elapsed stopwatch time.
     */
    private class StopwatchTask implements PropertyChangeListener {
        private Timer timer;
        private Runnable runnable;
        private boolean onReset;
        private long delay;
        private long period;

        public StopwatchTask(Runnable runnable, long period, boolean onReset) {
            this.runnable = runnable;
            this.period = period;
            this.delay = period;
            this.onReset = onReset;
        }

        /**
         * Schedule the execution of the task.
         */
        private void schedule() {
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runnable.run();
                }
            }, delay, period);
        }

        /**
         * Stop the execution of the task.
         */
        private void unschedule() {
            if (timer != null) {
                timer.cancel();
            }
            timer = null;
        }

        @Override
        public void propertyChange(PropertyChangeEvent event) {
            if (event.getPropertyName().equals(STATE_CHANGED)) {
                int currentState = (Integer) event.getNewValue();
                switch (currentState) {
                    case STATE_RUNNING:
                        schedule();
                        break;
                    case STATE_PAUSED:
                        unschedule();
                        // If the stopwatch is resumed then delay the task so it occurs on the mark.
                        delay = period - (elapsedTime % period);
                        break;
                    case STATE_STOPPED:
                        unschedule();
                        delay = period;
                        break;
                    case STATE_RESET:
                        unschedule();
                        delay = period;
                        if (onReset) {
                            runnable.run();
                        }
                        break;
                }
            }
        }
    }
}
