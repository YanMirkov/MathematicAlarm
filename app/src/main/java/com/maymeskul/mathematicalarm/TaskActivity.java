package com.maymeskul.mathematicalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.AlarmClock;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import java.util.Calendar;

/**
 * Created by alexander on 12.02.15.
 */
public class TaskActivity extends ActionBarActivity{
    AddAlarmActivity addAlarmActivity = new AddAlarmActivity();
    private Alarm alarm = addAlarmActivity.getAlarm();
    private AlarmReceiver alarmReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_layout);
        alarmReceiver = new AlarmReceiver();
    }

    public void createAlarm(Context context){

    }

    public void onClickCreateAlarm(View v){ // здесь мы создаем будильник и тут будет вызываться метод,

       Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);

       i.putExtra(AlarmClock.EXTRA_HOUR,14);
       i.putExtra(AlarmClock.EXTRA_MINUTES,30);
        startActivity(i);
    }
}
