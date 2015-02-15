package com.maymeskul.mathematicalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * Created by Ян on 2/10/2015.
 */
public class AddAlarmActivity extends ActionBarActivity {
    public static final int REQUEST = 1;
    private Alarm alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_alarm);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        alarm = new Alarm();
        final LinearLayout alarmRingtoneLayout = (LinearLayout) findViewById(R.id.alarm_ringtone_container);
        alarmRingtoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER); // вызываем чузера,с помощью
                // которого можно выбрать мелодию
                startActivityForResult(intent, REQUEST);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        saveAlarmSettings();
    }

    // выбираем рингтон,который будет играть при запуске будильника
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUEST){
            alarm.ringtoneUri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            TextView ringtoneSelection = (TextView) findViewById(R.id.alarm_label_tone_selection);
            ringtoneSelection.setText(RingtoneManager.getRingtone(this,alarm.ringtoneUri).getTitle(this));

        }
    }



    public void onClickCreateAlarm(View v){
        saveAlarmSettings();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, alarm.hours);
        calendar.set(Calendar.MINUTE,alarm.minutes);

        Intent intent2 = new Intent();

        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("hour",alarm.hours);
        intent.putExtra("minute",alarm.minutes);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Alarm set in " + alarm.hours + ": " + alarm.minutes,
                Toast.LENGTH_LONG).show();

        intent2.putExtra("hour",alarm.hours);
        intent2.putExtra("minute",alarm.minutes);
        intent2.putExtra("name",alarm.alarmName);
        setResult(RESULT_OK,intent2);

        finish();

    }


    public void saveAlarmSettings(){ // Сохраняем значения,которые нужны для создания будильника

        // сохраняем время
        TimePicker timePicker = (TimePicker) findViewById(R.id.alarm_details_time_picker);
        alarm.minutes = timePicker.getCurrentMinute().intValue();
        alarm.hours = timePicker.getCurrentHour().intValue();


        // сохраняем имя будильника
        EditText edtName = (EditText) findViewById(R.id.alarm_details_name);
        alarm.alarmName = edtName.getText().toString();


        // определяем какие дни задействованы
        CheckBox chkWeekly = (CheckBox) findViewById(R.id.alarm_details_repeat_weekly);
        alarm.repeatWeekly = chkWeekly.isChecked();

        CheckBox chkSunday = (CheckBox) findViewById(R.id.alarm_details_repeat_sunday);
        alarm.setDay(Alarm.SUNDAY, chkSunday.isChecked());

        CheckBox chkMonday = (CheckBox) findViewById(R.id.alarm_details_repeat_monday);
        alarm.setDay(Alarm.MONDAY, chkMonday.isChecked());

        CheckBox chkTuesday = (CheckBox) findViewById(R.id.alarm_details_repeat_tuesday);
        alarm.setDay(Alarm.TUESDAY, chkTuesday.isChecked());

        CheckBox chkWednesday = (CheckBox) findViewById(R.id.alarm_details_repeat_wednesday);
        alarm.setDay(Alarm.WEDNESDAY, chkWednesday.isChecked());

        CheckBox chkThursday = (CheckBox) findViewById(R.id.alarm_details_repeat_thursday);
        alarm.setDay(Alarm.THURSDAY, chkThursday.isChecked());

        CheckBox chkFriday = (CheckBox) findViewById(R.id.alarm_details_repeat_friday);
        alarm.setDay(Alarm.FRIDAY, chkFriday.isChecked());

        CheckBox chkSaturday = (CheckBox) findViewById(R.id.alarm_details_repeat_saturday);
        alarm.setDay(Alarm.SATURDAY, chkSaturday.isChecked());

        alarm.isEnabled = true;
    }

    public int getHour(){
        return alarm.hours;
    }
    public int getMinute(){
        return alarm.minutes;
    }
}
