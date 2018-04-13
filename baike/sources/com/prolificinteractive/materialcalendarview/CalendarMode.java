package com.prolificinteractive.materialcalendarview;

@Experimental
public enum CalendarMode {
    MONTHS(6),
    WEEKS(1);
    
    final int a;

    private CalendarMode(int i) {
        this.a = i;
    }
}
