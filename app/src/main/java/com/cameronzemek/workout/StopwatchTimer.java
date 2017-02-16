package com.cameronzemek.workout;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

public class StopwatchTimer extends Chronometer {
    private long startTime;
    private long timeWhenStopped = 0;
    private boolean isRunning = false;
    private boolean resetOnStart = false;

    public StopwatchTimer(Context context) {
        super(context);
    }

    public StopwatchTimer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StopwatchTimer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void start() {
        if (resetOnStart) {
            setBase(SystemClock.elapsedRealtime());
            resetOnStart = false;
        } else {
            setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
        }
        isRunning = true;
        super.start();
        startTime = System.currentTimeMillis();
    }

    @Override
    public void stop() {
        pause();
        resetOnStart = true;
    }

    public void pause() {
        super.stop();
        isRunning = false;
        timeWhenStopped = getBase() - SystemClock.elapsedRealtime();
        resetOnStart = false;
    }

    public void reset() {
        stop();
        setBase(SystemClock.elapsedRealtime());
        timeWhenStopped = 0;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getElapsedTime() {
        return SystemClock.elapsedRealtime() - getBase();
    }

    public long getCurrentTime() {
        return timeWhenStopped;
    }

    public void setCurrentTime(long time) {
        timeWhenStopped = time;
        setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
    }
}
