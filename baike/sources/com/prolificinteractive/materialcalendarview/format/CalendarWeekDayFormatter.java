package com.prolificinteractive.materialcalendarview.format;

import com.prolificinteractive.materialcalendarview.CalendarUtils;
import java.util.Calendar;
import java.util.Locale;

public class CalendarWeekDayFormatter implements WeekDayFormatter {
    private final Calendar a;

    public CalendarWeekDayFormatter(Calendar calendar) {
        calendar.get(7);
        this.a = calendar;
    }

    public CalendarWeekDayFormatter() {
        this(CalendarUtils.getInstance());
    }

    public CharSequence format(int i) {
        this.a.set(7, i);
        return this.a.getDisplayName(7, 1, Locale.getDefault());
    }
}
