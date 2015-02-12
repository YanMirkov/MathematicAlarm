package com.maymeskul.mathematicalarm;

import android.net.Uri;

import java.net.URI;

/**
 * Created by alexander on 12.02.15.
 */
public class Alarm {
    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;
    public static final int SATURDAY = 5;
    public static final int SUNDAY = 6;

    public String alarmName;
    public boolean repeatWeekly;
    private boolean days[];
    public int hours;
    public int minutes;
    public Uri ringtoneUri;
    public boolean isEnabled;

    public Alarm(){
        days = new boolean[7];
    }

    public void setDay(int day,boolean value) {
        days[day] = value;
    }

    public boolean getDay(int day) {
        return days[day];
    }
}
