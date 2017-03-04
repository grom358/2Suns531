package com.cameronzemek.workout;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.SystemClock;
import android.widget.Chronometer;

public class BellPlayer implements Chronometer.OnChronometerTickListener {
    private SoundPool soundPool;
    private int soundBell;
    private float volume;

    public BellPlayer(Context ctx, float volume) {
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundBell = soundPool.load(ctx, R.raw.bell, 1);
        this.volume = volume;
    }

    public void play() {
        soundPool.play(soundBell, volume, volume, 1, 0, 1.0f);
    }

    @Override
    public void onChronometerTick(Chronometer chronometer) {
        long elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
        if (elapsedTime > 1000 && elapsedTime % 60000 < 1000) {
            play();
        }
    }
}
