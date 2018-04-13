package okhttp3;

import java.net.Socket;
import javax.net.ssl.SSLSocket;
import okhttp3.internal.a;
import okhttp3.internal.connection.c;
import okhttp3.internal.connection.d;
import okhttp3.internal.connection.f;

class w$1 extends a {
    w$1() {
    }

    public void a(s$a s_a, String str) {
        s_a.a(str);
    }

    public void a(s$a s_a, String str, String str2) {
        s_a.b(str, str2);
    }

    public boolean a(j jVar, c cVar) {
        return jVar.b(cVar);
    }

    public c a(j jVar, a aVar, f fVar, ac acVar) {
        return jVar.a(aVar, fVar, acVar);
    }

    public boolean a(a aVar, a aVar2) {
        return aVar.a(aVar2);
    }

    public Socket a(j jVar, a aVar, f fVar) {
        return jVar.a(aVar, fVar);
    }

    public void b(j jVar, c cVar) {
        jVar.a(cVar);
    }

    public d a(j jVar) {
        return jVar.a;
    }

    public int a(aa.a aVar) {
        return aVar.c;
    }

    public void a(k kVar, SSLSocket sSLSocket, boolean z) {
        kVar.a(sSLSocket, z);
    }
}
