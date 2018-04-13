package org.greenrobot.eventbus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

public class EventBusBuilder {
    private static final ExecutorService l = Executors.newCachedThreadPool();
    boolean a = true;
    boolean b = true;
    boolean c = true;
    boolean d = true;
    boolean e;
    boolean f = true;
    boolean g;
    boolean h;
    ExecutorService i = l;
    List<Class<?>> j;
    List<SubscriberInfoIndex> k;

    EventBusBuilder() {
    }

    public EventBusBuilder logSubscriberExceptions(boolean z) {
        this.a = z;
        return this;
    }

    public EventBusBuilder logNoSubscriberMessages(boolean z) {
        this.b = z;
        return this;
    }

    public EventBusBuilder sendSubscriberExceptionEvent(boolean z) {
        this.c = z;
        return this;
    }

    public EventBusBuilder sendNoSubscriberEvent(boolean z) {
        this.d = z;
        return this;
    }

    public EventBusBuilder throwSubscriberException(boolean z) {
        this.e = z;
        return this;
    }

    public EventBusBuilder eventInheritance(boolean z) {
        this.f = z;
        return this;
    }

    public EventBusBuilder executorService(ExecutorService executorService) {
        this.i = executorService;
        return this;
    }

    public EventBusBuilder skipMethodVerificationFor(Class<?> cls) {
        if (this.j == null) {
            this.j = new ArrayList();
        }
        this.j.add(cls);
        return this;
    }

    public EventBusBuilder ignoreGeneratedIndex(boolean z) {
        this.g = z;
        return this;
    }

    public EventBusBuilder strictMethodVerification(boolean z) {
        this.h = z;
        return this;
    }

    public EventBusBuilder addIndex(SubscriberInfoIndex subscriberInfoIndex) {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.add(subscriberInfoIndex);
        return this;
    }

    public EventBus installDefaultEventBus() {
        EventBus eventBus;
        synchronized (EventBus.class) {
            if (EventBus.a != null) {
                throw new EventBusException("Default instance already exists. It may be only set once before it's used the first time to ensure consistent behavior.");
            }
            EventBus.a = build();
            eventBus = EventBus.a;
        }
        return eventBus;
    }

    public EventBus build() {
        return new EventBus(this);
    }
}
