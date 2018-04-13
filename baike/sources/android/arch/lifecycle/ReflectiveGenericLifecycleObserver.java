package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class ReflectiveGenericLifecycleObserver implements GenericLifecycleObserver {
    static final Map<Class, a> a = new HashMap();
    private final Object b;
    private final a c = a(this.b.getClass());

    static class a {
        final Map<Event, List<b>> a = new HashMap();
        final Map<b, Event> b;

        a(Map<b, Event> map) {
            this.b = map;
            for (Entry entry : map.entrySet()) {
                Event event = (Event) entry.getValue();
                List list = (List) this.a.get(event);
                if (list == null) {
                    list = new ArrayList();
                    this.a.put(event, list);
                }
                list.add(entry.getKey());
            }
        }
    }

    static class b {
        final int a;
        final Method b;

        b(int i, Method method) {
            this.a = i;
            this.b = method;
            this.b.setAccessible(true);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            if (this.a == bVar.a && this.b.getName().equals(bVar.b.getName())) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.a * 31) + this.b.getName().hashCode();
        }
    }

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.b = obj;
    }

    public void onStateChanged(LifecycleOwner lifecycleOwner, Event event) {
        a(this.c, lifecycleOwner, event);
    }

    private void a(List<b> list, LifecycleOwner lifecycleOwner, Event event) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                a((b) list.get(size), lifecycleOwner, event);
            }
        }
    }

    private void a(a aVar, LifecycleOwner lifecycleOwner, Event event) {
        a((List) aVar.a.get(event), lifecycleOwner, event);
        a((List) aVar.a.get(Event.ON_ANY), lifecycleOwner, event);
    }

    private void a(b bVar, LifecycleOwner lifecycleOwner, Event event) {
        try {
            switch (bVar.a) {
                case 0:
                    bVar.b.invoke(this.b, new Object[0]);
                    return;
                case 1:
                    bVar.b.invoke(this.b, new Object[]{lifecycleOwner});
                    return;
                case 2:
                    bVar.b.invoke(this.b, new Object[]{lifecycleOwner, event});
                    return;
                default:
                    return;
            }
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Failed to call observer method", e.getCause());
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    private static a a(Class cls) {
        a aVar = (a) a.get(cls);
        if (aVar != null) {
            return aVar;
        }
        return b(cls);
    }

    private static void a(Map<b, Event> map, b bVar, Event event, Class cls) {
        Event event2 = (Event) map.get(bVar);
        if (event2 != null && event != event2) {
            throw new IllegalArgumentException("Method " + bVar.b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous" + " value " + event2 + ", new value " + event);
        } else if (event2 == null) {
            map.put(bVar, event);
        }
    }

    private static a b(Class cls) {
        a a;
        Class superclass = cls.getSuperclass();
        Map hashMap = new HashMap();
        if (superclass != null) {
            a = a(superclass);
            if (a != null) {
                hashMap.putAll(a.b);
            }
        }
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Class a2 : cls.getInterfaces()) {
            for (Entry entry : a(a2).b.entrySet()) {
                a(hashMap, (b) entry.getKey(), (Event) entry.getValue(), cls);
            }
        }
        for (Method method : declaredMethods) {
            OnLifecycleEvent onLifecycleEvent = (OnLifecycleEvent) method.getAnnotation(OnLifecycleEvent.class);
            if (onLifecycleEvent != null) {
                int i;
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else if (parameterTypes[0].isAssignableFrom(LifecycleOwner.class)) {
                    i = 1;
                } else {
                    throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                }
                Event value = onLifecycleEvent.value();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(Event.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    } else if (value != Event.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    } else {
                        i = 2;
                    }
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                a(hashMap, new b(i, method), value, cls);
            }
        }
        a = new a(hashMap);
        a.put(cls, a);
        return a;
    }
}
