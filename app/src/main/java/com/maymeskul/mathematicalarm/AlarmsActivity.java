package com.maymeskul.mathematicalarm;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Ян on 2/10/2015.
 */
public class AlarmsActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);


    }


    public void onClickCreateAlarm(View v){
        Intent intent = new Intent(this,AddAlarmActivity.class);
        startActivityForResult(intent,1);
    }

    public void drawAlarm(int h,int m,String d){


        final TableLayout table = (TableLayout) findViewById(R.id.table);

        TableLayout.LayoutParams lParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);

        TableRow.LayoutParams bParams = new TableRow.LayoutParams();
        TableRow.LayoutParams vParams = new TableRow.LayoutParams();


        vParams.setMargins(85,18,0,0);
        bParams.setMargins(105,14,0,0);
        lParams.setMargins(0,20,0,0);

        final TableRow tr = new TableRow(this);
        tr.setBackgroundColor(Color.parseColor("#9DAF5C"));
        tr.setLayoutParams(lParams);

        final ImageButton state_button = new ImageButton(this);
        state_button.setBackgroundResource(R.drawable.ic_action_alarms);
        state_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tr.setBackgroundColor(Color.parseColor("#FF6161"));
            }
        });

        ImageButton remove_button = new ImageButton(this);
        remove_button.setBackgroundResource(R.drawable.ic_action_remove);
        remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.removeView(tr);
                Intent intent = new Intent(AlarmsActivity.this,AlarmReceiver.class);
                PendingIntent pIntent = PendingIntent.getBroadcast(AlarmsActivity.this.getApplicationContext(),1,
                        intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager aManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                aManager.cancel(pIntent);
            }
        });

        TextView tv = new TextView(this);
        tv.setText(h + " : " + m + " ( " +d + " )");
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(20);

        tr.addView(state_button);
        tr.addView(tv,vParams);
        tr.addView(remove_button,bParams);
        table.addView(tr);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            int hour = data.getIntExtra("hour", 1);
            int minute = data.getIntExtra("minute", 1);
            String days = data.getStringExtra("days");
            drawAlarm(hour, minute, days);
        }
    }
}
