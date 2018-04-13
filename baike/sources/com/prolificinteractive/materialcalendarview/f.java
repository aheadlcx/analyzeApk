package com.prolificinteractive.materialcalendarview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.widget.CheckedTextView;
import com.prolificinteractive.materialcalendarview.DayViewFacade.a;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView.ShowOtherDates;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import java.util.Calendar;
import java.util.List;

@SuppressLint({"ViewConstructor"})
class f extends CheckedTextView {
    private final int a = getResources().getInteger(17694720);
    private final Rect b = new Rect();
    private final Rect c = new Rect();
    private CalendarDay d;
    private int e = -7829368;
    private Drawable f = null;
    private Drawable g;
    private Drawable h;
    private DayFormatter i = DayFormatter.DEFAULT;
    private boolean j = true;
    private boolean k = true;
    private boolean l = false;
    @ShowOtherDates
    private int m = 4;

    public f(Context context, CalendarDay calendarDay) {
        super(context);
        setSelectionColor(this.e);
        setGravity(17);
        if (VERSION.SDK_INT >= 17) {
            setTextAlignment(4);
        }
        setDay(calendarDay);
    }

    private static Drawable a(int i, int i2, Rect rect) {
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.setExitFadeDuration(i2);
        stateListDrawable.addState(new int[]{16842912}, a(i));
        if (VERSION.SDK_INT >= 21) {
            stateListDrawable.addState(new int[]{16842919}, a(i, rect));
        } else {
            stateListDrawable.addState(new int[]{16842919}, a(i));
        }
        stateListDrawable.addState(new int[0], a(0));
        return stateListDrawable;
    }

    private static Drawable a(int i) {
        Drawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(i);
        return shapeDrawable;
    }

    @TargetApi(21)
    private static Drawable a(int i, Rect rect) {
        Drawable rippleDrawable = new RippleDrawable(ColorStateList.valueOf(i), null, a(-1));
        if (VERSION.SDK_INT == 21) {
            rippleDrawable.setBounds(rect);
        }
        if (VERSION.SDK_INT == 22) {
            int i2 = (rect.left + rect.right) / 2;
            rippleDrawable.setHotspotBounds(i2, rect.top, i2, rect.bottom);
        }
        return rippleDrawable;
    }

    public void setDay(CalendarDay calendarDay) {
        this.d = calendarDay;
        setText(getLabel());
    }

    public void setDayFormatter(DayFormatter dayFormatter) {
        if (dayFormatter == null) {
            dayFormatter = DayFormatter.DEFAULT;
        }
        this.i = dayFormatter;
        CharSequence text = getText();
        Object[] objArr = null;
        if (text instanceof Spanned) {
            objArr = ((Spanned) text).getSpans(0, text.length(), Object.class);
        }
        CharSequence spannableString = new SpannableString(getLabel());
        if (objArr != null) {
            for (Object span : objArr) {
                spannableString.setSpan(span, 0, spannableString.length(), 33);
            }
        }
        setText(spannableString);
    }

    @NonNull
    public String getLabel() {
        if (this.d.getCalendar().get(1) == Calendar.getInstance().get(1) && this.d.getCalendar().get(6) == Calendar.getInstance().get(6)) {
            return "今";
        }
        return this.i.format(this.d);
    }

    public void setSelectionColor(int i) {
        this.e = i;
        b();
    }

    public void setSelectionDrawable(Drawable drawable) {
        if (drawable == null) {
            this.g = null;
        } else {
            this.g = drawable.getConstantState().newDrawable(getResources());
        }
        b();
    }

    public void setCustomBackground(Drawable drawable) {
        if (drawable == null) {
            this.f = null;
        } else {
            this.f = drawable.getConstantState().newDrawable(getResources());
        }
        invalidate();
    }

    public CalendarDay getDate() {
        return this.d;
    }

    private void a() {
        boolean z;
        int i;
        int i2 = 0;
        int i3 = (this.k && this.j && !this.l) ? 1 : 0;
        if (!this.j || this.l) {
            z = false;
        } else {
            z = true;
        }
        super.setEnabled(z);
        boolean showOtherMonths = MaterialCalendarView.showOtherMonths(this.m);
        if (MaterialCalendarView.showOutOfRange(this.m) || showOtherMonths) {
            i = 1;
        } else {
            i = 0;
        }
        boolean showDecoratedDisabled = MaterialCalendarView.showDecoratedDisabled(this.m);
        if (!this.k && showOtherMonths) {
            i3 = 1;
        }
        if (!(this.j || r3 == 0)) {
            i3 |= this.k;
        }
        if (this.l && showDecoratedDisabled) {
            i = (this.k && this.j) ? 1 : 0;
            i3 |= i;
        }
        if (!(this.k || i3 == 0)) {
            setTextColor(getTextColors().getColorForState(new int[]{-16842910}, -7829368));
        }
        if (i3 == 0) {
            i2 = 4;
        }
        setVisibility(i2);
    }

    protected void a(@ShowOtherDates int i, boolean z, boolean z2) {
        this.m = i;
        this.k = z2;
        this.j = z;
        a();
    }

    protected void onDraw(@NonNull Canvas canvas) {
        if (this.f != null) {
            this.f.setBounds(this.b);
            this.f.setState(getDrawableState());
            this.f.draw(canvas);
        }
        this.h.setBounds(this.c);
        super.onDraw(canvas);
    }

    private void b() {
        if (this.g != null) {
            setBackgroundDrawable(this.g);
            return;
        }
        this.h = a(this.e, this.a, this.c);
        setBackgroundDrawable(this.h);
    }

    void a(DayViewFacade dayViewFacade) {
        this.l = dayViewFacade.areDaysDisabled();
        a();
        setCustomBackground(dayViewFacade.d());
        setSelectionDrawable(dayViewFacade.c());
        List<a> e = dayViewFacade.e();
        if (e.isEmpty()) {
            setText(getLabel());
            return;
        }
        String label = getLabel();
        CharSequence spannableString = new SpannableString(getLabel());
        for (a aVar : e) {
            spannableString.setSpan(aVar.a, 0, label.length(), 33);
        }
        setText(spannableString);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a(i3 - i, i4 - i2);
        b();
    }

    private void a(int i, int i2) {
        int min = Math.min(i2, i);
        int abs = Math.abs(i2 - i) / 2;
        int i3 = VERSION.SDK_INT == 21 ? abs / 2 : abs;
        if (i >= i2) {
            this.b.set(abs, 0, min + abs, i2);
            this.c.set(i3, 0, min + i3, i2);
            return;
        }
        this.b.set(0, abs, i, min + abs);
        this.c.set(0, i3, i, min + i3);
    }
}
