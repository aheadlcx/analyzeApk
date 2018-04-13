package com.prolificinteractive.materialcalendarview;

import android.support.annotation.NonNull;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WeekPagerAdapter$Weekly implements e {
    private final CalendarDay a;
    private final int b;

    public WeekPagerAdapter$Weekly(@NonNull CalendarDay calendarDay, @NonNull CalendarDay calendarDay2, int i) {
        this.a = a(calendarDay, i);
        this.b = a(this.a, calendarDay2) + 1;
    }

    public int getCount() {
        return this.b;
    }

    public int indexOf(CalendarDay calendarDay) {
        return a(this.a, calendarDay);
    }

    public CalendarDay getItem(int i) {
        return CalendarDay.from(new Date(this.a.getDate().getTime() + TimeUnit.MILLISECONDS.convert((long) (i * 7), TimeUnit.DAYS)));
    }

    private int a(@NonNull CalendarDay calendarDay, @NonNull CalendarDay calendarDay2) {
        return (int) (TimeUnit.DAYS.convert(((calendarDay2.getDate().getTime() - calendarDay.getDate().getTime()) + ((long) calendarDay2.getCalendar().get(16))) - ((long) calendarDay.getCalendar().get(16)), TimeUnit.MILLISECONDS) / 7);
    }

    private CalendarDay a(@NonNull CalendarDay calendarDay, int i) {
        Calendar instance = Calendar.getInstance();
        calendarDay.copyTo(instance);
        while (instance.get(7) != i) {
            instance.add(7, -1);
        }
        return CalendarDay.from(instance);
    }
}
