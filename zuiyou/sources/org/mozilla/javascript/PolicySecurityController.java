package org.mozilla.javascript;

import android.support.v4.app.NotificationCompat;
import java.lang.ref.SoftReference;
import java.lang.reflect.UndeclaredThrowableException;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.SecureClassLoader;
import java.util.Map;
import java.util.WeakHashMap;
import org.mozilla.classfile.ClassFileWriter;

public class PolicySecurityController extends SecurityController {
    private static final Map<CodeSource, Map<ClassLoader, SoftReference<SecureCaller>>> callers = new WeakHashMap();
    private static final byte[] secureCallerImplBytecode = loadBytecode();

    private static class Loader extends SecureClassLoader implements GeneratedClassLoader {
        private final CodeSource codeSource;

        Loader(ClassLoader classLoader, CodeSource codeSource) {
            super(classLoader);
            this.codeSource = codeSource;
        }

        public Class<?> defineClass(String str, byte[] bArr) {
            return defineClass(str, bArr, 0, bArr.length, this.codeSource);
        }

        public void linkClass(Class<?> cls) {
            resolveClass(cls);
        }
    }

    public static abstract class SecureCaller {
        public abstract Object call(Callable callable, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr);
    }

    public Class<?> getStaticSecurityDomainClassInternal() {
        return CodeSource.class;
    }

    public GeneratedClassLoader createClassLoader(final ClassLoader classLoader, final Object obj) {
        return (Loader) AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                return new Loader(classLoader, (CodeSource) obj);
            }
        });
    }

    public Object getDynamicSecurityDomain(Object obj) {
        return obj;
    }

    public Object callWithDomain(Object obj, final Context context, Callable callable, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Map map;
        SecureCaller secureCaller;
        final ClassLoader classLoader = (ClassLoader) AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                return context.getApplicationClassLoader();
            }
        });
        final CodeSource codeSource = (CodeSource) obj;
        synchronized (callers) {
            Map map2 = (Map) callers.get(codeSource);
            if (map2 == null) {
                WeakHashMap weakHashMap = new WeakHashMap();
                callers.put(codeSource, weakHashMap);
                map = weakHashMap;
            } else {
                map = map2;
            }
        }
        synchronized (map) {
            SecureCaller secureCaller2;
            SoftReference softReference = (SoftReference) map.get(classLoader);
            if (softReference != null) {
                secureCaller2 = (SecureCaller) softReference.get();
            } else {
                secureCaller2 = null;
            }
            if (secureCaller2 == null) {
                try {
                    secureCaller2 = (SecureCaller) AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
                        public Object run() throws Exception {
                            return new Loader(classLoader, codeSource).defineClass(SecureCaller.class.getName() + "Impl", PolicySecurityController.secureCallerImplBytecode).newInstance();
                        }
                    });
                    map.put(classLoader, new SoftReference(secureCaller2));
                    secureCaller = secureCaller2;
                } catch (PrivilegedActionException e) {
                    throw new UndeclaredThrowableException(e.getCause());
                }
            }
            secureCaller = secureCaller2;
        }
        return secureCaller.call(callable, context, scriptable, scriptable2, objArr);
    }

    private static byte[] loadBytecode() {
        int i = 1;
        String name = SecureCaller.class.getName();
        ClassFileWriter classFileWriter = new ClassFileWriter(name + "Impl", name, "<generated>");
        classFileWriter.startMethod("<init>", "()V", (short) 1);
        classFileWriter.addALoad(0);
        classFileWriter.addInvoke(183, name, "<init>", "()V");
        classFileWriter.add(177);
        classFileWriter.stopMethod((short) 1);
        name = "Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;";
        classFileWriter.startMethod(NotificationCompat.CATEGORY_CALL, "(Lorg/mozilla/javascript/Callable;" + name, (short) 17);
        while (i < 6) {
            classFileWriter.addALoad(i);
            i++;
        }
        classFileWriter.addInvoke(185, "org/mozilla/javascript/Callable", NotificationCompat.CATEGORY_CALL, "(" + name);
        classFileWriter.add(176);
        classFileWriter.stopMethod((short) 6);
        return classFileWriter.toByteArray();
    }
}
