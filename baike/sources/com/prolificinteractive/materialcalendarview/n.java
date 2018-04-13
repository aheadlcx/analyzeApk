package com.prolificinteractive.materialcalendarview;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import java.util.Calendar;
import java.util.Collection;

@SuppressLint({"ViewConstructor"})
class n extends CalendarPagerView {
    public n(@NonNull MaterialCalendarView materialCalendarView, CalendarDay calendarDay, int i) {
        super(materialCalendarView, calendarDay, i);
    }

    protected void b(Collection<f> collection, Calendar calendar) {
        for (int i = 0; i < 6; i++) {
            for (int i2 = 0; i2 < 7; i2++) {
                a(collection, calendar);
            }
        }
    }

    public CalendarDay getMonth() {
        return getFirstViewDay();
    }

    protected boolean a(CalendarDay calendarDay) {
        return calendarDay.getMonth() == getFirstViewDay().getMonth();
    }

    protected int getRows() {
        return 7;
    }
}
