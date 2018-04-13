package org.mozilla.javascript;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.Map;

public class Kit {
    private static Method Throwable_initCause;

    private static final class ComplexKey {
        private int hash;
        private Object key1;
        private Object key2;

        ComplexKey(Object obj, Object obj2) {
            this.key1 = obj;
            this.key2 = obj2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ComplexKey)) {
                return false;
            }
            ComplexKey complexKey = (ComplexKey) obj;
            if (this.key1.equals(complexKey.key1) && this.key2.equals(complexKey.key2)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            if (this.hash == 0) {
                this.hash = this.key1.hashCode() ^ this.key2.hashCode();
            }
            return this.hash;
        }
    }

    static {
        Throwable_initCause = null;
        try {
            Throwable_initCause = classOrNull("java.lang.Throwable").getMethod("initCause", new Class[]{classOrNull("java.lang.Throwable")});
        } catch (Exception e) {
        }
    }

    public static Class<?> classOrNull(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            return null;
        } catch (SecurityException e2) {
            return null;
        } catch (LinkageError e3) {
            return null;
        } catch (IllegalArgumentException e4) {
            return null;
        }
    }

    public static Class<?> classOrNull(ClassLoader classLoader, String str) {
        try {
            return classLoader.loadClass(str);
        } catch (ClassNotFoundException e) {
            return null;
        } catch (SecurityException e2) {
            return null;
        } catch (LinkageError e3) {
            return null;
        } catch (IllegalArgumentException e4) {
            return null;
        }
    }

    static Object newInstanceOrNull(Class<?> cls) {
        try {
            return cls.newInstance();
        } catch (SecurityException e) {
            return null;
        } catch (LinkageError e2) {
            return null;
        } catch (InstantiationException e3) {
            return null;
        } catch (IllegalAccessException e4) {
            return null;
        }
    }

    static boolean testIfCanLoadRhinoClasses(ClassLoader classLoader) {
        Class cls = ScriptRuntime.ContextFactoryClass;
        if (classOrNull(classLoader, cls.getName()) != cls) {
            return false;
        }
        return true;
    }

    public static RuntimeException initCause(RuntimeException runtimeException, Throwable th) {
        if (Throwable_initCause != null) {
            try {
                Throwable_initCause.invoke(runtimeException, new Object[]{th});
            } catch (Exception e) {
            }
        }
        return runtimeException;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int xDigitToInt(int r2, int r3) {
        /*
        r0 = 57;
        if (r2 > r0) goto L_0x000c;
    L_0x0004:
        r0 = r2 + -48;
        if (r0 < 0) goto L_0x0022;
    L_0x0008:
        r1 = r3 << 4;
        r0 = r0 | r1;
    L_0x000b:
        return r0;
    L_0x000c:
        r0 = 70;
        if (r2 > r0) goto L_0x0017;
    L_0x0010:
        r0 = 65;
        if (r0 > r2) goto L_0x0022;
    L_0x0014:
        r0 = r2 + -55;
        goto L_0x0008;
    L_0x0017:
        r0 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r2 > r0) goto L_0x0022;
    L_0x001b:
        r0 = 97;
        if (r0 > r2) goto L_0x0022;
    L_0x001f:
        r0 = r2 + -87;
        goto L_0x0008;
    L_0x0022:
        r0 = -1;
        goto L_0x000b;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Kit.xDigitToInt(int, int):int");
    }

    public static Object addListener(Object obj, Object obj2) {
        if (obj2 == null) {
            throw new IllegalArgumentException();
        } else if (obj2 instanceof Object[]) {
            throw new IllegalArgumentException();
        } else if (obj == null) {
            return obj2;
        } else {
            if (obj instanceof Object[]) {
                Object[] objArr = (Object[]) obj;
                int length = objArr.length;
                if (length < 2) {
                    throw new IllegalArgumentException();
                }
                Object obj3 = new Object[(length + 1)];
                System.arraycopy(objArr, 0, obj3, 0, length);
                obj3[length] = obj2;
                return obj3;
            }
            return new Object[]{obj, obj2};
        }
    }

    public static Object removeListener(Object obj, Object obj2) {
        if (obj2 == null) {
            throw new IllegalArgumentException();
        } else if (obj2 instanceof Object[]) {
            throw new IllegalArgumentException();
        } else if (obj == obj2) {
            return null;
        } else {
            if (!(obj instanceof Object[])) {
                return obj;
            }
            Object[] objArr = (Object[]) obj;
            int length = objArr.length;
            if (length < 2) {
                throw new IllegalArgumentException();
            } else if (length != 2) {
                int i = length;
                do {
                    i--;
                    if (objArr[i] == obj2) {
                        obj = new Object[(length - 1)];
                        System.arraycopy(objArr, 0, obj, 0, i);
                        System.arraycopy(objArr, i + 1, obj, i, length - (i + 1));
                        return obj;
                    }
                } while (i != 0);
                return obj;
            } else if (objArr[1] == obj2) {
                return objArr[0];
            } else {
                if (objArr[0] == obj2) {
                    return objArr[1];
                }
                return obj;
            }
        }
    }

    public static Object getListener(Object obj, int i) {
        Object[] objArr;
        if (i == 0) {
            if (obj == null) {
                return null;
            }
            if (!(obj instanceof Object[])) {
                return obj;
            }
            objArr = (Object[]) obj;
            if (objArr.length >= 2) {
                return objArr[0];
            }
            throw new IllegalArgumentException();
        } else if (i != 1) {
            objArr = (Object[]) obj;
            int length = objArr.length;
            if (length < 2) {
                throw new IllegalArgumentException();
            } else if (i == length) {
                return null;
            } else {
                return objArr[i];
            }
        } else if (obj instanceof Object[]) {
            return ((Object[]) obj)[1];
        } else {
            if (obj != null) {
                return null;
            }
            throw new IllegalArgumentException();
        }
    }

    static Object initHash(Map<Object, Object> map, Object obj, Object obj2) {
        synchronized (map) {
            Object obj3 = map.get(obj);
            if (obj3 == null) {
                map.put(obj, obj2);
            } else {
                obj2 = obj3;
            }
        }
        return obj2;
    }

    public static Object makeHashKeyFromPair(Object obj, Object obj2) {
        if (obj == null) {
            throw new IllegalArgumentException();
        } else if (obj2 != null) {
            return new ComplexKey(obj, obj2);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static String readReader(Reader reader) throws IOException {
        Object obj = new char[512];
        int i = 0;
        while (true) {
            int read = reader.read(obj, i, obj.length - i);
            if (read < 0) {
                return new String(obj, 0, i);
            }
            i += read;
            if (i == obj.length) {
                Object obj2 = new char[(obj.length * 2)];
                System.arraycopy(obj, 0, obj2, 0, i);
                obj = obj2;
            }
        }
    }

    public static byte[] readStream(InputStream inputStream, int i) throws IOException {
        if (i <= 0) {
            throw new IllegalArgumentException("Bad initialBufferCapacity: " + i);
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (true) {
            int read = inputStream.read(bArr, i2, bArr.length - i2);
            if (read < 0) {
                break;
            }
            i2 += read;
            if (i2 == bArr.length) {
                Object obj = new byte[(bArr.length * 2)];
                System.arraycopy(bArr, 0, obj, 0, i2);
                bArr = obj;
            }
        }
        if (i2 == bArr.length) {
            return bArr;
        }
        obj = new byte[i2];
        System.arraycopy(bArr, 0, obj, 0, i2);
        return obj;
    }

    public static RuntimeException codeBug() throws RuntimeException {
        RuntimeException illegalStateException = new IllegalStateException("FAILED ASSERTION");
        illegalStateException.printStackTrace(System.err);
        throw illegalStateException;
    }

    public static RuntimeException codeBug(String str) throws RuntimeException {
        RuntimeException illegalStateException = new IllegalStateException("FAILED ASSERTION: " + str);
        illegalStateException.printStackTrace(System.err);
        throw illegalStateException;
    }
}
