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

    @Override

    public void onReceive(Context context, Intent intent) {
        //Получает интент если приложение было закрыто

    }


    public void setAlarm(Context context) {

    }

    public void cancelAlarm(Context context){}

}
