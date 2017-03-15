package com.cameronzemek.workout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class StopwatchActivity extends AppCompatActivity {
    private Stopwatch stopwatch;
    private StopwatchTimer stopwatchTimer;
    private StopwatchNotification notification;
    private ImageButton btnStartStop;
    private ImageButton btnResetPause;
    private Drawable iconStop;
    private Drawable iconPause;
    private Drawable iconPlay;
    private Drawable iconReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        float volume = sharedPref.getInt("volume", 100) / 100f;

        setContentView(R.layout.activity_stopwatch);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        stopwatchTimer = (StopwatchTimer) findViewById(R.id.stopwatch);
        btnStartStop = (ImageButton) findViewById(R.id.btnStartStop);
        btnResetPause = (ImageButton) findViewById(R.id.btnResetPause);

        Context ctx = getApplicationContext();
        iconStop = ContextCompat.getDrawable(ctx, R.drawable.ic_stop);
        iconPause = ContextCompat.getDrawable(ctx, R.drawable.ic_pause);
        iconPlay = ContextCompat.getDrawable(ctx, R.drawable.ic_play);
        iconReset = ContextCompat.getDrawable(ctx, R.drawable.ic_reset);

        // Attach bell.
        stopwatch = new Stopwatch();
        stopwatch.schedule(new BellPlayer(this, volume), 60000, false);
        // Create notification.
        notification = StopwatchNotification.build(this, getIntent());
    }

    /**
     * Callback to start/stop timer. This is the main button for this activity.
     */
    public void onStartStop(View view) {
        if (!stopwatchTimer.isRunning()) {
            stopwatch.start();
            stopwatchTimer.start();
            // Set the notification to elapsed time in the past when resuming.
            notification.setWhen(stopwatchTimer.getStartTime() - stopwatchTimer.getElapsedTime());
            notification.show();
            btnStartStop.setImageDrawable(iconStop);
            btnResetPause.setImageDrawable(iconPause);
        } else {
            stopwatch.stop();
            stopwatchTimer.stop();
            notification.cancel();
            btnStartStop.setImageDrawable(iconPlay);
            btnResetPause.setImageDrawable(iconReset);
        }
    }

    /**
     * Callback for reset/pause timer.
     */
    public void onResetPause(View view) {
        notification.cancel();
        if (!stopwatchTimer.isRunning()) {
            stopwatch.reset();
            stopwatchTimer.reset();
        } else {
            stopwatch.pause();
            stopwatchTimer.pause();
        }
        btnStartStop.setImageDrawable(iconPlay);
        btnResetPause.setImageDrawable(iconReset);
    }

    public void onBack(View view) {
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopwatch.stop();
        notification.cancel();
    }
}
