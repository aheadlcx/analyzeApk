package com.prolificinteractive.materialcalendarview.format;

public class ArrayWeekDayFormatter implements WeekDayFormatter {
    private final CharSequence[] a;

    public ArrayWeekDayFormatter(CharSequence[] charSequenceArr) {
        if (charSequenceArr == null) {
            throw new IllegalArgumentException("Cannot be null");
        } else if (charSequenceArr.length != 7) {
            throw new IllegalArgumentException("Array must contain exactly 7 elements");
        } else {
            this.a = charSequenceArr;
        }
    }

    public CharSequence format(int i) {
        return this.a[i - 1];
    }
}
