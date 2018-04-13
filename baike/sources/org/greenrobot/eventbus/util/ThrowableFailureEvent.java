package org.greenrobot.eventbus.util;

public class ThrowableFailureEvent implements HasExecutionScope {
    protected final Throwable a;
    protected final boolean b;
    private Object c;

    public ThrowableFailureEvent(Throwable th) {
        this.a = th;
        this.b = false;
    }

    public ThrowableFailureEvent(Throwable th, boolean z) {
        this.a = th;
        this.b = z;
    }

    public Throwable getThrowable() {
        return this.a;
    }

    public boolean isSuppressErrorUi() {
        return this.b;
    }

    public Object getExecutionScope() {
        return this.c;
    }

    public void setExecutionScope(Object obj) {
        this.c = obj;
    }
}
