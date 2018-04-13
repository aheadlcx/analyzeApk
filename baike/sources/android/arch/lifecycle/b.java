package android.arch.lifecycle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({Scope.LIBRARY_GROUP})
class b {
    private static Constructor<? extends GenericLifecycleObserver> a;
    private static Map<Class, Constructor<? extends GenericLifecycleObserver>> b = new HashMap();

    static {
        try {
            a = ReflectiveGenericLifecycleObserver.class.getDeclaredConstructor(new Class[]{Object.class});
        } catch (NoSuchMethodException e) {
        }
    }

    @NonNull
    static GenericLifecycleObserver a(Object obj) {
        if (obj instanceof GenericLifecycleObserver) {
            return (GenericLifecycleObserver) obj;
        }
        try {
            Class cls = obj.getClass();
            Constructor constructor = (Constructor) b.get(cls);
            if (constructor != null) {
                return (GenericLifecycleObserver) constructor.newInstance(new Object[]{obj});
            }
            constructor = a(cls);
            if (constructor == null) {
                constructor = a;
            } else if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            b.put(cls, constructor);
            return (GenericLifecycleObserver) constructor.newInstance(new Object[]{obj});
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        } catch (Throwable e22) {
            throw new RuntimeException(e22);
        }
    }

    @Nullable
    private static Constructor<? extends GenericLifecycleObserver> a(Class<?> cls) {
        Package packageR = cls.getPackage();
        String name = packageR != null ? packageR.getName() : "";
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            return null;
        }
        if (!name.isEmpty()) {
            canonicalName = canonicalName.substring(name.length() + 1);
        }
        canonicalName = a(canonicalName);
        try {
            return Class.forName(name.isEmpty() ? canonicalName : name + "." + canonicalName).getDeclaredConstructor(new Class[]{cls});
        } catch (ClassNotFoundException e) {
            Class superclass = cls.getSuperclass();
            if (superclass != null) {
                return a(superclass);
            }
            return null;
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    static String a(String str) {
        return str.replace(".", "_") + "_LifecycleAdapter";
    }
}
