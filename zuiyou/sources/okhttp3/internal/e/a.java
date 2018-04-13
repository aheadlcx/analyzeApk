package okhttp3.internal.e;

import android.util.Log;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import okhttp3.internal.c;

class a extends e {
    private final Class<?> a;
    private final d<Socket> b;
    private final d<Socket> c;
    private final d<Socket> d;
    private final d<Socket> e;
    private final b f = b.a();

    static final class a extends okhttp3.internal.f.b {
        private final Object a;
        private final Method b;

        a(Object obj, Method method) {
            this.a = obj;
            this.b = method;
        }

        public List<Certificate> a(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
            try {
                X509Certificate[] x509CertificateArr = (X509Certificate[]) list.toArray(new X509Certificate[list.size()]);
                return (List) this.b.invoke(this.a, new Object[]{x509CertificateArr, "RSA", str});
            } catch (Throwable e) {
                SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e.getMessage());
                sSLPeerUnverifiedException.initCause(e);
                throw sSLPeerUnverifiedException;
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public boolean equals(Object obj) {
            return obj instanceof a;
        }

        public int hashCode() {
            return 0;
        }
    }

    static final class b {
        private final Method a;
        private final Method b;
        private final Method c;

        b(Method method, Method method2, Method method3) {
            this.a = method;
            this.b = method2;
            this.c = method3;
        }

        Object a(String str) {
            if (this.a != null) {
                try {
                    Object invoke = this.a.invoke(null, new Object[0]);
                    this.b.invoke(invoke, new Object[]{str});
                    return invoke;
                } catch (Exception e) {
                }
            }
            return null;
        }

        boolean a(Object obj) {
            if (obj == null) {
                return false;
            }
            try {
                this.c.invoke(obj, new Object[0]);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        static b a() {
            Method method;
            Method method2;
            Method method3 = null;
            try {
                Class cls = Class.forName("dalvik.system.CloseGuard");
                method = cls.getMethod("get", new Class[0]);
                method2 = cls.getMethod("open", new Class[]{String.class});
                method3 = cls.getMethod("warnIfOpen", new Class[0]);
            } catch (Exception e) {
                method2 = method3;
                method = method3;
            }
            return new b(method, method2, method3);
        }
    }

    a(Class<?> cls, d<Socket> dVar, d<Socket> dVar2, d<Socket> dVar3, d<Socket> dVar4) {
        this.a = cls;
        this.b = dVar;
        this.c = dVar2;
        this.d = dVar3;
        this.e = dVar4;
    }

    public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        try {
            socket.connect(inetSocketAddress, i);
        } catch (AssertionError e) {
            if (c.a(e)) {
                throw new IOException(e);
            }
            throw e;
        } catch (Throwable e2) {
            IOException iOException = new IOException("Exception in connect");
            iOException.initCause(e2);
            throw iOException;
        }
    }

    public void a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        if (str != null) {
            this.b.b(sSLSocket, Boolean.valueOf(true));
            this.c.b(sSLSocket, str);
        }
        if (this.e != null && this.e.a((Object) sSLSocket)) {
            this.e.d(sSLSocket, e.b((List) list));
        }
    }

    public String a(SSLSocket sSLSocket) {
        if (this.d == null || !this.d.a((Object) sSLSocket)) {
            return null;
        }
        byte[] bArr = (byte[]) this.d.d(sSLSocket, new Object[0]);
        return bArr != null ? new String(bArr, c.e) : null;
    }

    public void a(int i, String str, Throwable th) {
        int i2 = i == 5 ? 5 : 3;
        if (th != null) {
            str = str + '\n' + Log.getStackTraceString(th);
        }
        int i3 = 0;
        int length = str.length();
        while (i3 < length) {
            int min;
            int indexOf = str.indexOf(10, i3);
            if (indexOf == -1) {
                indexOf = length;
            }
            while (true) {
                min = Math.min(indexOf, i3 + 4000);
                Log.println(i2, "OkHttp", str.substring(i3, min));
                if (min >= indexOf) {
                    break;
                }
                i3 = min;
            }
            i3 = min + 1;
        }
    }

    public Object a(String str) {
        return this.f.a(str);
    }

    public void a(String str, Object obj) {
        if (!this.f.a(obj)) {
            a(5, str, null);
        }
    }

    public boolean b(String str) {
        try {
            Class cls = Class.forName("android.security.NetworkSecurityPolicy");
            Object invoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", new Class[]{String.class}).invoke(invoke, new Object[]{str})).booleanValue();
        } catch (ClassNotFoundException e) {
            return super.b(str);
        } catch (NoSuchMethodException e2) {
            return super.b(str);
        } catch (IllegalAccessException e3) {
            throw new AssertionError();
        } catch (IllegalArgumentException e4) {
            throw new AssertionError();
        } catch (InvocationTargetException e5) {
            throw new AssertionError();
        }
    }

    public okhttp3.internal.f.b a(X509TrustManager x509TrustManager) {
        try {
            Class cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new a(cls.getConstructor(new Class[]{X509TrustManager.class}).newInstance(new Object[]{x509TrustManager}), cls.getMethod("checkServerTrusted", new Class[]{X509Certificate[].class, String.class, String.class}));
        } catch (Exception e) {
            return super.a(x509TrustManager);
        }
    }

    public static e a() {
        d dVar;
        Class cls;
        try {
            cls = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
            try {
                d dVar2;
                d dVar3;
                d dVar4 = new d(null, "setUseSessionTickets", Boolean.TYPE);
                d dVar5 = new d(null, "setHostname", String.class);
                try {
                    Class.forName("android.net.Network");
                    dVar = new d(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
                    try {
                        dVar2 = new d(null, "setAlpnProtocols", byte[].class);
                        dVar3 = dVar;
                    } catch (ClassNotFoundException e) {
                        dVar2 = null;
                        dVar3 = dVar;
                        return new a(cls, dVar4, dVar5, dVar3, dVar2);
                    }
                } catch (ClassNotFoundException e2) {
                    dVar = null;
                    dVar2 = null;
                    dVar3 = dVar;
                    return new a(cls, dVar4, dVar5, dVar3, dVar2);
                }
                return new a(cls, dVar4, dVar5, dVar3, dVar2);
            } catch (ClassNotFoundException e3) {
                return null;
            }
        } catch (ClassNotFoundException e4) {
            cls = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
        }
    }
}
