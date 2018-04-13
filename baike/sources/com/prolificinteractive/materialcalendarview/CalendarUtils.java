package com.prolificinteractive.materialcalendarview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {
    public static Calendar getInstance(@Nullable Date date) {
        Calendar instance = Calendar.getInstance();
        if (date != null) {
            instance.setTime(date);
        }
        copyDateTo(instance, instance);
        return instance;
    }

    @NonNull
    public static Calendar getInstance() {
        Calendar instance = Calendar.getInstance();
        copyDateTo(instance, instance);
        return instance;
    }

    public static void setToFirstDay(Calendar calendar) {
        int year = getYear(calendar);
        int month = getMonth(calendar);
        calendar.clear();
        calendar.set(year, month, 1);
    }

    public static void copyDateTo(Calendar calendar, Calendar calendar2) {
        int year = getYear(calendar);
        int month = getMonth(calendar);
        int day = getDay(calendar);
        calendar2.clear();
        calendar2.set(year, month, day);
    }

    public static int getYear(Calendar calendar) {
        return calendar.get(1);
    }

    public static int getMonth(Calendar calendar) {
        return calendar.get(2);
    }

    public static int getDay(Calendar calendar) {
        return calendar.get(5);
    }

    public static int getDayOfWeek(Calendar calendar) {
        return calendar.get(7);
    }
}
