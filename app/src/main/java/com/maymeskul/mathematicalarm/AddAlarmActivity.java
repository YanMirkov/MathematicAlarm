package com.maymeskul.mathematicalarm;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Ян on 2/10/2015.
 */
public class AddAlarmActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_alarm);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }
}
