package org.greenrobot.eventbus;

import android.os.Looper;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

public class EventBus {
    public static String TAG = "EventBus";
    static volatile EventBus a;
    private static final EventBusBuilder b = new EventBusBuilder();
    private static final Map<Class<?>, List<Class<?>>> c = new HashMap();
    private final Map<Class<?>, CopyOnWriteArrayList<i>> d;
    private final Map<Object, List<Class<?>>> e;
    private final Map<Class<?>, Object> f;
    private final ThreadLocal<a> g;
    private final e h;
    private final b i;
    private final a j;
    private final h k;
    private final ExecutorService l;
    private final boolean m;
    private final boolean n;
    private final boolean o;
    private final boolean p;
    private final boolean q;
    private final boolean r;
    private final int s;

    static final class a {
        final List<Object> a = new ArrayList();
        boolean b;
        boolean c;
        i d;
        Object e;
        boolean f;

        a() {
        }
    }

    public static EventBus getDefault() {
        if (a == null) {
            synchronized (EventBus.class) {
                if (a == null) {
                    a = new EventBus();
                }
            }
        }
        return a;
    }

    public static EventBusBuilder builder() {
        return new EventBusBuilder();
    }

    public static void clearCaches() {
        h.a();
        c.clear();
    }

    public EventBus() {
        this(b);
    }

    EventBus(EventBusBuilder eventBusBuilder) {
        this.g = new c(this);
        this.d = new HashMap();
        this.e = new HashMap();
        this.f = new ConcurrentHashMap();
        this.h = new e(this, Looper.getMainLooper(), 10);
        this.i = new b(this);
        this.j = new a(this);
        this.s = eventBusBuilder.k != null ? eventBusBuilder.k.size() : 0;
        this.k = new h(eventBusBuilder.k, eventBusBuilder.h, eventBusBuilder.g);
        this.n = eventBusBuilder.a;
        this.o = eventBusBuilder.b;
        this.p = eventBusBuilder.c;
        this.q = eventBusBuilder.d;
        this.m = eventBusBuilder.e;
        this.r = eventBusBuilder.f;
        this.l = eventBusBuilder.i;
    }

    public void register(Object obj) {
        List<SubscriberMethod> a = this.k.a(obj.getClass());
        synchronized (this) {
            for (SubscriberMethod a2 : a) {
                a(obj, a2);
            }
        }
    }

    private void a(Object obj, SubscriberMethod subscriberMethod) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        Class cls = subscriberMethod.c;
        i iVar = new i(obj, subscriberMethod);
        CopyOnWriteArrayList copyOnWriteArrayList2 = (CopyOnWriteArrayList) this.d.get(cls);
        if (copyOnWriteArrayList2 == null) {
            copyOnWriteArrayList2 = new CopyOnWriteArrayList();
            this.d.put(cls, copyOnWriteArrayList2);
            copyOnWriteArrayList = copyOnWriteArrayList2;
        } else if (copyOnWriteArrayList2.contains(iVar)) {
            throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
        } else {
            copyOnWriteArrayList = copyOnWriteArrayList2;
        }
        int size = copyOnWriteArrayList.size();
        int i = 0;
        while (i <= size) {
            if (i == size || subscriberMethod.d > ((i) copyOnWriteArrayList.get(i)).b.d) {
                copyOnWriteArrayList.add(i, iVar);
                break;
            }
            i++;
        }
        List list = (List) this.e.get(obj);
        if (list == null) {
            list = new ArrayList();
            this.e.put(obj, list);
        }
        list.add(cls);
        if (!subscriberMethod.e) {
            return;
        }
        if (this.r) {
            for (Entry entry : this.f.entrySet()) {
                if (cls.isAssignableFrom((Class) entry.getKey())) {
                    b(iVar, entry.getValue());
                }
            }
            return;
        }
        b(iVar, this.f.get(cls));
    }

    private void b(i iVar, Object obj) {
        if (obj != null) {
            a(iVar, obj, Looper.getMainLooper() == Looper.myLooper());
        }
    }

    public synchronized boolean isRegistered(Object obj) {
        return this.e.containsKey(obj);
    }

    private void a(Object obj, Class<?> cls) {
        List list = (List) this.d.get(cls);
        if (list != null) {
            int size = list.size();
            int i = 0;
            while (i < size) {
                int i2;
                i iVar = (i) list.get(i);
                if (iVar.a == obj) {
                    iVar.c = false;
                    list.remove(i);
                    i2 = i - 1;
                    i = size - 1;
                } else {
                    i2 = i;
                    i = size;
                }
                size = i;
                i = i2 + 1;
            }
        }
    }

    public synchronized void unregister(Object obj) {
        List<Class> list = (List) this.e.get(obj);
        if (list != null) {
            for (Class a : list) {
                a(obj, a);
            }
            this.e.remove(obj);
        } else {
            Log.w(TAG, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public void post(Object obj) {
        a aVar = (a) this.g.get();
        List list = aVar.a;
        list.add(obj);
        if (!aVar.b) {
            boolean z;
            if (Looper.getMainLooper() == Looper.myLooper()) {
                z = true;
            } else {
                z = false;
            }
            aVar.c = z;
            aVar.b = true;
            if (aVar.f) {
                throw new EventBusException("Internal error. Abort state was not reset");
            }
            while (!list.isEmpty()) {
                try {
                    a(list.remove(0), aVar);
                } finally {
                    aVar.b = false;
                    aVar.c = false;
                }
            }
        }
    }

    public void cancelEventDelivery(Object obj) {
        a aVar = (a) this.g.get();
        if (!aVar.b) {
            throw new EventBusException("This method may only be called from inside event handling methods on the posting thread");
        } else if (obj == null) {
            throw new EventBusException("Event may not be null");
        } else if (aVar.e != obj) {
            throw new EventBusException("Only the currently handled event may be aborted");
        } else if (aVar.d.b.b != ThreadMode.POSTING) {
            throw new EventBusException(" event handlers may only abort the incoming event");
        } else {
            aVar.f = true;
        }
    }

    public void postSticky(Object obj) {
        synchronized (this.f) {
            this.f.put(obj.getClass(), obj);
        }
        post(obj);
    }

    public <T> T getStickyEvent(Class<T> cls) {
        T cast;
        synchronized (this.f) {
            cast = cls.cast(this.f.get(cls));
        }
        return cast;
    }

    public <T> T removeStickyEvent(Class<T> cls) {
        T cast;
        synchronized (this.f) {
            cast = cls.cast(this.f.remove(cls));
        }
        return cast;
    }

    public boolean removeStickyEvent(Object obj) {
        boolean z;
        synchronized (this.f) {
            Class cls = obj.getClass();
            if (obj.equals(this.f.get(cls))) {
                this.f.remove(cls);
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public void removeAllStickyEvents() {
        synchronized (this.f) {
            this.f.clear();
        }
    }

    public boolean hasSubscriberForEvent(Class<?> cls) {
        List a = a((Class) cls);
        if (a != null) {
            int size = a.size();
            for (int i = 0; i < size; i++) {
                CopyOnWriteArrayList copyOnWriteArrayList;
                Class cls2 = (Class) a.get(i);
                synchronized (this) {
                    copyOnWriteArrayList = (CopyOnWriteArrayList) this.d.get(cls2);
                }
                if (copyOnWriteArrayList != null && !copyOnWriteArrayList.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void a(Object obj, a aVar) throws Error {
        boolean z;
        Class cls = obj.getClass();
        if (this.r) {
            List a = a(cls);
            boolean z2 = false;
            for (int i = 0; i < a.size(); i++) {
                z2 |= a(obj, aVar, (Class) a.get(i));
            }
            z = z2;
        } else {
            z = a(obj, aVar, cls);
        }
        if (!z) {
            if (this.o) {
                Log.d(TAG, "No subscribers registered for event " + cls);
            }
            if (this.q && cls != NoSubscriberEvent.class && cls != SubscriberExceptionEvent.class) {
                post(new NoSubscriberEvent(this, obj));
            }
        }
    }

    private boolean a(Object obj, a aVar, Class<?> cls) {
        synchronized (this) {
            CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.d.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        Iterator it = copyOnWriteArrayList.iterator();
        loop0:
        while (it.hasNext()) {
            i iVar = (i) it.next();
            aVar.e = obj;
            aVar.d = iVar;
            try {
                a(iVar, obj, aVar.c);
                Object obj2 = aVar.f;
                continue;
            } finally {
                aVar.e = null;
                aVar.d = null;
                aVar.f = false;
            }
            if (obj2 != null) {
                break loop0;
            }
        }
        return true;
    }

    private void a(i iVar, Object obj, boolean z) {
        switch (d.a[iVar.b.b.ordinal()]) {
            case 1:
                a(iVar, obj);
                return;
            case 2:
                if (z) {
                    a(iVar, obj);
                    return;
                } else {
                    this.h.a(iVar, obj);
                    return;
                }
            case 3:
                if (z) {
                    this.i.enqueue(iVar, obj);
                    return;
                } else {
                    a(iVar, obj);
                    return;
                }
            case 4:
                this.j.enqueue(iVar, obj);
                return;
            default:
                throw new IllegalStateException("Unknown thread mode: " + iVar.b.b);
        }
    }

    private static List<Class<?>> a(Class<?> cls) {
        List<Class<?>> list;
        synchronized (c) {
            list = (List) c.get(cls);
            if (list == null) {
                list = new ArrayList();
                for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    list.add(cls2);
                    a((List) list, cls2.getInterfaces());
                }
                c.put(cls, list);
            }
        }
        return list;
    }

    static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                a((List) list, cls.getInterfaces());
            }
        }
    }

    void a(f fVar) {
        Object obj = fVar.a;
        i iVar = fVar.b;
        f.a(fVar);
        if (iVar.c) {
            a(iVar, obj);
        }
    }

    void a(i iVar, Object obj) {
        try {
            iVar.b.a.invoke(iVar.a, new Object[]{obj});
        } catch (InvocationTargetException e) {
            a(iVar, obj, e.getCause());
        } catch (Throwable e2) {
            throw new IllegalStateException("Unexpected exception", e2);
        }
    }

    private void a(i iVar, Object obj, Throwable th) {
        if (obj instanceof SubscriberExceptionEvent) {
            if (this.n) {
                Log.e(TAG, "SubscriberExceptionEvent subscriber " + iVar.a.getClass() + " threw an exception", th);
                SubscriberExceptionEvent subscriberExceptionEvent = (SubscriberExceptionEvent) obj;
                Log.e(TAG, "Initial event " + subscriberExceptionEvent.causingEvent + " caused exception in " + subscriberExceptionEvent.causingSubscriber, subscriberExceptionEvent.throwable);
            }
        } else if (this.m) {
            throw new EventBusException("Invoking subscriber failed", th);
        } else {
            if (this.n) {
                Log.e(TAG, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + iVar.a.getClass(), th);
            }
            if (this.p) {
                post(new SubscriberExceptionEvent(this, th, obj, iVar.a));
            }
        }
    }

    ExecutorService a() {
        return this.l;
    }

    public String toString() {
        return "EventBus[indexCount=" + this.s + ", eventInheritance=" + this.r + "]";
    }
}
