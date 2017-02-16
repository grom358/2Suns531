package com.cameronzemek.workout;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class WorkoutNotification extends BaseNotification {
    protected WorkoutNotification(Context context, NotificationCompat.Builder builder) {
        super(context, builder);
    }

    public void setText(String text) {
        builder.setContentText(text);
    }

    public static WorkoutNotification build(Context context, Intent intent) {
        NotificationCompat.Builder builder = build(
                context,
                "PLACEHOLDER",
                intent
        );
        Intent intentSendDone = new Intent(WorkoutActivity.KEY_DONE);
        PendingIntent intentDone = PendingIntent.getBroadcast(context, 0, intentSendDone, 0);
        builder.addAction(R.drawable.ic_no, "Failed", PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        builder.addAction(R.drawable.ic_yes, "Done", intentDone);
        return new WorkoutNotification(context, builder);
    }
}
