package org.greenrobot.eventbus.meta;

import org.greenrobot.eventbus.EventBusException;
import org.greenrobot.eventbus.SubscriberMethod;
import org.greenrobot.eventbus.ThreadMode;

public abstract class AbstractSubscriberInfo implements SubscriberInfo {
    private final Class a;
    private final Class<? extends SubscriberInfo> b;
    private final boolean c;

    protected AbstractSubscriberInfo(Class cls, Class<? extends SubscriberInfo> cls2, boolean z) {
        this.a = cls;
        this.b = cls2;
        this.c = z;
    }

    public Class getSubscriberClass() {
        return this.a;
    }

    public SubscriberInfo getSuperSubscriberInfo() {
        Throwable e;
        if (this.b == null) {
            return null;
        }
        try {
            return (SubscriberInfo) this.b.newInstance();
        } catch (InstantiationException e2) {
            e = e2;
            throw new RuntimeException(e);
        } catch (IllegalAccessException e3) {
            e = e3;
            throw new RuntimeException(e);
        }
    }

    public boolean shouldCheckSuperclass() {
        return this.c;
    }

    protected SubscriberMethod a(String str, Class<?> cls, ThreadMode threadMode, int i, boolean z) {
        try {
            return new SubscriberMethod(this.a.getDeclaredMethod(str, new Class[]{cls}), cls, threadMode, i, z);
        } catch (Throwable e) {
            throw new EventBusException("Could not find subscriber method in " + this.a + ". Maybe a missing ProGuard rule?", e);
        }
    }
}
