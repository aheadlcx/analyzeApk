package org.mozilla.javascript;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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

public abstract class SecureCaller {
    private static final Map<CodeSource, Map<ClassLoader, SoftReference<SecureCaller>>> callers = new WeakHashMap();
    private static final byte[] secureCallerImplBytecode = loadBytecode();

    private static class SecureClassLoaderImpl extends SecureClassLoader {
        SecureClassLoaderImpl(ClassLoader classLoader) {
            super(classLoader);
        }

        Class<?> defineAndLinkClass(String str, byte[] bArr, CodeSource codeSource) {
            Class<?> defineClass = defineClass(str, bArr, 0, bArr.length, codeSource);
            resolveClass(defineClass);
            return defineClass;
        }
    }

    public abstract Object call(Callable callable, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr);

    static Object callSecurely(final CodeSource codeSource, Callable callable, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Map map;
        SecureCaller secureCaller;
        final Thread currentThread = Thread.currentThread();
        final ClassLoader classLoader = (ClassLoader) AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                return currentThread.getContextClassLoader();
            }
        });
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
                            ClassLoader classLoader;
                            Class cls = getClass();
                            if (classLoader.loadClass(cls.getName()) != cls) {
                                classLoader = cls.getClassLoader();
                            } else {
                                classLoader = classLoader;
                            }
                            return new SecureClassLoaderImpl(classLoader).defineAndLinkClass(SecureCaller.class.getName() + "Impl", SecureCaller.secureCallerImplBytecode, codeSource).newInstance();
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
        return (byte[]) AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                return SecureCaller.loadBytecodePrivileged();
            }
        });
    }

    private static byte[] loadBytecodePrivileged() {
        InputStream openStream;
        try {
            openStream = SecureCaller.class.getResource("SecureCallerImpl.clazz").openStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = openStream.read();
                if (read == -1) {
                    byte[] toByteArray = byteArrayOutputStream.toByteArray();
                    openStream.close();
                    return toByteArray;
                }
                byteArrayOutputStream.write(read);
            }
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        } catch (Throwable th) {
            openStream.close();
        }
    }
}
