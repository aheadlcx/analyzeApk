package com.facebook.drawee.c;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class a {
    a a;
    final float b;
    boolean c;
    boolean d;
    long e;
    float f;
    float g;

    public interface a {
        boolean onClick();
    }

    public a(Context context) {
        this.b = (float) ViewConfiguration.get(context).getScaledTouchSlop();
        a();
    }

    public static a a(Context context) {
        return new a(context);
    }

    public void a() {
        this.a = null;
        b();
    }

    public void b() {
        this.c = false;
        this.d = false;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public boolean c() {
        return this.c;
    }

    public boolean a(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.c = true;
                this.d = true;
                this.e = motionEvent.getEventTime();
                this.f = motionEvent.getX();
                this.g = motionEvent.getY();
                break;
            case 1:
                this.c = false;
                if (Math.abs(motionEvent.getX() - this.f) > this.b || Math.abs(motionEvent.getY() - this.g) > this.b) {
                    this.d = false;
                }
                if (this.d && motionEvent.getEventTime() - this.e <= ((long) ViewConfiguration.getLongPressTimeout()) && this.a != null) {
                    this.a.onClick();
                }
                this.d = false;
                break;
            case 2:
                if (Math.abs(motionEvent.getX() - this.f) > this.b || Math.abs(motionEvent.getY() - this.g) > this.b) {
                    this.d = false;
                    break;
                }
            case 3:
                this.c = false;
                this.d = false;
                break;
        }
        return true;
    }
}
