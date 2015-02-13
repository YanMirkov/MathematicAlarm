package com.maymeskul.mathematicalarm;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * Created by alexander on 12.02.15.
 */
public class TaskActivity extends ActionBarActivity{
    AddAlarmActivity addAlarmActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_layout);
    }

    public void createAlarm(){}

    public void onClickCreateAlarm(View v){ // здесь мы создаем будильник и тут будет вызываться метод,
        // который отрисовывает динамически новый будильник в AlarmsActivity
        addAlarmActivity.saveAlarmSettings();
    }
}
