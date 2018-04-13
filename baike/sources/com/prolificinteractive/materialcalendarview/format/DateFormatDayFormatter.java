package com.prolificinteractive.materialcalendarview.format;

import android.support.annotation.NonNull;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateFormatDayFormatter implements DayFormatter {
    private final DateFormat a;

    public DateFormatDayFormatter() {
        this.a = new SimpleDateFormat("d", Locale.getDefault());
    }

    public DateFormatDayFormatter(@NonNull DateFormat dateFormat) {
        this.a = dateFormat;
    }

    @NonNull
    public String format(@NonNull CalendarDay calendarDay) {
        return this.a.format(calendarDay.getDate());
    }
}
