package com.prolificinteractive.materialcalendarview.format;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateFormatTitleFormatter implements TitleFormatter {
    private final DateFormat a;

    public DateFormatTitleFormatter() {
        this.a = new SimpleDateFormat("LLLL yyyy", Locale.getDefault());
    }

    public DateFormatTitleFormatter(DateFormat dateFormat) {
        this.a = dateFormat;
    }

    public CharSequence format(CalendarDay calendarDay) {
        return this.a.format(calendarDay.getDate());
    }
}
