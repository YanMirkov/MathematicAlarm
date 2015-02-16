package com.maymeskul.mathematicalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;


import java.util.Calendar;


public class AlarmReceiver extends BroadcastReceiver {
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    static MediaPlayer mPlayer;
    @Override

    public void onReceive(Context context, Intent intent) {
        int hour = intent.getIntExtra("hour",1);
        int minute = intent.getIntExtra("minute",1);
        int compl = intent.getIntExtra("complexity",2);

        Log.d("My","Alarm!!!!!!!!!!!!!!!!1");
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
         mPlayer = new MediaPlayer();
        Uri tone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(tone != null && !tone.equals("")){
            tone =  intent.getParcelableExtra("tone");
        }

        try {

            mPlayer.setDataSource(context, tone);
            mPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
            mPlayer.setLooping(true);
            mPlayer.prepare();
            mPlayer.start();


        } catch (Exception e){
            e.printStackTrace();
        }
        Intent activeAlarm = new Intent(context,ActiveAlarmActivity.class);
        activeAlarm.putExtra("hour",hour);
        activeAlarm.putExtra("minute",minute);
        activeAlarm.putExtra("complexity",compl);
        activeAlarm.putExtra("message",intent.getStringExtra("message").toString());
        activeAlarm.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(activeAlarm);

    }

}
