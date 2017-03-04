package sg.edu.nus.personalmedipal.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import sg.edu.nus.personalmedipal.R;
import sg.edu.nus.personalmedipal.activity.AppointmentActivity;

public class NotificationReceiver extends BroadcastReceiver {
    public NotificationReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationID = intent.getExtras().getInt("ID");
        String time = intent.getExtras().getString("TIME");
        String message = intent.getExtras().getString("MESSAGE");

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_notification);
        mBuilder.setContentTitle("Scheduled appointment: " + time);
        mBuilder.setContentText(message);

        Intent notificationIntent = new Intent(context, AppointmentActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(contentIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID, mBuilder.build());
        mBuilder.build().flags |= Notification.FLAG_AUTO_CANCEL;
    }
}
