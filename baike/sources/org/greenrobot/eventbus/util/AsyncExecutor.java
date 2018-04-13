package org.greenrobot.eventbus.util;

import android.app.Activity;
import java.lang.reflect.Constructor;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.greenrobot.eventbus.EventBus;

public class AsyncExecutor {
    private final Executor a;
    private final Constructor<?> b;
    private final EventBus c;
    private final Object d;

    public static class Builder {
        private Executor a;
        private Class<?> b;
        private EventBus c;

        private Builder() {
        }

        public Builder threadPool(Executor executor) {
            this.a = executor;
            return this;
        }

        public Builder failureEventType(Class<?> cls) {
            this.b = cls;
            return this;
        }

        public Builder eventBus(EventBus eventBus) {
            this.c = eventBus;
            return this;
        }

        public AsyncExecutor build() {
            return buildForScope(null);
        }

        public AsyncExecutor buildForActivityScope(Activity activity) {
            return buildForScope(activity.getClass());
        }

        public AsyncExecutor buildForScope(Object obj) {
            if (this.c == null) {
                this.c = EventBus.getDefault();
            }
            if (this.a == null) {
                this.a = Executors.newCachedThreadPool();
            }
            if (this.b == null) {
                this.b = ThrowableFailureEvent.class;
            }
            return new AsyncExecutor(this.a, this.c, this.b, obj);
        }
    }

    public interface RunnableEx {
        void run() throws Exception;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static AsyncExecutor create() {
        return new Builder().build();
    }

    private AsyncExecutor(Executor executor, EventBus eventBus, Class<?> cls, Object obj) {
        this.a = executor;
        this.c = eventBus;
        this.d = obj;
        try {
            this.b = cls.getConstructor(new Class[]{Throwable.class});
        } catch (Throwable e) {
            throw new RuntimeException("Failure event class must have a constructor with one parameter of type Throwable", e);
        }
    }

    public void execute(RunnableEx runnableEx) {
        this.a.execute(new a(this, runnableEx));
    }
}
