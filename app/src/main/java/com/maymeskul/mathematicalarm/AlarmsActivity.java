package com.maymeskul.mathematicalarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Layout;
import android.view.View;

/**
 * Created by Ян on 2/10/2015.
 */
public class AlarmsActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
       View v = new View(this);
       v.getSolidColor();
    }

    public void onClickCreateAlarm(View v){
        Intent intent = new Intent(this,AddAlarmActivity.class);
        startActivity(intent);
    }
}
