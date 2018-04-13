package com.prolificinteractive.materialcalendarview;

import android.support.annotation.NonNull;
import java.util.List;

public interface OnRangeSelectedListener {
    void onRangeSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull List<CalendarDay> list);
}
