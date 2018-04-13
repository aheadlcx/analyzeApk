package com.prolificinteractive.materialcalendarview.format;

import android.text.SpannableStringBuilder;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.prolificinteractive.materialcalendarview.CalendarDay;

public class MonthArrayTitleFormatter implements TitleFormatter {
    private final CharSequence[] a;

    public MonthArrayTitleFormatter(CharSequence[] charSequenceArr) {
        if (charSequenceArr == null) {
            throw new IllegalArgumentException("Label array cannot be null");
        } else if (charSequenceArr.length < 12) {
            throw new IllegalArgumentException("Label array is too short");
        } else {
            this.a = charSequenceArr;
        }
    }

    public CharSequence format(CalendarDay calendarDay) {
        return new SpannableStringBuilder().append(this.a[calendarDay.getMonth()]).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(String.valueOf(calendarDay.getYear()));
    }
}
