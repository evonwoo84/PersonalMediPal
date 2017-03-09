package sg.edu.nus.personalmedipal.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import sg.edu.nus.personalmedipal.R;
import sg.edu.nus.personalmedipal.receiver.NotificationReceiver;

public class AppointmentActivity extends AppCompatActivity {

    private static final String TAG = "AppointmentActivity";
    private CheckBox checkBoxApptNotification;
    private EditText editTextApptDateTime;
    private EditText editTextAppID;
    private Integer notificationID;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        editTextApptDateTime = (EditText) findViewById(R.id.edit_text_appt_date);
        editTextAppID = (EditText) findViewById(R.id.edit_text_app_ID);

        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int hour = calendar.get(Calendar.HOUR);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        editTextApptDateTime.setText(day + "/" + month + "/" + year + " " +
                                        hour + ":" + minutes + ":" + seconds);

        Random r = new Random();
        notificationID = r.nextInt(9999 - 1);

        editTextAppID.setText(String.format(Locale.getDefault(), "%s", notificationID));

    }

    public void onClickAdd(View view) {

        checkBoxApptNotification = (CheckBox) findViewById(R.id.check_box_appt_notification);
        if (checkBoxApptNotification.isChecked()) {
            createNotificationMessage();
        }
    }

    public void onClickDelete(View view) {

        modifyNotificationMessage();
    }

    public void createNotificationMessage() {
        Intent intent = new Intent(this, NotificationReceiver.class);

        notificationID = Integer.parseInt(editTextAppID.getText().toString());
        intent.putExtra("ID", notificationID);
        intent.putExtra("TIME", "11.00");
        intent.putExtra("MESSAGE", "You have appointment at _xxx_");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notificationID, intent, 0);

        //calendar.set(year, month, day, hour, minutes); //to obtain date/time from user input
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 10000, pendingIntent); // testing to send after 10s
        Log.i(TAG, "ID:" + notificationID);
        Log.i(TAG, "Time:" + calendar.getTimeInMillis());

    }

    public void modifyNotificationMessage() {
        Intent intent = new Intent(this, NotificationReceiver.class);

        notificationID = Integer.parseInt(editTextAppID.getText().toString());
        intent.putExtra("ID", notificationID);
        intent.putExtra("TIME", "12.00");
        intent.putExtra("MESSAGE", "You have appointment at _yyy_");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notificationID, intent, 0);

        //calendar.set(year, month, day, hour, minutes); //to obtain date/time from user input
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 15000, pendingIntent); // testing to send after 15s
        Log.i(TAG, "ID:" + notificationID);
        Log.i(TAG, "Time:" + calendar.getTimeInMillis());
        
    }

}
