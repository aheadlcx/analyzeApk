package com.prolificinteractive.materialcalendarview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.widget.TextView;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;
import java.util.Calendar;

@SuppressLint({"ViewConstructor"})
@Experimental
class q extends TextView {
    private WeekDayFormatter a = WeekDayFormatter.DEFAULT;
    private int b;

    public q(Context context, int i) {
        super(context);
        setGravity(17);
        if (VERSION.SDK_INT >= 17) {
            setTextAlignment(4);
        }
        setDayOfWeek(i);
    }

    public void setWeekDayFormatter(WeekDayFormatter weekDayFormatter) {
        if (weekDayFormatter == null) {
            weekDayFormatter = WeekDayFormatter.DEFAULT;
        }
        this.a = weekDayFormatter;
        setDayOfWeek(this.b);
    }

    public void setDayOfWeek(int i) {
        this.b = i;
        setText(this.a.format(i));
    }

    public void setDayOfWeek(Calendar calendar) {
        setDayOfWeek(CalendarUtils.getDayOfWeek(calendar));
    }
}
