package rx.e;

import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

public class f {
    static final b a = new f$1();
    private static final f b = new f();
    private final AtomicReference<b> c = new AtomicReference();
    private final AtomicReference<d> d = new AtomicReference();
    private final AtomicReference<h> e = new AtomicReference();
    private final AtomicReference<a> f = new AtomicReference();
    private final AtomicReference<g> g = new AtomicReference();

    @Deprecated
    public static f a() {
        return b;
    }

    f() {
    }

    public b b() {
        if (this.c.get() == null) {
            Object a = a(b.class, f());
            if (a == null) {
                this.c.compareAndSet(null, a);
            } else {
                this.c.compareAndSet(null, (b) a);
            }
        }
        return (b) this.c.get();
    }

    public d c() {
        if (this.d.get() == null) {
            Object a = a(d.class, f());
            if (a == null) {
                this.d.compareAndSet(null, e.a());
            } else {
                this.d.compareAndSet(null, (d) a);
            }
        }
        return (d) this.d.get();
    }

    public h d() {
        if (this.e.get() == null) {
            Object a = a(h.class, f());
            if (a == null) {
                this.e.compareAndSet(null, i.a());
            } else {
                this.e.compareAndSet(null, (h) a);
            }
        }
        return (h) this.e.get();
    }

    public a e() {
        if (this.f.get() == null) {
            Object a = a(a.class, f());
            if (a == null) {
                this.f.compareAndSet(null, new f$2(this));
            } else {
                this.f.compareAndSet(null, (a) a);
            }
        }
        return (a) this.f.get();
    }

    static Properties f() {
        try {
            return System.getProperties();
        } catch (SecurityException e) {
            return new Properties();
        }
    }

    static Object a(Class<?> cls, Properties properties) {
        SecurityException securityException;
        Properties properties2 = (Properties) properties.clone();
        String simpleName = cls.getSimpleName();
        String str = "rxjava.plugin.";
        String property = properties2.getProperty(str + simpleName + ".implementation");
        if (property == null) {
            String str2 = ".class";
            String str3 = ".impl";
            try {
                String property2;
                for (Entry entry : properties2.entrySet()) {
                    String obj = entry.getKey().toString();
                    if (obj.startsWith(str) && obj.endsWith(str2) && simpleName.equals(entry.getValue().toString())) {
                        String str4 = str + obj.substring(0, obj.length() - str2.length()).substring(str.length()) + str3;
                        property2 = properties2.getProperty(str4);
                        if (property2 == null) {
                            try {
                                throw new IllegalStateException("Implementing class declaration for " + simpleName + " missing: " + str4);
                            } catch (SecurityException e) {
                                property = property2;
                                securityException = e;
                                securityException.printStackTrace();
                                if (property != null) {
                                    return null;
                                }
                                try {
                                    return Class.forName(property).asSubclass(cls).newInstance();
                                } catch (Throwable e2) {
                                    throw new IllegalStateException(simpleName + " implementation is not an instance of " + simpleName + ": " + property, e2);
                                } catch (Throwable e22) {
                                    throw new IllegalStateException(simpleName + " implementation class not found: " + property, e22);
                                } catch (Throwable e222) {
                                    throw new IllegalStateException(simpleName + " implementation not able to be instantiated: " + property, e222);
                                } catch (Throwable e2222) {
                                    throw new IllegalStateException(simpleName + " implementation not able to be accessed: " + property, e2222);
                                }
                            }
                        }
                        property = property2;
                    }
                }
                property2 = property;
                property = property2;
            } catch (SecurityException e3) {
                securityException = e3;
                securityException.printStackTrace();
                if (property != null) {
                    return Class.forName(property).asSubclass(cls).newInstance();
                }
                return null;
            }
        }
        if (property != null) {
            return Class.forName(property).asSubclass(cls).newInstance();
        }
        return null;
    }

    public g g() {
        if (this.g.get() == null) {
            Object a = a(g.class, f());
            if (a == null) {
                this.g.compareAndSet(null, g.g());
            } else {
                this.g.compareAndSet(null, (g) a);
            }
        }
        return (g) this.g.get();
    }
}
