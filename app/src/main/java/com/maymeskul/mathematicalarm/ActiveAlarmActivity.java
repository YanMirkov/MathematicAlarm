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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Random;


public class ActiveAlarmActivity extends ActionBarActivity {
    int hour;
    int minute;
    String message;

    TextView time;
    TextView task;
    TextView msg;
    EditText editText;
    Random generator;
    int firstNumber,secondNumber,result,userResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_alarm);

        hour = getIntent().getIntExtra("hour",1);
        minute = getIntent().getIntExtra("minute",1);
        message = getIntent().getStringExtra("message");

        editText = (EditText) findViewById(R.id.edit);

        task = (TextView)findViewById(R.id.exacttask);
        time = (TextView) findViewById(R.id.time);
        msg = (TextView) findViewById(R.id.message);
        msg.setText(message);
        time.setText(hour + " : " + minute);


        setTask();
    }


    public void onClickDismiss(View v){

        Intent intent = new Intent(this,AlarmReceiver.class);

        userResult= Integer.parseInt(editText.getText().toString());
        if(userResult == result){
            PendingIntent pIntent = PendingIntent.getBroadcast(this.getApplicationContext(),1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager aManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            aManager.cancel(pIntent);
            MediaPlayer mp = AlarmReceiver.mPlayer;
            mp.stop();
            Intent main = new Intent(this,AlarmsActivity.class);
            startActivity(main);
            finish();
        } else {
              TextView error = (TextView) findViewById(R.id.error);
            error.setText("Попробуйте еще раз");
        }
    }
    public void setTask(){
        int compl = getIntent().getIntExtra("complexity",2);
        generator = new Random();

        switch (compl){
            case 1:
                firstNumber = generator.nextInt(10);
                secondNumber = generator.nextInt(10);
                result = firstNumber*secondNumber;
                break;
            case 2:
                firstNumber = generator.nextInt(30);
                secondNumber = generator.nextInt(30);
                result = firstNumber*secondNumber;
                break;
            case 3:
                firstNumber = generator.nextInt(100);
                secondNumber = generator.nextInt(100);
                result = firstNumber*secondNumber;
                break;

            default: firstNumber = generator.nextInt(10);
                secondNumber = generator.nextInt(10);
                result = firstNumber*secondNumber;
                break;
        }
        task.setText(firstNumber + " * " + secondNumber +" = ...");
    }

}
