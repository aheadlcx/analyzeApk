package org.mozilla.javascript.tools.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.charset.Charset;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Scriptable;

public abstract class f {
    private static final Class[] a = new Class[0];
    private static final Class[] b = new Class[]{Boolean.TYPE};
    private static final Class[] c = new Class[]{String.class};
    private static final Class[] d = new Class[]{CharSequence.class};

    private static class a extends InputStream {
        private static final byte[] a = new byte[0];
        private final f b;
        private final Charset c;
        private byte[] d = a;
        private int e = -1;
        private boolean f = false;

        public a(f fVar, Charset charset) {
            this.b = fVar;
            this.c = charset;
        }

        public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = 0;
            synchronized (this) {
                if (bArr == null) {
                    throw new NullPointerException();
                }
                if (i >= 0 && i2 >= 0) {
                    if (i2 <= bArr.length - i) {
                        if (i2 != 0) {
                            if (a()) {
                                int min = Math.min(i2, this.d.length - this.e);
                                while (i3 < min) {
                                    bArr[i + i3] = this.d[this.e + i3];
                                    i3++;
                                }
                                if (min < i2) {
                                    i3 = min + 1;
                                    bArr[min + i] = (byte) 10;
                                } else {
                                    i3 = min;
                                }
                                this.e += i3;
                            } else {
                                i3 = -1;
                            }
                        }
                    }
                }
                throw new IndexOutOfBoundsException();
            }
            return i3;
        }

        public synchronized int read() throws IOException {
            int i;
            if (!a()) {
                i = -1;
            } else if (this.e == this.d.length) {
                this.e++;
                i = 10;
            } else {
                byte[] bArr = this.d;
                int i2 = this.e;
                this.e = i2 + 1;
                i = bArr[i2];
            }
            return i;
        }

        private boolean a() throws IOException {
            if (this.f) {
                return false;
            }
            if (this.e < 0 || this.e > this.d.length) {
                if (b() == -1) {
                    this.f = true;
                    return false;
                }
                this.e = 0;
            }
            return true;
        }

        private int b() throws IOException {
            String a = this.b.a(null);
            if (a != null) {
                this.d = a.getBytes(this.c);
                return this.d.length;
            }
            this.d = a;
            return -1;
        }
    }

    private static class b extends f {
        private final Object a;
        private final InputStream b;

        b(Object obj, Charset charset) {
            this.a = obj;
            this.b = new a(this, charset);
        }

        public InputStream a() {
            return this.b;
        }

        public String a(String str) throws IOException {
            return (String) f.b(this.a, "readLine", f.c, str);
        }

        public void b() throws IOException {
            f.b(this.a, "flushConsole", f.a, new Object[0]);
        }

        public void c() throws IOException {
            f.b(this.a, "printNewline", f.a, new Object[0]);
        }

        public void b(String str) throws IOException {
            f.b(this.a, "printString", f.c, str);
            f.b(this.a, "printNewline", f.a, new Object[0]);
        }
    }

    private static class c extends f {
        private final Object a;
        private final InputStream b;

        c(Object obj, Charset charset) {
            this.a = obj;
            this.b = new a(this, charset);
        }

        public InputStream a() {
            return this.b;
        }

        public String a(String str) throws IOException {
            return (String) f.b(this.a, "readLine", f.c, str);
        }

        public void b() throws IOException {
            f.b(this.a, "flush", f.a, new Object[0]);
        }

        public void c() throws IOException {
            f.b(this.a, "println", f.a, new Object[0]);
        }

        public void b(String str) throws IOException {
            f.b(this.a, "println", f.d, str);
        }
    }

    private static class d extends f {
        private final InputStream a;
        private final PrintWriter b;
        private final BufferedReader c;

        d(InputStream inputStream, PrintStream printStream, Charset charset) {
            this.a = inputStream;
            this.b = new PrintWriter(printStream);
            this.c = new BufferedReader(new InputStreamReader(inputStream, charset));
        }

        public InputStream a() {
            return this.a;
        }

        public String a(String str) throws IOException {
            if (str != null) {
                this.b.write(str);
                this.b.flush();
            }
            return this.c.readLine();
        }

        public void b() throws IOException {
            this.b.flush();
        }

        public void c() throws IOException {
            this.b.println();
        }

        public void b(String str) throws IOException {
            this.b.println(str);
        }
    }

    public abstract InputStream a();

    public abstract String a(String str) throws IOException;

    public abstract void b() throws IOException;

    public abstract void b(String str) throws IOException;

    public abstract void c() throws IOException;

    protected f() {
    }

    private static Object b(Object obj, String str, Class[] clsArr, Object... objArr) {
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(str, clsArr);
            if (declaredMethod != null) {
                return declaredMethod.invoke(obj, objArr);
            }
        } catch (NoSuchMethodException e) {
        } catch (IllegalArgumentException e2) {
        } catch (IllegalAccessException e3) {
        } catch (InvocationTargetException e4) {
        }
        return null;
    }

    public static f a(InputStream inputStream, PrintStream printStream, Charset charset) {
        return new d(inputStream, printStream, charset);
    }

    public static f a(Scriptable scriptable, Charset charset) {
        ClassLoader classLoader = f.class.getClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        if (classLoader == null) {
            return null;
        }
        try {
            Class classOrNull = Kit.classOrNull(classLoader, "jline.console.ConsoleReader");
            if (classOrNull != null) {
                return b(classLoader, classOrNull, scriptable, charset);
            }
            classOrNull = Kit.classOrNull(classLoader, "jline.ConsoleReader");
            if (classOrNull != null) {
                return a(classLoader, classOrNull, scriptable, charset);
            }
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        } catch (IllegalAccessException e2) {
            return null;
        } catch (InstantiationException e3) {
            return null;
        } catch (InvocationTargetException e4) {
            return null;
        }
    }

    private static b a(ClassLoader classLoader, Class<?> cls, Scriptable scriptable, Charset charset) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Object newInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
        b(newInstance, "setBellEnabled", b, Boolean.FALSE);
        Class[] clsArr = new Class[]{Kit.classOrNull(classLoader, "jline.Completor")};
        Object newProxyInstance = Proxy.newProxyInstance(classLoader, clsArr, new a(Kit.classOrNull(classLoader, "jline.Completor"), scriptable));
        b(newInstance, "addCompletor", new Class[]{r1}, newProxyInstance);
        return new b(newInstance, charset);
    }

    private static c b(ClassLoader classLoader, Class<?> cls, Scriptable scriptable, Charset charset) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Object newInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
        b(newInstance, "setBellEnabled", b, Boolean.FALSE);
        Class[] clsArr = new Class[]{Kit.classOrNull(classLoader, "jline.console.completer.Completer")};
        Object newProxyInstance = Proxy.newProxyInstance(classLoader, clsArr, new a(Kit.classOrNull(classLoader, "jline.console.completer.Completer"), scriptable));
        b(newInstance, "addCompleter", new Class[]{r1}, newProxyInstance);
        return new c(newInstance, charset);
    }
}
