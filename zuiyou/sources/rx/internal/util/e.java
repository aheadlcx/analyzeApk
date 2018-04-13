package rx.internal.util;

import java.util.Queue;
import rx.exceptions.MissingBackpressureException;
import rx.internal.operators.NotificationLite;
import rx.internal.util.a.d;
import rx.internal.util.a.l;
import rx.internal.util.a.y;
import rx.internal.util.atomic.b;
import rx.k;

public class e implements k {
    public static final int b;
    public volatile Object a;
    private Queue<Object> c;
    private final int d;

    static {
        int i = 128;
        if (d.a()) {
            i = 16;
        }
        String property = System.getProperty("rx.ring-buffer.size");
        if (property != null) {
            try {
                i = Integer.parseInt(property);
            } catch (NumberFormatException e) {
                System.err.println("Failed to set 'rx.buffer.size' with value " + property + " => " + e.getMessage());
            }
        }
        b = i;
    }

    public static e a() {
        if (y.a()) {
            return new e(false, b);
        }
        return new e();
    }

    private e(Queue<Object> queue, int i) {
        this.c = queue;
        this.d = i;
    }

    private e(boolean z, int i) {
        this.c = z ? new d(i) : new l(i);
        this.d = i;
    }

    public synchronized void b() {
    }

    public void unsubscribe() {
        b();
    }

    e() {
        this(new b(b), b);
    }

    public void a(Object obj) throws MissingBackpressureException {
        Object obj2 = 1;
        Object obj3 = null;
        synchronized (this) {
            Queue queue = this.c;
            if (queue == null) {
                int i = 1;
                obj2 = null;
            } else if (queue.offer(NotificationLite.a(obj))) {
                obj2 = null;
            }
        }
        if (obj3 != null) {
            throw new IllegalStateException("This instance has been unsubscribed and the queue is no longer usable.");
        } else if (obj2 != null) {
            throw new MissingBackpressureException();
        }
    }

    public boolean c() {
        Queue queue = this.c;
        return queue == null || queue.isEmpty();
    }

    public Object d() {
        Object obj = null;
        synchronized (this) {
            Queue queue = this.c;
            if (queue == null) {
            } else {
                Object poll = queue.poll();
                obj = this.a;
                if (poll == null && obj != null && queue.peek() == null) {
                    this.a = null;
                } else {
                    obj = poll;
                }
            }
        }
        return obj;
    }

    public boolean isUnsubscribed() {
        return this.c == null;
    }
}
