package com.minh.findtheshipper.helpers;
import android.content.Context;
import android.os.SystemClock;

import com.bumptech.glide.load.engine.Resource;
import com.minh.findtheshipper.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by trinh on 7/16/2017.
 */

public class TimeAgoHelpers {
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60*SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60*MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24*HOUR_MILLIS;

    public TimeAgoHelpers() {
    }

    private static long convertStringToLong(String stringTime)
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
        try {
            Date date = df.parse(stringTime);
            return  date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getTimeAgo(String stringTime, Context context)
    {
        long time = convertStringToLong(stringTime);
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if(time > now || time <=0)
        {
            return null;
        }

        final long timeAgo = now -time;
        if(timeAgo <MINUTE_MILLIS)
        {
            return context.getResources().getString(R.string.time_ago_just_now);
        }
        if(timeAgo < 2 * MINUTE_MILLIS)
        {
            return context.getResources().getString(R.string.time_ago_a_minutes_ago);
        }
        if(timeAgo < 60*MINUTE_MILLIS)
        {
            return timeAgo/MINUTE_MILLIS + " "+ context.getResources().getString(R.string.time_ago_minutes_ago);
        }
        if(timeAgo < 24 * HOUR_MILLIS)
        {
            return timeAgo/HOUR_MILLIS + " "+ context.getResources().getString(R.string.time_ago_hours_ago);
        }
        if(timeAgo < 48* HOUR_MILLIS)
        {
            return context.getResources().getString(R.string.time_ago_yesterday);
        }
        else {
            return timeAgo/DAY_MILLIS+ " "+ context.getResources().getString(R.string.time_ago_days_ago);
        }

    }

}
