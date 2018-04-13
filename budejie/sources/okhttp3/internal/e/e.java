package okhttp3.internal.e;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import okhttp3.internal.f.a;
import okhttp3.internal.f.b;
import okhttp3.w;
import okio.c;

public class e {
    private static final e a = a();
    private static final Logger b = Logger.getLogger(w.class.getName());

    public static e b() {
        return a;
    }

    public String c() {
        return "OkHttp";
    }

    public void a(SSLSocket sSLSocket, String str, List<Protocol> list) {
    }

    public void b(SSLSocket sSLSocket) {
    }

    public String a(SSLSocket sSLSocket) {
        return null;
    }

    public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    public void a(int i, String str, Throwable th) {
        b.log(i == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    public boolean b(String str) {
        return true;
    }

    public Object a(String str) {
        if (b.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public void a(String str, Object obj) {
        if (obj == null) {
            str = str + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        a(5, str, (Throwable) obj);
    }

    public static List<String> a(List<Protocol> list) {
        List<String> arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = (Protocol) list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                arrayList.add(protocol.toString());
            }
        }
        return arrayList;
    }

    public b a(X509TrustManager x509TrustManager) {
        return new a(okhttp3.internal.f.e.a(x509TrustManager));
    }

    private static e a() {
        e a = a.a();
        if (a != null) {
            return a;
        }
        a = b.a();
        if (a != null) {
            return a;
        }
        a = c.a();
        return a == null ? new e() : a;
    }

    static byte[] b(List<Protocol> list) {
        c cVar = new c();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = (Protocol) list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                cVar.b(protocol.toString().length());
                cVar.a(protocol.toString());
            }
        }
        return cVar.s();
    }
}
