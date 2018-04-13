package org.greenrobot.eventbus.util;

import android.content.res.Resources;
import android.util.Log;
import org.greenrobot.eventbus.EventBus;

public class ErrorDialogConfig {
    final Resources a;
    final int b;
    final int c;
    final ExceptionToResourceMapping d;
    EventBus e;
    boolean f = true;
    String g;
    int h;
    Class<?> i;

    public ErrorDialogConfig(Resources resources, int i, int i2) {
        this.a = resources;
        this.b = i;
        this.c = i2;
        this.d = new ExceptionToResourceMapping();
    }

    public ErrorDialogConfig addMapping(Class<? extends Throwable> cls, int i) {
        this.d.addMapping(cls, i);
        return this;
    }

    public int getMessageIdForThrowable(Throwable th) {
        Integer mapThrowable = this.d.mapThrowable(th);
        if (mapThrowable != null) {
            return mapThrowable.intValue();
        }
        Log.d(EventBus.TAG, "No specific message ressource ID found for " + th);
        return this.c;
    }

    public void setDefaultDialogIconId(int i) {
        this.h = i;
    }

    public void setDefaultEventTypeOnDialogClosed(Class<?> cls) {
        this.i = cls;
    }

    public void disableExceptionLogging() {
        this.f = false;
    }

    public void setTagForLoggingExceptions(String str) {
        this.g = str;
    }

    public void setEventBus(EventBus eventBus) {
        this.e = eventBus;
    }

    EventBus a() {
        return this.e != null ? this.e : EventBus.getDefault();
    }
}
