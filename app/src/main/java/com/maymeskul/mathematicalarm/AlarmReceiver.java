package com.maymeskul.mathematicalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import java.util.Calendar;

/**
 * Created by Ян on 2/13/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private AlarmManager alarmMgr;

    private PendingIntent alarmIntent;

    private AddAlarmActivity addAlarmActivity = new AddAlarmActivity();
    private Alarm alarm = addAlarmActivity.getAlarm();

    @Override

    public void onReceive(Context context, Intent intent) {
        //Получает интент если приложение было закрыто

    }


    public void setAlarm(Context context) {
        addAlarmActivity.saveAlarmSettings();
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        intent.putExtra("hour",alarm.hours);
        intent.putExtra("minutes",alarm.minutes);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, alarm.hours);
        calendar.set(Calendar.MINUTE,alarm.minutes);


        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
    }

    public void cancelAlarm(Context context){}

}
