package com.prolificinteractive.materialcalendarview;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;

public class MonthPagerAdapter$Monthly implements e {
    private final CalendarDay a;
    private final int b;
    private SparseArrayCompat<CalendarDay> c = new SparseArrayCompat();

    public MonthPagerAdapter$Monthly(@NonNull CalendarDay calendarDay, @NonNull CalendarDay calendarDay2) {
        this.a = CalendarDay.from(calendarDay.getYear(), calendarDay.getMonth(), 1);
        this.b = indexOf(CalendarDay.from(calendarDay2.getYear(), calendarDay2.getMonth(), 1)) + 1;
    }

    public int getCount() {
        return this.b;
    }

    public int indexOf(CalendarDay calendarDay) {
        return ((calendarDay.getYear() - this.a.getYear()) * 12) + (calendarDay.getMonth() - this.a.getMonth());
    }

    public CalendarDay getItem(int i) {
        CalendarDay calendarDay = (CalendarDay) this.c.get(i);
        if (calendarDay != null) {
            return calendarDay;
        }
        int year = this.a.getYear() + (i / 12);
        int month = this.a.getMonth() + (i % 12);
        if (month >= 12) {
            year++;
            month -= 12;
        }
        calendarDay = CalendarDay.from(year, month, 1);
        this.c.put(i, calendarDay);
        return calendarDay;
    }
}
