package com.maymeskul.mathematicalarm;

import android.app.Activity;
import android.app.AlarmManager;
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

import org.w3c.dom.Text;

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

    public void onClickChoseTask(View v){
        Intent intent = new Intent(this,TaskActivity.class);
        startActivity(intent);

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

    public Alarm getAlarm(){ // получаем оббъект класса Alarm,для того чтобы иметь доступ к сохраненным
        // полям в классе TaskActivity
        return alarm;
    }
}
