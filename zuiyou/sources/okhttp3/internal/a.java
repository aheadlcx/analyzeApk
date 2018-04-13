package okhttp3.internal;

import java.net.Socket;
import javax.net.ssl.SSLSocket;
import okhttp3.ac;
import okhttp3.internal.connection.c;
import okhttp3.internal.connection.d;
import okhttp3.internal.connection.f;
import okhttp3.j;
import okhttp3.k;
import okhttp3.s$a;

public abstract class a {
    public static a a;

    public abstract int a(okhttp3.aa.a aVar);

    public abstract Socket a(j jVar, okhttp3.a aVar, f fVar);

    public abstract c a(j jVar, okhttp3.a aVar, f fVar, ac acVar);

    public abstract d a(j jVar);

    public abstract void a(k kVar, SSLSocket sSLSocket, boolean z);

    public abstract void a(s$a s_a, String str);

    public abstract void a(s$a s_a, String str, String str2);

    public abstract boolean a(okhttp3.a aVar, okhttp3.a aVar2);

    public abstract boolean a(j jVar, c cVar);

    public abstract void b(j jVar, c cVar);
}
