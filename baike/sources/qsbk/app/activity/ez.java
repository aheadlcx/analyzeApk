package qsbk.app.activity;

import android.support.annotation.NonNull;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

class ez implements OnDateSelectedListener {
    final /* synthetic */ h a;

    ez(h hVar) {
        this.a = hVar;
    }

    public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean z) {
        if (z) {
            this.a.g.h.add(this.a.g.b.format(calendarDay.getDate()));
        } else {
            this.a.g.h.remove(this.a.g.b.format(calendarDay.getDate()));
        }
        this.a.g.q();
    }
}
