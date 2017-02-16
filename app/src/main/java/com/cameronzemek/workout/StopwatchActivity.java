package com.cameronzemek.workout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class StopwatchActivity extends AppCompatActivity {
    private StopwatchTimer stopwatch;
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
        setContentView(R.layout.activity_stopwatch);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        stopwatch = (StopwatchTimer) findViewById(R.id.stopwatch);
        btnStartStop = (ImageButton) findViewById(R.id.btnStartStop);
        btnResetPause = (ImageButton) findViewById(R.id.btnResetPause);

        Context ctx = getApplicationContext();
        iconStop = ContextCompat.getDrawable(ctx, R.drawable.ic_stop);
        iconPause = ContextCompat.getDrawable(ctx, R.drawable.ic_pause);
        iconPlay = ContextCompat.getDrawable(ctx, R.drawable.ic_play);
        iconReset = ContextCompat.getDrawable(ctx, R.drawable.ic_reset);

        // Attach bell.
        stopwatch.setOnChronometerTickListener(new BellPlayer(this));
        // Create notification.
        notification = StopwatchNotification.build(this, getIntent());
    }

    /**
     * Callback to start/stop timer. This is the main button for this activity.
     */
    public void onStartStop(View view) {
        if (!stopwatch.isRunning()) {
            stopwatch.start();
            notification.setWhen(stopwatch.getStartTime());
            notification.show();
            btnStartStop.setImageDrawable(iconStop);
            btnResetPause.setImageDrawable(iconPause);
        } else {
            stopwatch.stop();
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
        if (!stopwatch.isRunning()) {
            stopwatch.reset();
        } else {
            stopwatch.pause();
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
        notification.cancel();
    }
}
