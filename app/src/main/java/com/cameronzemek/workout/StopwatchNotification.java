package com.cameronzemek.workout;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class StopwatchNotification extends BaseNotification {
    protected StopwatchNotification(Context context, NotificationCompat.Builder builder) {
        super(context, builder);
    }

    public static StopwatchNotification build(Context context, Intent intent) {
        NotificationCompat.Builder builder = build(
                context,
                "Stopwatch",
                intent
        );
        return new StopwatchNotification(context, builder);
    }
}
