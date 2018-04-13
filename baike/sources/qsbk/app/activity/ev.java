package qsbk.app.activity;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

class ev implements OnMonthChangedListener {
    final /* synthetic */ h a;

    ev(h hVar) {
        this.a = hVar;
    }

    public void onMonthChanged(MaterialCalendarView materialCalendarView, CalendarDay calendarDay) {
        this.a.g.a(calendarDay.getCalendar());
    }
}
