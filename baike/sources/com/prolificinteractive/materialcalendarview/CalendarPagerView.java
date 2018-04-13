package com.prolificinteractive.materialcalendarview;

import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView.ShowOtherDates;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

abstract class CalendarPagerView extends ViewGroup implements OnClickListener {
    private static final Calendar b = CalendarUtils.getInstance();
    @ShowOtherDates
    protected int a = 4;
    private final ArrayList<q> c = new ArrayList();
    private final ArrayList<g> d = new ArrayList();
    private final Collection<f> e = new ArrayList();
    private MaterialCalendarView f;
    private CalendarDay g;
    private CalendarDay h = null;
    private CalendarDay i = null;
    private int j;

    protected abstract boolean a(CalendarDay calendarDay);

    protected abstract void b(Collection<f> collection, Calendar calendar);

    protected abstract int getRows();

    protected /* synthetic */ LayoutParams generateDefaultLayoutParams() {
        return d();
    }

    public CalendarPagerView(@NonNull MaterialCalendarView materialCalendarView, CalendarDay calendarDay, int i) {
        super(materialCalendarView.getContext());
        this.f = materialCalendarView;
        this.g = calendarDay;
        this.j = i;
        setClipChildren(false);
        setClipToPadding(false);
        a(a());
        b(this.e, a());
    }

    private void a(Calendar calendar) {
        for (int i = 0; i < 7; i++) {
            View qVar = new q(getContext(), CalendarUtils.getDayOfWeek(calendar));
            this.c.add(qVar);
            addView(qVar);
            calendar.add(5, 1);
        }
    }

    protected void a(Collection<f> collection, Calendar calendar) {
        View fVar = new f(getContext(), CalendarDay.from(calendar));
        fVar.setOnClickListener(this);
        collection.add(fVar);
        addView(fVar, new CalendarPagerView$LayoutParams());
        calendar.add(5, 1);
    }

    protected Calendar a() {
        Object obj = 1;
        getFirstViewDay().copyTo(b);
        b.setFirstDayOfWeek(getFirstDayOfWeek());
        int firstDayOfWeek = getFirstDayOfWeek() - CalendarUtils.getDayOfWeek(b);
        if (MaterialCalendarView.showOtherMonths(this.a)) {
            if (firstDayOfWeek < 0) {
                obj = null;
            }
        } else if (firstDayOfWeek <= 0) {
            obj = null;
        }
        if (obj != null) {
            firstDayOfWeek -= 7;
        }
        b.add(5, firstDayOfWeek);
        return b;
    }

    protected int getFirstDayOfWeek() {
        return this.j;
    }

    void setDayViewDecorators(List<g> list) {
        this.d.clear();
        if (list != null) {
            this.d.addAll(list);
        }
        c();
    }

    public void setWeekDayTextAppearance(int i) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((q) it.next()).setTextAppearance(getContext(), i);
        }
    }

    public void setDateTextAppearance(int i) {
        for (f textAppearance : this.e) {
            textAppearance.setTextAppearance(getContext(), i);
        }
    }

    public void setShowOtherDates(@ShowOtherDates int i) {
        this.a = i;
        b();
    }

    public void setSelectionEnabled(boolean z) {
        for (f fVar : this.e) {
            fVar.setOnClickListener(z ? this : null);
            fVar.setClickable(z);
        }
    }

    public void setSelectionColor(int i) {
        for (f selectionColor : this.e) {
            selectionColor.setSelectionColor(i);
        }
    }

    public void setWeekDayFormatter(WeekDayFormatter weekDayFormatter) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((q) it.next()).setWeekDayFormatter(weekDayFormatter);
        }
    }

    public void setDayFormatter(DayFormatter dayFormatter) {
        for (f dayFormatter2 : this.e) {
            dayFormatter2.setDayFormatter(dayFormatter);
        }
    }

    public void setMinimumDate(CalendarDay calendarDay) {
        this.h = calendarDay;
        b();
    }

    public void setMaximumDate(CalendarDay calendarDay) {
        this.i = calendarDay;
        b();
    }

    public void setSelectedDates(Collection<CalendarDay> collection) {
        for (f fVar : this.e) {
            boolean z = collection != null && collection.contains(fVar.getDate());
            fVar.setChecked(z);
        }
        postInvalidate();
    }

    protected void b() {
        for (f fVar : this.e) {
            CalendarDay date = fVar.getDate();
            fVar.a(this.a, date.isInRange(this.h, this.i), a(date));
        }
        postInvalidate();
    }

    protected void c() {
        DayViewFacade dayViewFacade = new DayViewFacade();
        for (f fVar : this.e) {
            dayViewFacade.a();
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                g gVar = (g) it.next();
                if (gVar.decorator.shouldDecorate(fVar.getDate())) {
                    gVar.result.a(dayViewFacade);
                }
            }
            fVar.a(dayViewFacade);
        }
    }

    public void onClick(View view) {
        if (view instanceof f) {
            this.f.a((f) view);
        }
    }

    protected CalendarPagerView$LayoutParams d() {
        return new CalendarPagerView$LayoutParams();
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        if (MeasureSpec.getMode(i2) == 0 || mode == 0) {
            throw new IllegalStateException("CalendarPagerView should never be left to decide it's size");
        }
        mode = size / 7;
        int rows = size2 / getRows();
        setMeasuredDimension(size, size2);
        size2 = getChildCount();
        for (size = 0; size < size2; size++) {
            getChildAt(size).measure(MeasureSpec.makeMeasureSpec(mode, 1073741824), MeasureSpec.makeMeasureSpec(rows, 1073741824));
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            childAt.layout(i5, i6, i5 + measuredWidth, i6 + measuredHeight);
            i5 += measuredWidth;
            if (i7 % 7 == 6) {
                i6 += measuredHeight;
                i5 = 0;
            }
        }
    }

    public CalendarPagerView$LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new CalendarPagerView$LayoutParams();
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof CalendarPagerView$LayoutParams;
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return new CalendarPagerView$LayoutParams();
    }

    public void onInitializeAccessibilityEvent(@NonNull AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(CalendarPagerView.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(CalendarPagerView.class.getName());
    }

    protected CalendarDay getFirstViewDay() {
        return this.g;
    }
}
