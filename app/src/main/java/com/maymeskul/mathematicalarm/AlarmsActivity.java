package com.maymeskul.mathematicalarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Ян on 2/10/2015.
 */
public class AlarmsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onClickCreateAlarm(View v){
        Intent intent = new Intent(this,AddAlarmActivity.class);
        startActivity(intent);
    }
}
