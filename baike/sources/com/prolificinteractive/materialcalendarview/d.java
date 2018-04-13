package com.prolificinteractive.materialcalendarview;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView.ShowOtherDates;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

abstract class d<V extends CalendarPagerView> extends PagerAdapter {
    protected final MaterialCalendarView a;
    private final ArrayDeque<V> b;
    private final CalendarDay c;
    private TitleFormatter d = null;
    private Integer e = null;
    private Integer f = null;
    private Integer g = null;
    @ShowOtherDates
    private int h = 4;
    private CalendarDay i = null;
    private CalendarDay j = null;
    private e k;
    private List<CalendarDay> l = new ArrayList();
    private WeekDayFormatter m = WeekDayFormatter.DEFAULT;
    private DayFormatter n = DayFormatter.DEFAULT;
    private List<DayViewDecorator> o = new ArrayList();
    private List<g> p = null;
    private boolean q = true;

    protected abstract int a(V v);

    protected abstract V a(int i);

    protected abstract e a(CalendarDay calendarDay, CalendarDay calendarDay2);

    protected abstract boolean a(Object obj);

    d(MaterialCalendarView materialCalendarView) {
        this.a = materialCalendarView;
        this.c = CalendarDay.today();
        this.b = new ArrayDeque();
        this.b.iterator();
        setRangeDates(null, null);
    }

    public void setDecorators(List<DayViewDecorator> list) {
        this.o = list;
        invalidateDecorators();
    }

    public void invalidateDecorators() {
        this.p = new ArrayList();
        for (DayViewDecorator dayViewDecorator : this.o) {
            DayViewFacade dayViewFacade = new DayViewFacade();
            dayViewDecorator.decorate(dayViewFacade);
            if (dayViewFacade.b()) {
                this.p.add(new g(dayViewDecorator, dayViewFacade));
            }
        }
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((CalendarPagerView) it.next()).setDayViewDecorators(this.p);
        }
    }

    public int getCount() {
        return this.k.getCount();
    }

    public CharSequence getPageTitle(int i) {
        return this.d == null ? "" : this.d.format(getItem(i));
    }

    public d<?> migrateStateAndReturn(d<?> dVar) {
        dVar.d = this.d;
        dVar.e = this.e;
        dVar.f = this.f;
        dVar.g = this.g;
        dVar.h = this.h;
        dVar.i = this.i;
        dVar.j = this.j;
        dVar.l = this.l;
        dVar.m = this.m;
        dVar.n = this.n;
        dVar.o = this.o;
        dVar.p = this.p;
        dVar.q = this.q;
        return dVar;
    }

    public int getIndexForDay(CalendarDay calendarDay) {
        if (calendarDay == null) {
            return getCount() / 2;
        }
        if (this.i != null && calendarDay.isBefore(this.i)) {
            return 0;
        }
        if (this.j == null || !calendarDay.isAfter(this.j)) {
            return this.k.indexOf(calendarDay);
        }
        return getCount() - 1;
    }

    public int getItemPosition(Object obj) {
        if (!a(obj)) {
            return -2;
        }
        if (((CalendarPagerView) obj).getFirstViewDay() == null) {
            return -2;
        }
        int a = a((CalendarPagerView) obj);
        if (a < 0) {
            return -2;
        }
        return a;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View a = a(i);
        a.setContentDescription(this.a.getCalendarContentDescription());
        a.setAlpha(0.0f);
        a.setSelectionEnabled(this.q);
        a.setWeekDayFormatter(this.m);
        a.setDayFormatter(this.n);
        if (this.e != null) {
            a.setSelectionColor(this.e.intValue());
        }
        if (this.f != null) {
            a.setDateTextAppearance(this.f.intValue());
        }
        if (this.g != null) {
            a.setWeekDayTextAppearance(this.g.intValue());
        }
        a.setShowOtherDates(this.h);
        a.setMinimumDate(this.i);
        a.setMaximumDate(this.j);
        a.setSelectedDates(this.l);
        viewGroup.addView(a);
        this.b.add(a);
        a.setDayViewDecorators(this.p);
        return a;
    }

    public void setSelectionEnabled(boolean z) {
        this.q = z;
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((CalendarPagerView) it.next()).setSelectionEnabled(this.q);
        }
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        CalendarPagerView calendarPagerView = (CalendarPagerView) obj;
        this.b.remove(calendarPagerView);
        viewGroup.removeView(calendarPagerView);
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void setTitleFormatter(@NonNull TitleFormatter titleFormatter) {
        this.d = titleFormatter;
    }

    public void setSelectionColor(int i) {
        this.e = Integer.valueOf(i);
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((CalendarPagerView) it.next()).setSelectionColor(i);
        }
    }

    public void setWeekDayFormatter(WeekDayFormatter weekDayFormatter) {
        this.m = weekDayFormatter;
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((CalendarPagerView) it.next()).setWeekDayFormatter(weekDayFormatter);
        }
    }

    public void setDayFormatter(DayFormatter dayFormatter) {
        this.n = dayFormatter;
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((CalendarPagerView) it.next()).setDayFormatter(dayFormatter);
        }
    }

    @ShowOtherDates
    public int getShowOtherDates() {
        return this.h;
    }

    public void setShowOtherDates(@ShowOtherDates int i) {
        this.h = i;
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((CalendarPagerView) it.next()).setShowOtherDates(i);
        }
    }

    public void setRangeDates(CalendarDay calendarDay, CalendarDay calendarDay2) {
        this.i = calendarDay;
        this.j = calendarDay2;
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            CalendarPagerView calendarPagerView = (CalendarPagerView) it.next();
            calendarPagerView.setMinimumDate(calendarDay);
            calendarPagerView.setMaximumDate(calendarDay2);
        }
        if (calendarDay == null) {
            calendarDay = CalendarDay.from(this.c.getYear() - 200, this.c.getMonth(), this.c.getDay());
        }
        if (calendarDay2 == null) {
            calendarDay2 = CalendarDay.from(this.c.getYear() + 200, this.c.getMonth(), this.c.getDay());
        }
        this.k = a(calendarDay, calendarDay2);
        notifyDataSetChanged();
        c();
    }

    public e getRangeIndex() {
        return this.k;
    }

    public void clearSelections() {
        this.l.clear();
        c();
    }

    public void setDateSelected(CalendarDay calendarDay, boolean z) {
        if (z) {
            if (!this.l.contains(calendarDay)) {
                this.l.add(calendarDay);
                c();
            }
        } else if (this.l.contains(calendarDay)) {
            this.l.remove(calendarDay);
            c();
        }
    }

    private void c() {
        d();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((CalendarPagerView) it.next()).setSelectedDates(this.l);
        }
    }

    private void d() {
        int i = 0;
        while (i < this.l.size()) {
            CalendarDay calendarDay = (CalendarDay) this.l.get(i);
            if ((this.i != null && this.i.isAfter(calendarDay)) || (this.j != null && this.j.isBefore(calendarDay))) {
                this.l.remove(i);
                this.a.b(calendarDay);
                i--;
            }
            i++;
        }
    }

    public CalendarDay getItem(int i) {
        return this.k.getItem(i);
    }

    @NonNull
    public List<CalendarDay> getSelectedDates() {
        return Collections.unmodifiableList(this.l);
    }

    protected int a() {
        return this.f == null ? 0 : this.f.intValue();
    }

    public void setDateTextAppearance(int i) {
        if (i != 0) {
            this.f = Integer.valueOf(i);
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                ((CalendarPagerView) it.next()).setDateTextAppearance(i);
            }
        }
    }

    protected int b() {
        return this.g == null ? 0 : this.g.intValue();
    }

    public void setWeekDayTextAppearance(int i) {
        if (i != 0) {
            this.g = Integer.valueOf(i);
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                ((CalendarPagerView) it.next()).setWeekDayTextAppearance(i);
            }
        }
    }
}
