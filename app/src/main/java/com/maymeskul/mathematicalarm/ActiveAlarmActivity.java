package com.maymeskul.mathematicalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;


public class ActiveAlarmActivity extends ActionBarActivity {
    int hour;
    int minute;
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_alarm);

        time = (TextView) findViewById(R.id.time);
    }


    public void onClickDismiss(View v){
        hour = getIntent().getIntExtra("hour",1);
        minute = getIntent().getIntExtra("minute",1);

        time.setText(hour + " : " + minute);

        Intent intent = new Intent(this,AlarmReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this.getApplicationContext(),1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager aManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        aManager.cancel(pIntent);
        MediaPlayer mp = AlarmReceiver.mPlayer;
        mp.stop();
    }
    public void onRadioButtonClick(View v){
        boolean checked = ((RadioButton) v).isChecked();
    }
}
