package com.cameronzemek.workout;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public abstract class BaseNotification {
    protected final Context context;
    protected final NotificationCompat.Builder builder;

    protected BaseNotification(final Context context, final NotificationCompat.Builder builder) {
        this.context = context;
        this.builder = builder;
    }

    protected String getNotificationId() {
        return "com.cameronzemek.workout.notification";
    }

    public void setWhen(long when) {
        builder.setWhen(when);
        builder.setShowWhen(true);
    }

    public void show() {
        notify(context, getNotificationId(), builder.build());
    }

    public void cancel() {
        cancel(context, getNotificationId());
    }

    /**
     * Build a timer notification.
     */
    protected static NotificationCompat.Builder build(final Context context,
                                                      final String text, Intent notificationIntent) {
        final Resources res = context.getResources();
        String title = res.getString(R.string.app_name);

        PendingIntent intentOpen = PendingIntent.getActivity(
                context,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_stopwatch)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setWhen(0)
                .setShowWhen(false)
                .setUsesChronometer(true)
                .setContentIntent(intentOpen)
                .setAutoCancel(false);

        return builder;
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private static void notify(final Context context, String notificationId, final Notification notification) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.notify(notificationId, 0, notification);
        } else {
            nm.notify(notificationId.hashCode(), notification);
        }
    }

    /**
     * Cancels stopwatch notification.
     */
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    public static void cancel(final Context context, String notificationId) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.cancel(notificationId, 0);
        } else {
            nm.cancel(notificationId.hashCode());
        }
    }
}
