package com.prolificinteractive.materialcalendarview;

class MonthPagerAdapter extends d<n> {
    protected /* synthetic */ CalendarPagerView a(int i) {
        return b(i);
    }

    MonthPagerAdapter(MaterialCalendarView materialCalendarView) {
        super(materialCalendarView);
    }

    protected n b(int i) {
        return new n(this.a, getItem(i), this.a.getFirstDayOfWeek());
    }

    protected int a(n nVar) {
        return getRangeIndex().indexOf(nVar.getMonth());
    }

    protected boolean a(Object obj) {
        return obj instanceof n;
    }

    protected e a(CalendarDay calendarDay, CalendarDay calendarDay2) {
        return new MonthPagerAdapter$Monthly(calendarDay, calendarDay2);
    }
}
