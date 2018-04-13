package com.prolificinteractive.materialcalendarview;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DayViewFacade {
    private final LinkedList<a> a = new LinkedList();
    private boolean b = false;
    private Drawable c = null;
    private Drawable d = null;
    private boolean e = false;

    static class a {
        final Object a;

        public a(Object obj) {
            this.a = obj;
        }
    }

    DayViewFacade() {
    }

    public void addSpan(@NonNull Object obj) {
        if (this.a != null) {
            this.a.add(new a(obj));
            this.b = true;
        }
    }

    public void setDaysDisabled(boolean z) {
        this.e = z;
        this.b = true;
    }

    void a() {
        this.c = null;
        this.d = null;
        this.a.clear();
        this.b = false;
        this.e = false;
    }

    void a(DayViewFacade dayViewFacade) {
        if (this.d != null) {
            dayViewFacade.setSelectionDrawable(this.d);
        }
        if (this.c != null) {
            dayViewFacade.setBackgroundDrawable(this.c);
        }
        dayViewFacade.a.addAll(this.a);
        dayViewFacade.b |= this.b;
        dayViewFacade.e = this.e;
    }

    boolean b() {
        return this.b;
    }

    Drawable c() {
        return this.d;
    }

    public void setSelectionDrawable(@NonNull Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Cannot be null");
        }
        this.d = drawable;
        this.b = true;
    }

    Drawable d() {
        return this.c;
    }

    public void setBackgroundDrawable(@NonNull Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Cannot be null");
        }
        this.c = drawable;
        this.b = true;
    }

    List<a> e() {
        return Collections.unmodifiableList(this.a);
    }

    public boolean areDaysDisabled() {
        return this.e;
    }
}
