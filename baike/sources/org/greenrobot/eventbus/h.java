package org.greenrobot.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.text.Typography;
import org.greenrobot.eventbus.meta.SubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

class h {
    private static final Map<Class<?>, List<SubscriberMethod>> a = new ConcurrentHashMap();
    private static final a[] e = new a[4];
    private List<SubscriberInfoIndex> b;
    private final boolean c;
    private final boolean d;

    static class a {
        final List<SubscriberMethod> a = new ArrayList();
        final Map<Class, Object> b = new HashMap();
        final Map<String, Class> c = new HashMap();
        final StringBuilder d = new StringBuilder(128);
        Class<?> e;
        Class<?> f;
        boolean g;
        SubscriberInfo h;

        a() {
        }

        void a(Class<?> cls) {
            this.f = cls;
            this.e = cls;
            this.g = false;
            this.h = null;
        }

        void a() {
            this.a.clear();
            this.b.clear();
            this.c.clear();
            this.d.setLength(0);
            this.e = null;
            this.f = null;
            this.g = false;
            this.h = null;
        }

        boolean a(Method method, Class<?> cls) {
            Object put = this.b.put(cls, method);
            if (put == null) {
                return true;
            }
            if (put instanceof Method) {
                if (b((Method) put, cls)) {
                    this.b.put(cls, this);
                } else {
                    throw new IllegalStateException();
                }
            }
            return b(method, cls);
        }

        private boolean b(Method method, Class<?> cls) {
            this.d.setLength(0);
            this.d.append(method.getName());
            this.d.append(Typography.greater).append(cls.getName());
            String stringBuilder = this.d.toString();
            Class declaringClass = method.getDeclaringClass();
            Class cls2 = (Class) this.c.put(stringBuilder, declaringClass);
            if (cls2 == null || cls2.isAssignableFrom(declaringClass)) {
                return true;
            }
            this.c.put(stringBuilder, cls2);
            return false;
        }

        void b() {
            if (this.g) {
                this.f = null;
                return;
            }
            this.f = this.f.getSuperclass();
            String name = this.f.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                this.f = null;
            }
        }
    }

    h(List<SubscriberInfoIndex> list, boolean z, boolean z2) {
        this.b = list;
        this.c = z;
        this.d = z2;
    }

    List<SubscriberMethod> a(Class<?> cls) {
        List<SubscriberMethod> list = (List) a.get(cls);
        if (list == null) {
            if (this.d) {
                list = c((Class) cls);
            } else {
                list = b((Class) cls);
            }
            if (list.isEmpty()) {
                throw new EventBusException("Subscriber " + cls + " and its super classes have no public methods with the @Subscribe annotation");
            }
            a.put(cls, list);
        }
        return list;
    }

    private List<SubscriberMethod> b(Class<?> cls) {
        a b = b();
        b.a(cls);
        while (b.f != null) {
            b.h = b(b);
            if (b.h != null) {
                for (SubscriberMethod subscriberMethod : b.h.getSubscriberMethods()) {
                    if (b.a(subscriberMethod.a, subscriberMethod.c)) {
                        b.a.add(subscriberMethod);
                    }
                }
            } else {
                c(b);
            }
            b.b();
        }
        return a(b);
    }

    private List<SubscriberMethod> a(a aVar) {
        List arrayList = new ArrayList(aVar.a);
        aVar.a();
        synchronized (e) {
            for (int i = 0; i < 4; i++) {
                if (e[i] == null) {
                    e[i] = aVar;
                    break;
                }
            }
        }
        return arrayList;
    }

    private a b() {
        synchronized (e) {
            for (int i = 0; i < 4; i++) {
                a aVar = e[i];
                if (aVar != null) {
                    e[i] = null;
                    return aVar;
                }
            }
            return new a();
        }
    }

    private SubscriberInfo b(a aVar) {
        SubscriberInfo superSubscriberInfo;
        if (!(aVar.h == null || aVar.h.getSuperSubscriberInfo() == null)) {
            superSubscriberInfo = aVar.h.getSuperSubscriberInfo();
            if (aVar.f == superSubscriberInfo.getSubscriberClass()) {
                return superSubscriberInfo;
            }
        }
        if (this.b != null) {
            for (SubscriberInfoIndex subscriberInfo : this.b) {
                superSubscriberInfo = subscriberInfo.getSubscriberInfo(aVar.f);
                if (superSubscriberInfo != null) {
                    return superSubscriberInfo;
                }
            }
        }
        return null;
    }

    private List<SubscriberMethod> c(Class<?> cls) {
        a b = b();
        b.a(cls);
        while (b.f != null) {
            c(b);
            b.b();
        }
        return a(b);
    }

    private void c(a aVar) {
        Method[] declaredMethods;
        try {
            declaredMethods = aVar.f.getDeclaredMethods();
        } catch (Throwable th) {
            Method[] methods = aVar.f.getMethods();
            aVar.g = true;
            declaredMethods = methods;
        }
        for (Method method : r6) {
            int modifiers = method.getModifiers();
            if ((modifiers & 1) != 0 && (modifiers & 5192) == 0) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {
                    Subscribe subscribe = (Subscribe) method.getAnnotation(Subscribe.class);
                    if (subscribe != null) {
                        Class cls = parameterTypes[0];
                        if (aVar.a(method, cls)) {
                            aVar.a.add(new SubscriberMethod(method, cls, subscribe.threadMode(), subscribe.priority(), subscribe.sticky()));
                        }
                    }
                } else if (this.c && method.isAnnotationPresent(Subscribe.class)) {
                    throw new EventBusException("@Subscribe method " + (method.getDeclaringClass().getName() + "." + method.getName()) + "must have exactly 1 parameter but has " + parameterTypes.length);
                }
            } else if (this.c && method.isAnnotationPresent(Subscribe.class)) {
                throw new EventBusException((method.getDeclaringClass().getName() + "." + method.getName()) + " is a illegal @Subscribe method: must be public, non-static, and non-abstract");
            }
        }
    }

    static void a() {
        a.clear();
    }
}
