package com.prolificinteractive.materialcalendarview;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView.ShowOtherDates;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;
import java.util.List;

@Experimental
public class WeekPagerAdapter extends d<WeekView> {
    protected /* synthetic */ CalendarPagerView a(int i) {
        return b(i);
    }

    public /* bridge */ /* synthetic */ void clearSelections() {
        super.clearSelections();
    }

    public /* bridge */ /* synthetic */ void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        super.destroyItem(viewGroup, i, obj);
    }

    public /* bridge */ /* synthetic */ int getCount() {
        return super.getCount();
    }

    public /* bridge */ /* synthetic */ int getIndexForDay(CalendarDay calendarDay) {
        return super.getIndexForDay(calendarDay);
    }

    public /* bridge */ /* synthetic */ CalendarDay getItem(int i) {
        return super.getItem(i);
    }

    public /* bridge */ /* synthetic */ int getItemPosition(Object obj) {
        return super.getItemPosition(obj);
    }

    public /* bridge */ /* synthetic */ CharSequence getPageTitle(int i) {
        return super.getPageTitle(i);
    }

    public /* bridge */ /* synthetic */ e getRangeIndex() {
        return super.getRangeIndex();
    }

    @NonNull
    public /* bridge */ /* synthetic */ List getSelectedDates() {
        return super.getSelectedDates();
    }

    @ShowOtherDates
    public /* bridge */ /* synthetic */ int getShowOtherDates() {
        return super.getShowOtherDates();
    }

    public /* bridge */ /* synthetic */ Object instantiateItem(ViewGroup viewGroup, int i) {
        return super.instantiateItem(viewGroup, i);
    }

    public /* bridge */ /* synthetic */ void invalidateDecorators() {
        super.invalidateDecorators();
    }

    public /* bridge */ /* synthetic */ boolean isViewFromObject(View view, Object obj) {
        return super.isViewFromObject(view, obj);
    }

    public /* bridge */ /* synthetic */ d migrateStateAndReturn(d dVar) {
        return super.migrateStateAndReturn(dVar);
    }

    public /* bridge */ /* synthetic */ void setDateSelected(CalendarDay calendarDay, boolean z) {
        super.setDateSelected(calendarDay, z);
    }

    public /* bridge */ /* synthetic */ void setDateTextAppearance(int i) {
        super.setDateTextAppearance(i);
    }

    public /* bridge */ /* synthetic */ void setDayFormatter(DayFormatter dayFormatter) {
        super.setDayFormatter(dayFormatter);
    }

    public /* bridge */ /* synthetic */ void setDecorators(List list) {
        super.setDecorators(list);
    }

    public /* bridge */ /* synthetic */ void setRangeDates(CalendarDay calendarDay, CalendarDay calendarDay2) {
        super.setRangeDates(calendarDay, calendarDay2);
    }

    public /* bridge */ /* synthetic */ void setSelectionColor(int i) {
        super.setSelectionColor(i);
    }

    public /* bridge */ /* synthetic */ void setSelectionEnabled(boolean z) {
        super.setSelectionEnabled(z);
    }

    public /* bridge */ /* synthetic */ void setShowOtherDates(@ShowOtherDates int i) {
        super.setShowOtherDates(i);
    }

    public /* bridge */ /* synthetic */ void setTitleFormatter(@NonNull TitleFormatter titleFormatter) {
        super.setTitleFormatter(titleFormatter);
    }

    public /* bridge */ /* synthetic */ void setWeekDayFormatter(WeekDayFormatter weekDayFormatter) {
        super.setWeekDayFormatter(weekDayFormatter);
    }

    public /* bridge */ /* synthetic */ void setWeekDayTextAppearance(int i) {
        super.setWeekDayTextAppearance(i);
    }

    public WeekPagerAdapter(MaterialCalendarView materialCalendarView) {
        super(materialCalendarView);
    }

    protected WeekView b(int i) {
        return new WeekView(this.a, getItem(i), this.a.getFirstDayOfWeek());
    }

    protected int a(WeekView weekView) {
        return getRangeIndex().indexOf(weekView.getFirstViewDay());
    }

    protected boolean a(Object obj) {
        return obj instanceof WeekView;
    }

    protected e a(CalendarDay calendarDay, CalendarDay calendarDay2) {
        return new WeekPagerAdapter$Weekly(calendarDay, calendarDay2, this.a.getFirstDayOfWeek());
    }
}
