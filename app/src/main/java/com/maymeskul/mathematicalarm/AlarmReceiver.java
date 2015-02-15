package com.maymeskul.mathematicalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import java.util.Calendar;


public class AlarmReceiver extends BroadcastReceiver {
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override

    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,intent.getIntExtra("hour",1)+ " : " + intent.getIntExtra("minute",1),
                Toast.LENGTH_LONG).show();
        Log.d("My","Alarm!!!!!!!!!!!!!!!!1");

    }


    public void setAlarm(Context context) {

    }

    public void cancelAlarm(Context context){

    }

}
